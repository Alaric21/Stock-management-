package fr.eitelalaric.gestiondestock.dto;

import fr.eitelalaric.gestiondestock.model.TypeMvtStk;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TypeMvtStkDto {

    private Integer id;

    private String nom;

    private Integer idCompany;

    public  static TypeMvtStkDto fromEntity(TypeMvtStk typeMvtStk) {
        return TypeMvtStkDto.builder()
                .id(typeMvtStk.getIdtypemvtstk())
                .idCompany(typeMvtStk.getIdCompany())
                .nom(typeMvtStk.getNom())
                .build();
    }

    public static TypeMvtStk toEntity(TypeMvtStkDto typeMvtStkDto) {
        return TypeMvtStk.builder()
                .idtypemvtstk(typeMvtStkDto.getId())
                .idCompany(typeMvtStkDto.getIdCompany())
                .nom(typeMvtStkDto.getNom())
                .build();
    }
}
