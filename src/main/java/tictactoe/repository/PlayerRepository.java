package tictactoe.repository;

import tictactoe.models.Player;

import java.sql.SQLException;
import java.util.List;

public interface PlayerRepository {

    List<Player> showPlayers() throws SQLException ;

    Player show(String id) throws SQLException;

    void save(Player player) throws SQLException ;

    void updateRating(String id, Player updatedPlayer) throws SQLException;

    void update(String id, Player updatedPlayer) throws SQLException ;

    void delete(String id) throws SQLException ;


}
