package fr.eitelalaric.gestiondestock.validator;

import fr.eitelalaric.gestiondestock.dto.ProductDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ProductValidator {

    public static List<String> validate(ProductDto productDto) {
        List<String> errors = new ArrayList<>();

        if(productDto ==null){
            errors.add("Veuillez renseigner le code de l'product");
            errors.add("Veuillez renseigner la designation de l'product");
            errors.add("Veuillez renseigner le prix unitaire hors tax de l'product");
            errors.add("Veuillez renseigner le prix unitaire TTC de l'product");
            errors.add("Veuillez selectioner une categorie");
        }

        if(!StringUtils.hasLength(productDto.getProductCode())){
            errors.add("Veuillez renseigner le code de l'product");
        }
        if(!StringUtils.hasLength(productDto.getDesignation())){
            errors.add("Veuillez renseigner la designation de l'product");
        }
        if(productDto.getPrixUnitaireHt()==null){
            errors.add("Veuillez renseigner le prix unitaire hors tax de l'product");
        }
        if(productDto.getTauxTva()==null){
            errors.add("Veuillez renseigner le taux TVA de l'product");
        }
        if(productDto.getPrixUnitaireTtc()==null){
            errors.add("Veuillez renseigner le prix unitaire TTC de l'product");
        }
        if(productDto.getCategory().getId()==null){
            errors.add("Veuillez selectioner une categorie");
        }
        return errors;
    }

}
