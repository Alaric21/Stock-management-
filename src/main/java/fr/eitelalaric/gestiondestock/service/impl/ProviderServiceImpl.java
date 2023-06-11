package fr.eitelalaric.gestiondestock.service.impl;

import fr.eitelalaric.gestiondestock.dto.ProviderDto;
import fr.eitelalaric.gestiondestock.exception.EntityNotFoundException;
import fr.eitelalaric.gestiondestock.exception.ErrorCodes;
import fr.eitelalaric.gestiondestock.exception.InvalidEntityException;
import fr.eitelalaric.gestiondestock.exception.InvalidOperationException;
import fr.eitelalaric.gestiondestock.model.CommandeProvider;
import fr.eitelalaric.gestiondestock.repository.CommandProviderRepository;
import fr.eitelalaric.gestiondestock.repository.ProviderRepository;
import fr.eitelalaric.gestiondestock.service.ProviderService;
import fr.eitelalaric.gestiondestock.validator.ProviderValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProviderServiceImpl implements ProviderService {

    private final ProviderRepository providerRepository;

    private final CommandProviderRepository commandProviderRepository;

    public ProviderServiceImpl(ProviderRepository providerRepository, CommandProviderRepository commandProviderRepository) {
        this.providerRepository = providerRepository;
        this.commandProviderRepository = commandProviderRepository;
    }

    @Override
    public ProviderDto save(ProviderDto providerDto) {
        List<String> errors = ProviderValidator.validate(providerDto);
        if (!errors.isEmpty()) {
            log.error("Provider not valid: {}", providerDto);
            throw new InvalidEntityException("Provider is not valid", ErrorCodes.PROVIDER_NOT_VALID,errors);
        }
        return ProviderDto.fromEntity(providerRepository.save(ProviderDto.toEntity(providerDto)));
    }

    @Override
    public ProviderDto findById(Integer id) {
        if (id==null){
            log.error("Provider ID is null");
            return null;
        }
        return providerRepository.findById(id).map(ProviderDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("Provider with ID "+id+"doesnt exist", ErrorCodes.PROVIDER_NOT_FOUND));

    }

    @Override
    public List<ProviderDto> findAll() {
        return providerRepository.findAll().stream()
                .map(ProviderDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id==null) {
            log.error("Provider ID is null");
            return;
        }
        List<CommandeProvider> commandeProviders = commandProviderRepository.findAllByProviderId(id);

        if (commandeProviders.isEmpty()){
            throw new InvalidOperationException("Impossible de supprimer un provider qui a deja des commandes",
                    ErrorCodes.PROVIDER_ALREADY_IN_USE);
        }
        providerRepository.deleteById(id);
    }
}
