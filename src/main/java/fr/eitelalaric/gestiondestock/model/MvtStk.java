package fr.eitelalaric.gestiondestock.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "mvtstk")
public class MvtStk extends AbstractEntity{

    @Column(name = "datemvt")
    private Instant dateMvt;

    @Column(name = "quantite")
    private BigDecimal quantite;

    @Column(name = "typemvtstk")
    private TypeMvtStk typeMvtStk;

    @Column(name = "sourcemvt")
    private SourceMvtStk sourceMvtStk;

    @Column(name = "idcompany")
    private Integer idCompany;

    @ManyToOne
    @JoinColumn(name = "idproduct")
    private Product product;

}
