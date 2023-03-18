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
@Table(name = "category")
public class Category extends  AbstractEntity{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Integer idcategory;

    @Column(name = "code")
    private String code;

    @Column(name = "designation")
    private String designation;

    @Column(name = "idcompany")
    private Integer idCompany;

    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
