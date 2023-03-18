package fr.eitelalaric.gestiondestock.service;

import fr.eitelalaric.gestiondestock.dto.CommandeProviderDto;

import java.util.List;

public interface CommandeProviderService {

    CommandeProviderDto save (CommandeProviderDto commandeProviderDto);

    CommandeProviderDto findById(Integer id);

    CommandeProviderDto findCommandeProviderByCode(String code);
    List<CommandeProviderDto> findAll();

    void delete(Integer id);
}
