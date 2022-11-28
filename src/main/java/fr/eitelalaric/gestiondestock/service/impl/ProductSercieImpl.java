package fr.eitelalaric.gestiondestock.service.impl;

import fr.eitelalaric.gestiondestock.dto.ProductDto;
import fr.eitelalaric.gestiondestock.exception.EntityNotFoundException;
import fr.eitelalaric.gestiondestock.exception.ErrorCodes;
import fr.eitelalaric.gestiondestock.exception.InvalidEntityException;
import fr.eitelalaric.gestiondestock.repository.ProductRepository;
import fr.eitelalaric.gestiondestock.service.ProductSercie;
import fr.eitelalaric.gestiondestock.validator.ProductValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductSercieImpl implements ProductSercie {

    private ProductRepository productRepository;

    public ProductSercieImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductDto save(ProductDto productDto) {
        List<String> errors = ProductValidator.validate(productDto);
        if (!errors.isEmpty()) {
            log.error("Product not valid: {}", productDto);
            throw new InvalidEntityException("Product n'est pas valide", ErrorCodes.ARTICLE_NOT_VALID,errors);
        }
        return ProductDto.fromEntity(productRepository.save(ProductDto.toEntity(productDto)));
    }

    @Override
    public ProductDto findById(Integer id) {
        if (id == null) {
            log.error("Product ID is null");
            return null;
        }
        return Optional.of(ProductDto.fromEntity(productRepository.findById(id).get()))
                .orElseThrow(()->new EntityNotFoundException(
                        "Product with ID = "+id+" not found",
                        ErrorCodes.PRODUCT_NOT_FOUND)
                );
    }

    @Override
    public ProductDto findByProductCode(String productCode) {
        if (!StringUtils.hasLength(productCode)) {
            log.error("Product code is nulll");
            return null;
        }
        return Optional.of(ProductDto.fromEntity(productRepository.findProductByProductCode(productCode).get()))
                .orElseThrow(()-> new EntityNotFoundException("Aucun product avec le code "+ productCode+"n'est trouve dans la db", ErrorCodes.PRODUCT_NOT_FOUND));
    }

    @Override
    public List<ProductDto> findAll() {
        return productRepository.findAll().stream()
                .map(ProductDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Product ID is null");
            return;
        }
        productRepository.deleteById(id);
    }
}
