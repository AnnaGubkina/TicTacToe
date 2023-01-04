package tictactoe.models;

import com.fasterxml.jackson.annotation.JsonProperty;


import java.util.ArrayList;

public class Game {
    @JsonProperty("Step")
    private ArrayList<Step> steps;

    public Game() {

    }

    public Game(ArrayList<String> stepsString) {
        this.steps = new ArrayList<>();
        for (int i = 1; i < stepsString.size(); i++) {
            steps.add(new Step(Integer.toString(i), stepsString.get(i)));
        }
    }

    public ArrayList<Step> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<Step> steps) {
        this.steps = steps;
    }

    @Override
    public String toString() {
        return "Game " + " steps: " + steps.toString();
    }
}
