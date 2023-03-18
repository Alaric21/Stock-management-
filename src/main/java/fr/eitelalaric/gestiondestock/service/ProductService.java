package fr.eitelalaric.gestiondestock.service;

import fr.eitelalaric.gestiondestock.dto.ProductDto;

import java.util.List;

public interface ProductService {

     ProductDto save (ProductDto productDto);

     ProductDto findById(Integer id);

     ProductDto findByProductCode(String codeArticle);

     List<ProductDto> findAll();

     void delete(Integer id);

}
