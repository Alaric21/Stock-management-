package fr.eitelalaric.gestiondestock.controller;

import fr.eitelalaric.gestiondestock.controller.api.CategoryApi;
import fr.eitelalaric.gestiondestock.dto.CategoryDto;
import fr.eitelalaric.gestiondestock.service.CategoryService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class CategoryController implements CategoryApi {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        return categoryService.save(categoryDto);
    }

    @Override
    public CategoryDto findById(Integer idcategory) {
        return categoryService.findById(idcategory) ;
    }

    @Override
    public CategoryDto findCategoryByCategoryCode(String categoryCode) {
        return categoryService.findCategoryByCode(categoryCode);
    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryService.findAll();
    }

    @Override
    public void delete(Integer id) {
        categoryService.delete(id);
    }
}
