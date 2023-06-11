package fr.eitelalaric.gestiondestock.repository;

import fr.eitelalaric.gestiondestock.model.CommandeProvider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommandProviderRepository extends JpaRepository<CommandeProvider, Integer> {
    Optional<CommandeProvider> findCommandeLigneByCode(String code);

    List<CommandeProvider> findAllByProviderId(Integer id);
}
