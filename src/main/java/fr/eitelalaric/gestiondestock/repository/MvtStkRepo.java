package fr.eitelalaric.gestiondestock.repository;

import fr.eitelalaric.gestiondestock.model.MvtStk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface MvtStkRepo extends JpaRepository<MvtStk, Integer> {


    @Query("select sum(m.quantite) from MvtStk m where m.product.id =:idProduct")
    BigDecimal stockReelProduct(@Param("idProduct") Integer idProduct);

    List<MvtStk> findAllByProductId(Integer idProduct);
}
