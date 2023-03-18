package fr.eitelalaric.gestiondestock.controller;

import fr.eitelalaric.gestiondestock.controller.api.LigneCommandClientApi;
import fr.eitelalaric.gestiondestock.controller.api.LigneCommandProviderApi;
import fr.eitelalaric.gestiondestock.dto.LigneCommandeClientDto;
import fr.eitelalaric.gestiondestock.dto.LigneCommandeProviderDto;
import fr.eitelalaric.gestiondestock.service.LigneCommandeClientService;
import fr.eitelalaric.gestiondestock.service.LigneCommandeProviderService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class LigneCommandProviderController implements LigneCommandProviderApi {

    private LigneCommandeProviderService ligneCommandeProviderService;

    public LigneCommandProviderController(LigneCommandeProviderService ligneCommandeProviderService) {
        this.ligneCommandeProviderService = ligneCommandeProviderService;
    }


    @Override
    public LigneCommandeProviderDto save(LigneCommandeProviderDto LigneCommandeProviderDto) {
        return ligneCommandeProviderService.save(LigneCommandeProviderDto);
    }

    @Override
    public LigneCommandeProviderDto findById(Integer idlignecommandclient) {
        return ligneCommandeProviderService.findById(idlignecommandclient);
    }

    @Override
    public List<LigneCommandeProviderDto> findAll() {
        return ligneCommandeProviderService.findAll();
    }

    @Override
    public void delete(Integer idlignecommandprovider) {
        ligneCommandeProviderService.delete(idlignecommandprovider);
    }
}
