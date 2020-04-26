package ru.relex.charity.services.mapstruct;

import org.mapstruct.Mapper;
import ru.relex.charity.db.models.Category;
import ru.relex.charity.services.dto.category.CategoryDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryStruct {
    CategoryDto toDto(Category category);

    Category fromDto(CategoryDto categoryDto);

    List<CategoryDto> toDto(List<Category> categories);

    List<Category> fromDto(List<CategoryDto> categoryDtos);
}
