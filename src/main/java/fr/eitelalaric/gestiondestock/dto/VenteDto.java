package fr.eitelalaric.gestiondestock.dto;

import fr.eitelalaric.gestiondestock.model.Vente;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class VenteDto {

    private Integer id;

    private String code;

    private Instant datevente;

    private Integer idCompany;

    private String comment;

    private List<LigneVenteDto> ligneVentes;

    public  static VenteDto fromEntity(Vente vente) {
        return VenteDto.builder()
                .id(vente.getId())
                .idCompany(vente.getIdCompany())
                .code(vente.getCode())
                .build();
    }

    public static Vente toEntity(VenteDto venteDto) {
        return Vente.builder()
                .idCompany(venteDto.getIdCompany())
                .code(venteDto.getCode())
                .build();
    }
}
