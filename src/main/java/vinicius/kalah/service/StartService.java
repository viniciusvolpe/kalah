package vinicius.kalah.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vinicius.kalah.configuration.KalahProperties;
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
    private static final Integer STONES = 6;
    private static final Map<Integer, Integer> INITIAL_PITS = IntStream.rangeClosed(1, 14)
            .boxed()
            .collect(toMap(identity(), pit -> STONES));

    private final GameRepository gameRepository;
    private final BoardRepository boardRepository;
    private final KalahProperties kalahProperties;

    @Autowired
    public StartService(GameRepository gameRepository, BoardRepository boardRepository, KalahProperties kalahProperties) {
        this.gameRepository = gameRepository;
        this.boardRepository = boardRepository;
        this.kalahProperties = kalahProperties;
    }

    public GameResponseDTO start() {
        Board board = createBoard();
        Game game = gameRepository.save(new Game(null, Player.ONE, board));
        return new GameResponseDTO(game.getId(), buildUri(game));
    }

    private String buildUri(Game game) {
        return kalahProperties.getApplicationUrl() + "/games/" + game.getId();
    }

    private Board createBoard() {
        return boardRepository.save(new Board(null, INITIAL_PITS));
    }
}
