package vinicius.kalah.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vinicius.kalah.dto.GameStatusDTO;
import vinicius.kalah.model.Game;
import vinicius.kalah.model.Player;
import vinicius.kalah.repository.BoardRepository;
import vinicius.kalah.repository.GameRepository;

@Service
public class MoveService {

    private final GameRepository gameRepository;
    private final BoardRepository boardRepository;
    private final UrlBuilder urlBuilder;

    @Autowired
    public MoveService(GameRepository gameRepository, BoardRepository boardRepository, UrlBuilder urlBuilder) {
        this.gameRepository = gameRepository;
        this.boardRepository = boardRepository;
        this.urlBuilder = urlBuilder;
    }

    public GameStatusDTO makeAMove(String id, Integer pit) {
        return gameRepository.findById(id).map(game -> {
            validateTurn(game, pit);
            move(pit, game);
            return new GameStatusDTO(game.getId(), urlBuilder.buildUrl(game), game.getPits());
        }).orElseThrow(() -> new IllegalStateException("Game not found"));
    }

    private void move(Integer pit, Game game) {
        gameRepository.save(game.move(pit));
        boardRepository.save(game.getBoard());
    }

    private void validateTurn(Game game, Integer pit) {
        Player player = game.getTurn();
        if(player.getKalah().equals(pit))
            throw new IllegalStateException("You can't move from kalah");
        if(player.isMyBoard(pit))
            throw new IllegalStateException("It's not your turn");
        if(game.getBoard().getPits().get(pit).equals(0))
            throw new IllegalStateException("There are any stone in this pit");
    }
}
