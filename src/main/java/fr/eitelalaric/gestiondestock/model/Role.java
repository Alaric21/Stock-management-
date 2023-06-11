package fr.eitelalaric.gestiondestock.model;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "roles")
public class Role extends AbstractEntity{

    @Column(name = "nom")
    private String nom;

    @Column(name = "idcompany")
    private Integer idCompany;

    @ManyToOne
    @JoinColumn(name = "idemployee",updatable = false,insertable = false)
    private Employee employee;
}
