package fr.eitelalaric.gestiondestock.controller;

import fr.eitelalaric.gestiondestock.controller.api.CommandClientApi;
import fr.eitelalaric.gestiondestock.controller.api.LigneCommandClientApi;
import fr.eitelalaric.gestiondestock.dto.CommandeClientDto;
import fr.eitelalaric.gestiondestock.dto.LigneCommandeClientDto;
import fr.eitelalaric.gestiondestock.service.CommandeClientService;
import fr.eitelalaric.gestiondestock.service.LigneCommandeClientService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class LigneCommandClientController implements LigneCommandClientApi {

    private LigneCommandeClientService lignecommandeClientService;

    public LigneCommandClientController(LigneCommandeClientService lignecommandeClientService) {
        this.lignecommandeClientService = lignecommandeClientService;
    }


    @Override
    public LigneCommandeClientDto save(LigneCommandeClientDto ligneCommandeClientDto) {
        return lignecommandeClientService.save(ligneCommandeClientDto);
    }

    @Override
    public LigneCommandeClientDto findById(Integer idlignecommandclient) {
        return lignecommandeClientService.findById(idlignecommandclient);
    }

    @Override
    public List<LigneCommandeClientDto> findAll() {
        return lignecommandeClientService.findAll();
    }

    @Override
    public void delete(Integer idlignecommandclient) {
        lignecommandeClientService.delete(idlignecommandclient);
    }
}
