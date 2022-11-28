package fr.eitelalaric.gestiondestock.dto;

import fr.eitelalaric.gestiondestock.model.*;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class UserDto {

    private Integer id;

    private String nom;

    private String prenom;

    private Instant dateDeNaissance;

    private String motDePasse;

    private Adresse adresse;

    private String photo;

    private String email;

    private String numTel;

    private CompanyDto company;

    private List<RoleDto> roles;

    private Integer idCompany;

    public  static UserDto fromEntity(User user) {
        return UserDto.builder()
                .id(user.getIduser())
                .prenom(user.getPrenom())
                .nom(user.getNom())
                .email(user.getEmail())
                .numTel(user.getNumTel())
                //.idCompany(user.getIdCompany())
                .dateDeNaissance(user.getDateDeNaissance())
                .motDePasse(user.getMotDePasse())
                .photo(user.getPhoto())
                .adresse(user.getAdresse())
                .build();
    }

    public static User toEntity(UserDto userDto) {
        return User.builder()
                .iduser(userDto.getId())
                .prenom(userDto.getPrenom())
                .nom(userDto.getNom())
                .email(userDto.getEmail())
                .numTel(userDto.getNumTel())
               // .idCompany(userDto.getIdCompany())
                .dateDeNaissance(userDto.getDateDeNaissance())
                .motDePasse(userDto.getMotDePasse())
                .photo(userDto.getPhoto())
                .adresse(userDto.getAdresse())
                .build();
    }
}
