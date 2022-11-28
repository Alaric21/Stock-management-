package fr.eitelalaric.gestiondestock.dto;

import fr.eitelalaric.gestiondestock.model.CommandeProvider;
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

    private Provider provider;

    private Integer idCompany;

    private List<LigneCommandeProviderDto> ligneCommandeProviders;

    public  static CommandeProviderDto fromEntity(CommandeProvider commandeProvider) {
        return CommandeProviderDto.builder()
                .id(commandeProvider.getIdcommandeprovider())
                .dateCommande(commandeProvider.getDateCommande())
                .code(commandeProvider.getCode())
                .idCompany(commandeProvider.getIdCompany())
                .provider(commandeProvider.getProvider())
                .build();
    }

    public static CommandeProvider toEntity(CommandeProviderDto commandeProviderDto) {
        return CommandeProvider.builder()
                .idcommandeprovider(commandeProviderDto.getId())
                .idCompany(commandeProviderDto.getIdCompany())
                .code(commandeProviderDto.getCode())
                .dateCommande(commandeProviderDto.getDateCommande())
                .provider(commandeProviderDto.getProvider())
                .build();
    }
}
