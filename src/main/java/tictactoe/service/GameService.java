package tictactoe.service;

import tictactoe.models.Player;

import java.util.List;

public interface GameService {

    List<Player> showPlayers();

    Player show(String id);

    void save(Player player);

    void gameProgress(String step, String symbol, String[][] field);

    boolean whoIsWin(String[][] field, String player);

    boolean isDraw(String[][] field);

    void updateField(String[][] field);

    void updateRating(String[][] field, String symbol);

    void update(String id, Player updatedPlayer);

    void delete(String id);

    void writeToXML();

    String readFromXML();

    void writeToJson();

    String readFromJson();

}
