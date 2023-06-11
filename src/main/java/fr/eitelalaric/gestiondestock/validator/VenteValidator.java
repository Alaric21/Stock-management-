package fr.eitelalaric.gestiondestock.validator;

import fr.eitelalaric.gestiondestock.dto.VenteDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class VenteValidator {

    public static List<String> validate(VenteDto venteDto) {
        List<String> errors = new ArrayList<>();
        if (venteDto == null) {
            errors.add("veuillez renseigner la date de la vente");
            errors.add("veuillez renseigner le code de la vente");
            return errors;
        }
        if (!StringUtils.hasLength(venteDto.getCode())){
            errors.add("veuillez renseigner le code de la vente");
        }
        if (venteDto.getDatevente() == null){
            errors.add("veuillez renseigner la date de la vente");
        }

        return errors;
    }
}
