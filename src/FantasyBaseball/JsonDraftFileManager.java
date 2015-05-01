/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FantasyBaseball;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonWriter;
import javax.json.JsonValue;

/**
 * This class grabs the information about the players out of the json file. 
 * @author Kenneth
 */
public class JsonDraftFileManager {
    
    ArrayList<Pitcher> arrP = new ArrayList<Pitcher>();
    ArrayList<Hitter> arrH = new ArrayList<Hitter>();
    
    ArrayList<Player> arr = new ArrayList<Player>();
    ArrayList<Player> all = new ArrayList<Player>();
    
    ObservableList<Superplayer> obp = FXCollections.observableArrayList();
    String draftName = "test";
    
    
    ArrayList<FantasyTeam> aft = new ArrayList<FantasyTeam>();
    
    
    public JsonDraftFileManager() {
        
    }
    
    public void loadPitchers() throws IOException{
        arrP.clear();
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
            
            Pitcher p = new Pitcher();
            p.setLast(a);
            p.setFirst(b);
            p.setProteam(c);
            p.setYear(Integer.parseInt(d));
            p.setRW(Integer.parseInt(e));
            p.setHRSV(Integer.parseInt(f));
            p.setRBIK(Integer.parseInt(g));
            p.setNote(h);
            p.setIp(k);
            p.setEr(l);
            p.setH(m);
            p.setBb(n);
            p.setNation(j);
            p.setPosition("P");
            
            Double erDouble = Double.parseDouble(l);
            Double ipDouble = Double.parseDouble(k);
            
            Double eraDouble = erDouble * 9 / ipDouble;
            
            if(!eraDouble.isNaN()){
            eraDouble = Math.round(eraDouble*1000)/1000.0d;
            }
            p.setSBERA(eraDouble);
            
            Double bbDouble = Double.parseDouble(n);
            Double hDouble = Double.parseDouble(m);
            Double whipDouble = (bbDouble + hDouble) / ipDouble;
            if(!whipDouble.isNaN()){
            whipDouble = Math.round(whipDouble*1000)/1000.0d;
            }
            p.setBAWHIP(whipDouble);
            
            arrP.add(p);
        }
        
