package fr.eitelalaric.gestiondestock.controller;

import fr.eitelalaric.gestiondestock.controller.api.CategoryApi;
import fr.eitelalaric.gestiondestock.dto.CategoryDto;
import fr.eitelalaric.gestiondestock.service.CategoryService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class CategoryController implements CategoryApi {

    private CategoryService  categorySercie;

    public CategoryController(CategoryService categorySercie) {
        this.categorySercie = categorySercie;
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        return categorySercie.save(categoryDto);
    }

    @Override
    public CategoryDto findById(Integer idcategory) {
        return categorySercie.findById(idcategory) ;
    }

    @Override
    public CategoryDto findCategoryByCategoryCode(String categoryCode) {
        return categorySercie.findCategoryByCode(categoryCode);
    }

    @Override
    public List<CategoryDto> findAll() {
        return categorySercie.findAll();
    }

    @Override
    public void delete(Integer id) {
        categorySercie.delete(id);
    }
}
