package fr.eitelalaric.gestiondestock.service.impl;

import fr.eitelalaric.gestiondestock.dto.CommandeProviderDto;
import fr.eitelalaric.gestiondestock.dto.LigneCommandeProviderDto;
import fr.eitelalaric.gestiondestock.dto.MvtStkDto;
import fr.eitelalaric.gestiondestock.dto.ProductDto;
import fr.eitelalaric.gestiondestock.exception.EntityNotFoundException;
import fr.eitelalaric.gestiondestock.exception.ErrorCodes;
import fr.eitelalaric.gestiondestock.exception.InvalidEntityException;
import fr.eitelalaric.gestiondestock.exception.InvalidOperationException;
import fr.eitelalaric.gestiondestock.model.*;
import fr.eitelalaric.gestiondestock.repository.CommandProviderRepository;
import fr.eitelalaric.gestiondestock.repository.LigneCommandProviderRepository;
import fr.eitelalaric.gestiondestock.repository.ProductRepository;
import fr.eitelalaric.gestiondestock.repository.ProviderRepository;
import fr.eitelalaric.gestiondestock.service.CommandeProviderService;
import fr.eitelalaric.gestiondestock.service.MvtStkService;
import fr.eitelalaric.gestiondestock.validator.CommandeProviderValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CommandeProviderServiceImpl implements CommandeProviderService {

    private final ProviderRepository providerRepository;
    private final ProductRepository productRepository;
    private final CommandProviderRepository commandeProviderRepository;
    private final LigneCommandProviderRepository ligneCommandeProviderRepo;

    private final MvtStkService mvtStkService;

    public CommandeProviderServiceImpl(ProviderRepository providerRepository, ProductRepository productRepository, CommandProviderRepository commandeProviderRepository, LigneCommandProviderRepository ligneCommandeProviderRepo, MvtStkService mvtStkService) {
        this.providerRepository = providerRepository;
        this.productRepository = productRepository;
        this.commandeProviderRepository = commandeProviderRepository;
        this.ligneCommandeProviderRepo = ligneCommandeProviderRepo;
        this.mvtStkService = mvtStkService;
    }

    @Override
    public CommandeProviderDto save(CommandeProviderDto commandeProviderDto) {
        List<String> errors = CommandeProviderValidator.validate(commandeProviderDto);
        if (!errors.isEmpty()) {
            log.error("Command provider not valid: {}", commandeProviderDto);
            throw new InvalidEntityException("Command provider is not valid", ErrorCodes.COMMAND_PROVIDER_NOT_VALID,errors);
        }
        Optional<Provider> provider = providerRepository.findById(commandeProviderDto.getProvider().getId());
        if (provider.isEmpty()) {
            log.error("Provider with ID {} is not present in db", commandeProviderDto.getProvider().getId());
            throw new EntityNotFoundException("NO provider with ID found in db", ErrorCodes.PROVIDER_NOT_FOUND);
        }
        List<String> productErrors = new ArrayList<>();
        if (commandeProviderDto.getLigneCommandeProviders() !=null){
            commandeProviderDto.getLigneCommandeProviders()
                    .forEach(ligneCdeProvider->{
                        if (ligneCdeProvider.getProduct() !=null){
                            Optional<Product> product = productRepository.findById(ligneCdeProvider.getProduct().getId());
                            if(product.isEmpty()) {
                                productErrors.add("product whit ID"+ligneCdeProvider.getProduct().getId()+"doesn't exist");
                            }
                        }else {
                            productErrors.add("Impossible to register a product null");
                        }
                    });
            if (!productErrors.isEmpty()) {
                log.warn("one or more products do not present in database");
                throw new InvalidEntityException("Product don't exist in our database");
            }
        }
        CommandeProvider saveCmdProvider = commandeProviderRepository.save(CommandeProviderDto.toEntity(commandeProviderDto));
        if (commandeProviderDto.getLigneCommandeProviders() !=null) {
            commandeProviderDto.getLigneCommandeProviders().forEach(
                    ligneCmdProvider -> {
                        LigneCommandeProvider ligneCommandeProvider = LigneCommandeProviderDto.toEntity(ligneCmdProvider);
                        ligneCommandeProvider.setCommandeProvider(saveCmdProvider);
                        ligneCommandeProviderRepo.save(ligneCommandeProvider);

                    }
            );
        }
        return CommandeProviderDto.fromEntity(saveCmdProvider);    }

    @Override
    public CommandeProviderDto findById(Integer id) {
        if (id==null){
            log.error("Command provider  ID is null");
            return null;
        }
        return commandeProviderRepository.findById(id).map(CommandeProviderDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("Commande Provider with ID "+id+"doesnt exist", ErrorCodes.COMMAND_PROVIDER_NOT_FOUND));
    }

    @Override
    public CommandeProviderDto findCommandeProviderByCode(String code) {
        if(!StringUtils.hasLength(code)){
            log.error("Command provider code is null");
            return null;
        }
        return commandeProviderRepository.findCommandeLigneByCode(code)
                .map(CommandeProviderDto::fromEntity)
                .orElseThrow(()->new EntityNotFoundException("Commande provider with CODE "+code+"doesnt exist", ErrorCodes.COMMAND_PROVIDER_NOT_FOUND));
    }

    @Override
    public List<CommandeProviderDto> findAll() {
        return commandeProviderRepository.findAll().stream()
                .map(CommandeProviderDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id==null) {
            log.error("Command provider ID is null");
            return;
        }
        List<LigneCommandeProvider> commandeProviders =ligneCommandeProviderRepo.findAllByCommandeProviderId(id);
        if (commandeProviders.isEmpty()) {
            throw new InvalidOperationException("Impossible de supprimer une commande provider en utilisation",
                    ErrorCodes.COMMAND_PROVIDER_ALREADY_IN_USE);
        }
        commandeProviderRepository.deleteById(id);
    }

    private void updateMvtStk(Integer idCommande) {
        List<LigneCommandeProvider> ligneCommandeProviders = ligneCommandeProviderRepo.findAllByCommandeProviderId(idCommande);
        ligneCommandeProviders.forEach(ligneCommandeProvider -> {
            MvtStkDto mvtStkDto = MvtStkDto.builder()
                    .product(ProductDto.fromEntity(ligneCommandeProvider.getProduct()))
                    .sourceMvtStk(SourceMvtStk.COMMANDE_FOURNISSEUR)
                    .quantite(ligneCommandeProvider.getQuantite())
                    .idCompany(ligneCommandeProvider.getIdCompany())
                    .build();
            mvtStkService.sortieStock(mvtStkDto);
        });

    }
}
