package tictactoe.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import tictactoe.models.Player;

import java.sql.*;
import java.util.List;

@Component
public class PlayerRepositoryImpl implements PlayerRepository{

    JdbcTemplate jdbcTemplate;
    private static int PEOPLE_COUNT;
    private static int RATING_X;
    private static int RATING_0;

    @Autowired
    public PlayerRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Player> showPlayers() throws SQLException {
        return jdbcTemplate.query("SELECT *FROM players", new BeanPropertyRowMapper<>(Player.class));
    }

    public Player show(String id) throws SQLException {
        return jdbcTemplate.query("SELECT * FROM players WHERE Id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Player.class))
                .stream().findAny().orElse(null);
    }

    public void save(Player player) throws SQLException {
        player.setId(String.valueOf(++PEOPLE_COUNT));
        Player playerFromBd = show(player.getId());
        if (playerFromBd == null) {
            if (player.getId().equals("1")) {
                jdbcTemplate.update("INSERT INTO players VALUES(1,?,?,?)", player.getName(), "X", player.getRating());
            } else if (player.getId().equals("2")) {
                jdbcTemplate.update("INSERT INTO players VALUES(2,?,?,?)", player.getName(), "0", player.getRating());
            }
        } else if(playerFromBd.getId().equals(player.getId())) {
            jdbcTemplate.update("UPDATE players SET name=?, rating=? WHERE id=?", player.getName(), 0,  player.getId());
        }
    }

    public void updateRating(String id, Player updatedPlayer) throws SQLException {
        if (updatedPlayer.getId().equals("1")) {
            jdbcTemplate.update("UPDATE players SET name=?, symbol=?, rating=? WHERE id=?", updatedPlayer.getName(), updatedPlayer.getSymbol(), ++RATING_X, id);
        } else if (updatedPlayer.getId().equals("2")) {
            jdbcTemplate.update("UPDATE players SET name=?, symbol=?, rating=? WHERE id=?", updatedPlayer.getName(), updatedPlayer.getSymbol(), ++RATING_0, id);
        }
    }

    public void update(String id, Player updatedPlayer) throws SQLException {
        jdbcTemplate.update("UPDATE players SET name=?, symbol=?, rating=? WHERE id=?", updatedPlayer.getName(), updatedPlayer.getSymbol(), updatedPlayer.getRating(), id);

    }

    public void delete(String id) throws SQLException {
        jdbcTemplate.update("DELETE FROM players WHERE id=?", id);
    }
}
