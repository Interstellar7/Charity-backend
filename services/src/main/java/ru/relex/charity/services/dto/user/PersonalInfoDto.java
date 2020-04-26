package ru.relex.charity.services.dto.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static ru.relex.charity.services.constraint.ConstraintMessage.Constraint;
import static ru.relex.charity.services.constraint.ConstraintMessage.Field;

public class PersonalInfoDto {

    @NotBlank(message = Field.FIRST_NAME + Constraint.IS_EMPTY)
    @Size(max = 50, message = Field.FIRST_NAME + Constraint.TOO_LONG)
    private String firstName;

    @NotBlank(message = Field.LAST_NAME + Constraint.IS_EMPTY)
    @Size(max = 50, message = Field.LAST_NAME + Constraint.TOO_LONG)
    private String lastName;

    @NotBlank(message = Field.EMAIL + Constraint.IS_EMPTY)
    @Size(max = 64, message = Field.EMAIL + Constraint.TOO_LONG)
    @Email()
    private String email;

    public PersonalInfoDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
