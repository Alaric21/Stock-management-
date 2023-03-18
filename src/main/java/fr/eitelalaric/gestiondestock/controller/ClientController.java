package fr.eitelalaric.gestiondestock.controller;

import fr.eitelalaric.gestiondestock.controller.api.CategoryApi;
import fr.eitelalaric.gestiondestock.controller.api.ClientApi;
import fr.eitelalaric.gestiondestock.dto.CategoryDto;
import fr.eitelalaric.gestiondestock.dto.ClientDto;
import fr.eitelalaric.gestiondestock.service.CategoryService;
import fr.eitelalaric.gestiondestock.service.ClientService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class ClientController implements ClientApi {

    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public ClientDto save(ClientDto clientDto) {
        return clientService.save(clientDto);
    }

    @Override
    public ClientDto findById(Integer idcategory) {
        return clientService.findById(idcategory) ;
    }

    @Override
    public List<ClientDto> findAll() {
        return clientService.findAll();
    }

    @Override
    public void delete(Integer id) {
        clientService.delete(id);
    }
}
