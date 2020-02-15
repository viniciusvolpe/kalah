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
        return boardRepository.save(new Board(null, createInitialBoard()));
    }

    private Map<Integer, Integer> createInitialBoard() {
        return IntStream.rangeClosed(1, 14)
                .boxed()
                .collect(toMap(identity(), pit -> isKalah(pit) ? 0 : STONES));
    }

    private boolean isKalah(Integer pit) {
        return Player.ONE.getKalah().equals(pit) || Player.TWO.getKalah().equals(pit);
    }
}
