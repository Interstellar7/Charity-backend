package ru.relex.charity.services.dto.user;

import ru.relex.common.models.Role;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

import static ru.relex.charity.services.constraint.ConstraintMessage.Constraint;
import static ru.relex.charity.services.constraint.ConstraintMessage.Field;

public class UserDto {
    private Integer id;

    @NotBlank(message = Field.USERNAME + Constraint.IS_EMPTY)
    private String username;

    @Size(min = 8, message = Field.PASSWORD + Constraint.TOO_SHORT)
    private String password;

    @Valid
    @NotNull(message = Field.PERSONAL_INFO + Constraint.IS_NULL)
    private PersonalInfoDto personalInfo;

    private List<Role> roles = new ArrayList<Role>();

    public UserDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PersonalInfoDto getPersonalInfo() {
        return personalInfo;
    }

    public void setPersonalInfo(PersonalInfoDto personalInfo) {
        this.personalInfo = personalInfo;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
