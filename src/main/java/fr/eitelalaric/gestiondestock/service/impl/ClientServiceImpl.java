package fr.eitelalaric.gestiondestock.service.impl;

import fr.eitelalaric.gestiondestock.dto.ClientDto;
import fr.eitelalaric.gestiondestock.exception.EntityNotFoundException;
import fr.eitelalaric.gestiondestock.exception.ErrorCodes;
import fr.eitelalaric.gestiondestock.exception.InvalidEntityException;
import fr.eitelalaric.gestiondestock.exception.InvalidOperationException;
import fr.eitelalaric.gestiondestock.model.CommandeClient;
import fr.eitelalaric.gestiondestock.repository.ClientRepository;
import fr.eitelalaric.gestiondestock.repository.CommandeClientRepository;
import fr.eitelalaric.gestiondestock.service.ClientService;
import fr.eitelalaric.gestiondestock.validator.ClientValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final CommandeClientRepository commandeClientRepository;

    public ClientServiceImpl(ClientRepository clientRepository, CommandeClientRepository commandeClientRepository) {
        this.clientRepository = clientRepository;
        this.commandeClientRepository = commandeClientRepository;
    }

    @Override
    public ClientDto save(ClientDto clientDto) {

        List<String> errors = ClientValidator.validate(clientDto);
        if (!errors.isEmpty()) {
            log.error("Client not valid: {}", clientDto);
            throw new InvalidEntityException("Client is not valid", ErrorCodes.CLIENT_NOT_VALID,errors);
        }
        return ClientDto.fromEntity(clientRepository.save(ClientDto.toEntity(clientDto)));
    }

    @Override
    public ClientDto findById(Integer id) {
        if (id==null){
            log.error("Client ID is null");
            return null;
        }
        return clientRepository.findById(id).map(ClientDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("Client with ID "+id+"doesnt exist", ErrorCodes.CLIENT_NOT_FOUND));

    }

    @Override
    public List<ClientDto> findAll() {
        return clientRepository.findAll().stream()
                .map(ClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id==null) {
            log.error("Client ID is null");
            return;
        }
        List<CommandeClient> commandeClients = commandeClientRepository.findAllByClientId(id);

        if (commandeClients.isEmpty()){
            throw new InvalidOperationException("Impossible de supprimer de supprimer un client qui a deja des commandes",
                    ErrorCodes.CLIENT_ALREADY_IN_USE);
        }
        clientRepository.deleteById(id);
    }
}
