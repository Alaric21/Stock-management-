package fr.eitelalaric.gestiondestock.dto;

import fr.eitelalaric.gestiondestock.model.CommandeClient;
import fr.eitelalaric.gestiondestock.model.LigneCommandeClient;
import fr.eitelalaric.gestiondestock.model.Product;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LigneCommandeClientDto {

    private Integer id;

    private ProductDto product;

    private CommandeClientDto commandeClient;

    private Integer idCompany;

    public  static LigneCommandeClientDto fromEntity(LigneCommandeClient ligneCommandeClient) {
        return LigneCommandeClientDto.builder()
                .id(ligneCommandeClient.getIdlignecommandeclient())
                .idCompany(ligneCommandeClient.getIdCompany())
                .build();
    }

    public static LigneCommandeClient toEntity(LigneCommandeClientDto ligneCommandeClientDto) {
        return LigneCommandeClient.builder()
                .idlignecommandeclient(ligneCommandeClientDto.getId())
                .idCompany(ligneCommandeClientDto.getIdCompany())
                .build();
    }
}
