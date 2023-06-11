package fr.eitelalaric.gestiondestock.service.strategy;

import com.flickr4java.flickr.FlickrException;
import fr.eitelalaric.gestiondestock.dto.ProductDto;
import fr.eitelalaric.gestiondestock.exception.ErrorCodes;
import fr.eitelalaric.gestiondestock.exception.InvalidOperationException;
import fr.eitelalaric.gestiondestock.service.FlickrService;
import fr.eitelalaric.gestiondestock.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;

/**
 * @author eitel.tchuenkam
 */
@Service("productStrategy")
@Slf4j
public class SaveProductPhoto implements Strategy<ProductDto> {

    private FlickrService flickrService;
    private ProductService productService;

    public SaveProductPhoto(FlickrService flickrService, ProductService productService) {
        this.flickrService = flickrService;
        this.productService = productService;
    }

    @Override
    public ProductDto savePhoto(Integer id, InputStream photo, String titre) throws FlickrException {
        ProductDto product  = productService.findById(id);
        String urlPhoto = flickrService.savePhoto(photo, titre);
        if (!StringUtils.hasLength(urlPhoto)) {
            throw new InvalidOperationException("Erreur lors de l'enregistrement de la photo product", ErrorCodes.UPDATE_PHOT0_FAIL);
        }
        product.setPhoto(urlPhoto);
        return productService.save(product);
    }
}
