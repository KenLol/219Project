/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FantasyBaseball;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonWriter;
import javax.json.JsonValue;

/**
 *
 * @author Kenneth
 */
public class JsonDraftFileManager {
    
    
    public JsonDraftFileManager(){
        
    }
    
    public void loadPitchers() throws IOException{
        JsonObject json = loadJSONFile("./data/Pitchers.json");
        JsonArray jsonPitcherArray = json.getJsonArray("Pitchers");
        for(int i = 0; i< jsonPitcherArray.size(); i++){
            JsonObject jso = jsonPitcherArray.getJsonObject(i);
            String a = jso.getString("TEAM");
            System.out.println(a);
        }
    }
    
    
    
     // LOADS A JSON FILE AS A SINGLE OBJECT AND RETURNS IT
    private JsonObject loadJSONFile(String jsonFilePath) throws IOException {
        InputStream is = new FileInputStream(jsonFilePath);
        JsonReader jsonReader = Json.createReader(is);
        JsonObject json = jsonReader.readObject();
        jsonReader.close();
        is.close();
        return json;
    }
}
