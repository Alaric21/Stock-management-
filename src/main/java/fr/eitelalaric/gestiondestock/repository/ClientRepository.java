package fr.eitelalaric.gestiondestock.repository;

import fr.eitelalaric.gestiondestock.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
