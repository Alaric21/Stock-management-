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
@Table(name = "users")
public class User extends AbstractEntity{

    @Id
    @GeneratedValue
    public Integer iduser;

    @Column(name = "nom")
    private String nom;

    /*@Column(name = "idcompany")
    private Integer idCompany;
*/
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

    @ManyToOne
    @JoinColumn(name = "idcompany")
    private Company company;

    @OneToMany(mappedBy = "user")
    private List<Role> roles;
}
