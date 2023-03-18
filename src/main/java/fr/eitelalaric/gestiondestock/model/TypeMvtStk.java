package fr.eitelalaric.gestiondestock.model;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "typeMvtStk")
public class TypeMvtStk extends AbstractEntity{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Integer idtypemvtstk;

    @Column(name = "idcompany")
    private Integer idCompany;

    @Column(name = "nom")
    private String nom;
}
