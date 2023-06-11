package fr.eitelalaric.gestiondestock.repository;

import fr.eitelalaric.gestiondestock.model.LigneVente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LigneVenteRepository extends JpaRepository<LigneVente, Integer> {
    List<LigneVente> findAllByProductId(Integer id);

    List<LigneVente> findAllByVenteId(Integer id);
}
