package fr.eitelalaric.gestiondestock.validator;

import fr.eitelalaric.gestiondestock.dto.UserDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class UserValidator {

    public static List<String> validate(UserDto userDto) {
        List<String> errors = new ArrayList<>();

        if (userDto ==null) {
            errors.add("Veuillez renseigner le nom de l'user ");
            errors.add("Veuillez renseigner le prenom de l'user ");
            errors.add("Veuillez renseigner l'email de l'user ");
            errors.add("Veuillez renseigner le mot de passe de l'user ");
            errors.add("Veuillez renseigner l'adresse de l'user ");
            return errors;
        }
        if(!StringUtils.hasLength(userDto.getNom())){
            errors.add("Veuillez renseigner le nom de l'user ");
        }
        if(!StringUtils.hasLength(userDto.getEmail())){
            errors.add("Veuillez renseigner l'email de l'user ");
        }
        if(!StringUtils.hasLength(userDto.getMotDePasse())){
            errors.add("Veuillez renseigner le mot de passe de l'user ");
        }
        if(!StringUtils.hasLength(userDto.getPrenom())){
            errors.add("Veuillez renseigner le prenom de l'user ");
        }
        if(userDto.getDateDeNaissance()==null){
            errors.add("Veuillez renseigner la date de naissance de l'user ");
        }
        if(userDto.getAdresse()== null){
            errors.add("Veuillez renseigner l'adresse de l'user ");
        }else {
            if(StringUtils.hasLength(userDto.getAdresse().getAdresse1())){
                errors.add("Le champ 'adresse1' est obligatoir ");
            }
            if(StringUtils.hasLength(userDto.getAdresse().getVille())){
                errors.add("Le champ 'ville' est obligatoir ");
            }
            if(StringUtils.hasLength(userDto.getAdresse().getCodePostale())){
                errors.add("Le champ 'code postale' est obligatoir ");
            }
            if(StringUtils.hasLength(userDto.getAdresse().getPays())){
                errors.add("Le champ 'pays' est obligatoir ");
            }

        }

        return errors;
    }
}
