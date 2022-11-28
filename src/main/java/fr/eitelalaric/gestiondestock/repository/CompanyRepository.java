package fr.eitelalaric.gestiondestock.repository;

import fr.eitelalaric.gestiondestock.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
}
