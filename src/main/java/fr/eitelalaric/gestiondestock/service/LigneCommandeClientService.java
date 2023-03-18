package fr.eitelalaric.gestiondestock.service;

import fr.eitelalaric.gestiondestock.dto.LigneCommandeClientDto;
import fr.eitelalaric.gestiondestock.model.LigneCommandeClient;

import java.util.List;

public interface LigneCommandeClientService {

    LigneCommandeClientDto save (LigneCommandeClientDto ligneCommandeClientDto);

    LigneCommandeClientDto findById(Integer id);

    List<LigneCommandeClientDto> findAll();

    void delete(Integer id);
}
