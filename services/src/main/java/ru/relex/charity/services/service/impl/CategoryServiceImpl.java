package ru.relex.charity.services.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ru.relex.charity.db.mappers.CategoryMapper;
import ru.relex.charity.services.dto.category.CategoryDto;
import ru.relex.charity.services.mapstruct.CategoryStruct;
import ru.relex.charity.services.service.ICategoryService;

import javax.validation.Valid;
import java.util.List;

@Service
@Validated
public class CategoryServiceImpl implements ICategoryService {
    private final CategoryMapper categoryMapper;
    private final CategoryStruct categoryStruct;

    @Autowired
    public CategoryServiceImpl(CategoryMapper categoryMapper, CategoryStruct categoryStruct) {
        this.categoryMapper = categoryMapper;
        this.categoryStruct = categoryStruct;
    }


    @Override
    public List<CategoryDto> findCategories(String search, Integer parentId) {
        var categories = categoryMapper.getCategories(search, parentId);

        return categoryStruct.toDto(categories);
    }

    @Override
    public CategoryDto getById(int categoryId) {
        var category = categoryMapper.getCategoryById(categoryId);

        return categoryStruct.toDto(category);
    }

    @Override
    public CategoryDto create(@Valid CategoryDto categoryDto) {
        var category = categoryStruct.fromDto(categoryDto);

        categoryMapper.insert(category);

        return categoryStruct.toDto(category);
    }

    @Override
    public CategoryDto update(@Valid CategoryDto categoryDto) {
        var category = categoryStruct.fromDto(categoryDto);

        categoryMapper.update(category);

        return categoryStruct.toDto(category);
    }

    @Override
    public void remove(int categoryId) {
        categoryMapper.delete(categoryId);
    }
}
