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
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
    
    ObservableList<SuperFantasyTeam> sft = FXCollections.observableArrayList();
    ObservableList<Superplayer> draftorder = FXCollections.observableArrayList();
    
    
    public JsonDraftFileManager() {
        
    }
    
    public ObservableList<Superplayer> getDraftOrder(){
        return draftorder; //can only contain s2 players.
    }
    
    public void addDraftOrder(Superplayer p){
        draftorder.add(p);
    }
    public void removeDraftOrder(Superplayer p){
        draftorder.remove(p);
    }
    
    public void setDraftOrder(ObservableList<Superplayer> os){
        draftorder = os;
    }
    
    
    
    public void loadDraftOrder(){
        draftorder.clear();
        int max = 0;
        int start = 1;
        ObservableList<Superplayer> os = FXCollections.observableArrayList();
        
        for(int i = 0; i<aft.size(); i++){
            FantasyTeam ft = aft.get(i);
            for(int j = 0; j<ft.getTeam().size(); j++){
                if(ft.getTeam().get(j).getPick() > 0){
                    draftorder.add(ft.getTeam().get(j));
                }
               
            }
        }
        
        max = draftorder.size();
        
        for(start = 1; start <= max; start ++){
        for(Superplayer sp : draftorder){
            if(sp.getPick() == start){
                os.add(sp);
            }
        }
        }
        
        setDraftOrder(os);
        
        
        
        
    }
    public void resortDraftOrder(){
        /**
        for(Superplayer sp : draftorder){
            if(!sp.getContract().equals("S2")){
                draftorder.remove(sp);
            }
        }
        */
        
        
        Iterator<Superplayer> iterP = draftorder.iterator();
        while(iterP.hasNext()){
            Superplayer p = iterP.next();
            
            if(!p.getContract().equals("S2") || p.getPick() == 0)
            {
                p.setPick(0);
                p.getPlayer().setPick(0);
                iterP.remove();
            }
        }
        
       for(Superplayer sp : draftorder){
        sp.setPick(draftorder.indexOf(sp)+1);
        sp.getPlayer().setPick(draftorder.indexOf(sp)+1);
       }
        
        
        
        
        
        
        
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
    
    public void clearsft(){
        sft.clear();
    }
        public void addsft(SuperFantasyTeam s){
        sft.add(s);
    }
    public void removeaftteam(SuperFantasyTeam s){
        
        sft.remove(s);
    }
    public ObservableList<SuperFantasyTeam> getsft(){
 
        return sft;
    }
            
            
    public void saveDraft(String a) throws FileNotFoundException{
        draftName = a;
        draftName = "./data/drafts/" + draftName + ".json";
        
        OutputStream os = new FileOutputStream(draftName);
        JsonWriter jsonWriter = Json.createWriter(os);
        
        JsonArray pitcherJsonArray = makePitcherJsonArray();
        JsonArray hitterJsonArray = makeHitterJsonArray();
        JsonArray teamJsonArray = makeTeamJsonArray();
        
        
        JsonObject draftJsonObject = Json.createObjectBuilder()
                                    .add("Pitchers", pitcherJsonArray)
                                    .add("Hitters", hitterJsonArray)
                                    .add("Teams", teamJsonArray)
                                    
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
        
        aft.clear();
        JsonArray teamArray = json.getJsonArray("Teams");
        for(int i = 0; i < teamArray.size(); i++){
            JsonArray ja = teamArray.getJsonArray(i);
            for(int j = 0; j < ja.size(); j++){
                JsonObject jso = ja.getJsonObject(j);
                //System.out.println(jso.getString("REALTEAM"));
                
                
                
                Boolean contains = false;
                FantasyTeam ct = null;
                String a = jso.getString("REALTEAM");
                String b = jso.getString("REALOWNER");
                for(FantasyTeam ft : aft){
                    if(ft.getName().equals(jso.getString("REALTEAM"))){
                        contains = true;
                        ct = ft;
                    }
                }
                
                if(contains == false){
                    FantasyTeam e = new FantasyTeam();
                    e.setName(a);
                    e.setOwner(b);
                    aft.add(e);
                    ct = e;
                    //System.out.println(e.getName());
                }
                
                //Superplayer q = new Superplayer();
                
                if(jso.getString("REALPOS").equals("HITTER")){
                    Superplayer p = new Superplayer();
                    Hitter h = new Hitter();
                    
                    h.setAb(jso.getString("AB"));
                    h.setContract(jso.getString("CONTRACT"));
                    h.setFirst(jso.getString("FIRST_NAME"));
                    h.setH(jso.getString("H"));
                    h.setHRSV(Integer.parseInt(jso.getString("HR")));
                    h.setLast(jso.getString("LAST_NAME"));
                    h.setNation(jso.getString("NATION_OF_BIRTH"));
                    h.setNote(jso.getString("NOTES"));
                    h.setPosition(jso.getString("QP"));
                    h.setProteam(jso.getString("TEAM"));
                    h.setRBIK(Integer.parseInt(jso.getString("RBI")));
                    h.setRW(Integer.parseInt(jso.getString("R")));
                    h.setSBERA(Double.parseDouble(jso.getString("SB")));
                    h.setSalary(jso.getString("SALARY"));
                    h.setTruePosition(jso.getString("REALPOSITION"));
                    h.setYear(Integer.parseInt(jso.getString("YEAR_OF_BIRTH")));
                    h.setBAWHIP(Double.parseDouble(h.getH()) / Double.parseDouble(h.getAb()));
                    h.setPick(Integer.parseInt(jso.getString("PICK")));
                    
                    Double baDouble = h.getBAWHIP();
                    if(!baDouble.isNaN()){
                     baDouble = Math.round(baDouble*1000)/1000.0d;
                    }
                     h.setBAWHIP(baDouble);
                    
                    p.setPlayer(h);
                    p.setBawhip(h.getBAWHIP());
                    p.setContract(h.getContract());
                    p.setFIRST(h.getFirst());
                    p.setFt(ct.getName());
                    p.setHrsv(h.getHRSV());
                    p.setLAST(h.getLast());
                    p.setNote(h.getNote());
                    p.setPosition(h.getPosition());
                    p.setProteam(h.getProteam());
                    p.setRbik(h.getRBIK());
                    p.setRw(h.getRW());
                    p.setSalary(h.getSalary());
                    p.setSbera(h.getSBERA());
                    p.setTruePositon(h.getTruePosition());
                    p.setYear(h.getYear());
                    p.setPick(h.getPick());
                    
                    
                    
                    
                    ct.addPlayer(p);
                
                }
                else{
                    Superplayer pi = new Superplayer();
                    Pitcher p = new Pitcher();
                    
                   //p.setBawhip(jso.getString(""));
                    p.setBb(jso.getString("BB"));
                    p.setContract(jso.getString("CONTRACT"));
                    p.setEr(jso.getString("ER"));
                    p.setFirst(jso.getString("FIRST_NAME"));
                    p.setH(jso.getString("H"));
                    p.setHRSV(Integer.parseInt(jso.getString("SV")));
                    p.setIp(jso.getString("IP"));
                    p.setLast(jso.getString("LAST_NAME"));
                    p.setNation(jso.getString("NATION_OF_BIRTH"));
                    p.setNote(jso.getString("NOTES"));
                    p.setPosition("P");
                    p.setProteam(jso.getString("TEAM"));
                    p.setRBIK(Integer.parseInt(jso.getString("K")));
                    p.setRW(Integer.parseInt(jso.getString("W")));
                  //  p.setSBERA();
                    p.setSalary(jso.getString("SALARY"));
                    p.setTruePosition("P");
                    p.setYear(Integer.parseInt(jso.getString("YEAR_OF_BIRTH")));
                    p.setPick(Integer.parseInt(jso.getString("PICK")));
                    
                    
                    Double eraDouble = Double.parseDouble(p.getEr()) * 9 / Double.parseDouble(p.getIp());
                    if(!eraDouble.isNaN()){
                     eraDouble = Math.round(eraDouble*1000)/1000.0d;
                     }
                    p.setSBERA(eraDouble);
                    
                    Double whipDouble = (Double.parseDouble(p.getBb()) + Double.parseDouble(p.getH())) / Double.parseDouble(p.getIp());
                    if(!whipDouble.isNaN()){
                      whipDouble = Math.round(whipDouble*1000)/1000.0d;
                    }
                    p.setBAWHIP(whipDouble);
                    
                    
                    pi.setBawhip(p.getBAWHIP());
                    pi.setContract(p.getContract());
                    pi.setFIRST(p.getFirst());
                    pi.setFt(ct.getName());
                    pi.setHrsv(p.getHRSV());
                    pi.setLAST(p.getLast());
                    pi.setNote(p.getNote());
                    pi.setPlayer(p);
                    pi.setPosition(p.getPosition());
                    pi.setProteam(p.getProteam());
                    pi.setRbik(p.getRBIK());
                    pi.setRw(p.getRW());
                    pi.setSalary(p.getSalary());
                    pi.setSbera(p.getSBERA());
                    pi.setTruePositon(p.getTruePosition());
                    pi.setYear(p.getYear());
                    pi.setPick(p.getPick());
                    
                    ct.addPlayer(pi);
                }
                
                
                
            }
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
    
    public JsonArray makeTeamJsonArray(){
        JsonArrayBuilder jsb = Json.createArrayBuilder();
        
        for(FantasyTeam ft : aft){
            jsb.add(makeSingleTeam(ft));
            
        }
        
        
        JsonArray jA = jsb.build();
        return jA;
    }
    
    public JsonArray makeSingleTeam(FantasyTeam ft){
        JsonArrayBuilder jsb = Json.createArrayBuilder();
        ObservableList<Superplayer> obsp = ft.getTeam();
        ArrayList<Superplayer> Hitterz = new ArrayList<Superplayer>();
        ArrayList<Superplayer> Pitcherz = new ArrayList<Superplayer>();
        String name = ft.getName();
        
        for(Superplayer sp : obsp){
            if(sp.getTruePosition().equals("P")){
                Pitcherz.add(sp);
                //System.out.println(sp.getFIRST());
                //System.out.println(sp.getPlayer().getProteam());
            }
            else{
                Hitterz.add(sp);
               // System.out.println(sp.getPlayer().getProteam());
            }
        }
        
       // JsonArray jar = ;
        //JsonObject jo = Json.createObjectBuilder().add("Hitter", jar).build();
        
        for(Superplayer aaa: Hitterz){
            Hitter a = (Hitter) aaa.getPlayer();
            JsonObject jso = Json.createObjectBuilder().add("REALTEAM", name)
                                                    .add("REALOWNER", ft.getOwner())
                                                    .add("REALPOS", "HITTER")
                                                    .add("TEAM", a.getProteam())
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
                                                    .add("REALPOSITION", aaa.getTruePosition())
                                                    .add("CONTRACT", aaa.getContract())
                                                    .add("SALARY", aaa.getSalary())
                                                    .add("PICK", Integer.toString(aaa.getPick()))
                                                    .build();
            
            jsb.add(jso);
            
        }
        
        for(Superplayer bbb : Pitcherz){
            Pitcher p = (Pitcher) bbb.getPlayer();
            
            JsonObject jso = Json.createObjectBuilder().add("REALTEAM", name)
                                                    .add("REALOWNER", ft.getOwner())
                                                    .add("REALPOS", "PITCHER")
                                                    .add("TEAM", p.getProteam())
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
                                                    .add("REALPOSITION", bbb.getTruePosition())
                                                    .add("CONTRACT", bbb.getContract())
                                                    .add("SALARY", bbb.getSalary())
                                                    .add("PICK", Integer.toString(bbb.getPick()))
                                                    .build();
            jsb.add(jso);
            
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
    
    public void addPlayer(Superplayer p){
        Player c = p.getPlayer();
        if(p.getPosition().equals("P")){
            arrP.add((Pitcher) c);
            
        }
        else{
            arrH.add((Hitter) c);
        }
    }
    
    
    public void calculatePoints(){
        int R = 0;
        int HR = 0;
        int RBI = 0;
        int SB = 0;
        double BA = 0.0;
        double W = 0.0;
        int SV = 0;
        int K = 0;
        double ERA = 0.0;
        double WHIP = 0.0;
        
        FantasyTeam fR = null;
        FantasyTeam fHR = null;
        FantasyTeam fRBI = null;
        FantasyTeam fSB = null;
        FantasyTeam fBA = null;
        FantasyTeam fW = null;
        FantasyTeam fSV = null;
        FantasyTeam fK = null;
        FantasyTeam fERA = null;
        FantasyTeam fWHIP = null;
        
        int number = aft.size();
        
        
        for(FantasyTeam ft: aft){
            ft.setPoints(0);
        }
        
        for(FantasyTeam ft : aft){
            if(ft.getR() > R){
                R = ft.getR();
                fR = ft;
            }
        }
        
        for(FantasyTeam ft : aft){
            if(ft.getHR() > HR){
                HR = ft.getHR();
                fHR = ft;
            }
        }
        for(FantasyTeam ft : aft){
            if(ft.getRBI() > RBI){
                RBI = ft.getRBI();
                fRBI = ft;
            }
        }
        for(FantasyTeam ft : aft){
            if(ft.getSB() > SB){
                SB = ft.getSB();
                fSB = ft;
            }
        }
        for(FantasyTeam ft : aft){
            if(ft.getBA() > BA){
                BA = ft.getBA();
                fBA = ft;
            }
        }
        for(FantasyTeam ft : aft){
            if(ft.getW() > W){
                W = ft.getW();
                fW = ft;
            }
        }
        for(FantasyTeam ft : aft){
            if(ft.getSV() > SV){
                SV = ft.getSV();
                fSV = ft;
            }
        }
        for(FantasyTeam ft : aft){
            if(ft.getK() > K){
                K = ft.getK();
                fK = ft;
            }
        }
        for(FantasyTeam ft : aft){
            if(ft.getERA() > ERA){
                ERA = ft.getERA();
                fERA = ft;
            }
        }
        for(FantasyTeam ft : aft){
            if(ft.getWHIP() > WHIP){
                WHIP = ft.getWHIP();
                fWHIP = ft;
            }
        }
        
        if(fR!=null){
            fR.addPoints(number);
        }
        if(fHR!=null){
            fHR.addPoints(number);
        }
        if(fRBI!=null){
            fRBI.addPoints(number);
        }
        if(fSB!=null){
            fSB.addPoints(number);
        }
        if(fBA!=null){
            fBA.addPoints(number);
        }
        if(fW!=null){
            fW.addPoints(number);
        }
        if(fSV!=null){
            fSV.addPoints(number);
        }
        if(fK!=null){
            fK.addPoints(number);
        }
        if(fERA!=null){
            fERA.addPoints(number);
        }
        if(fWHIP!=null){
            fWHIP.addPoints(number);
        }
    }
    
    
    
}
