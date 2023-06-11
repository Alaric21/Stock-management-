package fr.eitelalaric.gestiondestock.dto;

import fr.eitelalaric.gestiondestock.model.CommandeClient;
import fr.eitelalaric.gestiondestock.model.LigneCommandeClient;
import fr.eitelalaric.gestiondestock.model.Product;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class LigneCommandeClientDto {

    private Integer id;

    private ProductDto product;

    private BigDecimal quantite;

    private CommandeClientDto commandeClient;

    private Integer idCompany;

    public  static LigneCommandeClientDto fromEntity(LigneCommandeClient ligneCommandeClient) {
        return LigneCommandeClientDto.builder()
                .quantite(ligneCommandeClient.getQuantite())
                .id(ligneCommandeClient.getId())
                .idCompany(ligneCommandeClient.getIdCompany())
                .build();
    }

    public static LigneCommandeClient toEntity(LigneCommandeClientDto ligneCommandeClientDto) {
        return LigneCommandeClient.builder()
                .quantite(ligneCommandeClientDto.getQuantite())
                .idCompany(ligneCommandeClientDto.getIdCompany())
                .build();
    }
}
