package fr.eitelalaric.gestiondestock.service;

import fr.eitelalaric.gestiondestock.dto.MvtStkDto;

import java.util.List;

public interface TypeMvtStkService {

    MvtStkDto save (MvtStkDto mvtStkDto);

    MvtStkDto findById(Integer id);

    List<MvtStkDto> findAll();

    void delete(Integer id);
}
