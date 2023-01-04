package tictactoe.utils.parsers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import tictactoe.models.JsonModel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JsonWriter implements Writer {
    JsonModel model;
    private final static String baseFile = "serialized.json";

    public JsonWriter(JsonModel model) {
        this.model = model;
    }

    @Override
    public void serialize() {


        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            String json = objectMapper.writeValueAsString(model);
            System.out.println(json);

            // write XML string to file
            File xmlOutput = new File(baseFile);
            FileWriter fileWriter = new FileWriter(xmlOutput);
            fileWriter.write(json);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("json created!");
    }
}
