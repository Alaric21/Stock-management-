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
            errors.add("Veuillez renseigner le prenom de l'employee ");
            errors.add("Veuillez renseigner l'email de l'employee ");
            errors.add("Veuillez renseigner le mot de passe de l'employee ");
            errors.add("Veuillez renseigner l'adresse de l'employee ");
            //errors.add("Veuillez renseigner la date de naissance ");
            errors.addAll(AdresseVadator.validate(null));
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
       /* if(!StringUtils.hasLength(employeeDto.getDateDeNaissance().toString())){
            errors.add("Veuillez renseigner la date de naissance ");
        }
*/
        errors.addAll(AdresseVadator.validate(employeeDto.getAdresse()));

        return errors;
    }
}
