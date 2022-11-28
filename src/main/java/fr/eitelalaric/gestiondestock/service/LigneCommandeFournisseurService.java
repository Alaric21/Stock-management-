package fr.eitelalaric.gestiondestock.service;

import fr.eitelalaric.gestiondestock.model.LigneCommandeProvider;

import java.util.List;

public interface LigneCommandeFournisseurService {

    LigneCommandeProvider save (LigneCommandeProvider ligneCommandeProvider);

    LigneCommandeProvider findById(Integer id);

    List<LigneCommandeProvider> findAll();

    void delete(Integer id);
}
