package ru.relex.charity.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.relex.charity.services.exceptions.BadRequestException;
import ru.relex.charity.services.constraint.ConstraintMessage.Constraint;
import ru.relex.charity.services.constraint.ConstraintMessage.Field;
import ru.relex.charity.services.dto.user.UserDto;
import ru.relex.charity.services.dto.user.UserUpdateDto;
import ru.relex.charity.services.service.IUserService;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping(
        path = "/api/users",
        produces = MediaType.APPLICATION_JSON_VALUE
)
@RolesAllowed("ROLE_ADMIN")
public class UserController {
    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping
    List<UserDto> getUsers(@RequestParam(name = "search", required = false) String search) {
        return userService.findUsers(search);
    }

    @GetMapping("/{id}")
    UserDto findById(@PathVariable("id") int id) {
        return userService.getById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    UserDto create(@RequestBody UserDto userDto) {
        return userService.create(userDto);
    }

    @PutMapping("/{id}")
    UserDto update(@PathVariable("id") int id, @RequestBody UserUpdateDto userDto) {
        userDto.setId(id);

        if (userService.existEmailExceptUser(userDto.getPersonalInfo().getEmail(), id)) {
            throw new BadRequestException(Field.EMAIL + Constraint.DUPLICATE);
        }

        return userService.update(userDto);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable("id") int id) {
        userService.remove(id);
    }

}
