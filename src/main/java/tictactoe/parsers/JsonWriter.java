package tictactoe.parsers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import tictactoe.gameclasses.JsonModel;

import java.io.File;
import java.io.IOException;

public class JsonWriter implements Writer{
    JsonModel model;
    private final static String baseFile = "history.json";

    public JsonWriter(JsonModel model) {
        this.model = model;
    }

    @Override
    public void write() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            mapper.writeValue(new File(baseFile), model);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("json created!");
    }
}
