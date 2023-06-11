package fr.eitelalaric.gestiondestock.dto;

import fr.eitelalaric.gestiondestock.model.Product;
import fr.eitelalaric.gestiondestock.model.Category;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductDto {

    private Integer id;

    private String productCode;

    private String designation;

    private BigDecimal prixUnitaireHt;

    private BigDecimal tauxTva;

    private BigDecimal prixUnitaireTtc;

    private String photo;

    private CategoryDto category;

    private Integer idCompany;

    public  static ProductDto fromEntity(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .idCompany(product.getIdCompany())
                .designation(product.getDesignation())
                .photo(product.getPhoto())
                .productCode(product.getProductCode())
                .prixUnitaireHt(product.getPrixUnitaireHt())
                .prixUnitaireTtc(product.getPrixUnitaireTtc())
                .tauxTva(product.getTauxTva())
                .build();
    }

    public static Product toEntity(ProductDto productDto) {
        return Product.builder()
                .idCompany(productDto.getIdCompany())
                .designation(productDto.getDesignation())
                .photo(productDto.getPhoto())
                .productCode(productDto.getProductCode())
                .prixUnitaireHt(productDto.getPrixUnitaireHt())
                .prixUnitaireTtc(productDto.getPrixUnitaireTtc())
                .tauxTva(productDto.getTauxTva())
                .build();
    }
}
