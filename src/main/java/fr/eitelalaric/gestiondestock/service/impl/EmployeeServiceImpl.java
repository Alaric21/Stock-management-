package fr.eitelalaric.gestiondestock.service.impl;

import fr.eitelalaric.gestiondestock.dto.ChangerMotDePasseUtilisateurDto;
import fr.eitelalaric.gestiondestock.dto.EmployeeDto;
import fr.eitelalaric.gestiondestock.exception.EntityNotFoundException;
import fr.eitelalaric.gestiondestock.exception.ErrorCodes;
import fr.eitelalaric.gestiondestock.exception.InvalidEntityException;
import fr.eitelalaric.gestiondestock.exception.InvalidOperationException;
import fr.eitelalaric.gestiondestock.model.Employee;
import fr.eitelalaric.gestiondestock.repository.EmployeeRepository;
import fr.eitelalaric.gestiondestock.service.EmployeeService;
import fr.eitelalaric.gestiondestock.validator.EmployeeValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
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

    @Override
    public EmployeeDto findByEmail(String email) {
        return employeeRepository.findEmployeeByEmail(email)
                .map(EmployeeDto::fromEntity)
                .orElseThrow(()->new EntityNotFoundException(
                        "Aucun employee avec l'email = "+email+"n'est trouve", ErrorCodes.USER_NOT_FOUND
                ));
    }

    @Override
    public EmployeeDto changerMotDePasseEmployee(ChangerMotDePasseUtilisateurDto changerMotDePasseUtilisateurDto) {
        validate(changerMotDePasseUtilisateurDto);
        Optional<Employee> employeeOptional = employeeRepository.findById(changerMotDePasseUtilisateurDto.getId());
        if (employeeOptional.isEmpty()) {
            log.warn("Aucun Employee n'a ete trouve avec l'ID "+changerMotDePasseUtilisateurDto.getId());
            throw  new EntityNotFoundException("Aucun Employee n'a ete trouve avec l'ID "+changerMotDePasseUtilisateurDto.getId(),
                    ErrorCodes.USER_NOT_FOUND);
        }

        Employee employee = employeeOptional.get();
        employee.setMotDePasse(changerMotDePasseUtilisateurDto.getMotDePasse());
        return EmployeeDto.fromEntity(employeeRepository.save(employee));
    }

    private void validate(ChangerMotDePasseUtilisateurDto changerMotDePasseUtilisateurDto) {
        if (changerMotDePasseUtilisateurDto == null ){
            log.warn("impossible de modifier le mot de passe avec un objet null");
            throw new InvalidOperationException("Aucune information n'a ete fourni pour changer le mot de passe",
                    ErrorCodes.USER_CHANGE_PASSWORD_OBJET_NOT_VALID);
        }
        if ( changerMotDePasseUtilisateurDto.getId() == null) {
            log.warn("impossible de modifier le mot de passe avec un ID null");
            throw new InvalidOperationException("User ID null : impossible modifier le mot de passe",
                    ErrorCodes.USER_CHANGE_PASSWORD_OBJET_NOT_VALID);
        }

        if (!StringUtils.hasLength(changerMotDePasseUtilisateurDto.getMotDePasse()) || !StringUtils.hasLength(changerMotDePasseUtilisateurDto.getConfirmMotDePasse()) ){
            log.warn("impossible de modifier le mot de passe avec un mot de passe null");
            throw new InvalidOperationException("mot de passe null : impossible modifier le mot de passe",
                    ErrorCodes.USER_CHANGE_PASSWORD_OBJET_NOT_VALID);
        }

        if (!changerMotDePasseUtilisateurDto.getConfirmMotDePasse().equals(changerMotDePasseUtilisateurDto.getMotDePasse())){
            log.warn("impossible de modifier le mot de passe avec un mot de passe different ");
            throw new InvalidOperationException("mot de passe non conforme: impossible modifier le mot de passe",
                    ErrorCodes.USER_CHANGE_PASSWORD_OBJET_NOT_VALID);
        }
    }
}
