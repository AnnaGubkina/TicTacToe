package tictactoe.utils.parsers;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import tictactoe.models.Gameplay;
import tictactoe.models.Player;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class XMLReader implements Reader {

    public String deserialize() {
        try {
            XmlMapper xmlMapper = new XmlMapper();

            // read file and put contents into the string
            String readContent = new String(Files.readAllBytes(Paths.get("serialized.xml")));

            // deserialize from the XML into a GamePlay object
            Gameplay deserializedData = xmlMapper.readValue(readContent, Gameplay.class);
            List<Player> players = deserializedData.getPlayer();
            String result = deserializedData.getResult().toString();
            String play = String.join((CharSequence) ", ", players.toString());

            return play + result;

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
