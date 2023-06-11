package fr.eitelalaric.gestiondestock.service.strategy;

import com.flickr4java.flickr.FlickrException;
import fr.eitelalaric.gestiondestock.dto.ClientDto;
import fr.eitelalaric.gestiondestock.exception.ErrorCodes;
import fr.eitelalaric.gestiondestock.exception.InvalidOperationException;
import fr.eitelalaric.gestiondestock.service.ClientService;
import fr.eitelalaric.gestiondestock.service.FlickrService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;

/**
 * @author eitel.tchuenkam
 */
@Service("clientStrategy")
@Slf4j
public class SaveClientPhoto implements Strategy<ClientDto> {

    private FlickrService flickrService;
    private ClientService clientService;

    public SaveClientPhoto(FlickrService flickrService, ClientService clientService) {
        this.flickrService = flickrService;
        this.clientService = clientService;
    }

    @Override
    public ClientDto savePhoto(Integer id, InputStream photo, String titre) throws FlickrException {
        ClientDto clientDto  = clientService.findById(id);
        String urlPhoto = flickrService.savePhoto(photo, titre);
        if (!StringUtils.hasLength(urlPhoto)) {
            throw new InvalidOperationException("Erreur lors de l'enregistrement de la photo client", ErrorCodes.UPDATE_PHOT0_FAIL);
        }
        clientDto.setPhoto(urlPhoto);
        return clientService.save(clientDto);
    }
}
