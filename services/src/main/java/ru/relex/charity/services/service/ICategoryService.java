package ru.relex.charity.services.service;

import ru.relex.charity.services.dto.category.CategoryDto;

import javax.validation.Valid;
import java.util.List;

public interface ICategoryService {
    List<CategoryDto> findCategories(String search, Integer parentId);

    CategoryDto getById(int categoryId);

    CategoryDto create(@Valid CategoryDto categoryDto);

    CategoryDto update(@Valid CategoryDto categoryDto);

    void remove(int categoryId);
}
