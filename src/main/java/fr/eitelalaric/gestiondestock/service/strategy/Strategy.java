package fr.eitelalaric.gestiondestock.service.strategy;

import com.flickr4java.flickr.FlickrException;

import java.io.InputStream;

/**
 * @author eitel.tchuenkam
 */
public interface Strategy<T>{
    T savePhoto(Integer id, InputStream photo, String titre) throws FlickrException;
}
