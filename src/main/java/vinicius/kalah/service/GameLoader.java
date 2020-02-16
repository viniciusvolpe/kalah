package vinicius.kalah.service;

import org.springframework.stereotype.Service;
import vinicius.kalah.dto.GameStatusDTO;
import vinicius.kalah.repository.GameRepository;

@Service
public class GameLoader {

    private final GameRepository gameRepository;
    private final UrlBuilder urlBuilder;

    public GameLoader(GameRepository gameRepository, UrlBuilder urlBuilder) {
        this.gameRepository = gameRepository;
        this.urlBuilder = urlBuilder;
    }

    public GameStatusDTO getById(String gameId) {
        return gameRepository.findById(gameId)
                .map(game -> new GameStatusDTO(game.getId(), urlBuilder.buildUrl(game), game.getPits()))
                .orElse(null);
    }
}
