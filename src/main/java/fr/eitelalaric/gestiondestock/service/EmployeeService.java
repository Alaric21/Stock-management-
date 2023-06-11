package fr.eitelalaric.gestiondestock.service;

import fr.eitelalaric.gestiondestock.dto.ChangerMotDePasseUtilisateurDto;
import fr.eitelalaric.gestiondestock.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    EmployeeDto save (EmployeeDto employeeDto);

    EmployeeDto findById(Integer id);

    List<EmployeeDto> findAll();

    void delete(Integer id);

    EmployeeDto findByEmail(String email);

    EmployeeDto changerMotDePasseEmployee(ChangerMotDePasseUtilisateurDto changerMotDePasseUtilisateurDto);
}
