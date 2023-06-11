package fr.eitelalaric.gestiondestock.controller;

import fr.eitelalaric.gestiondestock.controller.api.MvtStkApi;
import fr.eitelalaric.gestiondestock.dto.MvtStkDto;
import fr.eitelalaric.gestiondestock.service.MvtStkService;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author eitel.tchuenkam
 */
@RestController
public class MvtStkController implements MvtStkApi {

    private final MvtStkService mvtStkService;

    public MvtStkController(MvtStkService mvtStkService) {
        this.mvtStkService = mvtStkService;
    }

    @Override
    public BigDecimal stockReelProduct(Integer idProduct) {
        return mvtStkService.stockReelProduct(idProduct);
    }

    @Override
    public List<MvtStkDto> mvtStkProduct(Integer idProduct) {
        return mvtStkService.mvtStkProduct(idProduct);
    }

    @Override
    public MvtStkDto entreeStock(MvtStkDto mvtStkDto) {
        return mvtStkService.entreeStock(mvtStkDto);
    }

    @Override
    public MvtStkDto sortieStock(MvtStkDto mvtStkDto) {
        return mvtStkService.sortieStock(mvtStkDto);
    }

    @Override
    public MvtStkDto correctionStockPositive(MvtStkDto mvtStkDto) {
        return mvtStkService.correctionStockPositive(mvtStkDto);
    }

    @Override
    public MvtStkDto correctionStockNegative(MvtStkDto mvtStkDto) {
        return mvtStkService.correctionStockNegative(mvtStkDto);
    }
}
