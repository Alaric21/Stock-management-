package fr.eitelalaric.gestiondestock.repository;

import fr.eitelalaric.gestiondestock.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
