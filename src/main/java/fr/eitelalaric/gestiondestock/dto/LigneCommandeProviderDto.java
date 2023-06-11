package fr.eitelalaric.gestiondestock.dto;

import fr.eitelalaric.gestiondestock.model.CommandeProvider;
import fr.eitelalaric.gestiondestock.model.LigneCommandeProvider;
import fr.eitelalaric.gestiondestock.model.Product;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LigneCommandeProviderDto {

    private Integer id;

    private ProductDto product;

    private CommandeProviderDto commandeProvider;

    private Integer idCompany;

    public  static LigneCommandeProviderDto fromEntity(LigneCommandeProvider ligneCommandeProvider) {
        return LigneCommandeProviderDto.builder()
                .id(ligneCommandeProvider.getId())
                .idCompany(ligneCommandeProvider.getIdCompany())
                .build();
    }

    public static LigneCommandeProvider toEntity(LigneCommandeProviderDto ligneCommandeProviderDto) {
        return LigneCommandeProvider.builder()
                .idCompany(ligneCommandeProviderDto.getIdCompany())
                .build();
    }
}
