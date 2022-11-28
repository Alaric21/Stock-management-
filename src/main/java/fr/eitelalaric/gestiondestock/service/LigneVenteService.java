package fr.eitelalaric.gestiondestock.service;

import fr.eitelalaric.gestiondestock.dto.LigneVenteDto;

import java.util.List;

public interface LigneVenteService {

    LigneVenteDto save (LigneVenteDto ligneVenteDto);

    LigneVenteDto findById(Integer id);

    List<LigneVenteDto> findAll();

    void delete(Integer id);
}
