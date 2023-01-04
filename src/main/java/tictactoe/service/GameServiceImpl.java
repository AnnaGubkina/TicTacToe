package tictactoe.service;

import org.springframework.stereotype.Component;
import tictactoe.exceptions.NoSuchPlayerException;
import tictactoe.exceptions.NotFoundException;
import tictactoe.models.*;
import tictactoe.repository.PlayerRepository;
import tictactoe.utils.parsers.*;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Component
public class GameServiceImpl implements GameService {

    PlayerRepository playerRepository;

    private final static String EMPTY = " - ";
    private final static String CROSS = "X";
    private final static String ZERO = "0";
    private final static int SIZE = 3;

    private static final ArrayList<String> steps = new ArrayList<>();
    private List<Player> players;
    private static Player winner;


    public GameServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> showPlayers() {
        try {
            return playerRepository.showPlayers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Player show(String id) {
        try {
            return playerRepository.show(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void save(Player player) {
        try {
            playerRepository.save(player);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void gameProgress(String step, String symbol, String[][] field) {
        if (step == null) {
            throw new NotFoundException("Step is null");
        } else if (symbol == null) {
            throw new NotFoundException("Symbol is null");
        } else if (field == null) {
            throw new NotFoundException("Field is null");
        }

        steps.add(step);
        switch (step) {
            case "11":
                if (field[0][0].equals(EMPTY)) {
                    if (symbol.equals(CROSS)) {
                        field[0][0] = CROSS;
                    } else if (symbol.equals(ZERO)) {
                        field[0][0] = ZERO;
                    }
                }
                break;
            case "12":
                if (field[0][1].equals(EMPTY)) {
                    if (symbol.equals(CROSS)) {
                        field[0][1] = CROSS;
                    } else if (symbol.equals(ZERO)) {
                        field[0][1] = ZERO;
                    }
                }
                break;
            case "13":
                if (field[0][2].equals(EMPTY)) {
                    if (symbol.equals(CROSS)) {
                        field[0][2] = CROSS;
                    } else if (symbol.equals(ZERO)) {
                        field[0][2] = ZERO;
                    }
                }
                break;
            case "21":
                if (field[1][0].equals(EMPTY)) {
                    if (symbol.equals(CROSS)) {
                        field[1][0] = CROSS;
                    } else if (symbol.equals(ZERO)) {
                        field[1][0] = ZERO;
                    }
                }
                break;
            case "22":
                if (field[1][1].equals(EMPTY)) {
                    if (symbol.equals(CROSS)) {
                        field[1][1] = CROSS;
                    } else if (symbol.equals(ZERO)) {
                        field[1][1] = ZERO;
                    }
                }
                break;
            case "23":
                if (field[1][2].equals(EMPTY)) {
                    if (symbol.equals(CROSS)) {
                        field[1][2] = CROSS;
                    } else if (symbol.equals(ZERO)) {
                        field[1][2] = ZERO;
                    }
                }
                break;
            case "31":
                if (field[2][0].equals(EMPTY)) {
                    if (symbol.equals(CROSS)) {
                        field[2][0] = CROSS;
                    } else if (symbol.equals(ZERO)) {
                        field[2][0] = ZERO;
                    }
                }
                break;
            case "32":
                if (field[2][1].equals(EMPTY)) {
                    if (symbol.equals(CROSS)) {
                        field[2][1] = CROSS;
                    } else if (symbol.equals(ZERO)) {
                        field[2][1] = ZERO;
                    }
                }
                break;
            case "33":
                if (field[2][2].equals(EMPTY)) {
                    if (symbol.equals(CROSS)) {
                        field[2][2] = CROSS;
                    } else if (symbol.equals(ZERO)) {
                        field[2][2] = ZERO;
                    }
                }
                break;
            default:
                break;
        }

    }


    public boolean whoIsWin(String[][] field, String player) {
        if (field == null) {
            throw new NotFoundException("Symbol is null");
        } else if (player == null) {
            throw new NoSuchPlayerException("Player is null");
        }

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
        if (field == null) {
            throw new NotFoundException("Symbol is null");
        }
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++)
                if (field[row][col].equals(EMPTY))
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


    public void updateRating(String[][] field, String symbol) {
        if (whoIsWin(field, symbol)) {
            try {
                if (symbol.equals("X")) {
                    playerRepository.updateRating("1", show("1"));
                    winner = show("1");
                } else {
                    playerRepository.updateRating("2", show("2"));
                    winner = show("2");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public void update(String id, Player updatedPlayer) {
        try {
            final Player byId = playerRepository.show(id);
            if (byId != null) {
                playerRepository.update(id, updatedPlayer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(String id) {
        try {
            final Player byId = playerRepository.show(id);
            if (byId != null) {
                playerRepository.delete(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void writeToXML() {
        determineTheWinnerForResult();
        Writer writerXML = new XMLWriter(steps, players, winner);
        writerXML.serialize();
    }


    public String readFromXML() {
        Reader readerXML = new XMLReader();
        return readerXML.deserialize();
    }

    public void writeToJson() {
        determineTheWinnerForResult();
        JsonModel model = new JsonModel(new Gameplay(players, new Game(steps), new GameResult(winner)));
        Writer writerJson = new JsonWriter(model);
        writerJson.serialize();
        steps.clear();
    }


    public String readFromJson() {
        Reader readerJson = new JsonReader();
        return readerJson.deserialize();
    }

    private void determineTheWinnerForResult() {
        players = showPlayers();
        int firstPlayerRating = players.get(0).getRating();
        int secondPlayerRating = players.get(1).getRating();
        winner = firstPlayerRating > secondPlayerRating ? players.get(0) : players.get(1);
        if (firstPlayerRating == secondPlayerRating) {
            winner = new Player(" ", "FRIENDSHIP", "DRAW");
        }
    }
}
