package tictactoe.gameclasses;

public class Step {

    String numStep;
    String value;

    public Step(String numStep, String value) {
        this.numStep = numStep;
        this.value = value;
    }

    public String getNumStep() {
        return numStep;
    }

    public String getValue() {
        return value;
    }

    public void setNumStep(String numStep) {
        this.numStep = numStep;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
