package tictactoe.utils.parsers;

import com.fasterxml.jackson.databind.ObjectMapper;
import tictactoe.models.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class JsonReader implements Reader {


    @Override
    public String deserialize() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonModel jsonModel = objectMapper.readValue(Files.readAllBytes(Paths.get("serialized.json")), JsonModel.class);

            List<Player> players = jsonModel.getGameplay().getPlayer();
            String result = jsonModel.getGameplay().getResult().toString();
            String play = String.join((CharSequence) ", ", players.toString());
            System.out.println(result);
            System.out.println(play);

            return play + result;


        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}



