package fr.eitelalaric.gestiondestock.model;

import fr.eitelalaric.gestiondestock.dto.LigneVenteDto;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "vente")
public class Vente extends AbstractEntity{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Integer idvente;

    @Column(name = "idcompany")
    private Integer idCompany;

    @Column(name = "code")
    private String code;

    @Column(name ="comment")
    private String comment;

    @OneToMany(mappedBy = "vente")
    private List<LigneVente> ligneVentes;

}
