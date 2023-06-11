package fr.eitelalaric.gestiondestock.repository;

import fr.eitelalaric.gestiondestock.model.LigneCommandeClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LigneCommandeClientRepository extends JpaRepository<LigneCommandeClient, Integer> {
   List <LigneCommandeClient> findAllByCommandeClientId(Integer id);
   List<LigneCommandeClient> findAllByProductId(Integer id);

}
