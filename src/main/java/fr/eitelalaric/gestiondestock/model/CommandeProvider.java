package fr.eitelalaric.gestiondestock.model;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name ="commandeProvider")
public class CommandeProvider extends AbstractEntity{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Integer idcommandeprovider;

    @Column(name = "code")
    private String code;

    @Column(name = "idcompany")
    private Integer idCompany;

    @Column(name = "datecommande")
    private Instant dateCommande;

    @ManyToOne
    @JoinColumn(name = "idprovider")
    private Provider provider;

    @OneToMany(mappedBy = "commandeProvider")
    private List<LigneCommandeProvider> ligneCommandeProviders;


}
