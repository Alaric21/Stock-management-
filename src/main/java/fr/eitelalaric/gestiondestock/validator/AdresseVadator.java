package fr.eitelalaric.gestiondestock.validator;

import fr.eitelalaric.gestiondestock.dto.AdresseDto;
import fr.eitelalaric.gestiondestock.dto.CategoryDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author eitel.tchuenkam
 */
public class AdresseVadator {
    public static List<String> validate(AdresseDto adresseDto) {
        List<String> errors = new ArrayList<>();

        if(adresseDto == null ){
            errors.add("Veuillez renseigner l'adresse 1");
            errors.add("Veuillez renseigner la ville");
            errors.add("Veuillez renseigner le pays");
            errors.add("Veuillez renseigner le code postal");
            return errors;
        }

        if (!StringUtils.hasLength(adresseDto.getAdresse1())){
            errors.add("Veuillez renseigner l'adresse 1");
        }
        if (!StringUtils.hasLength(adresseDto.getVille())){
            errors.add("Veuillez renseigner la ville");
        }
        if (!StringUtils.hasLength(adresseDto.getPays())){
            errors.add("Veuillez renseigner le pays");
        }
        if (!StringUtils.hasLength(adresseDto.getCodePostale())){
            errors.add("Veuillez renseigner le code postal");
        }
        return errors;
    }
}
