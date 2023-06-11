package fr.eitelalaric.gestiondestock.service.impl;

import fr.eitelalaric.gestiondestock.dto.LigneVenteDto;
import fr.eitelalaric.gestiondestock.dto.MvtStkDto;
import fr.eitelalaric.gestiondestock.dto.ProductDto;
import fr.eitelalaric.gestiondestock.dto.VenteDto;
import fr.eitelalaric.gestiondestock.exception.EntityNotFoundException;
import fr.eitelalaric.gestiondestock.exception.ErrorCodes;
import fr.eitelalaric.gestiondestock.exception.InvalidEntityException;
import fr.eitelalaric.gestiondestock.exception.InvalidOperationException;
import fr.eitelalaric.gestiondestock.model.*;
import fr.eitelalaric.gestiondestock.repository.LigneVenteRepository;
import fr.eitelalaric.gestiondestock.repository.ProductRepository;
import fr.eitelalaric.gestiondestock.repository.VenteRepository;
import fr.eitelalaric.gestiondestock.service.MvtStkService;
import fr.eitelalaric.gestiondestock.service.VenteService;
import fr.eitelalaric.gestiondestock.validator.VenteValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class VenteServiceImpl implements VenteService {

    private final VenteRepository venteRepository;

    private final ProductRepository productRepository;

    private final LigneVenteRepository ligneVenteRepository;
    private final MvtStkService mvtStkService;

    public VenteServiceImpl(VenteRepository venteRepository, ProductRepository productRepository, LigneVenteRepository ligneVenteRepository, MvtStkService mvtStkService) {
        this.venteRepository = venteRepository;
        this.productRepository = productRepository;
        this.ligneVenteRepository = ligneVenteRepository;
        this.mvtStkService = mvtStkService;
    }

    @Override
    public VenteDto save(VenteDto venteDto) {
        List<String> errors = VenteValidator.validate(venteDto);
        if (!errors.isEmpty()) {
            log.error("Sale is not valid");
            throw new InvalidEntityException("Sale is not valid", ErrorCodes.VENTE_NOT_VALID,errors);
        }
        List<String> productErrors = new ArrayList<>();
        venteDto.getLigneVentes().forEach(ligneVente -> {
            Optional<Product> product = productRepository.findById(ligneVente.getProduct().getId());
            if (product.isEmpty()) {
                productErrors.add("No product with ID " + ligneVente.getProduct().getId() + " exist in database");
            }
        });
        if (!productErrors.isEmpty()){
            log.error("One or more product were not found in the database {}", productErrors);
            throw new InvalidEntityException("One or more product were not found in the database ", ErrorCodes.PRODUCT_NOT_FOUND);
        }

        Vente savevente = venteRepository.save(VenteDto.toEntity(venteDto));
        venteDto.getLigneVentes().forEach(ligneVenteDto -> {
            LigneVente ligneVente = LigneVenteDto.toEntity(ligneVenteDto);
            ligneVente.setVente(savevente);
            ligneVenteRepository.save(ligneVente);
            updateMvtStk(ligneVente);
        });
        return VenteDto.fromEntity(savevente);
    }

    @Override
    public VenteDto findById(Integer id) {
        if (id==null){
            log.error("Sale ID is null");
            return null;
        }
        return venteRepository.findById(id).map(VenteDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("Sale with ID "+id+"doesnt exist", ErrorCodes.VENTE_NOT_FOUND));

    }

    @Override
    public VenteDto findByCode(String code) {
        if (!StringUtils.hasLength(code)) {
            log.error("Sale code is null");
            return null;
        }
        return venteRepository.findVenteByCode(code).map(VenteDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("Aucune vente avec le code "+ code+"n'est trouve dans la database", ErrorCodes.VENTE_NOT_FOUND));

    }

    @Override
    public List<VenteDto> findAll() {
        return venteRepository.findAll().stream()
                .map(VenteDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id==null) {
            log.error("Employee ID is null");
            return;
        }
        List<LigneVente> ligneVentes = ligneVenteRepository.findAllByVenteId(id);
        if (ligneVentes.isEmpty()){
            throw new InvalidOperationException("Impossible de supprimer une vente avec des commandes",
                    ErrorCodes.VENTE_ALREADY_IN_USE);
        }
        venteRepository.deleteById(id);
    }
    private void updateMvtStk(LigneVente ligneVente) {

            MvtStkDto mvtStkDto = MvtStkDto.builder()
                    .dateMvt(Instant.now())
                    .typeMvtStk(TypeMvtStk.SORTIE)
                    .product(ProductDto.fromEntity(ligneVente.getProduct()))
                    .sourceMvtStk(SourceMvtStk.VENTE)
                    .quantite(ligneVente.getQuantite())
                    .idCompany(ligneVente.getIdCompany())
                    .build();
            mvtStkService.sortieStock(mvtStkDto);

    }
}
