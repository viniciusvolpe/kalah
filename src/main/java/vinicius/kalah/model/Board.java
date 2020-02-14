package vinicius.kalah.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;
import java.util.stream.IntStream;

@Document
public class Board {

    @Id
    private String id;
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

    public void setId(String id) {
        this.id = id;
    }

    public Map<Integer, Integer> getPits() {
        return pits;
    }

    public void setPits(Map<Integer, Integer> pits) {
        this.pits = pits;
    }

    public Board move(Integer pit) {
        IntStream.iterate(1, pitCount -> pitCount + 1)
                .limit(pits.get(pit))
                .forEach(pitCount -> {
                    Integer currentPit = pit + pitCount;
                    if(currentPit > 14)
                        currentPit = 16 - currentPit;
                    if(currentPit.equals(pit)) return;
                    pits.put(currentPit, pits.get(currentPit) + 1);
                });
        pits.put(pit, 0);
        return this;
    }
}
