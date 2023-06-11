package fr.eitelalaric.gestiondestock.dto;

import fr.eitelalaric.gestiondestock.model.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleDto {

    private Integer id;

    private EmployeeDto user;

    private Integer idCompany;

    public  static RoleDto fromEntity(Role role) {
        return RoleDto.builder()
                .id(role.getId())
                .idCompany(role.getIdCompany())
                .build();
    }

    public static Role toEntity(RoleDto roleDto) {
        return Role.builder()
                .idCompany(roleDto.getIdCompany())
                .build();
    }
}
