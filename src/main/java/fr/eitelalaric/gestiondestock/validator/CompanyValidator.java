package fr.eitelalaric.gestiondestock.validator;

import fr.eitelalaric.gestiondestock.dto.CompanyDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CompanyValidator {

    public static List<String> validate(CompanyDto companyDto) {
        List<String> errors = new ArrayList<>();

        if(companyDto == null){
            errors.add("Veuillez renseigner le nom de l'company");
            errors.add("Veuillez renseigner le code fiscale de company");
            errors.add("Veuillez renseigner l'email de la company");
            errors.add("Veuillez renseigner le numero de telephone de la company");
            errors.addAll(AdresseVadator.validate(null));
            return errors;
        }
        if (!StringUtils.hasLength(companyDto.getNom())){
            errors.add("Veuillez renseigner le nom de l'company");
        }
        if (!StringUtils.hasLength(companyDto.getCodeFiscale())){
            errors.add("Veuillez renseigner le code fiscale de la company");
        }
        if (!StringUtils.hasLength(companyDto.getEmail())){
            errors.add("Veuillez renseigner l'email de la company");
        }
        if (!StringUtils.hasLength(companyDto.getNumTel())){
            errors.add("Veuillez renseigner le numero de telephone de la company");
        }

        errors.addAll(AdresseVadator.validate(companyDto.getAdresse()));
        return errors;
    }
}
