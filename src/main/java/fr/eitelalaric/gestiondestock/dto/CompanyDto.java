package fr.eitelalaric.gestiondestock.dto;

import fr.eitelalaric.gestiondestock.model.Adresse;
import fr.eitelalaric.gestiondestock.model.Company;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CompanyDto {

    private Integer id;

    private String nom;

    private String description;

    private AdresseDto adresse;

    private String codeFiscale;

    private String email;

    private String numTel;

    private String steWeb;

    private String photo;

    public static CompanyDto fromEntity(Company company) {
        return CompanyDto.builder()
                .id(company.getId())
                .nom(company.getNom())
                .numTel(company.getNumTel())
                .email(company.getEmail())
                .codeFiscale(company.getCodeFiscale())
                .description(company.getDescription())
                .adresse(AdresseDto.fromEntity(company.getAdresse()))
                .steWeb(company.getSteWeb())
                .build();
    }

    public static Company toEntity(CompanyDto companyDto){
        return Company.builder()
                .codeFiscale(companyDto.getCodeFiscale())
                .steWeb(companyDto.getSteWeb())
                .nom(companyDto.getNom())
                .email(companyDto.getEmail())
                .adresse(AdresseDto.toEntity(companyDto.getAdresse()))
                .numTel(companyDto.getNumTel())
                .description(companyDto.getDescription())
                .codeFiscale(companyDto.getCodeFiscale())
                .build();
    }
}
