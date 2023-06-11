package fr.eitelalaric.gestiondestock.service.impl;

import fr.eitelalaric.gestiondestock.dto.*;
import fr.eitelalaric.gestiondestock.exception.EntityNotFoundException;
import fr.eitelalaric.gestiondestock.exception.ErrorCodes;
import fr.eitelalaric.gestiondestock.exception.InvalidEntityException;
import fr.eitelalaric.gestiondestock.exception.InvalidOperationException;
import fr.eitelalaric.gestiondestock.model.*;
import fr.eitelalaric.gestiondestock.repository.ClientRepository;
import fr.eitelalaric.gestiondestock.repository.CommandeClientRepository;
import fr.eitelalaric.gestiondestock.repository.LigneCommandeClientRepository;
import fr.eitelalaric.gestiondestock.repository.ProductRepository;
import fr.eitelalaric.gestiondestock.service.CommandeClientService;
import fr.eitelalaric.gestiondestock.service.MvtStkService;
import fr.eitelalaric.gestiondestock.validator.CommandeClientValidator;
import fr.eitelalaric.gestiondestock.validator.ProductValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CommandeClientServiceImpl implements CommandeClientService {

    private final CommandeClientRepository commandeClientRepository;

    private final ClientRepository clientRepository;

    private final ProductRepository productRepository;

    private final LigneCommandeClientRepository ligneCommandeClientRepository;

    private final MvtStkService mvtStkService;

    public CommandeClientServiceImpl(CommandeClientRepository commandeClientRepository, ClientRepository clientRepository, ProductRepository productRepository, LigneCommandeClientRepository ligneCommandeClientRepository, MvtStkService mvtStkService) {
        this.commandeClientRepository = commandeClientRepository;
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
        this.ligneCommandeClientRepository = ligneCommandeClientRepository;
        this.mvtStkService = mvtStkService;
    }

    @Override
    public CommandeClientDto save(CommandeClientDto commandeClientDto) {
        List<String> errors = CommandeClientValidator.validate(commandeClientDto);
        if (!errors.isEmpty()) {
            log.error("Command client not valid: {}", commandeClientDto);
            throw new InvalidEntityException("Command client is not valid", ErrorCodes.CLIENT_NOT_VALID,errors);
        }
        if (commandeClientDto.getId() != null && commandeClientDto.isCommandeLivree()){
            throw new InvalidOperationException("impossible modifier une commande deja livree", ErrorCodes.COMMAND_CLIENT_NOT_UPDATABLE);
        }
        Optional<Client> client = clientRepository.findById(commandeClientDto.getClient().getId());
        if (client.isEmpty()) {
            log.error("Client with ID {} is not present in db", commandeClientDto.getClient().getId());
            throw new EntityNotFoundException("NO client with ID found in db", ErrorCodes.CLIENT_NOT_FOUND);
        }
        List<String> productErrors = new ArrayList<>();
        if (commandeClientDto.getLigneCommandeClients() !=null){
            commandeClientDto.getLigneCommandeClients()
                    .forEach(ligneCdeClt->{
                        if (ligneCdeClt.getProduct() !=null){
                            Optional<Product> product = productRepository.findById(ligneCdeClt.getProduct().getId());
                            if(product.isEmpty()) {
                                productErrors.add("product whit ID"+ligneCdeClt.getProduct().getId()+"doesn't exist");
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
        CommandeClient saveCmdclt = commandeClientRepository.save(CommandeClientDto.toEntity(commandeClientDto));
        if (commandeClientDto.getLigneCommandeClients() !=null) {
            commandeClientDto.getLigneCommandeClients().forEach(
                    ligneCmdClt -> {
                        LigneCommandeClient ligneCommandeClient = LigneCommandeClientDto.toEntity(ligneCmdClt);
                        ligneCommandeClient.setCommandeClient(saveCmdclt);
                        ligneCommandeClientRepository.save(ligneCommandeClient);

                    }
            );
        }
        return CommandeClientDto.fromEntity(saveCmdclt);
    }

    @Override
    public CommandeClientDto updateEtatCommande(Integer idcommande , EtatCommande etatCommande) {

        checkIdcommande(idcommande);
        if (!StringUtils.hasLength(String.valueOf(etatCommande))) {
            log.error("Etat commande est null ");
            throw new InvalidOperationException("Impossible modifier l'etat de la commande", ErrorCodes.COMMAND_CLIENT_NOT_UPDATABLE);
        }
        CommandeClientDto commandeClientDto = getCommandeClientDto(idcommande);
        commandeClientDto.setEtatCommande(etatCommande);

        CommandeClient savedCmdClient = commandeClientRepository.save(CommandeClientDto.toEntity(commandeClientDto));
        if (commandeClientDto.isCommandeLivree()){
            updateMvtStk(idcommande);
        }
        return CommandeClientDto.fromEntity(savedCmdClient);
    }


    @Override
    public CommandeClientDto updateQuanditeCommande(Integer idcommande, Integer idLigneCommande, BigDecimal quantite) {
        checkIdcommande(idcommande);
        checkIdLigneCommande(idLigneCommande);
        CommandeClientDto commandeClientDto = getCommandeClientDto(idcommande);

        if (quantite == null || quantite.compareTo(BigDecimal.ZERO) == 0){
            throw new InvalidOperationException("impossible modifier la quantite de la ligne commande", ErrorCodes.COMMAND_CLIENT_NOT_UPDATABLE);
        }
        Optional<LigneCommandeClient> ligneCommandeClientOptional = getLigneCommandeClient(idLigneCommande);
        LigneCommandeClient ligneCommandeClient = ligneCommandeClientOptional.get();
        ligneCommandeClient.setQuantite(quantite);
        ligneCommandeClientRepository.save(ligneCommandeClient);
        return commandeClientDto;
    }

    @Override
    public CommandeClientDto updateClient(Integer idcommande, Integer idClient) {
        checkIdcommande(idcommande);
        if (idClient == null) {
            log.error("ID ligne commande est null ");
            throw new InvalidOperationException("Impossible modifier le client  avec  ID client null", ErrorCodes.COMMAND_CLIENT_NOT_UPDATABLE);
        }
        CommandeClientDto commandeClientDto = getCommandeClientDto(idcommande);

        Optional<Client> clientOptional = clientRepository.findById(idClient);

        if (clientOptional.isEmpty()){
            throw new EntityNotFoundException("Client with ID "+idClient+"doesnt exist", ErrorCodes.CLIENT_NOT_FOUND);
        }
        commandeClientDto.setClient(ClientDto.fromEntity(clientOptional.get()));

        return CommandeClientDto.fromEntity(commandeClientRepository.save(CommandeClientDto.toEntity(commandeClientDto)));
    }

    @Override
    public CommandeClientDto updateArticle(Integer idcommande, Integer idLigneCommande, Integer idproduct) {
        checkIdcommande(idcommande);
        checkIdLigneCommande(idLigneCommande);
        checkIdArticle(idproduct, "nouvel");
        CommandeClientDto commandeClientDto = getCommandeClientDto(idcommande);
        Optional<LigneCommandeClient> ligneCommandeClientOptional = getLigneCommandeClient(idLigneCommande);
        Optional<Product> product = productRepository.findById(idproduct);
        if (product.isEmpty()){
            throw new EntityNotFoundException("product with ID "+ idproduct +"doesnt exist", ErrorCodes.PRODUCT_NOT_FOUND);
        }

        List<String> errors = ProductValidator.validate(ProductDto.fromEntity(product.get()));
        if (!errors.isEmpty()){
            throw new InvalidEntityException("Product invalid :", ErrorCodes.ARTICLE_NOT_VALID, errors);
        }
        LigneCommandeClient ligneCommandeClientSaved= ligneCommandeClientOptional.get();
        ligneCommandeClientSaved.setProduct(product.get());
        ligneCommandeClientRepository.save(ligneCommandeClientSaved);

        return commandeClientDto;
    }

    @Override
    public CommandeClientDto deleteProduct(Integer idcommande, Integer idLigneCommande) {
        checkIdcommande(idcommande);
        checkIdLigneCommande(idLigneCommande);
        CommandeClientDto commandeClientDto = getCommandeClientDto(idcommande);
        getLigneCommandeClient(idLigneCommande);
        ligneCommandeClientRepository.deleteById(idLigneCommande);

        return commandeClientDto;
    }

    @Override
    public CommandeClientDto findById(Integer idcommande) {
        checkIdcommande(idcommande);
        return commandeClientRepository.findById(idcommande).map(CommandeClientDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("Commande client with ID "+idcommande+"doesnt exist", ErrorCodes.COMMAND_CLIENT_NOT_FOUND));

    }

    @Override
    public CommandeClientDto findCommandeClientByCode(String code) {
        if(!StringUtils.hasLength(code)){
            log.error("Command client code is null");
            return null;
        }
        return commandeClientRepository.findCommandeLigneByCode(code)
                .map(CommandeClientDto::fromEntity)
                .orElseThrow(()->new EntityNotFoundException("Commande client with CODE "+code+"doesnt exist", ErrorCodes.COMMAND_CLIENT_NOT_FOUND));
    }

    @Override
    public List<CommandeClientDto> findAll() {
        return commandeClientRepository.findAll().stream()
                .map(CommandeClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer idcommande) {
        if (idcommande==null) {
            log.error("Command client ID is null");
            return;
        }
        List<LigneCommandeClient> commandeClients =ligneCommandeClientRepository.findAllByCommandeClientId(idcommande);
        if (commandeClients.isEmpty()) {
            throw new InvalidOperationException("Impossible de supprimer une commande client en utilisation",
                    ErrorCodes.COMMAND_CLIENT_ALREADY_IN_USE);
        }
        commandeClientRepository.deleteById(idcommande);
    }

    @Override
    public List<LigneCommandeClientDto> findAllLigneCommandeClientByCommandClientId(Integer id) {
       return ligneCommandeClientRepository.findAllByCommandeClientId(id).stream()
               .map(LigneCommandeClientDto::fromEntity)
               .collect(Collectors.toList());

    }

    private void checkIdcommande(Integer idcommande){
        if (idcommande == null) {
            log.error("ID commande null ");
            throw new InvalidOperationException("Impossible modifier l'etat de la commande avec ID null", ErrorCodes.COMMAND_CLIENT_NOT_UPDATABLE);
        }
    }
    private void checkIdLigneCommande(Integer idLigneCommande){
        if (idLigneCommande == null) {
            log.error("ID ligne commande est null ");
            throw new InvalidOperationException("Impossible modifier la quandite car id ligne commande null", ErrorCodes.COMMAND_CLIENT_NOT_UPDATABLE);
        }
    }

    private void checkIdArticle(Integer idarticle, String msg){
        if (idarticle == null) {
            log.error("ID "+msg+" est null ");
            throw new InvalidOperationException("Impossible modifier commande avec "+msg+" ID null", ErrorCodes.COMMAND_CLIENT_NOT_UPDATABLE);
        }
    }

    private CommandeClientDto getCommandeClientDto(Integer idcommande) {
        CommandeClientDto commandeClientDto = this.findById(idcommande);
        if (commandeClientDto.isCommandeLivree()){
            throw new InvalidOperationException("impossible modifier une commande deja livree", ErrorCodes.COMMAND_CLIENT_NOT_UPDATABLE);
        }
        return commandeClientDto;
    }

    private Optional<LigneCommandeClient> getLigneCommandeClient(Integer idLigneCommande) {
        Optional<LigneCommandeClient> ligneCommandeClientOptional = ligneCommandeClientRepository.findById(idLigneCommande);
        if (ligneCommandeClientOptional.isEmpty()){
            throw new EntityNotFoundException("Commande client with ID "+ idLigneCommande +"doesnt exist", ErrorCodes.LIGNE_COMMANDE_CLIENT_NOT_FOUND);
        }
        return ligneCommandeClientOptional;
    }

    private void updateMvtStk(Integer idCommande) {
        List<LigneCommandeClient> ligneCommandeClients = ligneCommandeClientRepository.findAllByCommandeClientId(idCommande);
        ligneCommandeClients.forEach(ligneCommandeClient -> {
                    MvtStkDto mvtStkDto = MvtStkDto.builder()
                            .dateMvt(Instant.now())
                            .typeMvtStk(TypeMvtStk.SORTIE)
                            .product(ProductDto.fromEntity(ligneCommandeClient.getProduct()))
                            .sourceMvtStk(SourceMvtStk.COMMANDE_CLIENT)
                            .quantite(ligneCommandeClient.getQuantite())
                            .idCompany(ligneCommandeClient.getIdCompany())
                            .build();
                    mvtStkService.sortieStock(mvtStkDto);
                });

    }
}
