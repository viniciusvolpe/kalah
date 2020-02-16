package vinicius.kalah.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import vinicius.kalah.service.BoardUtils;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

@Document
public class Board {

    @Id
    private String id;
    private Integer lastPit;
    private Map<Integer, Integer> pits;

    public Board() {
    }

    public Board(String id, Map<Integer, Integer> pits) {
        this.id = id;
        this.pits = pits;
    }

    public String getId() {
        return id;
    }

    public Map<Integer, Integer> getPits() {
        return pits;
    }

    public Board move(Integer pit, Player player) {
        AtomicInteger lastPitHolder = new AtomicInteger();
        IntStream.iterate(1, pitCount -> pitCount + 1)
                .limit(pits.get(pit))
                .forEach(pitCount -> {
                    Integer currentPit = pit + pitCount;
                    if (currentPit > Player.TWO.getKalah())
                        currentPit = currentPit - Player.TWO.getKalah();
                    lastPitHolder.set(currentPit);
                    if (currentPit.equals(pit)) return;
                    pits.put(currentPit, pits.get(currentPit) + 1);
                });
        pits.put(pit, 0);
        lastPit = lastPitHolder.get();
        if (player.isMyBoard(lastPit) && pits.get(lastPit).equals(1) && !BoardUtils.isKalah(lastPit))
            moveAllToKalah(lastPit, player);
        if (someBoardEmpty())
            finishGame();
        return this;
    }

    private void finishGame() {
        pits.entrySet()
                .stream()
                .filter(pit -> !pit.getValue().equals(0))
                .forEach(pit ->
                        pits.put(getPlayerByPit(pit.getKey()).getKalah(), pit.getValue())
                );
    }

    private Player getPlayerByPit(Integer pit) {
        return Player.ONE.isMyBoard(pit) ? Player.ONE : Player.TWO;
    }

    private boolean someBoardEmpty() {
        return isPlayerBoardEmpty(Player.ONE) || isPlayerBoardEmpty(Player.TWO);
    }

    private boolean isPlayerBoardEmpty(Player player) {
        return pits.entrySet()
                .stream()
                .filter(pit -> player.isMyBoard(pit.getKey()))
                .allMatch(pit -> pit.getValue().equals(0));
    }

    private void moveAllToKalah(Integer lastPit, Player player) {
        Integer opposite = getOppositePit(lastPit, player);
        pits.put(player.getKalah(), pits.get(lastPit) + pits.get(opposite));
        pits.put(lastPit, 0);
        pits.put(opposite, 0);
    }

    private Integer getOppositePit(Integer lastPit, Player player) {
        if (Player.ONE.equals(player))
            return Player.TWO.getKalah() - lastPit;
        return Player.ONE.getKalah() - (lastPit - Player.ONE.getKalah());
    }

    public Integer getLastPit() {
        return lastPit;
    }
}
