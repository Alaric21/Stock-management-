package fr.eitelalaric.gestiondestock.validator;

import fr.eitelalaric.gestiondestock.dto.EmployeeDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class EmployeeValidator {

    public static List<String> validate(EmployeeDto employeeDto) {
        List<String> errors = new ArrayList<>();

        if (employeeDto ==null) {
            errors.add("Veuillez renseigner le nom de l'employee ");
            //errors.add("Veuillez renseigner le prenom de l'employee ");
            errors.add("Veuillez renseigner l'email de l'employee ");
            errors.add("Veuillez renseigner le mot de passe de l'employee ");
            errors.add("Veuillez renseigner l'adresse de l'employee ");
            return errors;
        }
        if(!StringUtils.hasLength(employeeDto.getNom())){
            errors.add("Veuillez renseigner le nom de l'employee ");
        }
        if(!StringUtils.hasLength(employeeDto.getEmail())){
            errors.add("Veuillez renseigner l'email de l'employee ");
        }
        if(!StringUtils.hasLength(employeeDto.getMotDePasse())){
            errors.add("Veuillez renseigner le mot de passe de l'employee ");
        }
        /*if(!StringUtils.hasLength(employeeDto.getNom())){
            errors.add("Veuillez renseigner le nom de l'employee ");
        }
        if(employeeDto.getDateDeNaissance()==null){
            errors.add("Veuillez renseigner la date de naissance de l'employee ");
        }*/
        if(employeeDto.getAdresse()== null){
            errors.add("Veuillez renseigner l'adresse de l'employee ");
        }else {
            if(!StringUtils.hasLength(employeeDto.getAdresse().getAdresse1())){
                errors.add("Le champ 'adresse1' est obligatoir ");
            }
            if(!StringUtils.hasLength(employeeDto.getAdresse().getVille())){
                errors.add("Le champ 'ville' est obligatoir ");
            }
            if(!StringUtils.hasLength(employeeDto.getAdresse().getCodePostale())){
                errors.add("Le champ 'code postale' est obligatoir ");
            }
            if(!StringUtils.hasLength(employeeDto.getAdresse().getPays())){
                errors.add("Le champ 'pays' est obligatoir ");
            }

        }

        return errors;
    }
}
