package vinicius.kalah.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vinicius.kalah.dto.GameMoveDTO;
import vinicius.kalah.model.Game;
import vinicius.kalah.model.Player;
import vinicius.kalah.repository.BoardRepository;
import vinicius.kalah.repository.GameRepository;

import java.util.Optional;
import java.util.function.Function;

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

    public GameMoveDTO makeAMove(String id, Integer pit) {
        return gameRepository.findById(id).map(game -> {
            validateTurn(game, pit);
            gameRepository.save(game.changeTurn());
            return move(game, pit);
        }).orElseThrow(() -> new IllegalStateException("Game not found"));
    }

    private GameMoveDTO move(Game game, Integer pit) {
        return Optional.ofNullable(game.getBoard())
                .map(board -> board.move(pit))
                .map(boardRepository::save)
                .map(board -> new GameMoveDTO(game.getId(), urlBuilder.buildUrl(game), board.getPits()))
                .orElse(null);
    }

    private void validateTurn(Game game, Integer pit) {
        Player player = game.getTurn();
        if(player.getKalah().equals(pit))
            throw new IllegalStateException("You can't move from kalah");
        if(pit < player.getFirstPit() || pit > player.getLastPit())
            throw new IllegalStateException("It's not your turn");
        if(game.getBoard().getPits().get(pit).equals(0))
            throw new IllegalStateException("There are any stone in this pit");
    }
}
