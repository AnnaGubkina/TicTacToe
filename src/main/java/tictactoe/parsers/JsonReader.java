package tictactoe.parsers;

import com.fasterxml.jackson.databind.ObjectMapper;
import tictactoe.gameclasses.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class JsonReader implements Reader{

    private static final String[][] field = {{"1", "2", "3"}, {"4", "5", "6"}, {"7", "8", "9"}};

    public void read()  {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonModel jsonModel = null;
        try {
            jsonModel = objectMapper.readValue(new File("history.json"), JsonModel.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        printParsedObject(jsonModel);

    }

    private static void printParsedObject(JsonModel model) {
        printGamePlayInfo(model.getGameplay());
        System.out.println();
    }

    private static void printGamePlayInfo(Gameplay gameplay) {
        printPlayerInfo(gameplay.getPlayerJson());
        System.out.println();
        printGameInfo(gameplay.getGame());
        System.out.println();
        printGameResultInfo(gameplay.getResult());
    }

    private static void printGameResultInfo(GameResult result) {
        if(result.getPlayer().getId().equals("1") || result.getPlayer().getId().equals("2")) {
            printPlayerWinnerInfo(result.getPlayer());}
    }

    private static void printPlayerWinnerInfo(Player player) {
        System.out.println("Player " + player.getId()+ " -> " + player.getName() + " is winner as " + "'" + player.getSymbol()+ "'!");
    }

    private static void printGameInfo(Game game) {
        printStepInfo(game.getSteps());
    }

    private static void printStepInfo(ArrayList<StepJson> steps) {
        for (int y = 0; y < steps.size(); y++) {
            StepJson step = steps.get(y);
            String text = steps.get(y).getText();

            switch (text) {
                case "11":
                    if (step.getPlayerId().equals("1")) {
                        field[0][0] = "X";
                    } else if (step.getPlayerId().equals("2")) {
                        field[0][0] = "0";
                    }
                    break;
                case "12":
                    if (step.getPlayerId().equals("1")) {
                        field[0][1] = "X";
                    } else if (step.getPlayerId().equals("2")) {
                        field[0][1] = "0";
                    }
                    break;
                case "13":
                    if (step.getPlayerId().equals("1")) {
                        field[0][2] = "X";
                    } else if (step.getPlayerId().equals("2")) {
                        field[0][2] = "0";
                    }
                    break;
                case "21":
                    if (step.getPlayerId().equals("1")) {
                        field[1][0] = "X";
                    } else if (step.getPlayerId().equals("2")) {
                        field[1][0] = "0";
                    }
                    break;
                case "22":
                    if (step.getPlayerId().equals("1")) {
                        field[1][1] = "X";
                    } else if (step.getPlayerId().equals("2")) {
                        field[1][1] = "0";
                    }
                    break;
                case "23":
                    if (step.getPlayerId().equals("1")) {
                        field[1][2] = "X";
                    } else if (step.getPlayerId().equals("2")) {
                        field[1][2] = "0";
                    }
                    break;
                case "31":
                    if (step.getPlayerId().equals("1")) {
                        field[2][0] = "X";
                    } else if (step.getPlayerId().equals("2")) {
                        field[2][0] = "0";
                    }
                    break;
                case "32":
                    if (step.getPlayerId().equals("1")) {
                        field[2][1] = "X";
                    } else if (step.getPlayerId().equals("2")) {
                        field[2][1] = "0";
                    }
                    break;
                case "33":
                    if (step.getPlayerId().equals("1")) {
                        field[2][2] = "X";
                    } else if (step.getPlayerId().equals("2")) {
                        field[2][2] = "0";
                    }
                    break;
                default:
                    break;
            }

            printFullField(field);
        }
        System.out.println();
    }

    static void printFullField(String[][] field) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(" " + field[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void printPlayerInfo(ArrayList<Player> playerJson) {
        System.out.println("Players: ");
        for (Player player: playerJson) {
            System.out.println("Player id " + player.getId() + " Name " + player.getName() + " played " + player.getSymbol());
        }
    }
}



