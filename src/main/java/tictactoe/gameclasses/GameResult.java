package tictactoe.gameclasses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GameResult {

    @JsonProperty("Player")
    Player player;

    public GameResult() {
    }

    public GameResult(String[] playerWinner) {
       // if(playerWinner[0].equals("1") || playerWinner[0].equals("2")) {
            this.player = new Player(playerWinner[0], playerWinner[1], playerWinner[2]);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
