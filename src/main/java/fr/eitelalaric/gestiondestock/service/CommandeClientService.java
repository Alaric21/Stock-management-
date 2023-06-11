package fr.eitelalaric.gestiondestock.service;

import fr.eitelalaric.gestiondestock.dto.CommandeClientDto;
import fr.eitelalaric.gestiondestock.dto.CommandeProviderDto;
import fr.eitelalaric.gestiondestock.dto.LigneCommandeClientDto;
import fr.eitelalaric.gestiondestock.model.EtatCommande;

import java.math.BigDecimal;
import java.util.List;

public interface CommandeClientService {

    CommandeClientDto save (CommandeClientDto commandeClientDto);

    CommandeClientDto updateEtatCommande(Integer id , EtatCommande etatCommande);

    CommandeClientDto updateQuanditeCommande(Integer idcommande, Integer idLigneCommande, BigDecimal quantite);
    CommandeClientDto updateClient(Integer idcommande, Integer idClient);

    CommandeClientDto updateArticle(Integer idcommande, Integer idLigneCommande, Integer newIdArticle);

    //delete une ligne commande client
    CommandeClientDto deleteProduct(Integer idcommande, Integer idLigneCommande);

    CommandeClientDto findById(Integer id);

    List<CommandeClientDto> findAll();

    void delete(Integer id);

    CommandeClientDto findCommandeClientByCode(String commandcode);

    public List<LigneCommandeClientDto> findAllLigneCommandeClientByCommandClientId(Integer id);

    }
