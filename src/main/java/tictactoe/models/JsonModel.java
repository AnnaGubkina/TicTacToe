package tictactoe.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonModel {

    @JsonProperty("Gameplay")
    private Gameplay gameplay;

    public JsonModel() {}

    public JsonModel(Gameplay gameplay) {
        this.gameplay = gameplay;
    }

    public Gameplay getGameplay() {
        return gameplay;
    }

    public void setGameplay(Gameplay gameplay) {
        this.gameplay = gameplay;
    }

}
