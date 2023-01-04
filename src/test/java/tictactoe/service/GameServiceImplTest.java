package tictactoe.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tictactoe.models.Player;
import tictactoe.repository.PlayerRepositoryImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@DisplayName("Testing game service functionality.")
public class GameServiceImplTest {


    @InjectMocks
    GameServiceImpl gameService;

    @Mock
    PlayerRepositoryImpl playerRepository;


    @Test
    @DisplayName("Получить всех игроков. Должно пройти успешно.")
    void showPlayers_Test() throws SQLException {
        String id = "1";
        String name = "Anna";
        String symbol = "X";

        String id2 = "2";
        String name2 = "Olga";
        String symbol2 = "0";

        Player newPlayerOne = new Player(id, name, symbol);
        Player newPlayerTwo = new Player(id2, name2, symbol2);
        List<Player> expectedPlayers = new ArrayList<>();
        expectedPlayers.add(newPlayerOne);
        expectedPlayers.add(newPlayerTwo);

        when(playerRepository.showPlayers()).thenReturn(expectedPlayers);


        List<Player> actualPlayers = gameService.showPlayers();
        assertEquals(expectedPlayers, actualPlayers);

    }

    @Test
    @DisplayName("Получить игрока по ID. Должно пройти успешно.")
    void show_Test() throws SQLException {
        String id = "1";
        String name = "Tomas";
        String symbol = "X";
        Player player = new Player(id, name, symbol);

        when(playerRepository.show(id)).thenReturn(player);

        Player actual = gameService.show(id);
        assertEquals(player, actual);


    }

    @Test
    @DisplayName("Сохранить игрока по ID. Должно пройти успешно.")
    void save_Test() throws SQLException {
        String id = "1";
        String name = "Tomas";
        String symbol = "X";
        Player player = new Player(id, name, symbol);

        playerRepository.save(player);
        when(playerRepository.show(id)).thenReturn(player);

        Player actual = gameService.show(player.getId());

        assertEquals(player, actual);
    }

    @Test
    @DisplayName("Прогресс игры. Должно пройти успешно.")
    void gameProgress_Test() {

        String step = "22";
        String symbol = "X";
        String[][] fieldActual = new String[][]{{" - ", " - ", " - "}, {" - ", " - ", " - "}, {" - ", " - ", " - "}};
        String[][] fieldExpected = new String[][]{{" - ", " - ", " - "}, {" - ", "X", " - "}, {" - ", " - ", " - "}};

        gameService.gameProgress(step, symbol, fieldActual);
        Assertions.assertArrayEquals(fieldExpected, fieldActual);

    }

    @Test
    @DisplayName("Определение победителя. Должно пройти успешно.")
    void whoIsWin_Test() {

        String[][] field = new String[][]{{"X", " - ", " - "}, {" - ", "X", " - "}, {" - ", " - ", "X"}};
        Player winnerExpected = new Player("1", "Tomas", "X");

        boolean winnerIsTomas = gameService.whoIsWin(field, winnerExpected.getSymbol());
        Assertions.assertTrue(winnerIsTomas);

    }

    @Test
    @DisplayName("Ничья. Должно пройти успешно.")
    void isDraw_Test() {

        String[][] field = new String[][]{{"X", " 0 ", "0"}, {"0", "X", "X"}, {"X", "0", "0"}};

        boolean isDraw = gameService.isDraw(field);
        Assertions.assertTrue(isDraw);

    }

    @Test
    @DisplayName("Обновление рейтинга. Должно пройти успешно.")
    void updateRating_Test() throws SQLException {
        String[][] field = new String[][]{{"X", " - ", " - "}, {" - ", "X", " - "}, {" - ", " - ", "X"}};
        String symbolOfTheWinner = "X";
        boolean isWinner = gameService.whoIsWin(field, symbolOfTheWinner);
        Player expected = new Player("1", "Tomas", "X", 1);

        Player winner = new Player("1", "Tomas", symbolOfTheWinner);
        when(playerRepository.show("1")).thenReturn(expected);

        gameService.updateRating(field, symbolOfTheWinner);
        winner = gameService.show("1");

        assertTrue(isWinner);
        assertEquals(expected, winner);

    }

    @Test
    @DisplayName("Обновление игрока. Должно пройти успешно.")
    void update_Test() throws SQLException {
        String id = "1";
        Player playerFromBD = new Player(id, "Tomas", "X");

        String newName = "Anna";
        Player updatedPlayer = new Player("1", newName, "X");

        //when
        when(playerRepository.show(id)).thenReturn(playerFromBD).thenReturn(updatedPlayer);
        gameService.update(id, updatedPlayer);
        Player updated = gameService.show(id);

        //then
        Mockito.verify(playerRepository, times(1))
                .update(id, updatedPlayer);

        assertEquals(updatedPlayer,updated);
    }

    @Test
    @DisplayName("Удаление игрока. Должно пройти успешно.")
    void delete_Test() throws SQLException {
        String id = "1";
        Player playerFromBD = new Player(id, "Tomas", "X");

        //when
        when(playerRepository.show(id)).thenReturn(playerFromBD);
        gameService.delete(id);
        when(playerRepository.show(id)).thenReturn(null);
        Player deleted = gameService.show(id);

        //then
        Mockito.verify(playerRepository, times(1))
                .delete(id);
        assertNull(deleted);

    }
}
