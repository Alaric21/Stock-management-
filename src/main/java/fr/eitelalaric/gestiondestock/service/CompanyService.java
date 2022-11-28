package fr.eitelalaric.gestiondestock.service;

import fr.eitelalaric.gestiondestock.dto.CompanyDto;

import java.util.List;

public interface CompanyService {

    CompanyDto save (CompanyDto companyDto);

    CompanyDto findById(Integer id);

    List<CompanyDto> findAll();

    void delete(Integer id);
}
