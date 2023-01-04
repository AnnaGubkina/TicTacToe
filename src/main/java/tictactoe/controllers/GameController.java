package tictactoe.controllers;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tictactoe.service.GameService;
import tictactoe.models.Player;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;


@Controller
@RequestMapping("/gameplay")
public class GameController {

    public GameService gameService;
    public List<Player> players;
    public String[][] field;
    public final static String EMPTY = " - ";
    public final static int SIZE = 3;


    public GameController(GameService gameService, List<Player> players) {
        this.gameService = gameService;
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
    public String create(@ModelAttribute("player") @Valid Player player, BindingResult bindingResult) throws SQLException {
        if (bindingResult.hasErrors()) {
            return "gameplay/start";
        }
        gameService.save(player);
        return "redirect:/gameplay/players";

    }

    @GetMapping("/players/game")
    public String game(@RequestParam(value = "step", required = false) String step,
                       @RequestParam(value = "symbol", required = false) String symbol,
                       Model model) throws SQLException {

        gameService.gameProgress(step, symbol, field);

        model.addAttribute("field1", field[0][0] + "  " + field[0][1] + "  " + field[0][2]);
        model.addAttribute("field2", field[1][0] + "  " + field[1][1] + "  " + field[1][2]);
        model.addAttribute("field3", field[2][0] + "  " + field[2][1] + "  " + field[2][2]);


        if (gameService.whoIsWin(field, symbol)) {
            gameService.updateRating(field, symbol);
            model.addAttribute("winner", symbol.equals("X") ?
                    "Winner is " + gameService.show("1").getName() + "  rating - " + gameService.show("1").getRating()
                            + " , " + gameService.show("2").getName() + "  rating - " + gameService.show("2").getRating():
                    "Winner is " + gameService.show("2").getName() + " rating - " + gameService.show("2").getRating()
                            + " , " + gameService.show("1").getName() + " rating - " + gameService.show("1").getRating());

        }

        if (gameService.whoIsWin(field, symbol)) {
            gameService.writeToXML();
            gameService.writeToJson();
        }

        if (gameService.isDraw(field)) {
            model.addAttribute("isDraw", " DRAW! FRIENDSHIP WON!");
        }

        return "gameplay/game";
    }

    @PostMapping("/players/game")
    public String updateField() {
        gameService.updateField(field);
        return "gameplay/game";
    }


    @GetMapping("/players")
    public String showPlayers(Model model) throws SQLException {
        model.addAttribute("players", gameService.showPlayers());
        return "gameplay/players";
    }

    @GetMapping("/players/{id}")
    public String show(@PathVariable("id") String id, Model model) throws SQLException {
        model.addAttribute("player", gameService.show(id));
        return "gameplay/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") String id) throws SQLException {
        model.addAttribute("player", gameService.show(id));
        return "gameplay/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Player player, BindingResult bindingResult, @PathVariable("id") String id) throws SQLException {
        if (bindingResult.hasErrors()) {
            return "gameplay/edit";
        }
        gameService.update(id, player);
        return "redirect:/gameplay/players";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") String id) throws SQLException {
        gameService.delete(id);
        return "redirect:/gameplay/players";
    }


    @GetMapping("/players/game/result")
    @ResponseBody
    public String getResultFromXML() {
        return gameService.readFromJson();

    }
}
