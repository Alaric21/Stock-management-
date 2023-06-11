package fr.eitelalaric.gestiondestock.controller.api;

import fr.eitelalaric.gestiondestock.dto.MvtStkDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author eitel.tchuenkam
 */
public interface MvtStkApi {

    @GetMapping("/mvtstk/stockreel/{idproduct}")
    BigDecimal stockReelProduct(@PathVariable("idproduct") Integer idProduct);

    @GetMapping("/mvtstk/filter/product/{idproduct}")
    List<MvtStkDto> mvtStkProduct(@PathVariable("idproduct")  Integer idProduct);

    @PostMapping("/mvtstk/entree")
    MvtStkDto entreeStock(@RequestBody  MvtStkDto mvtStkDto);

    @PostMapping("/mvtstk/sortie")
    MvtStkDto sortieStock(@RequestBody MvtStkDto mvtStkDto);

    @PostMapping("/mvtstk/correctionpositive")
    MvtStkDto correctionStockPositive(@RequestBody MvtStkDto mvtStkDto);

    @PostMapping("/mvtstk/correctionnegative")
    MvtStkDto correctionStockNegative(@RequestBody MvtStkDto mvtStkDto);
}