       // arrP.get(1).toSTRING(); // to test
        
        
        
    }
    
    public void loadHitters() throws IOException{
        arrH.clear();
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
            
            Hitter z = new Hitter();
            z.setLast(a);
            z.setFirst(b);
            z.setProteam(c);
            z.setYear(Integer.parseInt(d));
            z.setPosition(e);
            z.setAb(f);
            z.setRW(Integer.parseInt(g));
            z.setH(h);
            z.setHRSV(Integer.parseInt(j));
            z.setRBIK(Integer.parseInt(k));
            
            z.setSBERA(Double.parseDouble(l));
            z.setNote(m);
            z.setNation(n);
            
            if(!baDouble.isNaN()){
            baDouble = Math.round(baDouble*1000)/1000.0d;
            }
            z.setBAWHIP(baDouble);
            
            z.fixPostion(); // adds the extra positions
            arrH.add(z);
        }
        
        
        
                
                
                
       // arrH.get(1).toSTRING();
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
    
    
    
    
    
    
    
    // reloads the hitters and pitchers from the json files.
    public void loadALL() throws IOException{
        this.loadHitters();
        this.loadPitchers();
    }
    
    public void loadC(){
        for(Hitter h : arrH){
            if(h.getPosition().contains("C_")){
                arr.add(h);
            }
        }
    }
    
    public void load1B(){
        for(Hitter h : arrH){
            if(h.getPosition().contains("1B")){
                arr.add(h);
            }
        }
    }
    
    public void loadCI(){
        for(Hitter h : arrH){
            if(h.getPosition().contains("CI")){
                arr.add(h);
            }
        }
    }
    
    public void load3B(){
        for(Hitter h : arrH){
            if(h.getPosition().contains("3B")){
                arr.add(h);
            }
        }
    }
    
    public void load2B(){
        for(Hitter h : arrH){
            if(h.getPosition().contains("2B")){
                arr.add(h);
            }
        }
    }
    
    public void loadMI(){
        for(Hitter h : arrH){
            if(h.getPosition().contains("MI")){
                arr.add(h);
            }
        }
    }
    
    public void loadSS(){
        for(Hitter h : arrH){
            if(h.getPosition().contains("SS")){
                arr.add(h);
            }
        }
    }
    
    public void loadOF(){
        for(Hitter h : arrH){
            if(h.getPosition().contains("OF")){
                arr.add(h);
            }
        }
    }
    
    public void loadU(){
        for(Hitter h : arrH){
            if(h.getPosition().contains("U")){
                arr.add(h);
            }
        }
    }
    
    public void loadA(){
        for(Hitter h: arrH){
            arr.add(h);
        }
        for(Pitcher p : arrP){
            arr.add(p);
        }
    }
    
    public void loadP(){
        for(Pitcher p : arrP){
            arr.add(p);
        }
    }
    
    public void loadH(){
        for(Hitter h : arrH){
            arr.add(h);
        }
    }
    
    public void emptyArr(){
        arr.clear();
    }
    public void emptyAll(){
        all.clear();
    }
    
    public void displayArr(){
        for(Player h : arr){
            h.toSTRING();
        }
        this.emptyArr(); // empty after use
    }
    
    //display search algo reults
    public void displayAll(){
        for(Player p : all){
            p.toSTRING();
        }
        this.emptyAll();
    }
    
    public void startWith(String a){
        a = a.toLowerCase();
        for(Player h : arr){
            String f = h.getFirst();
            String l = h.getLast();
            f = f.toLowerCase();
            l = l.toLowerCase();
            
            
            if(f.startsWith(a) || l.startsWith(a)){
                all.add(h);
            }
            
        }
    }
    
    
    public void clearobp(){
        obp.clear();
    }
    
    public void addobp(Superplayer p){
        obp.add(p);
    }
    
    public ObservableList<Superplayer> getobp(){
        return obp;
    }
    
    public void removeobpplayer(Superplayer p){
        obp.remove(p);
    }
    
    public void saveDraft(String a) throws FileNotFoundException{
        draftName = a;
        draftName = "./data/drafts/" + draftName + ".json";
        
        OutputStream os = new FileOutputStream(draftName);
        JsonWriter jsonWriter = Json.createWriter(os);
        
        JsonArray pitcherJsonArray = makePitcherJsonArray();
        JsonArray hitterJsonArray = makeHitterJsonArray();
        
        JsonObject draftJsonObject = Json.createObjectBuilder()
                                    .add("Pitchers", pitcherJsonArray)
                                    .add("Hitters", hitterJsonArray)
                .build();
        
        jsonWriter.writeObject(draftJsonObject);
    }
    
    public void loadDraft(String aaa) throws IOException{
        emptyAll();
        emptyArr();
        arrP.clear();
        arrH.clear();
        
        draftName = aaa;
        draftName = "./data/drafts/" + draftName;
        
        JsonObject json = loadJSONFile(draftName);
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
            
            Pitcher p = new Pitcher();
            p.setLast(a);
            p.setFirst(b);
            p.setProteam(c);
            p.setYear(Integer.parseInt(d));
            p.setRW(Integer.parseInt(e));
            p.setHRSV(Integer.parseInt(f));
            p.setRBIK(Integer.parseInt(g));
            p.setNote(h);
            p.setIp(k);
            p.setEr(l);
            p.setH(m);
            p.setBb(n);
            p.setNation(j);
            p.setPosition("P");
            
            Double erDouble = Double.parseDouble(l);
            Double ipDouble = Double.parseDouble(k);
            
            Double eraDouble = erDouble * 9 / ipDouble;
            
            if(!eraDouble.isNaN()){
            eraDouble = Math.round(eraDouble*1000)/1000.0d;
            }
            p.setSBERA(eraDouble);
            
            Double bbDouble = Double.parseDouble(n);
            Double hDouble = Double.parseDouble(m);
            Double whipDouble = (bbDouble + hDouble) / ipDouble;
            if(!whipDouble.isNaN()){
            whipDouble = Math.round(whipDouble*1000)/1000.0d;
            }
            p.setBAWHIP(whipDouble);
            
            arrP.add(p);
        
         }
        
        
        
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
            
            Hitter z = new Hitter();
            z.setLast(a);
            z.setFirst(b);
            z.setProteam(c);
            z.setYear(Integer.parseInt(d));
            z.setPosition(e);
            z.setAb(f);
            z.setRW(Integer.parseInt(g));
            z.setH(h);
            z.setHRSV(Integer.parseInt(j));
            z.setRBIK(Integer.parseInt(k));
            
            z.setSBERA(Double.parseDouble(l));
            z.setNote(m);
            z.setNation(n);
            
            if(!baDouble.isNaN()){
            baDouble = Math.round(baDouble*1000)/1000.0d;
            }
            z.setBAWHIP(baDouble);
            
            //z.fixPostion(); // adds the extra positions
            arrH.add(z);
        }
        
        
        
        
        
        
        
        
        
    }
    
    private JsonObject makePitcherJsonObject(Pitcher p){ //<- takes in a single pitcher
        JsonObject jso = Json.createObjectBuilder().add("TEAM", p.getProteam())
                                                    .add("LAST_NAME", p.getLast())
                                                    .add("FIRST_NAME", p.getFirst())
                                                    .add("IP", p.getIp())
                                                    .add("ER", p.getEr())
                                                    .add("W", Integer.toString(p.getRW()))
                                                    .add("SV", Integer.toString(p.getHRSV()))
                                                    .add("H", p.getH())
                                                    .add("BB", p.getBb())
                                                    .add("K", Integer.toString(p.getRBIK()))
                                                    .add("NOTES", p.getNote())
                                                    .add("YEAR_OF_BIRTH", Integer.toString(p.getYear()))
                                                    .add("NATION_OF_BIRTH", p.getNation())
                                                    .build();
        return jso;
    }

    private JsonArray makePitcherJsonArray() {
        JsonArrayBuilder jsb = Json.createArrayBuilder();
        
        emptyArr();
        emptyAll();
        loadP(); // loads only pitchers
        for(Player p : arr){
            Pitcher a = (Pitcher) p;
            jsb.add(makePitcherJsonObject(a));
        }
        
        JsonArray jA = jsb.build();
        return jA;
    }
    
    private JsonObject makeHitterJsonObject(Hitter a){ //<- takes in a single pitcher
        JsonObject jso = Json.createObjectBuilder().add("TEAM", a.getProteam())
                                                    .add("LAST_NAME", a.getLast())
                                                    .add("FIRST_NAME", a.getFirst())
                                                    .add("QP", a.getPosition())
                                                    .add("AB", a.getAb())
                                                    .add("R", Integer.toString(a.getRW()))
                                                    .add("H", a.getH())
                                                    .add("HR", Integer.toString(a.getHRSV()))
                                                    .add("RBI", Integer.toString(a.getRBIK()))
                                                    .add("SB", Double.toString(a.getSBERA()))
                                                    .add("NOTES", a.getNote())
                                                    .add("YEAR_OF_BIRTH", Integer.toString(a.getYear()))
                                                    .add("NATION_OF_BIRTH", a.getNation())
                                                    .build();
        return jso;
    }
    
    private JsonArray makeHitterJsonArray(){
        JsonArrayBuilder jsb = Json.createArrayBuilder();
        
        emptyArr();
        emptyAll();
        loadH();
        for(Player p : arr){
            Hitter a = (Hitter) p;
            jsb.add(makeHitterJsonObject(a));
        }
        JsonArray jA = jsb.build();
        return jA;
    }
    
    
    public void removeplayer(Superplayer a){
        
        /**
        for(Pitcher p: arrP){
            if(p.getFirst().equals(a.getFIRST()) && p.getLast().equals(a.getLAST())){
                arrP.remove(p);
            }
        }
        for(Hitter p: arrH){
            if(p.getFirst().equals(a.getFIRST()) && p.getLast().equals(a.getLAST())){
                arrH.remove(p);
            }
        }
        * */
        
        //to avoid concurent modification error
        
        Iterator<Hitter> iterH = arrH.iterator();
        while(iterH.hasNext()){
            Hitter h = iterH.next();
            
            if(h.getFirst().equals(a.getFIRST()) && h.getLast().equals(a.getLAST())){
                iterH.remove();
            }
            
        }
        
        Iterator<Pitcher> iterP = arrP.iterator();
        while(iterP.hasNext()){
            Pitcher p = iterP.next();
            
            if(p.getFirst().equals(a.getFIRST()) && p.getLast().equals(a.getLAST())){
                iterP.remove();
            }
        }
        
        
    }
    
    public void addTeam(FantasyTeam team){
        aft.add(team);
    }
    
    public ArrayList<FantasyTeam> getFantasyTeamList(){
        return aft;
    }
    
}
