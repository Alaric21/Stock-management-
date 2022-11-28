package fr.eitelalaric.gestiondestock.validator;

import fr.eitelalaric.gestiondestock.dto.CommandeClientDto;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CommandeClientValidator {
    public static List<String> validate(CommandeClientDto commandeClientDto) {
        List<String> errors = new ArrayList<>();
        if (commandeClientDto == null) {
            log.error("Client command can't be null");

        }

        //TODO
        return errors;
    }
}
