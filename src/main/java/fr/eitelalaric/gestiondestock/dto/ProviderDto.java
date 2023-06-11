package fr.eitelalaric.gestiondestock.dto;

import fr.eitelalaric.gestiondestock.model.Adresse;
import fr.eitelalaric.gestiondestock.model.Provider;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProviderDto {

    private Integer id;

    private String nom;

    private String prenom;

    private AdresseDto adresse;

    private String photo;

    private String email;

    private String numTel;

    private Integer idCompany;

    private List<CommandeProviderDto> commandeProviders;

    public  static ProviderDto fromEntity(Provider provider) {
        return fr.eitelalaric.gestiondestock.dto.ProviderDto.builder()
                .id(provider.getId())
                .nom(provider.getNom())
                .idCompany(provider.getIdCompany())
                .prenom(provider.getPrenom())
                .email(provider.getEmail())
                .photo(provider.getPhoto())
                .numTel(provider.getNumTel())
                .build();
    }

    public static Provider toEntity(ProviderDto providerDto) {
        return Provider.builder()
                .idCompany(providerDto.getIdCompany())
                .photo(providerDto.getPhoto())
                .prenom(providerDto.getPrenom())
                .nom(providerDto.getNom())
                .email(providerDto.getEmail())
                .numTel(providerDto.getNumTel())
                .build();
    }
}
