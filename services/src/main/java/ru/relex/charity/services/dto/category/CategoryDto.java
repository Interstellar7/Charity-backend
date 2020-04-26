package ru.relex.charity.services.dto.category;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static ru.relex.charity.services.constraint.ConstraintMessage.Constraint;
import static ru.relex.charity.services.constraint.ConstraintMessage.Field;

public class CategoryDto {
    private Integer id;

    @NotBlank(message = Field.NAME + Constraint.IS_EMPTY)
    @Size(max = 32, message = Field.NAME + Constraint.TOO_LONG)
    private String name;

    @NotNull(message = Field.PARENT_ID + Constraint.IS_NULL)
    private Integer parentId;

    private Integer points;

    private boolean hasChildren;

    public CategoryDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public boolean getHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(boolean hasChildren) { this.hasChildren = hasChildren; }
}
