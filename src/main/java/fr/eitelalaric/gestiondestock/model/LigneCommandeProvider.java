package fr.eitelalaric.gestiondestock.model;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "lignecommandeProvider")
public class LigneCommandeProvider extends AbstractEntity{

    @Id
    @GeneratedValue
    public Integer idlignecommandeprovider;

    @ManyToOne
    @JoinColumn(name = "idarticle")
    private Product product;

    @Column(name = "idcompany")
    private Integer idCompany;

    @ManyToOne
    @JoinColumn(name = "idcommandeProvider")
    private CommandeProvider commandeProvider;
}
