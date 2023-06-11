package fr.eitelalaric.gestiondestock.service;

import fr.eitelalaric.gestiondestock.dto.MvtStkDto;

import java.math.BigDecimal;
import java.util.List;

public interface MvtStkService {
    BigDecimal stockReelProduct(Integer idProduct);

    List<MvtStkDto> mvtStkProduct(Integer idProduct);

    MvtStkDto entreeStock(MvtStkDto mvtStkDto);
    MvtStkDto sortieStock(MvtStkDto mvtStkDto);
    MvtStkDto correctionStockPositive(MvtStkDto mvtStkDto);
    MvtStkDto correctionStockNegative(MvtStkDto mvtStkDto);




}
