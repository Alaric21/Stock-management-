package fr.eitelalaric.gestiondestock.controller;

import fr.eitelalaric.gestiondestock.auth.AuthenticationRequest;
import fr.eitelalaric.gestiondestock.auth.AuthenticationResponse;
import fr.eitelalaric.gestiondestock.service.auth.AuthenticationUserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static fr.eitelalaric.gestiondestock.utils.Constants.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT +"/auth")
public class AuthenticationController {

    private final AuthenticationUserService authenticationUserService;


    public AuthenticationController(AuthenticationUserService authenticationUserService) {
        this.authenticationUserService = authenticationUserService;
    }
    @Operation(summary = "employee authentication", description = "This method allow employee to authenticate ", tags= "Authenticate")
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest){
        return ResponseEntity.ok(authenticationUserService.authentication(authenticationRequest));
    }
}
