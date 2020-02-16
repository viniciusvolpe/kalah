package vinicius.kalah.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vinicius.kalah.dto.GameStatusDTO;
import vinicius.kalah.dto.GameResponseDTO;
import vinicius.kalah.service.GameLoader;
import vinicius.kalah.service.MoveService;
import vinicius.kalah.service.StartService;

@RestController
@RequestMapping("/games")
public class GameController {

    private final StartService startService;
    private final MoveService moveService;
    private final GameLoader gameLoader;

    @Autowired
    public GameController(StartService startService, MoveService moveService, GameLoader gameLoader) {
        this.startService = startService;
        this.moveService = moveService;
        this.gameLoader = gameLoader;
    }

    @PostMapping
    public GameResponseDTO startGame() {
        return startService.start();
    }

    @PutMapping("{gameId}/pits/{pitId}")
    public GameStatusDTO makeMove(@PathVariable("gameId") String gameId, @PathVariable("pitId") Integer pitId) {
        return moveService.makeAMove(gameId, pitId);
    }

    @GetMapping("{gameId}")
    public GameStatusDTO getGame(@PathVariable("gameId") String gameId) {
        return gameLoader.getById(gameId);
    }
}
