package fr.eitelalaric.gestiondestock.service.impl;

import fr.eitelalaric.gestiondestock.dto.EmployeeDto;
import fr.eitelalaric.gestiondestock.exception.EntityNotFoundException;
import fr.eitelalaric.gestiondestock.exception.ErrorCodes;
import fr.eitelalaric.gestiondestock.exception.InvalidEntityException;
import fr.eitelalaric.gestiondestock.repository.EmployeeRepository;
import fr.eitelalaric.gestiondestock.service.EmployeeService;
import fr.eitelalaric.gestiondestock.validator.EmployeeValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.employeeRepository = employeeRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public EmployeeDto save(EmployeeDto employeeDto) {
        List<String> errors = EmployeeValidator.validate(employeeDto);
        if (!errors.isEmpty()) {
            log.error("Employee not valid: {}", employeeDto);
            throw new InvalidEntityException("Employee is not valid", ErrorCodes.USER_NOT_VALID,errors);
        }
        employeeDto.setMotDePasse(bCryptPasswordEncoder.encode(employeeDto.getMotDePasse()));
        return EmployeeDto.fromEntity(employeeRepository.save(EmployeeDto.toEntity(employeeDto)));
    }

    @Override
    public EmployeeDto findById(Integer id) {
        if (id==null){
            log.error("Employee ID is null");
            return null;
        }
        return employeeRepository.findById(id).map(EmployeeDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("Employee with ID "+id+"doesnt exist", ErrorCodes.USER_NOT_FOUND));
    }

    @Override
    public List<EmployeeDto> findAll() {
        return employeeRepository.findAll().stream()
                .map(EmployeeDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id==null) {
            log.error("Employee ID is null");
            return;
        }
        employeeRepository.deleteById(id);
    }
}
