package fr.eitelalaric.gestiondestock.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "fournisseur")
public class Provider extends AbstractEntity{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Integer idprovider;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "idcompany")
    private Integer idCompany;

    //address is embeddable
    @Embedded
    private Adresse adresse;

    @Column(name = "photo")
    private String photo;

    @Column(name = "email")
    private String email;

    @Column(name = "numTel")
    private String numTel;

    @OneToMany(mappedBy = "provider")
    private List<CommandeProvider> commandeProviders;
}
