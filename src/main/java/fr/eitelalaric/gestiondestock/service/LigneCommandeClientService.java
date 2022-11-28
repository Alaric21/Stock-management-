package fr.eitelalaric.gestiondestock.service;

import fr.eitelalaric.gestiondestock.model.LigneCommandeClient;

import java.util.List;

public interface LigneCommandeClientService {

    LigneCommandeClient save (LigneCommandeClient ligneCommandeClient);

    LigneCommandeClient findById(Integer id);

    List<LigneCommandeClient> findAll();

    void delete(Integer id);
}
