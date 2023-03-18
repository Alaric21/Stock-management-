package fr.eitelalaric.gestiondestock.service.impl;

import fr.eitelalaric.gestiondestock.dto.LigneVenteDto;
import fr.eitelalaric.gestiondestock.dto.VenteDto;
import fr.eitelalaric.gestiondestock.exception.EntityNotFoundException;
import fr.eitelalaric.gestiondestock.exception.ErrorCodes;
import fr.eitelalaric.gestiondestock.exception.InvalidEntityException;
import fr.eitelalaric.gestiondestock.model.LigneVente;
import fr.eitelalaric.gestiondestock.model.Product;
import fr.eitelalaric.gestiondestock.model.Vente;
import fr.eitelalaric.gestiondestock.repository.LigneVenteRepository;
import fr.eitelalaric.gestiondestock.repository.ProductRepository;
import fr.eitelalaric.gestiondestock.repository.VenteRepository;
import fr.eitelalaric.gestiondestock.service.VenteService;
import fr.eitelalaric.gestiondestock.validator.VenteValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class VenteServiceImpl implements VenteService {

    private VenteRepository venteRepository;

    private ProductRepository productRepository;

    private LigneVenteRepository ligneVenteRepository;

    public VenteServiceImpl(VenteRepository venteRepository, ProductRepository productRepository, LigneVenteRepository ligneVenteRepository) {
        this.venteRepository = venteRepository;
        this.productRepository = productRepository;
        this.ligneVenteRepository = ligneVenteRepository;
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
        //TODO control befor the delete to ensure that the sale isn't associat to a movement or eslse
        venteRepository.deleteById(id);
    }
}
