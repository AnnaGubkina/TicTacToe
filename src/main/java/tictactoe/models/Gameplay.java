package tictactoe.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Gameplay {

    @JsonProperty("Player")
    private List<Player> player;

    @JsonProperty("Game")
    private Game game;

    @JsonProperty("GameResult")
    private GameResult result;

    public Gameplay() {
    }

    public Gameplay(List<Player> player, Game game, GameResult result) {
        this.player = player;
        this.game = game;
        this.result = result;
    }


    public List<Player> getPlayer() {
        return player;
    }

    public void setPlayer(ArrayList<Player> player) {
        this.player = player;
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


    @Override
    public String toString() {
        return
                "Player " + player +
                ", game " + game +
                ", result " + result +
                '}';
    }
}
