package fr.eitelalaric.gestiondestock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.eitelalaric.gestiondestock.model.Category;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CategoryDto {

    private Integer id;

    private String code;

    private String designation;

    private Integer idCompany;


    public  static CategoryDto fromEntity(Category category) {
        return CategoryDto.builder()
                .id(category.getIdcategory())
                .code(category.getCode())
                .designation(category.getDesignation())
                .idCompany(category.getIdCompany())
                .build();
    }

    public static Category toEntity(CategoryDto categoryDto) {
        return Category.builder()
                .idcategory(categoryDto.getId())
                .code(categoryDto.getCode())
                .designation(categoryDto.getDesignation())
                .idCompany(categoryDto.getIdCompany())
                .build();
    }
}
