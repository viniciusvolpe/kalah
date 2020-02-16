package vinicius.kalah.model;

import org.junit.jupiter.api.Test;
import vinicius.kalah.service.BoardUtils;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class BoardTest {

    @Test
    public void shouldMakeAMove() {
        int pit = 1;
        Board board = new Board(null, BoardUtils.createInitialBoard()).move(pit, Player.ONE);
        assertThat(board.getPits().get(pit)).isZero();
        assertThat(board.getLastPit()).isEqualTo(Player.ONE.getKalah());
    }

    @Test
    public void shouldMoveFromPlayerTwoBoard() {
        int pit = 13;
        Board board = new Board(null, BoardUtils.createInitialBoard()).move(pit, Player.ONE);
        assertThat(board.getPits().get(pit)).isZero();
        assertThat(board.getLastPit()).isEqualTo(5);
    }

    @Test
    public void shouldGetStonesIfIsLastStone() {
        Map<Integer, Integer> initialBoard = BoardUtils.createInitialBoard();
        int pit = 5;
        initialBoard.put(pit, 1);
        initialBoard.put(6, 0);
        Board board = new Board(null, initialBoard).move(pit, Player.ONE);
        assertThat(board.getPits().get(pit)).isZero();
        assertThat(board.getPits().get(8)).isZero();
        assertThat(board.getPits().get(Player.ONE.getKalah())).isNotZero();
    }

    @Test
    public void shouldFinishGame() {
        Map<Integer, Integer> initialBoard = BoardUtils.createInitialBoard();
        initialBoard.keySet()
                .stream()
                .filter(Player.ONE::isMyBoard)
                .forEach(pit -> initialBoard.put(pit, 0));
        int pit = 6;
        initialBoard.put(pit, 1);
        Board board = new Board(null, initialBoard).move(pit, Player.ONE);
        board.getPits().forEach((key, value) -> {
            if (BoardUtils.isKalah(key))
                assertThat(value).isNotZero();
            else
                assertThat(value).isZero();
        });
    }
}