package vinicius.kalah.dto;

import java.util.Map;

public class GameMoveDTO {
    private String id;
    private String url;
    private Map<Integer, Integer> status;

    public GameMoveDTO() {
    }

    public GameMoveDTO(String id, String url, Map<Integer, Integer> status) {
        this.id = id;
        this.url = url;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public Map<Integer, Integer> getStatus() {
        return status;
    }
}
