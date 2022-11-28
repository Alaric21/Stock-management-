package fr.eitelalaric.gestiondestock.service.impl;

import fr.eitelalaric.gestiondestock.dto.CommandeClientDto;
import fr.eitelalaric.gestiondestock.dto.LigneCommandeClientDto;
import fr.eitelalaric.gestiondestock.exception.EntityNotFoundException;
import fr.eitelalaric.gestiondestock.exception.ErrorCodes;
import fr.eitelalaric.gestiondestock.exception.InvalidEntityException;
import fr.eitelalaric.gestiondestock.model.Client;
import fr.eitelalaric.gestiondestock.model.CommandeClient;
import fr.eitelalaric.gestiondestock.model.LigneCommandeClient;
import fr.eitelalaric.gestiondestock.model.Product;
import fr.eitelalaric.gestiondestock.repository.ClientRepository;
import fr.eitelalaric.gestiondestock.repository.CommandeClientRepository;
import fr.eitelalaric.gestiondestock.repository.LigneCommandeClientRepository;
import fr.eitelalaric.gestiondestock.repository.ProductRepository;
import fr.eitelalaric.gestiondestock.service.CommandeClientService;
import fr.eitelalaric.gestiondestock.validator.CommandeClientValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CommandeClientServiceImpl implements CommandeClientService {

    private CommandeClientRepository commandeClientRepository;

    private ClientRepository clientRepository;

    private ProductRepository productRepository;

    private LigneCommandeClientRepository ligneCommandeClientRepository;

    public CommandeClientServiceImpl(CommandeClientRepository commandeClientRepository, ClientRepository clientRepository, ProductRepository productRepository, LigneCommandeClientRepository ligneCommandeClientRepository) {
        this.commandeClientRepository = commandeClientRepository;
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
        this.ligneCommandeClientRepository = ligneCommandeClientRepository;
    }

    @Override
    public CommandeClientDto save(CommandeClientDto commandeClientDto) {
        List<String> errors = CommandeClientValidator.validate(commandeClientDto);
        if (!errors.isEmpty()) {
            log.error("Command client not valid: {}", commandeClientDto);
            throw new InvalidEntityException("Command client is not valid", ErrorCodes.CLIENT_NOT_VALID,errors);
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
    public CommandeClientDto findById(Integer id) {
        if (id==null){
            log.error("Command Client  ID is null");
            return null;
        }
        return commandeClientRepository.findById(id).map(CommandeClientDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("Commande client with ID "+id+"doesnt exist", ErrorCodes.COMMAND_CLIENT_NOT_FOUND));

    }

    @Override
    public CommandeClientDto findByCode(String code) {
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
    public void delete(Integer id) {
        if (id==null) {
            log.error("Command client ID is null");
            return;
        }
        commandeClientRepository.deleteById(id);
    }
}
