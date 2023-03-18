package fr.eitelalaric.gestiondestock.service;

import fr.eitelalaric.gestiondestock.dto.CategoryDto;

import java.util.List;


public interface CategoryService {

    CategoryDto save (CategoryDto CategoryDto);

    CategoryDto findById(Integer id);

    CategoryDto findCategoryByCode(String code);

    List<CategoryDto> findAll();

    void delete(Integer id);
}
