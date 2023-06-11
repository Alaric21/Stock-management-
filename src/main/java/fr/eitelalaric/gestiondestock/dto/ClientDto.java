package fr.eitelalaric.gestiondestock.dto;

import fr.eitelalaric.gestiondestock.model.Adresse;
import fr.eitelalaric.gestiondestock.model.Client;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ClientDto {

    private Integer id;

    private String nom;

    private String prenom;

    private AdresseDto adresse;

    private String photo;

    private String email;

    private Integer idCompany;

    private String numTel;

    private List<CommandeClientDto> commandeClients;

    public  static ClientDto fromEntity(Client client) {
        return ClientDto.builder()
                .id(client.getId())
                .nom(client.getNom())
                .idCompany(client.getIdCompany())
                .prenom(client.getPrenom())
                .email(client.getEmail())
                .photo(client.getPhoto())
                .numTel(client.getNumTel())
                .build();
    }

    public static Client toEntity(ClientDto clientDto) {
        return Client.builder()
                .idCompany(clientDto.getIdCompany())
                .photo(clientDto.getPhoto())
                .prenom(clientDto.getPrenom())
                .nom(clientDto.getNom())
                .email(clientDto.getEmail())
                .numTel(clientDto.getNumTel())
                .build();
    }
}
