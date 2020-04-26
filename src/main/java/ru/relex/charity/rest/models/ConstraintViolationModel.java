package ru.relex.charity.rest.models;

public class ConstraintViolationModel extends ExceptionModel {
    public ConstraintViolationModel(String error) {
        super(error);
    }
}
