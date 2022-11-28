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

            return errors;
        }
        if (!StringUtils.hasLength(companyDto.getNom())){
            errors.add("Veuillez renseigner le nom de l'company");
        }
        return errors;
    }
}
