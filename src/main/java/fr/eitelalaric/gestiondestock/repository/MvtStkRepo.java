package fr.eitelalaric.gestiondestock.repository;

import fr.eitelalaric.gestiondestock.model.MvtStk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MvtStkRepo extends JpaRepository<MvtStk, Integer> {
}
