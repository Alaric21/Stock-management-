package fr.eitelalaric.gestiondestock.validator;

import fr.eitelalaric.gestiondestock.dto.MvtStkDto;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MvtStkValidator {
    public static List<String> validate(MvtStkDto mvtStkDto) {
        List<String> errors = new ArrayList<>();

        if (mvtStkDto == null){
            errors.add("Veuillez renseigner la date du mouvement ");
            errors.add("Veuillez renseigner la quantite ");
            errors.add("Veuillez renseigner le product ");
            errors.add("Veuillez renseigner le type du mouvement");

            return errors;
        }
        if (mvtStkDto.getDateMvt() == null) {
            errors.add("Veuillez renseigner la date du mouvement ");
        }

        if (mvtStkDto.getQuantite() == null || mvtStkDto.getQuantite().compareTo(BigDecimal.ZERO) == 0) {
            errors.add("Veuillez renseigner la quantite ");
        }
        if (mvtStkDto.getProduct() == null || mvtStkDto.getProduct().getId() == null) {
            errors.add("Veuillez renseigner le product ");
        }

        if (!StringUtils.hasLength(mvtStkDto.getTypeMvtStk().name())){
            errors.add("Veuillez renseigner le type du mouvement");
        }
        return errors;
    }
}
