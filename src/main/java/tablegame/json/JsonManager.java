package tablegame.json;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JsonManager {
    private JsonResult jsonResult;
    private ObjectMapper objectMapper = new ObjectMapper();
    private static File file = new File("results.json");

    /**
     *
     * @param jsonResult results to be saved
     */
    public JsonManager(JsonResult jsonResult) {
        this.jsonResult = jsonResult;
    }

    /**
     *Saves the result to a json file
     */
    public void save(){
        try {
            List<JsonResult> resultList;
            resultList = load();
            resultList.add(jsonResult);
            ObjectMapper mapper = new ObjectMapper();
            ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
            writer.writeValue(Paths.get("results.json").toFile(), resultList);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @return the json file
     */
    public static File getFile() {
        return file;
    }

    /**
     * Loads the data from json file
     * @return the list of results
     */
    public ArrayList<JsonResult> load() {
        ArrayList<JsonResult> results = new ArrayList<>();
        if(file.length() != 0){
            try {
                results = objectMapper.readValue(file, new TypeReference<>(){});
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return results;
    }

    /**
     * Clears the content of the json file
     */
    public void clearFile() {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("results.json");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        writer.print("");
        writer.close();
    }
}

