package fr.eitelalaric.gestiondestock.dto;

import fr.eitelalaric.gestiondestock.model.Client;
import fr.eitelalaric.gestiondestock.model.CommandeClient;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class CommandeClientDto {

    private Integer id;

    private String code;

    private Instant dateCommande;

    private ClientDto client;

    private Integer idCompany;

    private List<LigneCommandeClientDto> ligneCommandeClients;

    public  static CommandeClientDto fromEntity(CommandeClient commandeClient) {
        return CommandeClientDto.builder()
                .id(commandeClient.getIdcommandeclient())
                .code(commandeClient.getCode())
                .dateCommande(commandeClient.getDateCommande())
                .idCompany(commandeClient.getIdCompany())
                .build();
    }

    public static CommandeClient toEntity(CommandeClientDto commandeClientDto) {
        return CommandeClient.builder()
                .idcommandeclient(commandeClientDto.getId())
                .code(commandeClientDto.getCode())
                .dateCommande(commandeClientDto.getDateCommande())
                .idCompany(commandeClientDto.getIdCompany())
                .build();
    }
}
