package fr.eitelalaric.gestiondestock.validator;

import fr.eitelalaric.gestiondestock.dto.ProviderDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ProviderValidator {

    public static List<String> validate(ProviderDto providerDto) {
        List<String> errors = new ArrayList<>();

        if(providerDto == null){
            errors.add("Veuillez renseigner le nom du client");
            errors.add("Veuillez renseigner le prenom du client");
            errors.add("Veuillez renseigner l'email du client");
            errors.add("Veuillez renseigner le numero de telephone du client");
            return errors;
        }
        if(!StringUtils.hasLength(providerDto.getNom())){
            errors.add("Veuillez renseigner le nom du client");
        }
        if(!StringUtils.hasLength(providerDto.getPrenom())){
            errors.add("Veuillez renseigner le prenom du client");
        }
        if(!StringUtils.hasLength(providerDto.getEmail())){
            errors.add("Veuillez renseigner l'email du client");
        }
        if(!StringUtils.hasLength(providerDto.getNumTel())){
            errors.add("Veuillez renseigner le numero de telephone du client");
        }

        return errors;
    }
}
