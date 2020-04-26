package ru.relex.charity.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.relex.charity.services.dto.role.RoleDto;
import ru.relex.charity.services.dto.role.RolesDto;
import ru.relex.charity.services.service.IUserRoleService;
import ru.relex.common.models.Role;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping(
        path = "/api/users/{userId}/roles",
        produces = MediaType.APPLICATION_JSON_VALUE
)
@RolesAllowed("ROLE_ADMIN")
public class UserRoleController {
    private final IUserRoleService userRoleService;

    @Autowired
    public UserRoleController(IUserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @GetMapping()
    List<Role> getRoles(@PathVariable("userId") int userId) {
        return userRoleService.findRolesByUserId(userId);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    void replace(@PathVariable("userId") int userId, @RequestBody RolesDto rolesDto) {
        userRoleService.replace(userId, rolesDto.getRoles());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    void assign(@PathVariable("userId") int userId, @RequestBody RoleDto roleDto) {
        var role = Role.valueOf(roleDto.getRole());

        userRoleService.assign(userId, role);
    }

    @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    void resign(@PathVariable("userId") int userId, @RequestBody RoleDto roleDto) {
        var role = Role.valueOf(roleDto.getRole());

        userRoleService.resign(userId, role);
    }
}
