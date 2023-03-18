package fr.eitelalaric.gestiondestock.service.impl;

import fr.eitelalaric.gestiondestock.dto.CategoryDto;
import fr.eitelalaric.gestiondestock.exception.EntityNotFoundException;
import fr.eitelalaric.gestiondestock.exception.ErrorCodes;
import fr.eitelalaric.gestiondestock.exception.InvalidEntityException;
import fr.eitelalaric.gestiondestock.model.Category;
import fr.eitelalaric.gestiondestock.repository.CategoryRepo;
import fr.eitelalaric.gestiondestock.service.CategoryService;
import fr.eitelalaric.gestiondestock.validator.CategoryValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepo categoryRepo;

    public CategoryServiceImpl(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        List<String> errors =  CategoryValidator.validate(categoryDto);
        if(!errors.isEmpty()) {
            log.error("Category is not valid: {}", categoryDto);
            throw new InvalidEntityException("Category is not valide", ErrorCodes.CATEGORY_NOT_VALID, errors);
        }
        Category saveCategory = CategoryDto.toEntity(categoryDto);
        saveCategory.setCreationDate(Instant.now());
        saveCategory.setLastUpdateDate(Instant.now());
        return CategoryDto.fromEntity(categoryRepo.save(saveCategory));
    }

    @Override
    public CategoryDto findById(Integer id) {
        if (id == null) {
            log.error("Category ID "+id+" is not valid");
            return null;
        }
        return categoryRepo.findById(id).map(CategoryDto::fromEntity)
                .orElseThrow(()->new EntityNotFoundException("Category with is "+id+"not found", ErrorCodes.CATEGORY_NOT_FOUND));
    }

    @Override
    public CategoryDto findCategoryByCode(String code) {
        if (!StringUtils.hasLength(code)) {
            log.error("Category code is null");
            return  null;
        }
        return categoryRepo.findCategoryByCode(code).map(CategoryDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("Category with code "+code+" not found",ErrorCodes.CATEGORY_NOT_FOUND));
    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryRepo.findAll().stream()
                .map(CategoryDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if ( id == null) {
            log.error("Category id is null");
            return;
        }
        categoryRepo.deleteById(id);
    }
}
