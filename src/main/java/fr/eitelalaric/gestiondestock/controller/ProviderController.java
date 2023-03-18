package fr.eitelalaric.gestiondestock.controller;

import fr.eitelalaric.gestiondestock.controller.api.CategoryApi;
import fr.eitelalaric.gestiondestock.controller.api.ProviderApi;
import fr.eitelalaric.gestiondestock.dto.CategoryDto;
import fr.eitelalaric.gestiondestock.dto.ProviderDto;
import fr.eitelalaric.gestiondestock.service.CategoryService;
import fr.eitelalaric.gestiondestock.service.ProviderService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class ProviderController implements ProviderApi {

    private ProviderService providerSercie;

    public ProviderController(ProviderService providerSercie) {
        this.providerSercie = providerSercie;
    }

    @Override
    public ProviderDto save(ProviderDto providerDto) {
        return providerSercie.save(providerDto);
    }

    @Override
    public ProviderDto findById(Integer idprovider) {
        return providerSercie.findById(idprovider) ;
    }

    @Override
    public List<ProviderDto> findAll() {
        return providerSercie.findAll();
    }

    @Override
    public void delete(Integer id) {
        providerSercie.delete(id);
    }
}
