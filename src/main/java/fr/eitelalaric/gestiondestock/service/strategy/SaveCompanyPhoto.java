package fr.eitelalaric.gestiondestock.service.strategy;

import com.flickr4java.flickr.FlickrException;
import fr.eitelalaric.gestiondestock.dto.CompanyDto;
import fr.eitelalaric.gestiondestock.exception.ErrorCodes;
import fr.eitelalaric.gestiondestock.exception.InvalidOperationException;
import fr.eitelalaric.gestiondestock.service.CompanyService;
import fr.eitelalaric.gestiondestock.service.FlickrService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;

/**
 * @author eitel.tchuenkam
 */
@Service("companyStrategy")
@Slf4j
public class SaveCompanyPhoto implements Strategy<CompanyDto> {
    private FlickrService flickrService;
    private CompanyService companyService;

    public SaveCompanyPhoto(FlickrService flickrService, CompanyService companyService) {
        this.flickrService = flickrService;
        this.companyService = companyService;
    }

    @Override
    public CompanyDto savePhoto(Integer id, InputStream photo, String titre) throws FlickrException {
        CompanyDto companyDto  = companyService.findById(id);
        String urlPhoto = flickrService.savePhoto(photo, titre);
        if (!StringUtils.hasLength(urlPhoto)) {
            throw new InvalidOperationException("Erreur lors de l'enregistrement de la photo client", ErrorCodes.UPDATE_PHOT0_FAIL);
        }
        companyDto.setPhoto(urlPhoto);
        return companyService.save(companyDto);
    }
}
