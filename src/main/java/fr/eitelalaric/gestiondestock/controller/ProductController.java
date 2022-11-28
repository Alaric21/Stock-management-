package fr.eitelalaric.gestiondestock.controller;

import fr.eitelalaric.gestiondestock.controller.api.ProductApi;
import fr.eitelalaric.gestiondestock.dto.ProductDto;
import fr.eitelalaric.gestiondestock.service.ProductSercie;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class ProductController implements ProductApi {

    private ProductSercie productSercie;

    public ProductController(ProductSercie productSercie) {
        this.productSercie = productSercie;
    }

    @Override
    public ProductDto save(ProductDto productDto) {
        return productSercie.save(productDto);
    }

    @Override
    public ProductDto findById(Integer idproduct) {
        return productSercie.findById(idproduct) ;
    }

    @Override
    public ProductDto findByProductCode(String codeArticle) {
        return productSercie.findByProductCode(codeArticle);
    }

    @Override
    public List<ProductDto> findAll() {
        return productSercie.findAll();
    }

    @Override
    public void delete(Integer id) {
        productSercie.delete(id);
    }
}
