package fr.eitelalaric.gestiondestock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.eitelalaric.gestiondestock.model.CommandeProvider;
import fr.eitelalaric.gestiondestock.model.EtatCommande;
import fr.eitelalaric.gestiondestock.model.Provider;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class CommandeProviderDto {

    private Integer id;

    private String code;

    private Instant dateCommande;

    private EtatCommande etatCommande;

    private Provider provider;

    private Integer idCompany;

    @JsonIgnore
    private List<LigneCommandeProviderDto> ligneCommandeProviders;

    public  static CommandeProviderDto fromEntity(CommandeProvider commandeProvider) {
        return CommandeProviderDto.builder()
                .id(commandeProvider.getId())
                .dateCommande(commandeProvider.getDateCommande())
                .code(commandeProvider.getCode())
                .idCompany(commandeProvider.getIdCompany())
                .provider(commandeProvider.getProvider())
                .build();
    }

    public static CommandeProvider toEntity(CommandeProviderDto commandeProviderDto) {
        return CommandeProvider.builder()
                .idCompany(commandeProviderDto.getIdCompany())
                .code(commandeProviderDto.getCode())
                .dateCommande(commandeProviderDto.getDateCommande())
                .provider(commandeProviderDto.getProvider())
                .build();
    }
}
