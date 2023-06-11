package fr.eitelalaric.gestiondestock.repository;

import fr.eitelalaric.gestiondestock.model.CommandeClient;
import fr.eitelalaric.gestiondestock.model.LigneCommandeClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommandeClientRepository extends JpaRepository<CommandeClient, Integer> {
    Optional<CommandeClient> findCommandeLigneByCode(String code);
    List<CommandeClient> findAllByClientId(Integer id);
}
