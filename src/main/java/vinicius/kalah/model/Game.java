package vinicius.kalah.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

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

    public void setId(String id) {
        this.id = id;
    }

    public Player getTurn() {
        return turn;
    }

    public void setTurn(Player turn) {
        this.turn = turn;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Game changeTurn() {
        turn = Player.ONE.equals(turn) ? Player.TWO : Player.ONE;
        return this;
    }
}
