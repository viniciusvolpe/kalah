package vinicius.kalah.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document
public class Game {

    @Id
    private String id;
    private Player turn;
    @DBRef
    private Board board;

    public Game() {
    }

    public Game(String id, Player turn, Board board) {
        this.id = id;
        this.turn = turn;
        this.board = board;
    }

    public String getId() {
        return id;
    }

    public Player getTurn() {
        return turn;
    }

    public Board getBoard() {
        return board;
    }

    public Game changeTurn() {
        turn = Player.ONE.equals(turn) ? Player.TWO : Player.ONE;
        return this;
    }

    public Game move(Integer pit) {
        Board board = this.board.move(pit, turn);
        if(!turn.getKalah().equals(board.getLastPit()))
            turn = Player.ONE.equals(turn) ? Player.TWO : Player.ONE;
        return this;
    }

    public Map<Integer, Integer> getPits() {
        return board.getPits();
    }
}
