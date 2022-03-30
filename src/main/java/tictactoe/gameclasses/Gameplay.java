package tictactoe.gameclasses;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Gameplay {

    @JsonProperty("Player")
    private ArrayList<Player> playerJson;

    @JsonProperty("Game")
    private Game game;

    @JsonProperty("GameResult")
    private GameResult result;

    public Gameplay(){

    }

    public Gameplay(ArrayList<Player> playerJson, Game game, GameResult result) {
        this.playerJson = playerJson;
        this.game = game;
        this.result = result;
    }

    public ArrayList<Player> getPlayerJson() {
        return playerJson;
    }

    public void setPlayerJson(ArrayList<Player> playerJson) {
        this.playerJson = playerJson;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public GameResult getResult() {
        return result;
    }

    public void setResult(GameResult result) {
        this.result = result;
    }

}
