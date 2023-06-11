package fr.eitelalaric.gestiondestock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.eitelalaric.gestiondestock.model.Client;
import fr.eitelalaric.gestiondestock.model.CommandeClient;
import fr.eitelalaric.gestiondestock.model.EtatCommande;
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

    private EtatCommande etatCommande;

    private ClientDto client;

    private Integer idCompany;

    @JsonIgnore
    private List<LigneCommandeClientDto> ligneCommandeClients;

    public  static CommandeClientDto fromEntity(CommandeClient commandeClient) {
        return CommandeClientDto.builder()
                .id(commandeClient.getId())
                .code(commandeClient.getCode())
                .dateCommande(commandeClient.getDateCommande())
                .idCompany(commandeClient.getIdCompany())
                .etatCommande(commandeClient.getEtatCommande())
                .build();
    }

    public static CommandeClient toEntity(CommandeClientDto commandeClientDto) {

        CommandeClient commandeClient = new CommandeClient();
        commandeClient.setId(commandeClientDto.getId());
        commandeClient.setCode(commandeClientDto.getCode());
        commandeClient.setIdCompany(commandeClientDto.getIdCompany());
        commandeClient.setEtatCommande(commandeClientDto.getEtatCommande());
        return commandeClient;
    }

    public boolean isCommandeLivree() {
        return EtatCommande.LIVREE.equals(this.etatCommande);
    }
}
