package ru.relex.charity.services.dto.role;

import ru.relex.common.models.Role;

import javax.validation.constraints.NotBlank;

public class RolesDto {

    @NotBlank(message = "ROLE_EMPTY")
    private Role[] roles;

    public Role[] getRoles() {
        return roles;
    }

    public void setRoles(Role[] roles) {
        this.roles = roles;
    }
}
