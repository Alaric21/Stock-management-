package fr.eitelalaric.gestiondestock.repository;

import fr.eitelalaric.gestiondestock.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

    Optional<Category> findCategoryByCode(String code);
}
