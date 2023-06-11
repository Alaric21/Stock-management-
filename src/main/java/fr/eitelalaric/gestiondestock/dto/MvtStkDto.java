package fr.eitelalaric.gestiondestock.dto;

import fr.eitelalaric.gestiondestock.model.MvtStk;
import fr.eitelalaric.gestiondestock.model.SourceMvtStk;
import fr.eitelalaric.gestiondestock.model.TypeMvtStk;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
public class MvtStkDto {

    private Integer id;

    private ProductDto product;

    private Integer idCompany;

    private BigDecimal quantite;

    private Instant dateMvt;

    private TypeMvtStk typeMvtStk;

    private SourceMvtStk sourceMvtStk;

    public  static MvtStkDto fromEntity(MvtStk mvtStk) {
        return MvtStkDto.builder()
                .id(mvtStk.getId())
                .dateMvt(mvtStk.getDateMvt())
                .sourceMvtStk(mvtStk.getSourceMvtStk())
                .idCompany(mvtStk.getIdCompany())
                .build();
    }

    public static MvtStk toEntity(MvtStkDto mvtStkDto) {
        return MvtStk.builder()
                .sourceMvtStk(mvtStkDto.getSourceMvtStk())
                .idCompany(mvtStkDto.getIdCompany())
                .build();
    }
}
