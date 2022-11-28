package fr.eitelalaric.gestiondestock.service.impl;

import fr.eitelalaric.gestiondestock.dto.CompanyDto;
import fr.eitelalaric.gestiondestock.exception.EntityNotFoundException;
import fr.eitelalaric.gestiondestock.exception.ErrorCodes;
import fr.eitelalaric.gestiondestock.exception.InvalidEntityException;
import fr.eitelalaric.gestiondestock.repository.CompanyRepository;
import fr.eitelalaric.gestiondestock.service.CompanyService;
import fr.eitelalaric.gestiondestock.validator.CompanyValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public CompanyDto save(CompanyDto companyDto) {

        List<String> errors = CompanyValidator.validate(companyDto);
        if (!errors.isEmpty()) {
            log.error("Company not valid: {}", companyDto);
            throw new InvalidEntityException("Company is not valid", ErrorCodes.COMPANY_NOT_VALID,errors);
        }
        return CompanyDto.fromEntity(companyRepository.save(CompanyDto.toEntity(companyDto)));    }

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
