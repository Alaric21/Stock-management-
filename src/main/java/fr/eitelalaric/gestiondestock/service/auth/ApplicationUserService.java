package fr.eitelalaric.gestiondestock.service.auth;

import fr.eitelalaric.gestiondestock.auth.UserPrincipal;
import fr.eitelalaric.gestiondestock.model.Employee;
import fr.eitelalaric.gestiondestock.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ApplicationUserService implements UserDetailsService {

    private final EmployeeRepository employeeRepository;

    public ApplicationUserService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findEmployeeByEmail(username).orElseThrow(
                ()->new BadCredentialsException("check your username or your password"));
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        employee.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getNom())));
        return new UserPrincipal(employee.getEmail(), employee.getMotDePasse(),authorities, employee.getCompany().getIdcompany());
    }

}
