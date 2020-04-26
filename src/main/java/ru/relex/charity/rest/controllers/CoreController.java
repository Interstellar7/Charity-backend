package ru.relex.charity.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.relex.charity.rest.dto.ProfileDto;
import ru.relex.charity.services.dto.user.UserDto;
import ru.relex.charity.services.service.IUserService;
import ru.relex.common.models.CurrentUser;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping(
        path = "/api",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class CoreController {
    private final IUserService userService;
    private final CurrentUser currentUser;

    @Autowired
    public CoreController(
            IUserService userService,
            CurrentUser currentUser) {
        this.userService = userService;
        this.currentUser = currentUser;
    }

    @GetMapping(path = "/profile")
    @RolesAllowed("ROLE_USER")
    ProfileDto profile() {
        var userDto = userService.getById(currentUser.getId());

        return new ProfileDto(true, userDto);
    }

    @PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    UserDto register(@RequestBody UserDto userDto) {
        return userService.createWithLogin(userDto);
    }

    @PostMapping(path = "/reset-password")
    void resetPassword() {
        // TODO
    }
}
