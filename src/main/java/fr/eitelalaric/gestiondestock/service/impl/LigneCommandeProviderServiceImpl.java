package fr.eitelalaric.gestiondestock.service.impl;

import fr.eitelalaric.gestiondestock.dto.LigneCommandeProviderDto;
import fr.eitelalaric.gestiondestock.model.LigneCommandeProvider;
import fr.eitelalaric.gestiondestock.service.LigneCommandeProviderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class LigneCommandeProviderServiceImpl implements LigneCommandeProviderService {
    @Override
    public LigneCommandeProviderDto save(LigneCommandeProviderDto ligneCommandeProviderDto) {
        return null;
    }

    @Override
    public LigneCommandeProviderDto findById(Integer id) {
        return null;
    }

    @Override
    public List<LigneCommandeProviderDto> findAll() {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
