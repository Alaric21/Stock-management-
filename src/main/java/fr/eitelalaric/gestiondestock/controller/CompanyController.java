package fr.eitelalaric.gestiondestock.controller;

import fr.eitelalaric.gestiondestock.controller.api.CompanyApi;
import fr.eitelalaric.gestiondestock.dto.CompanyDto;
import fr.eitelalaric.gestiondestock.service.CompanyService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class CompanyController implements CompanyApi {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }


    @Override
    public CompanyDto save(CompanyDto companyDto) {
        return companyService.save(companyDto);
    }

    @Override
    public CompanyDto findById(Integer idcompany) {
        return companyService.findById(idcompany);
    }

    @Override
    public List<CompanyDto> findAll() {
        return companyService.findAll();
    }

    @Override
    public void delete(Integer id) {
        companyService.delete(id);
    }
}
