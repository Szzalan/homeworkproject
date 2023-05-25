package jsontest;

import org.junit.jupiter.api.Test;
import tablegame.json.JsonManager;
import tablegame.json.JsonResult;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class JsonManagerTest {
    JsonManager jsonManager = new JsonManager(new JsonResult("test",1));
    @Test
    void testSaveAndLoadWhenFileIsEmpty() {
        jsonManager.clearFile();
        jsonManager.save();

        ArrayList<JsonResult> results = jsonManager.load();

        assertEquals(1,results.size());
        assertEquals("test",results.get(0).getWinner());
        assertEquals(1,results.get(0).getStepCounter());
    }
    @Test
    void testSaveAndLoadWhenFileIsNotEmpty() {
        if(JsonManager.getFile().length() == 0)
            jsonManager.save();
        jsonManager.save();
        ArrayList<JsonResult> results = jsonManager.load();

        assertTrue(results.size() > 1);
        assertEquals("test",results.get(results.size()-1).getWinner());
        assertEquals(1,results.get(results.size()-1).getStepCounter());
    }
    @Test
    void clearFile() {
        if(JsonManager.getFile().length() == 0)
            jsonManager.save();
        assertTrue(JsonManager.getFile().length() > 0);
        jsonManager.clearFile();
        assertEquals(0, JsonManager.getFile().length());
    }
}