package fr.eitelalaric.gestiondestock.repository;

import fr.eitelalaric.gestiondestock.model.LigneCommandeProvider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LigneCommandProviderRepository extends JpaRepository<LigneCommandeProvider, Integer> {
}
