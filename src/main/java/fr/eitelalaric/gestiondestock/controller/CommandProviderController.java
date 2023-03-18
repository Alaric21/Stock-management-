package fr.eitelalaric.gestiondestock.controller;

import fr.eitelalaric.gestiondestock.controller.api.CommandProviderApi;
import fr.eitelalaric.gestiondestock.controller.api.ProviderApi;
import fr.eitelalaric.gestiondestock.dto.CommandeProviderDto;
import fr.eitelalaric.gestiondestock.dto.ProviderDto;
import fr.eitelalaric.gestiondestock.service.CommandeProviderService;
import fr.eitelalaric.gestiondestock.service.ProviderService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class CommandProviderController implements CommandProviderApi {

    private CommandeProviderService  commandeProviderService;

    public CommandProviderController(CommandeProviderService commandeProviderService) {
        this.commandeProviderService = commandeProviderService;
    }

    @Override
    public CommandeProviderDto save(CommandeProviderDto commandeProviderDto) {
        return commandeProviderService.save(commandeProviderDto);
    }

    @Override
    public CommandeProviderDto findById(Integer idcommand) {
        return commandeProviderService.findById(idcommand) ;
    }

    @Override
    public CommandeProviderDto findCommandeProviderByCode(String commandcode) {
        return commandeProviderService.findCommandeProviderByCode(commandcode);
    }

    @Override
    public List<CommandeProviderDto> findAll() {
        return commandeProviderService.findAll();
    }

    @Override
    public void delete(Integer id) {
        commandeProviderService.delete(id);
    }
}
