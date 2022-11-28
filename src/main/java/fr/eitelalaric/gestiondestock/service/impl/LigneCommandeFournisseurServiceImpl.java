package fr.eitelalaric.gestiondestock.service.impl;

import fr.eitelalaric.gestiondestock.model.LigneCommandeProvider;
import fr.eitelalaric.gestiondestock.service.LigneCommandeFournisseurService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class LigneCommandeFournisseurServiceImpl implements LigneCommandeFournisseurService {
    @Override
    public LigneCommandeProvider save(LigneCommandeProvider ligneCommandeProvider) {
        return null;
    }

    @Override
    public LigneCommandeProvider findById(Integer id) {
        return null;
    }

    @Override
    public List<LigneCommandeProvider> findAll() {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
