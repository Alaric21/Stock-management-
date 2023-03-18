package fr.eitelalaric.gestiondestock.service;

import fr.eitelalaric.gestiondestock.dto.LigneCommandeProviderDto;
import fr.eitelalaric.gestiondestock.model.LigneCommandeProvider;

import java.util.List;

public interface LigneCommandeProviderService {

    LigneCommandeProviderDto save (LigneCommandeProviderDto ligneCommandeProviderDto);

    LigneCommandeProviderDto findById(Integer id);

    List<LigneCommandeProviderDto> findAll();

    void delete(Integer id);
}
