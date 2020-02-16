package vinicius.kalah.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vinicius.kalah.configuration.KalahProperties;
import vinicius.kalah.model.Game;

@Service
public class UrlBuilder {

    private final KalahProperties kalahProperties;

    @Autowired
    public UrlBuilder(KalahProperties kalahProperties) {
        this.kalahProperties = kalahProperties;
    }

    public String buildUrl(Game game) {
        return kalahProperties.getApplicationUrl() + "/games/" + game.getId();
    }
}
