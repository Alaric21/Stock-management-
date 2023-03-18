package fr.eitelalaric.gestiondestock.auth;

import fr.eitelalaric.gestiondestock.model.Adresse;
import fr.eitelalaric.gestiondestock.model.Company;
import fr.eitelalaric.gestiondestock.model.Role;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;
import java.util.List;

@Setter
@Getter
public class UserPrincipal extends User {

    private Integer idcompany;
    public UserPrincipal(String username, String password, Collection<? extends GrantedAuthority> authorities, Integer idcompany) {
        super(username, password, authorities);
        this.idcompany = idcompany;
    }


}
