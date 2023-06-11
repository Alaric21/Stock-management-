package fr.eitelalaric.gestiondestock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.eitelalaric.gestiondestock.model.*;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class EmployeeDto {

    private Integer id;

    private String nom;

    private String prenom;

    private Instant dateDeNaissance;

    private String motDePasse;

    private AdresseDto adresse;

    private String photo;

    private String email;

    private String numTel;

    @JsonIgnore
    private CompanyDto company;

    private List<RoleDto> roles;

    public  static EmployeeDto fromEntity(Employee employee) {
        return EmployeeDto.builder()
                .id(employee.getId())
                .prenom(employee.getPrenom())
                .nom(employee.getNom())
                .email(employee.getEmail())
                .numTel(employee.getNumTel())
                .dateDeNaissance(employee.getDateDeNaissance())
                .motDePasse(employee.getMotDePasse())
                .photo(employee.getPhoto())
                .adresse(AdresseDto.fromEntity(employee.getAdresse()))
                .company(CompanyDto.fromEntity(employee.getCompany()))
                .build();
    }

    public static Employee toEntity(EmployeeDto employeeDto) {
        return Employee.builder()
                .prenom(employeeDto.getPrenom())
                .nom(employeeDto.getNom())
                .email(employeeDto.getEmail())
                .numTel(employeeDto.getNumTel())
                .dateDeNaissance(employeeDto.getDateDeNaissance())
                .motDePasse(employeeDto.getMotDePasse())
                .photo(employeeDto.getPhoto())
                .adresse(AdresseDto.toEntity(employeeDto.getAdresse()))
                .company(CompanyDto.toEntity(employeeDto.getCompany()))
                .build();
    }
}
