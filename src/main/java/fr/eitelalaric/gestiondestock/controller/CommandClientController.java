package fr.eitelalaric.gestiondestock.controller;

import fr.eitelalaric.gestiondestock.controller.api.CommandClientApi;
import fr.eitelalaric.gestiondestock.controller.api.CommandProviderApi;
import fr.eitelalaric.gestiondestock.dto.CommandeClientDto;
import fr.eitelalaric.gestiondestock.dto.CommandeProviderDto;
import fr.eitelalaric.gestiondestock.dto.LigneCommandeClientDto;
import fr.eitelalaric.gestiondestock.model.EtatCommande;
import fr.eitelalaric.gestiondestock.service.CommandeClientService;
import fr.eitelalaric.gestiondestock.service.CommandeProviderService;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;


@RestController
public class CommandClientController implements CommandClientApi {

    private final CommandeClientService commandeClientService;

    public CommandClientController(CommandeClientService commandeClientService) {
        this.commandeClientService = commandeClientService;
    }

    @Override
    public CommandeClientDto save(CommandeClientDto commandeClientDto) {
        return commandeClientService.save(commandeClientDto);
    }

    @Override
    public CommandeClientDto updateEtatCommande(Integer idcommande, EtatCommande etatcommande) {
        return commandeClientService.updateEtatCommande(idcommande, etatcommande);
    }

    @Override
    public CommandeClientDto updateQuanditeCommande(Integer idcommande, Integer idLigneCommande, BigDecimal quantite) {
        return commandeClientService.updateQuanditeCommande(idcommande, idLigneCommande, quantite);
    }

    @Override
    public CommandeClientDto updateClient(Integer idcommande, Integer idClient) {
        return commandeClientService.updateClient(idcommande, idClient);
    }

    @Override
    public CommandeClientDto updateProduct(Integer idcommande, Integer idlignecommande, Integer idproduct) {
        return commandeClientService.updateArticle(idcommande, idlignecommande, idproduct);
    }

    @Override
    public CommandeClientDto deleteProdutFromCommand(Integer idcommande, Integer idlignecommande) {
        return commandeClientService.deleteProduct(idcommande, idlignecommande);
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
    public List<LigneCommandeClientDto> findAllLigneCommandeClientByCommandeClientId(Integer idCommande) {
        return commandeClientService.findAllLigneCommandeClientByCommandClientId(idCommande);
    }

    @Override
    public void delete(Integer id) {
        commandeClientService.delete(id);
    }
}
