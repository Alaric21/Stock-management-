package fr.eitelalaric.gestiondestock.repository;

import fr.eitelalaric.gestiondestock.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Optional<Product> findProductByProductCode(String productCode);
}
