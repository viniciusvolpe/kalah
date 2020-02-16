package vinicius.kalah.dto;

public class GameResponseDTO {

    private String id;
    private String uri;

    public GameResponseDTO() {
    }

    public GameResponseDTO(String id, String uri) {
        this.id = id;
        this.uri = uri;
    }

    public String getId() {
        return id;
    }

    public String getUri() {
        return uri;
    }
}
