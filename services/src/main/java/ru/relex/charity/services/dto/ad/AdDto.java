package ru.relex.charity.services.dto.ad;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

import static ru.relex.charity.services.constraint.ConstraintMessage.Constraint;
import static ru.relex.charity.services.constraint.ConstraintMessage.Field;

public class AdDto {
    private Integer id;

    @NotBlank(message = Field.TITLE + Constraint.IS_EMPTY)
    @Size(max = 256, message = Field.TITLE + Constraint.TOO_LONG)
    private String title;

    private String description;

    @NotNull(message = Field.CATEGORY_ID + Constraint.IS_NULL)
    private Integer categoryId;

    @NotNull(message = Field.AD_STATUS_ID + Constraint.IS_NULL)
    private Integer adStatusId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getAdStatusId() {
        return adStatusId;
    }

    public void setAdStatusId(Integer adStatusId) {
        this.adStatusId = adStatusId;
    }
}
