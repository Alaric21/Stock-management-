package fr.eitelalaric.gestiondestock.service.impl;

import fr.eitelalaric.gestiondestock.dto.ProductDto;
import fr.eitelalaric.gestiondestock.exception.EntityNotFoundException;
import fr.eitelalaric.gestiondestock.exception.ErrorCodes;
import fr.eitelalaric.gestiondestock.exception.InvalidEntityException;
import fr.eitelalaric.gestiondestock.exception.InvalidOperationException;
import fr.eitelalaric.gestiondestock.model.LigneCommandeClient;
import fr.eitelalaric.gestiondestock.model.LigneCommandeProvider;
import fr.eitelalaric.gestiondestock.model.LigneVente;
import fr.eitelalaric.gestiondestock.model.Product;
import fr.eitelalaric.gestiondestock.repository.*;
import fr.eitelalaric.gestiondestock.service.ProductService;
import fr.eitelalaric.gestiondestock.validator.ProductValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final LigneCommandeClientRepository ligneCommandeClientRepository;
    private final LigneVenteRepository ligneVenteRepository;
    private final LigneCommandProviderRepository ligneCommandProviderRepository;

    public ProductServiceImpl(ProductRepository productRepository , LigneCommandeClientRepository ligneCommandeClientRepository, LigneVenteRepository ligneVenteRepository, VenteRepository venteRepository, LigneCommandProviderRepository ligneCommandProviderRepository) {
        this.productRepository = productRepository;
        this.ligneCommandeClientRepository = ligneCommandeClientRepository;
        this.ligneVenteRepository = ligneVenteRepository;
        this.ligneCommandProviderRepository = ligneCommandProviderRepository;
    }

    @Override
    public ProductDto save(ProductDto productDto) {
        List<String> errors = ProductValidator.validate(productDto);
        if (!errors.isEmpty()) {
            log.error("Product not valid: {}", productDto);
            throw new InvalidEntityException("Product n'est pas valide", ErrorCodes.ARTICLE_NOT_VALID,errors);
        }
        Product saveProduct = ProductDto.toEntity(productDto);
        saveProduct.setCreationDate(Instant.now());
        saveProduct.setLastUpdateDate(null);
        return ProductDto.fromEntity(productRepository.save(saveProduct));
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

        List<LigneCommandeClient> ligneCommandeClients = ligneCommandeClientRepository.findAllByProductId(id);
        if (ligneCommandeClients.isEmpty()) {
            throw new InvalidOperationException("Impossible de supprimer un product deja utilise dans des commandes client",
                    ErrorCodes.ARTICLE_ALREADY_IN_USED);
        }
        List<LigneCommandeProvider> ligneCommandeProviders = ligneCommandProviderRepository.findAllByProductId(id);

        if (ligneCommandeProviders.isEmpty()) {
            throw new InvalidOperationException("Impossible de supprimer un product deja utilise dans des commandes provider",
                    ErrorCodes.ARTICLE_ALREADY_IN_USED);
        }
        List<LigneVente> ligneVentes = ligneVenteRepository.findAllByProductId(id);

        if (ligneVentes.isEmpty()) {
            throw new InvalidOperationException("Impossible de supprimer un product deja utilise dans des ventes",
                    ErrorCodes.ARTICLE_ALREADY_IN_USED);
        }
        productRepository.deleteById(id);
    }
}
