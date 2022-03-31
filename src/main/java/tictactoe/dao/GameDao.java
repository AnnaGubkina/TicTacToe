package tictactoe.dao;

import tictactoe.gameclasses.Player;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class GameDao {

    public final static String EMPTY = " - ";
    public final static String CROSS = "X";
    public final static String ZERO = "0";
    public final static int SIZE = 3;


    private static int PEOPLE_COUNT;
    public List<Player> players = new ArrayList<>();


    public List<Player> showPlayers() {
        return players;
    }

    public Player show(String id) {
        return players.stream().filter(player -> player.getId().equals(id)).findAny().orElse(null);
    }

    public void save(Player player) {
        player.setId(String.valueOf(++PEOPLE_COUNT));
        if (player.getId().equals("1")) {
            player.setSymbol(CROSS);
            players.add(player);
        } else if (player.getId().equals("2")) {
            player.setSymbol(ZERO);
            players.add(player);
        }
    }

    public void gameProgress(String step, String symbol, String[][] field) {
        switch (step) {
            case "11":
                if (symbol.equals("X")) {
                    field[0][0] = "X";
                } else if (symbol.equals("0")) {
                    field[0][0] = "0";
                }
                break;
            case "12":
                if (symbol.equals("X")) {
                    field[0][1] = "X";
                } else if (symbol.equals("0")) {
                    field[0][1] = "0";
                }
                break;
            case "13":
                if (symbol.equals("X")) {
                    field[0][2] = "X";
                } else if (symbol.equals("0")) {
                    field[0][2] = "0";
                }
                break;
            case "21":
                if (symbol.equals("X")) {
                    field[1][0] = "X";
                } else if (symbol.equals("0")) {
                    field[1][0] = "0";
                }
                break;
            case "22":
                if (symbol.equals("X")) {
                    field[1][1] = "X";
                } else if (symbol.equals("0")) {
                    field[1][1] = "0";
                }
                break;
            case "23":
                if (symbol.equals("X")) {
                    field[1][2] = "X";
                } else if (symbol.equals("0")) {
                    field[1][2] = "0";
                }
                break;
            case "31":
                if (symbol.equals("X")) {
                    field[2][0] = "X";
                } else if (symbol.equals("0")) {
                    field[2][0] = "0";
                }
                break;
            case "32":
                if (symbol.equals("X")) {
                    field[2][1] = "X";
                } else if (symbol.equals("0")) {
                    field[2][1] = "0";
                }
                break;
            case "33":
                if (symbol.equals("X")) {
                    field[2][2] = "X";
                } else if (symbol.equals("0")) {
                    field[2][2] = "0";
                }
                break;
            default:
                break;
        }
    }

    public boolean whoIsWin(String[][] field, String player) {
        if (field[0][0].equals(player) && field[0][1].equals(player) && field[0][2].equals(player))
            return true;
        if (field[1][0].equals(player) && field[1][1].equals(player) && field[1][2].equals(player))
            return true;
        if (field[2][0].equals(player) && field[2][1].equals(player) && field[2][2].equals(player))
            return true;
        if (field[0][0].equals(player) && field[1][0].equals(player) && field[2][0].equals(player))
            return true;
        if (field[0][1].equals(player) && field[1][1].equals(player) && field[2][1].equals(player))
            return true;
        if (field[0][2].equals(player) && field[1][2].equals(player) && field[2][2].equals(player))
            return true;
        if (field[0][0].equals(player) && field[1][1].equals(player) && field[2][2].equals(player))
            return true;
        if (field[2][0].equals(player) && field[1][1].equals(player) && field[0][2].equals(player))
            return true;

        return false;
    }

    public boolean isDraw(String[][] field) {
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++)
                if (field[row][col] == EMPTY)
                    return false;
        return true;
    }

    public void updateField(String[][] field) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                field[i][j] = EMPTY;
            }
        }
    }


    public void update(String id, Player updatedPlayer) {
        //находим человека по id  с помощью метода show
        Player playerToBeUpdated = show(id);
        playerToBeUpdated.setName(updatedPlayer.getName());
    }

    public void delete(String id) {
        players.removeIf(p -> p.getId().equals(id));
    }

}
