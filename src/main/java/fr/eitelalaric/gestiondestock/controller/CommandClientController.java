package fr.eitelalaric.gestiondestock.controller;

import fr.eitelalaric.gestiondestock.controller.api.CommandClientApi;
import fr.eitelalaric.gestiondestock.controller.api.CommandProviderApi;
import fr.eitelalaric.gestiondestock.dto.CommandeClientDto;
import fr.eitelalaric.gestiondestock.dto.CommandeProviderDto;
import fr.eitelalaric.gestiondestock.service.CommandeClientService;
import fr.eitelalaric.gestiondestock.service.CommandeProviderService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class CommandClientController implements CommandClientApi {

    private CommandeClientService commandeClientService;

    public CommandClientController(CommandeClientService commandeClientService) {
        this.commandeClientService = commandeClientService;
    }

    @Override
    public CommandeClientDto save(CommandeClientDto commandeClientDto) {
        return commandeClientService.save(commandeClientDto);
    }

    @Override
    public CommandeClientDto findById(Integer idcommand) {
        return commandeClientService.findById(idcommand) ;
    }

    @Override
    public CommandeClientDto findCommandeClientByCode(String commandcode) {
        return commandeClientService.findCommandeClientByCode(commandcode);
    }

    @Override
    public List<CommandeClientDto> findAll() {
        return commandeClientService.findAll();
    }

    @Override
    public void delete(Integer id) {
        commandeClientService.delete(id);
    }
}
