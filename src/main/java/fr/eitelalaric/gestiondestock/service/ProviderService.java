package fr.eitelalaric.gestiondestock.service;

import fr.eitelalaric.gestiondestock.dto.ProviderDto;
import fr.eitelalaric.gestiondestock.model.Provider;

import java.util.List;

public interface ProviderService {

    ProviderDto save (ProviderDto providerDto);

    ProviderDto findById(Integer id);

    List<ProviderDto> findAll();

    void delete(Integer id);
}
