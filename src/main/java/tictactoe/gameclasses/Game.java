package tictactoe.gameclasses;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Game {
    @JsonProperty("Step")
    private ArrayList<StepJson> steps;

    public Game (){

    }

    public Game(ArrayList<String> stepsString) {
        this.steps = new ArrayList<>();
        for (int i = 0; i < stepsString.size(); i++) {
            steps.add(new StepJson(Integer.toString(i+1),Integer.toString((i+1)%2==0?2:1),stepsString.get(i)));
        }
    }

    public ArrayList<StepJson> getSteps() {
     return steps;
    }

    public void setSteps(ArrayList<StepJson> steps) {
        this.steps = steps;
    }
}
