package fr.eitelalaric.gestiondestock.service.auth;

import fr.eitelalaric.gestiondestock.dto.auth.AuthenticationRequest;
import fr.eitelalaric.gestiondestock.dto.auth.AuthenticationResponse;
import fr.eitelalaric.gestiondestock.model.Employee;
import fr.eitelalaric.gestiondestock.utils.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationUserService {

    private final AuthenticationManager authenticationManager;
    private final ApplicationUserService applicationUserService;
    private final JwtUtils jwtUtils;

    public AuthenticationUserService(AuthenticationManager authenticationManager, ApplicationUserService applicationUserService, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.applicationUserService = applicationUserService;
        this.jwtUtils = jwtUtils;
    }


    public AuthenticationResponse authentication(AuthenticationRequest authenticationRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.getEmail(),
                authenticationRequest.getPassword()
        ));
        UserDetails userDetails =applicationUserService.loadUserByUsername(authenticationRequest.getEmail());
        String token = jwtUtils.generateToken(Employee.builder()
                .email(userDetails.getUsername())
                .motDePasse(userDetails.getPassword())
                .build());
        return AuthenticationResponse.builder().jwtToken(token).build();
    }
}
