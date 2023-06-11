package fr.eitelalaric.gestiondestock.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author eitel.tchuenkam
 */
@Builder
@Data
public class ChangerMotDePasseUtilisateurDto {

    private Integer id;

    private String email;

    private String motDePasse;

    private String confirmMotDePasse;
}
