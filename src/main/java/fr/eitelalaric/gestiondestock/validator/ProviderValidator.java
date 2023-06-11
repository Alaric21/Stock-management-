package fr.eitelalaric.gestiondestock.validator;

import fr.eitelalaric.gestiondestock.dto.ProviderDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ProviderValidator {

    public static List<String> validate(ProviderDto providerDto) {
        List<String> errors = new ArrayList<>();

        if(providerDto == null){
            errors.add("Veuillez renseigner le nom du provider");
            errors.add("Veuillez renseigner le prenom du provider");
            errors.add("Veuillez renseigner l'email du provider");
            errors.add("Veuillez renseigner le numero de telephone du provider");
            errors.addAll(AdresseVadator.validate(null));

            return errors;
        }
        if(!StringUtils.hasLength(providerDto.getNom())){
            errors.add("Veuillez renseigner le nom du provider");
        }
        if(!StringUtils.hasLength(providerDto.getPrenom())){
            errors.add("Veuillez renseigner le prenom du provider");
        }
        if(!StringUtils.hasLength(providerDto.getEmail())){
            errors.add("Veuillez renseigner l'email du provider");
        }
        if(!StringUtils.hasLength(providerDto.getNumTel())){
            errors.add("Veuillez renseigner le numero de telephone du provider");
        }
        errors.addAll(AdresseVadator.validate(providerDto.getAdresse()));

        return errors;
    }
}
