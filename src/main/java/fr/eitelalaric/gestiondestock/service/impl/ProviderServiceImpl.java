package fr.eitelalaric.gestiondestock.service.impl;

import fr.eitelalaric.gestiondestock.dto.ClientDto;
import fr.eitelalaric.gestiondestock.dto.ProviderDto;
import fr.eitelalaric.gestiondestock.exception.EntityNotFoundException;
import fr.eitelalaric.gestiondestock.exception.ErrorCodes;
import fr.eitelalaric.gestiondestock.exception.InvalidEntityException;
import fr.eitelalaric.gestiondestock.model.Provider;
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

    private ProviderRepository providerRepository;

    public ProviderServiceImpl(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
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
        //TODO befor to delete check if there is command associat to the provider
        providerRepository.deleteById(id);
    }
}
