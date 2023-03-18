package fr.eitelalaric.gestiondestock.service;

import fr.eitelalaric.gestiondestock.dto.RoleDto;

import java.util.List;

public interface RoleService {

    RoleDto save (RoleDto roleDto);

    RoleDto findById(Integer id);

    List<RoleDto> findAll();

    void delete(Integer id);
}
