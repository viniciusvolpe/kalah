package vinicius.kalah.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vinicius.kalah.dto.GameStatusDTO;
import vinicius.kalah.model.Board;
import vinicius.kalah.model.Game;
import vinicius.kalah.model.Player;
import vinicius.kalah.repository.BoardRepository;
import vinicius.kalah.repository.GameRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MoveServiceTest {
    private GameRepository gameRepository;
    private BoardRepository boardRepository;
    private UrlBuilder urlBuilder;
    private String gameId = "1234";

    @BeforeEach
    public void setup() {
        gameRepository = mock(GameRepository.class);
        boardRepository = mock(BoardRepository.class);
        urlBuilder = mock(UrlBuilder.class);
    }

    @Test
    public void shouldMakeMove() {
        Game game = createGame();
        GameStatusDTO gameStatusDTO = new MoveService(gameRepository, boardRepository, urlBuilder).makeAMove(gameId, 2);
        verify(gameRepository).save(any(Game.class));
        verify(boardRepository).save(any(Board.class));
        assertThat(gameStatusDTO).isNotNull();
        assertThat(gameStatusDTO.getStatus().get(2)).isZero();
        assertThat(game.getTurn()).isEqualTo(Player.TWO);
    }

    @Test
    public void shouldNotMoveFromKala() {
        createGame();
        try {
            new MoveService(gameRepository, boardRepository, urlBuilder).makeAMove(gameId, Player.ONE.getKalah());
        } catch (Exception e) {
            assertThat(e).isInstanceOf(IllegalStateException.class);
            assertThat(e.getMessage()).isEqualTo("You can't move from kalah");
        }
    }

    @Test
    public void shouldNotMoveIfItsNotYourTurn() {
        createGame();
        try {
            new MoveService(gameRepository, boardRepository, urlBuilder).makeAMove(gameId, 8);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(IllegalStateException.class);
            assertThat(e.getMessage()).isEqualTo("It's not your turn");
        }
    }

    @Test
    public void shouldNotMoveIfPitsIsEmpty() {
        createGame();
        try {
            MoveService moveService = new MoveService(gameRepository, boardRepository, urlBuilder);
            moveService.makeAMove(gameId, 1);
            moveService.makeAMove(gameId, 1);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(IllegalStateException.class);
            assertThat(e.getMessage()).isEqualTo("There are any stone in this pit");
        }
    }

    private Game createGame() {
        Board board = new Board(null, BoardUtils.createInitialBoard());
        Game game = new Game(null, Player.ONE, board);
        when(gameRepository.findById(gameId)).thenReturn(Optional.of(game));
        return game;
    }
}