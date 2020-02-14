package vinicius.kalah.model;

public enum Player {
    ONE(1, 6, 7),
    TWO(8, 13, 14);

    private final Integer firstPit;
    private final Integer lastPit;
    private final Integer kalah;

    Player(Integer firstPit, Integer lastPit, Integer kalah) {
        this.firstPit = firstPit;
        this.lastPit = lastPit;
        this.kalah = kalah;
    }

    public Integer getFirstPit() {
        return firstPit;
    }

    public Integer getLastPit() {
        return lastPit;
    }

    public Integer getKalah() {
        return kalah;
    }
}
