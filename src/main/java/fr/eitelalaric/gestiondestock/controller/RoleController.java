package fr.eitelalaric.gestiondestock.controller;

import fr.eitelalaric.gestiondestock.controller.api.RoleApi;
import fr.eitelalaric.gestiondestock.dto.CategoryDto;
import fr.eitelalaric.gestiondestock.dto.RoleDto;
import fr.eitelalaric.gestiondestock.service.CategoryService;
import fr.eitelalaric.gestiondestock.service.RoleService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class RoleController implements RoleApi {

    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public RoleDto save(RoleDto roleDto) {
        return roleService.save(roleDto);
    }

    @Override
    public RoleDto findById(Integer idrole) {
        return roleService.findById(idrole) ;
    }


    @Override
    public List<RoleDto> findAll() {
        return roleService.findAll();
    }

    @Override
    public void delete(Integer id) {
        roleService.delete(id);
    }
}
