package fr.eitelalaric.gestiondestock.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "lignecommandeProvider")
public class LigneCommandeProvider extends AbstractEntity{

    @JoinColumn(name = "quantite")
    private BigDecimal quantite;

    @ManyToOne
    @JoinColumn(name = "idproduct")
    private Product product;

    @Column(name = "idcompany")
    private Integer idCompany;

    @ManyToOne
    @JoinColumn(name = "idcommandeProvider")
    private CommandeProvider commandeProvider;
}
