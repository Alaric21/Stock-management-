package fr.eitelalaric.gestiondestock.model;

import lombok.*;
import org.springframework.context.annotation.EnableMBeanExport;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "lignevente")
public class LigneVente extends AbstractEntity{

    @Id
    @GeneratedValue
    public Integer idlignevente;

    @Column(name = "idcompany")
    private Integer idCompany;

    @ManyToOne
    @JoinColumn(name = "idvente")
    private Vente vente;

    @ManyToOne
    @JoinColumn(name = "idproduct")
    private Product product;

    private BigDecimal quantite;
}
