package fr.eitelalaric.gestiondestock.service.impl;

import fr.eitelalaric.gestiondestock.dto.CompanyDto;
import fr.eitelalaric.gestiondestock.dto.EmployeeDto;
import fr.eitelalaric.gestiondestock.exception.EntityNotFoundException;
import fr.eitelalaric.gestiondestock.exception.ErrorCodes;
import fr.eitelalaric.gestiondestock.exception.InvalidEntityException;
import fr.eitelalaric.gestiondestock.model.Adresse;
import fr.eitelalaric.gestiondestock.model.Company;
import fr.eitelalaric.gestiondestock.model.Employee;
import fr.eitelalaric.gestiondestock.repository.CompanyRepository;
import fr.eitelalaric.gestiondestock.service.CompanyService;
import fr.eitelalaric.gestiondestock.service.EmployeeService;
import fr.eitelalaric.gestiondestock.utils.PasswordGenerator;
import fr.eitelalaric.gestiondestock.validator.CompanyValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final EmployeeService employeeService;

    public CompanyServiceImpl(CompanyRepository companyRepository, EmployeeService employeeService) {
        this.companyRepository = companyRepository;
        this.employeeService = employeeService;
    }

    @Override
    public EmployeeDto save(CompanyDto companyDto) {

        List<String> errors = CompanyValidator.validate(companyDto);
        if (!errors.isEmpty()) {
            log.error("Company not valid: {}", companyDto);
            throw new InvalidEntityException("Company is not valid", ErrorCodes.COMPANY_NOT_VALID,errors);
        }
        Company saveCompany = companyRepository.save(CompanyDto.toEntity(companyDto));
        return employeeService.save(EmployeeDto.fromEntity(Employee.builder()
                .company(saveCompany)
                .nom(companyDto.getNom())
                .email(companyDto.getEmail())
                .motDePasse("666666")
                .numTel(companyDto.getNumTel())
                .adresse(Adresse.builder()
                        .codePostale(companyDto.getAdresse().getCodePostale())
                        .pays(companyDto.getAdresse().getPays())
                        .adresse2(companyDto.getAdresse().getAdresse2())
                        .adresse1(companyDto.getAdresse().getAdresse1())
                        .ville(companyDto.getAdresse().getVille())
                        .build())
                .build()));
    }

    @Override
    public CompanyDto findById(Integer id) {
        if (id==null){
            log.error("Company ID is null");
            return null;
        }
        return companyRepository.findById(id).map(CompanyDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("Company with ID "+id+"doesnt exist", ErrorCodes.COMPANY_NOT_FOUND));
    }

    @Override
    public List<CompanyDto> findAll() {
        return companyRepository.findAll().stream()
                .map(CompanyDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id==null) {
            log.error("Company ID is null");
            return;
        }
        companyRepository.deleteById(id);
    }
}
