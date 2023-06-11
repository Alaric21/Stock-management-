package fr.eitelalaric.gestiondestock.controller;

import com.flickr4java.flickr.FlickrException;
import fr.eitelalaric.gestiondestock.controller.api.PhotoApi;
import fr.eitelalaric.gestiondestock.service.strategy.StrategyPhotoContext;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author eitel.tchuenkam
 */
@RestController
public class PhotoController implements PhotoApi {

    private final StrategyPhotoContext strategyPhotoContext;

    public PhotoController(StrategyPhotoContext strategyPhotoContext) {
        this.strategyPhotoContext = strategyPhotoContext;
    }

    @Override
    public Object savePhoto(String context, Integer id, MultipartFile photo, String title) throws IOException, FlickrException {
        return strategyPhotoContext.savePhoto(context, id, photo.getInputStream(), title);
    }
}
