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

public class UserUpdateDto {
    private Integer id;

    @Valid
    @NotNull(message = Field.PERSONAL_INFO + Constraint.IS_NULL)
    private PersonalInfoDto personalInfo;

    public UserUpdateDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PersonalInfoDto getPersonalInfo() {
        return personalInfo;
    }

    public void setPersonalInfo(PersonalInfoDto personalInfo) {
        this.personalInfo = personalInfo;
    }
}
