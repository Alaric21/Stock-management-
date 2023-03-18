package fr.eitelalaric.gestiondestock.dto;

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

    private Adresse adresse;

    private String photo;

    private String email;

    private String numTel;

    private CompanyDto company;

    private List<RoleDto> roles;

    private Integer idCompany;

    public  static EmployeeDto fromEntity(Employee employee) {
        return EmployeeDto.builder()
                .id(employee.getIdemployee())
                .prenom(employee.getPrenom())
                .nom(employee.getNom())
                .email(employee.getEmail())
                .numTel(employee.getNumTel())
                //.idCompany(employee.getIdCompany())
                .dateDeNaissance(employee.getDateDeNaissance())
                .motDePasse(employee.getMotDePasse())
                .photo(employee.getPhoto())
                .adresse(employee.getAdresse())
                .build();
    }

    public static Employee toEntity(EmployeeDto employeeDto) {
        return Employee.builder()
                .idemployee(employeeDto.getId())
                .prenom(employeeDto.getPrenom())
                .nom(employeeDto.getNom())
                .email(employeeDto.getEmail())
                .numTel(employeeDto.getNumTel())
               // .idCompany(employeeDto.getIdCompany())
                .dateDeNaissance(employeeDto.getDateDeNaissance())
                .motDePasse(employeeDto.getMotDePasse())
                .photo(employeeDto.getPhoto())
                .adresse(employeeDto.getAdresse())
                .build();
    }
}
