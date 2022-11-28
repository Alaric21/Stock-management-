package fr.eitelalaric.gestiondestock.repository;

import fr.eitelalaric.gestiondestock.model.Vente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VenteRepository extends JpaRepository<Vente, Integer> {

    Optional<Vente> findVenteByCode(String code);
}
