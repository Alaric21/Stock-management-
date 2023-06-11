package fr.eitelalaric.gestiondestock.service.strategy;

import com.flickr4java.flickr.FlickrException;
import fr.eitelalaric.gestiondestock.dto.ProviderDto;
import fr.eitelalaric.gestiondestock.exception.ErrorCodes;
import fr.eitelalaric.gestiondestock.exception.InvalidOperationException;
import fr.eitelalaric.gestiondestock.service.FlickrService;
import fr.eitelalaric.gestiondestock.service.impl.ProviderServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;

/**
 * @author eitel.tchuenkam
 */
@Service("providerStrategy")
@Slf4j
public class SaveProviderPhoto implements Strategy<ProviderDto> {
    private FlickrService flickrService;
    private ProviderServiceImpl providerService;

    public SaveProviderPhoto(FlickrService flickrService, ProviderServiceImpl providerService) {
        this.flickrService = flickrService;
        this.providerService = providerService;
    }

    @Override
    public ProviderDto savePhoto(Integer id, InputStream photo, String titre) throws FlickrException {
        ProviderDto providerDto  = providerService.findById(id);
        String urlPhoto = flickrService.savePhoto(photo, titre);
        if (!StringUtils.hasLength(urlPhoto)) {
            throw new InvalidOperationException("Erreur lors de l'enregistrement de la photo provider", ErrorCodes.UPDATE_PHOT0_FAIL);
        }
        providerDto.setPhoto(urlPhoto);
        return providerService.save(providerDto);
    }
}
