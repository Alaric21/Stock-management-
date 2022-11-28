package fr.eitelalaric.gestiondestock.dto;

import fr.eitelalaric.gestiondestock.model.MvtStk;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MvtStkDto {

    private Integer id;

    private ProductDto product;

    private Integer idCompany;

    public  static MvtStkDto fromEntity(MvtStk mvtStk) {
        return MvtStkDto.builder()
                .id(mvtStk.getIdmvtstk())
                .idCompany(mvtStk.getIdCompany())
                .build();
    }

    public static MvtStk toEntity(MvtStkDto mvtStkDto) {
        return MvtStk.builder()
                .idmvtstk(mvtStkDto.getId())
                .idCompany(mvtStkDto.getIdCompany())
                .build();
    }
}
