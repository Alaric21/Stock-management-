package fr.eitelalaric.gestiondestock.service.strategy;

import com.flickr4java.flickr.FlickrException;
import fr.eitelalaric.gestiondestock.exception.ErrorCodes;
import fr.eitelalaric.gestiondestock.exception.InvalidOperationException;
import lombok.Setter;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * @author eitel.tchuenkam
 */
@Service
public class StrategyPhotoContext {
    private BeanFactory beanFactory;
    private Strategy strategy;

    @Setter
    private String context;

    public StrategyPhotoContext(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public Object savePhoto(String context, Integer id, InputStream photo, String titre) throws FlickrException {
        determinContext(context);
        return strategy.savePhoto(id, photo, titre);
    }

    private void determinContext(String context) {
        final String beanName = context + "Strategy";
        switch (context) {
            case "product":
                strategy =  beanFactory.getBean(beanName, SaveProductPhoto.class);
                break;
            case "client":
                strategy =  beanFactory.getBean(beanName, SaveClientPhoto.class);
                break;
            case "provider":
                strategy =  beanFactory.getBean(beanName, SaveProviderPhoto.class);
                break;
            case "employee":
                strategy =  beanFactory.getBean(beanName, SaveEmployeePhoto.class);
                break;
            case "company":
                strategy =  beanFactory.getBean(beanName, SaveCompanyPhoto.class);
                break;
            default:
                throw new InvalidOperationException("contexte inconnue pour l'enregistrement de la photo", ErrorCodes.UNKNOWN_CONTEXT);

        }
    }
}
