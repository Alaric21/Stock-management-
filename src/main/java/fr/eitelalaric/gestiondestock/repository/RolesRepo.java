package fr.eitelalaric.gestiondestock.repository;

import fr.eitelalaric.gestiondestock.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepo extends JpaRepository<Role, Integer> {
}
