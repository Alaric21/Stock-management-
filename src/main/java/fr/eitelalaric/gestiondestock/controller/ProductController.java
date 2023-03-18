package fr.eitelalaric.gestiondestock.controller;

import fr.eitelalaric.gestiondestock.controller.api.ProductApi;
import fr.eitelalaric.gestiondestock.dto.ProductDto;
import fr.eitelalaric.gestiondestock.service.ProductService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class ProductController implements ProductApi {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public ProductDto save(ProductDto productDto) {
        return productService.save(productDto);
    }

    @Override
    public ProductDto findById(Integer idproduct) {
        return productService.findById(idproduct) ;
    }

    @Override
    public ProductDto findByProductCode(String productCode) {
        return productService.findByProductCode(productCode);
    }

    @Override
    public List<ProductDto> findAll() {
        return productService.findAll();
    }

    @Override
    public void delete(Integer id) {
        productService.delete(id);
    }
}
