package fr.eitelalaric.gestiondestock.dto;

import fr.eitelalaric.gestiondestock.model.LigneVente;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class LigneVenteDto {

    private Integer id;

    private VenteDto vente;

    private ProductDto product;

    private BigDecimal quantite;

    private Integer idCompany;

    public  static LigneVenteDto fromEntity(LigneVente ligneVente) {
        return LigneVenteDto.builder()
                .id(ligneVente.getId())
                .idCompany(ligneVente.getIdCompany())
                .quantite(ligneVente.getQuantite())
                .build();
    }

    public static LigneVente toEntity(LigneVenteDto ligneVenteDto) {
        return LigneVente.builder()
                .idCompany(ligneVenteDto.getIdCompany())
                .quantite(ligneVenteDto.getQuantite())
                .build();
    }
}
