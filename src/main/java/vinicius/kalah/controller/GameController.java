package vinicius.kalah.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vinicius.kalah.dto.GameMoveDTO;
import vinicius.kalah.dto.GameResponseDTO;
import vinicius.kalah.service.StartService;

import javax.servlet.ServletContext;

@RestController
@RequestMapping("/games")
public class GameController {

    private final StartService startService;

    @Autowired
    public GameController(StartService startService) {
        this.startService = startService;
    }

    @PostMapping
    public GameResponseDTO startGame() {
        return startService.start();
    }

    @PutMapping("{gameId}/pits/{pitId}")
    public GameMoveDTO makeMove(@PathVariable("gameId") String gameId, @PathVariable("pitId") String pitId) {
        return new GameMoveDTO();
    }
}
