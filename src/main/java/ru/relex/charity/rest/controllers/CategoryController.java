package ru.relex.charity.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.relex.charity.services.service.ICategoryService;
import ru.relex.charity.services.dto.category.CategoryDto;

import java.util.List;

@RestController
@RequestMapping(
        path = "/api/categories",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class CategoryController {
    private final ICategoryService categoryService;

    @Autowired
    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    List<CategoryDto> getCategories(@RequestParam(name = "search", required = false) String search,
                                    @RequestParam(name = "parentId", required = false) Integer parentId) {
        return categoryService.findCategories(search, parentId);
    }

    @GetMapping("/{id}")
    CategoryDto findById(@PathVariable("id") int id) {
        return categoryService.getById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    CategoryDto create(@RequestBody CategoryDto categoryDto) {
        categoryService.create(categoryDto);
        return categoryDto;
    }

    @PutMapping("/{id}")
    CategoryDto update(@PathVariable("id") int id, @RequestBody CategoryDto categoryDto) {
        categoryDto.setId(id);
        return categoryService.update(categoryDto);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable("id") int id) {
        categoryService.remove(id);
    }
}
