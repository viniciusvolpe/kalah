package vinicius.kalah.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vinicius.kalah.dto.GameResponseDTO;
import vinicius.kalah.model.Board;
import vinicius.kalah.model.Game;
import vinicius.kalah.model.Player;
import vinicius.kalah.repository.BoardRepository;
import vinicius.kalah.repository.GameRepository;

import java.util.Map;
import java.util.stream.IntStream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

@Service
public class StartService {
    private final GameRepository gameRepository;
    private final BoardRepository boardRepository;
    private final UrlBuilder urlBuilder;

    @Autowired
    public StartService(GameRepository gameRepository, BoardRepository boardRepository, UrlBuilder urlBuilder) {
        this.gameRepository = gameRepository;
        this.boardRepository = boardRepository;
        this.urlBuilder = urlBuilder;
    }

    public GameResponseDTO start() {
        Board board = createBoard();
        Game game = gameRepository.save(new Game(null, Player.ONE, board));
        return new GameResponseDTO(game.getId(), urlBuilder.buildUrl(game));
    }

    private Board createBoard() {
        return boardRepository.save(new Board(null, BoardUtils.createInitialBoard()));
    }
}
