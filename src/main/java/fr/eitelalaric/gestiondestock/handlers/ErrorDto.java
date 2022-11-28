package fr.eitelalaric.gestiondestock.handlers;

import fr.eitelalaric.gestiondestock.exception.ErrorCodes;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
@Builder
public class ErrorDto {

    private Integer httpCode;

    private ErrorCodes code;

    private String message;

    private List<String> errors;
}
