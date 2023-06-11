package fr.eitelalaric.gestiondestock.service.strategy;

import com.flickr4java.flickr.FlickrException;
import fr.eitelalaric.gestiondestock.dto.EmployeeDto;
import fr.eitelalaric.gestiondestock.exception.ErrorCodes;
import fr.eitelalaric.gestiondestock.exception.InvalidOperationException;
import fr.eitelalaric.gestiondestock.service.EmployeeService;
import fr.eitelalaric.gestiondestock.service.FlickrService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;

/**
 * @author eitel.tchuenkam
 */
@Service("employeeStrategy")
@Slf4j
public class SaveEmployeePhoto implements Strategy<EmployeeDto> {
    private FlickrService flickrService;
    private EmployeeService employeeService;

    public SaveEmployeePhoto(FlickrService flickrService, EmployeeService employeeService) {
        this.flickrService = flickrService;
        this.employeeService = employeeService;
    }

    @Override
    public EmployeeDto savePhoto(Integer id, InputStream photo, String titre) throws FlickrException {
        EmployeeDto employeeDto  = employeeService.findById(id);
        String urlPhoto = flickrService.savePhoto(photo, titre);
        if (!StringUtils.hasLength(urlPhoto)) {
            throw new InvalidOperationException("Erreur lors de l'enregistrement de la photo employer", ErrorCodes.UPDATE_PHOT0_FAIL);
        }
        employeeDto.setPhoto(urlPhoto);
        return employeeService.save(employeeDto);
    }
}
