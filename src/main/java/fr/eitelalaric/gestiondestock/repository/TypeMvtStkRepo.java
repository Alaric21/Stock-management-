package fr.eitelalaric.gestiondestock.repository;

import fr.eitelalaric.gestiondestock.model.Category;
import fr.eitelalaric.gestiondestock.model.TypeMvtStk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeMvtStkRepo extends JpaRepository<TypeMvtStk, Integer> {
}
