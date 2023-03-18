package fr.eitelalaric.gestiondestock.controller;

import fr.eitelalaric.gestiondestock.controller.api.CategoryApi;
import fr.eitelalaric.gestiondestock.controller.api.VenteApi;
import fr.eitelalaric.gestiondestock.dto.CategoryDto;
import fr.eitelalaric.gestiondestock.dto.VenteDto;
import fr.eitelalaric.gestiondestock.service.CategoryService;
import fr.eitelalaric.gestiondestock.service.VenteService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class VenteController implements VenteApi {

    private VenteService venteService;

    public VenteController(VenteService venteService) {
        this.venteService = venteService;
    }

    @Override
    public VenteDto save(VenteDto VenteDto) {
        return venteService.save(VenteDto);
    }

    @Override
    public VenteDto findById(Integer idvente) {
        return venteService.findById(idvente) ;
    }

    @Override
    public List<VenteDto> findAll() {
        return venteService.findAll();
    }

    @Override
    public void delete(Integer id) {
        venteService.delete(id);
    }
}
