package vinicius.kalah.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vinicius.kalah.dto.GameResponseDTO;
import vinicius.kalah.model.Board;
import vinicius.kalah.model.Game;
import vinicius.kalah.repository.BoardRepository;
import vinicius.kalah.repository.GameRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class StartServiceTest {
    private GameRepository gameRepository;
    private BoardRepository boardRepository;
    private UrlBuilder urlBuilder;

    @BeforeEach
    public void setup() {
        gameRepository = mock(GameRepository.class);
        boardRepository = mock(BoardRepository.class);
        urlBuilder = mock(UrlBuilder.class);
        Game game = mock(Game.class);
        when(game.getId()).thenReturn("1234");
        when(gameRepository.save(any(Game.class))).thenReturn(game);
    }

    @Test
    public void shouldStartGame() {
        GameResponseDTO game = new StartService(gameRepository, boardRepository, urlBuilder).start();
        verify(gameRepository).save(any(Game.class));
        verify(boardRepository).save(any(Board.class));
        assertThat(game.getId()).isNotBlank();
        assertThat(game.getUri()).isNotBlank();
    }
}