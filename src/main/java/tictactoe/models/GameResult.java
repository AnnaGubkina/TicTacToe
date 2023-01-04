package tictactoe.models;

import com.fasterxml.jackson.annotation.JsonProperty;



public class GameResult {

    @JsonProperty("Player")
    Player player;

    public GameResult() {
    }

    public GameResult(Player player) {

            this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }


    @Override
    public String toString() {
        return " RESULT: " + "\n"
                + player.getName()  +" is winner as '" + player.getSymbol() +  "' !";
    }
}
