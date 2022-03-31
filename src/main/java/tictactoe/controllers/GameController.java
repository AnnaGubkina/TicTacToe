package tictactoe.controllers;

import org.springframework.web.bind.annotation.*;
import tictactoe.dao.GameDao;
import tictactoe.gameclasses.Player;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.List;


@Controller
@RequestMapping("/gameplay")
public class GameController {

    public GameDao gameDao;
    public List<Player> players;
    public String[][] field;
    public final static String EMPTY = " - ";
    public final static int SIZE = 3;
    private static int PEOPLE_RATING;


    public GameController(GameDao gameDao, List<Player> players) {
        this.gameDao = gameDao;
        this.players = players;
        this.field = new String[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                field[i][j] = EMPTY;
            }
        }
    }

    @GetMapping()
    public String start(Model model) {
        model.addAttribute("player", new Player());
        return "gameplay/start";
    }

    @PostMapping
    public String create(@ModelAttribute("player") Player player) {
        //сохраняем нового человека и передаем на отображение в представлении на редирект
        gameDao.save(player);
        return "redirect:/gameplay/players";

    }

    @GetMapping("/players/game")
    public String game(@RequestParam(value = "step", required = false) String step,
                       @RequestParam(value = "symbol", required = false) String symbol,
                       Model model) {

        gameDao.gameProgress(step, symbol, field);

        model.addAttribute("field1", field[0][0] + "  " + field[0][1] + "  " + field[0][2]);
        model.addAttribute("field2", field[1][0] + "  " + field[1][1] + "  " + field[1][2]);
        model.addAttribute("field3", field[2][0] + "  " + field[2][1] + "  " + field[2][2]);
        if (gameDao.whoIsWin(field, symbol)) {
            if (symbol.equals("X")) {
                gameDao.players.get(0).setRating(++PEOPLE_RATING);
            } else {
                gameDao.players.get(1).setRating(++PEOPLE_RATING);
            }
        }

        if (gameDao.whoIsWin(field, symbol)) {
            model.addAttribute("winner", symbol.equals("X") ?
                    "Winner is " + gameDao.players.get(0).getName() + "  rating - " + gameDao.players.get(0).getRating()
                            + " , " + gameDao.players.get(1).getName() + "  rating - " + gameDao.players.get(1).getRating() :
                    "Winner is " + gameDao.players.get(1).getName() + " rating - " + gameDao.players.get(1).getRating()
                            + " , " + gameDao.players.get(0).getName() + " rating - " + gameDao.players.get(0).getRating());
        }
        if (gameDao.isDraw(field)) {
            model.addAttribute("isDraw", "Draw!");
        }

        return "gameplay/game";
    }

    @PostMapping("/players/game")
    public String updateField() {
        gameDao.updateField(field);
        return "gameplay/game";
    }

    @GetMapping("/players")
    public String showPlayers(Model model) {
        //получим всех игроков из DAO и перейдать на представление
        model.addAttribute("players", gameDao.showPlayers());
        return "gameplay/players";
    }

    @GetMapping("/players/{id}")
    public String show(@PathVariable("id") String id, Model model) {
        //получим одного человека из DAO и перейдадим на отображение в представление
        model.addAttribute("player", gameDao.show(id));
        return "gameplay/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") String id) {
        //@PathVariable извлекает id данного человека и кладет в переменную String id
        //запрашиваем определенного человека по id и помещаем его в модель(с этим айди)
        //Этого человека мы будем отражать в форме редактирования
        //Этот человек будет доступен в модели в нашем представлении
        model.addAttribute("player", gameDao.show(id));
        return "gameplay/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") Player player, @PathVariable("id") String id) {
        //обновляем человека, если такое понадобится в будущем расширении игры
        gameDao.update(id, player);
        return "redirect:/gameplay/players";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") String id) {
        //удаляем игрока по id
        gameDao.delete(id);
        return "redirect:/gameplay/players";
    }
}
