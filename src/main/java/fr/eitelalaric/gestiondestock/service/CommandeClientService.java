package fr.eitelalaric.gestiondestock.service;

import fr.eitelalaric.gestiondestock.dto.CommandeClientDto;
import fr.eitelalaric.gestiondestock.dto.CommandeProviderDto;

import java.util.List;

public interface CommandeClientService {

    CommandeClientDto save (CommandeClientDto commandeClientDto);

    CommandeClientDto findById(Integer id);

    List<CommandeClientDto> findAll();

    void delete(Integer id);

    CommandeClientDto findCommandeClientByCode(String commandcode);
}
