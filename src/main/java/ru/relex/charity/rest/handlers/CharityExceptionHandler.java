package ru.relex.charity.rest.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.relex.charity.services.exceptions.BadRequestException;
import ru.relex.charity.rest.models.ConstraintViolationModel;
import ru.relex.charity.rest.models.ExceptionModel;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class CharityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<ConstraintViolationModel>> handleConstraintViolation(ConstraintViolationException exception) {
        var violations = exception.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .map(ConstraintViolationModel::new)
                .collect(Collectors.toList());

        return new ResponseEntity<>(violations, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionModel> handleConstraintViolation(BadRequestException exception) {
        return new ResponseEntity<>(new ExceptionModel(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
