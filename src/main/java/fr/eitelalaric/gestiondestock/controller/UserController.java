package fr.eitelalaric.gestiondestock.controller;

import fr.eitelalaric.gestiondestock.controller.api.UserApi;
import fr.eitelalaric.gestiondestock.dto.EmployeeDto;
import fr.eitelalaric.gestiondestock.service.EmployeeService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class UserController implements UserApi {

    private final EmployeeService employeeService;

    public UserController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public EmployeeDto save(EmployeeDto employeeDto) {
        return employeeService.save(employeeDto);
    }

    @Override
    public EmployeeDto findById(Integer iduser) {
        return employeeService.findById(iduser) ;
    }

    @Override
    public List<EmployeeDto> findAll() {
        return employeeService.findAll();
    }

    @Override
    public void delete(Integer id) {
        employeeService.delete(id);
    }
}
