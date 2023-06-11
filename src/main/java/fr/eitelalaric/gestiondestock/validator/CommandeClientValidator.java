package fr.eitelalaric.gestiondestock.validator;

import fr.eitelalaric.gestiondestock.dto.CommandeClientDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CommandeClientValidator {
    public static List<String> validate(CommandeClientDto commandeClientDto) {
        List<String> errors = new ArrayList<>();
        if (commandeClientDto == null) {
            errors.add("veuillez renseigner le client");
            errors.add("veuillez renseigner l'etat de la commande");
            errors.add("veuillez renseigner la date de la commande");
            errors.add("veuillez renseigner le code de la commande");
            return errors;
        }
        if (!StringUtils.hasLength(commandeClientDto.getCode())){
            errors.add("veuillez renseigner le code de la commande");
        }
        if (commandeClientDto.getDateCommande() == null){
            errors.add("veuillez renseigner la date de la commande");
        }
        if (!StringUtils.hasLength(commandeClientDto.getEtatCommande().toString())){
            errors.add("veuillez renseigner l'etat de la commande");
        }
        if ( commandeClientDto.getClient() ==null || commandeClientDto.getClient().getId() == null){
            errors.add("veuillez renseigner le client");

        }

        return errors;
    }
}
