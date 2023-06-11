package fr.eitelalaric.gestiondestock.repository;

import fr.eitelalaric.gestiondestock.model.LigneCommandeProvider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LigneCommandProviderRepository extends JpaRepository<LigneCommandeProvider, Integer> {

    List<LigneCommandeProvider> findAllByCommandeProviderId(Integer idCommande);

    List<LigneCommandeProvider> findAllByProductId(Integer id);
}
