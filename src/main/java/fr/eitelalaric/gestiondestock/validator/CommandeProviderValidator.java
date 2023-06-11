package fr.eitelalaric.gestiondestock.validator;

import fr.eitelalaric.gestiondestock.dto.CommandeProviderDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CommandeProviderValidator {
    public static List<String> validate(CommandeProviderDto commandeProviderDto) {
        List<String> errors = new ArrayList<>();
        if (commandeProviderDto == null) {
            errors.add("veuillez renseigner le provider");
            errors.add("veuillez renseigner l'etat de la commande");
            errors.add("veuillez renseigner la date de la commande");
            errors.add("veuillez renseigner le code de la commande");
            return errors;
        }
        if (!StringUtils.hasLength(commandeProviderDto.getCode())){
            errors.add("veuillez renseigner le code de la commande");
        }
        if (commandeProviderDto.getDateCommande() == null){
            errors.add("veuillez renseigner la date de la commande");
        }
        if (!StringUtils.hasLength(commandeProviderDto.getEtatCommande().toString())){
            errors.add("veuillez renseigner l'etat de la commande");
        }
        if ( commandeProviderDto.getProvider() ==null || commandeProviderDto.getProvider().getId() == null){
            errors.add("veuillez renseigner le provider");

        }

        return errors;
    }
}
