package ru.relex.charity.services.dto.role;

import javax.validation.constraints.NotBlank;

public class RoleDto {
    // TODO: 21.03.2020 вынести как полагается
    @NotBlank(message = "ROLE_EMPTY")
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
