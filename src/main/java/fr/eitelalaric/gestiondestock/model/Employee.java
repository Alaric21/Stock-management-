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
@Table(name = "employees")
public class Employee extends AbstractEntity{

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "datedenaissance")
    private Instant dateDeNaissance;

    @Column(name = "motdepasse")
    private String motDePasse;

    //adresse is embeddable
    @Embedded
    private Adresse adresse;

    @Column(name = "photo")
    private String photo;

    @Column(name = "email")
    private String email;

    @Column(name = "numTel")
    private String numTel;

    @ManyToOne(cascade = CascadeType.ALL )
    @JoinColumn(name = "idcompany" )
    private Company company;

    @OneToMany(mappedBy = "employee")
    private List<Role> roles;
}
