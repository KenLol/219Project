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
    
    ArrayList<Pitcher> arrP = new ArrayList<Pitcher>();
    ArrayList<Hitter> arrH = new ArrayList<Hitter>();
    
    
    public JsonDraftFileManager(){
        
    }
    
    public void loadPitchers() throws IOException{
        JsonObject json = loadJSONFile("./data/Pitchers.json");
        JsonArray jsonPitcherArray = json.getJsonArray("Pitchers");
        for(int i = 0; i< jsonPitcherArray.size(); i++){
            JsonObject jso = jsonPitcherArray.getJsonObject(i);
            String a = jso.getString("LAST_NAME");
            String b = jso.getString("FIRST_NAME");
            String c = jso.getString("TEAM");
            String d = jso.getString("YEAR_OF_BIRTH");
            String e = jso.getString("W");
            String f = jso.getString("SV");
            String g = jso.getString("K");
            String h = jso.getString("NOTES");
            String j = jso.getString("NATION_OF_BIRTH");
            String k = jso.getString("IP");
            String l = jso.getString("ER");
            String m = jso.getString("H");
            String n = jso.getString("BB");
            
            Pitcher p = new Pitcher(a,b);
            p.setProteam(c);
            p.setYear(d);
            p.setW(e);
            p.setSv(f);
            p.setK(g);
            p.setNote(h);
            p.setIp(k);
            p.setEr(l);
            p.setH(m);
            p.setBb(n);
            p.setNation(j);
            
            Double erDouble = Double.parseDouble(l);
            Double ipDouble = Double.parseDouble(k);
            Double eraDouble = erDouble * 9 / ipDouble;
            p.setEra(eraDouble);
            
            Double bbDouble = Double.parseDouble(n);
            Double hDouble = Double.parseDouble(m);
            Double whipDouble = (bbDouble + hDouble) / ipDouble;
            p.setWhip(whipDouble);
            
            arrP.add(p);
        }
        
       // arrP.get(1).toSTRING(); // to test
        
        
        
    }
    
    public void loadHitters() throws IOException{
        JsonObject json = loadJSONFile("./data/Hitters.json");
        JsonArray jsonHitterArray = json.getJsonArray("Hitters");
        for(int i = 0; i< jsonHitterArray.size(); i++){
            JsonObject jso = jsonHitterArray.getJsonObject(i);
            String a = jso.getString("LAST_NAME");
            String b = jso.getString("FIRST_NAME");
            String c = jso.getString("TEAM");
            String d = jso.getString("YEAR_OF_BIRTH");
            String e = jso.getString("QP");
            String f = jso.getString("AB");
            String g = jso.getString("R");
            String h = jso.getString("H");
            String j = jso.getString("HR");
            String k = jso.getString("RBI");
            String l = jso.getString("SB");
            String m = jso.getString("NOTES");
            String n = jso.getString("NATION_OF_BIRTH");
            
            
            Double hDouble = Double.parseDouble(h);
            Double abDouble = Double.parseDouble(f);
            Double baDouble = hDouble/abDouble;
            
            Hitter z = new Hitter(a, b);
            z.setLast(a);
            z.setFirst(b);
            z.setProteam(c);
            z.setYear(d);
            z.setPosition(e);
            z.setAb(f);
            z.setR(g);
            z.setH(h);
            z.setHr(j);
            z.setRbi(k);
            z.setSb(l);
            z.setNote(m);
            z.setNation(n);
            z.setBa(baDouble);
            
            arrH.add(z);
        }
        
        
        
                
                
                
        //arrH.get(1).toSTRING();
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
