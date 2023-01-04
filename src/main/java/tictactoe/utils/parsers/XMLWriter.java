package tictactoe.utils.parsers;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import tictactoe.models.Game;
import tictactoe.models.GameResult;
import tictactoe.models.Gameplay;
import tictactoe.models.Player;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XMLWriter implements Writer {

    private final ArrayList<String> steps;
    private final List<Player> players;
    private final Player winner;


    public XMLWriter(ArrayList<String> steps, List<Player> players, Player winner) {
        this.steps = steps;
        this.players = players;
        this.winner = winner;
    }


    /**
     * This function writes serializes the Java object into XML and writes it
     * into an XML file.
     */
    public void serialize() {

        try {
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);

            // serialize our Object into XML string
            String xmlString = xmlMapper.writeValueAsString(new Gameplay(players, new Game(steps), new GameResult(winner)));

            // write to the console
            System.out.println(xmlString);

            // write XML string to file
            File xmlOutput = new File("serialized.xml");
            FileWriter fileWriter = new FileWriter(xmlOutput);
            fileWriter.write(xmlString);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("xml created!");
    }

}
