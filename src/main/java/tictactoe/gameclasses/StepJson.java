package tictactoe.gameclasses;

public class StepJson {

    String num;
    String playerId;
    String text;

    public StepJson (){}

    public StepJson(String num, String playerId, String text) {
        this.num = num;
        this.playerId = playerId;
        this.text = text;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
