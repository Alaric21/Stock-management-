package fr.eitelalaric.gestiondestock.controller.api;

import com.flickr4java.flickr.FlickrException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static fr.eitelalaric.gestiondestock.utils.Constants.APP_ROOT;

/**
 * @author eitel.tchuenkam
 */
public interface PhotoApi {

    @PostMapping(value = APP_ROOT+"/photos/{id}/{title}/{context}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object savePhoto(@PathVariable String context, @PathVariable  Integer id, @RequestPart MultipartFile photo, @PathVariable  String title) throws IOException, FlickrException;
}
