package fr.eitelalaric.gestiondestock.repository;

import fr.eitelalaric.gestiondestock.model.CommandeClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommandeClientRepository extends JpaRepository<CommandeClient, Integer> {
    Optional<CommandeClient> findCommandeLigneByCode(String code);
}
