package vinicius.kalah.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import vinicius.kalah.dto.GameMoveDTO;
import vinicius.kalah.dto.GameResponseDTO;

import javax.websocket.server.PathParam;

@RestController("/games")
public class GameController {

    @PostMapping
    public GameResponseDTO startGame() {
        return new GameResponseDTO();
    }

    @PutMapping("{gameId}/pits/{pitId}")
    public GameMoveDTO makeMove(@PathVariable("gameId") String gameId, @PathVariable("pitId") String pitId) {
        return new GameMoveDTO();
    }
}
