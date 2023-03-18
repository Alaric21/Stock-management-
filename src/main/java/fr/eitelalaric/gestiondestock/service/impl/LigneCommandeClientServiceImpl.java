package fr.eitelalaric.gestiondestock.service.impl;

import fr.eitelalaric.gestiondestock.dto.LigneCommandeClientDto;
import fr.eitelalaric.gestiondestock.model.LigneCommandeClient;
import fr.eitelalaric.gestiondestock.service.LigneCommandeClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class LigneCommandeClientServiceImpl implements LigneCommandeClientService {
    @Override
    public LigneCommandeClientDto save(LigneCommandeClientDto ligneCommandeClientDto) {
        return null;
    }

    @Override
    public LigneCommandeClientDto findById(Integer id) {
        return null;
    }

    @Override
    public List<LigneCommandeClientDto> findAll() {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
