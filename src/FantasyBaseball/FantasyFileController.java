/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FantasyBaseball;

import static csb.CSB_PropertyType.NAMETHEDRAFTPLEASE;
import static csb.CSB_PropertyType.NEW_COURSE_CREATED_MESSAGE;
import static csb.CSB_PropertyType.SAVE_UNSAVED_WORK_MESSAGE;
import csb.data.CourseDataManager;
import csb.error.ErrorHandler;
import csb.gui.CSB_GUI;
//import csb.file.CourseFileManager;
//import csb.file.CourseSiteExporter;
import csb.gui.MessageDialog;
import csb.gui.ProgressDialog;
import csb.gui.YesNoCancelDialog;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.SwingUtilities;
import properties_manager.PropertiesManager;

/**
 *
 * @author Kenneth
 */
public class FantasyFileController {
    private boolean saved; 
    private boolean loaded;
    // THIS WILL PROVIDE FEEDBACK TO THE USER AFTER
    // WORK BY THIS CLASS HAS COMPLETED
    MessageDialog messageDialog;
    
    // AND WE'LL USE THIS TO ASK YES/NO/CANCEL QUESTIONS
    YesNoCancelDialog yesNoCancelDialog;
    
    // WE'LL USE THIS TO SHOW PROGRESS DURING EXPORTING
    ProgressDialog progressDialog;
    
    PropertiesManager properties;
    
    boolean running = false;
    
    
    
     public FantasyFileController(
            MessageDialog initMessageDialog,
            YesNoCancelDialog initYesNoCancelDialog,
            ProgressDialog initProgressDialog){
            //CourseFileManager initCourseIO,
            //CourseSiteExporter initExporter) {
        // NOTHING YET
        saved = true;
        
        // KEEP THESE GUYS FOR LATER
       // courseIO = initCourseIO;
        //exporter = initExporter;
        
        // BE READY FOR ERRORS
        //errorHandler = ErrorHandler.getErrorHandler();
        
        // AND GET READY TO PROVIDE FEEDBACK
        messageDialog = initMessageDialog;
        yesNoCancelDialog = initYesNoCancelDialog;
        progressDialog = initProgressDialog;
        properties = PropertiesManager.getPropertiesManager();
    }
     
     public void handleNewCourseRequest(FantasyGUI gui) {
        //try {
            // WE MAY HAVE TO SAVE CURRENT WORK
            boolean continueToMakeNew = true;
            if (!saved) {
                // THE USER CAN OPT OUT HERE WITH A CANCEL
                continueToMakeNew = promptToSave(gui);
            }

            // IF THE USER REALLY WANTS TO MAKE A NEW COURSE
            if (continueToMakeNew) {
                // RESET THE DATA, WHICH SHOULD TRIGGER A RESET OF THE UI
                //CourseDataManager dataManager = gui.getDataManager();
                //dataManager.reset();
                saved = false;

                // REFRESH THE GUI, WHICH WILL ENABLE AND DISABLE
                // THE APPROPRIATE CONTROLS
                gui.updateToolbarControls(saved);
                //gui.reload();
                if(!loaded){
                    loaded = true;
                    gui.activateWorkspace();
                    
                }
                gui.setNameTeamField(null);
                gui.setRealName(null);
                gui.setthisisanewfile(true);
                gui.getjcfm().getFantasyTeamList().clear();
                
                
                try{
                    gui.clearStartingTable();
                    gui.setCurrentTeam(null);
                    
                }
                catch(NullPointerException e){
                    
                }
                
                gui.getjcfm().getDraftOrder().clear();
                gui.getjcfm().getsft().clear();
                gui.getjcfm().getobp().clear();
                gui.FantasyTeam();
                
                
                

                // TELL THE USER THE COURSE HAS BEEN CREATED
                messageDialog.show(properties.getProperty(NEW_COURSE_CREATED_MESSAGE));
            }
        //} catch (IOException ioe) {
        //    // SOMETHING WENT WRONG, PROVIDE FEEDBACK
        //    errorHandler.handleNewCourseError();
        }
    

    private boolean promptToSave(FantasyGUI gui) {
        
        
        yesNoCancelDialog.show(properties.getProperty(SAVE_UNSAVED_WORK_MESSAGE));
        
        String selection = yesNoCancelDialog.getSelection();
        
        if(selection.equals(YesNoCancelDialog.YES)){
            handleSaveDraftRequest(gui);
           // saved = true;
        }
        else if (selection.equals(YesNoCancelDialog.CANCEL)){
            return false;
        }
        
        return true;
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public void handleExitRequest(FantasyGUI GUI) {
        boolean continueToExit = true;
        if (!saved) {
            // THE USER CAN OPT OUT HERE
            continueToExit = promptToSave(GUI);
        }
        if (continueToExit) {
            // EXIT THE APPLICATION
            System.exit(0);
        }
    }
    
    public void handleFantasyTeamRequest(FantasyGUI GUI){
        GUI.FantasyTeam();
    }
    
    public void handleAvailablePlayersRequest(FantasyGUI GUI){
        GUI.AvailablePlayers();
    }
    
    public void handleFantasyStandingsRequest(FantasyGUI GUI){
        GUI.FantasyStandings();
    }
    
    public void handleDraftSummaryRequest(FantasyGUI GUI){
        GUI.DraftSummary();
    }
    public void handleMLBTeamsRequest(FantasyGUI GUI){
        GUI.MLBTeams();
    }
    
    
    public void handleSaveDraftRequest(FantasyGUI GUI){
        JsonDraftFileManager  jcfm = GUI.getjcfm();
        String sname = GUI.getSNAME();
        if(sname == null || sname.isEmpty()){
            messageDialog.show(properties.getProperty(NAMETHEDRAFTPLEASE));
        }
        else{
            if(GUI.getthisisanewfile()){
                try {
                    jcfm.saveDraft(sname);
                    messageDialog.show("New Draft Saved");
                } catch (FileNotFoundException ex) {
                   System.out.println("This should never be runned");
                }
                GUI.setthisisanewfile(false);
                GUI.setRealName(sname);
               // System.out.println("1step");
            }
            else{
                String realname = GUI.getRealName();
                String exists = "./data/drafts/" + realname +".json";
                
                File fi = new File(exists);
                
                
                
                if(sname.equals(realname)){//no name change so just overwrite
                    //System.out.println("same path");
                    
                    try {
                    messageDialog.show("Draft Saved Over Old Draft");
                    jcfm.saveDraft(sname);
                } catch (FileNotFoundException ex) {
                   System.out.println("This should never be runned");
                }
                }
                else{
                    try {
                        //System.out.println("different paths");
                        if(fi.exists()){
                            //System.out.println("exists???");
                        }
                        fi.delete();
                        
                        jcfm.saveDraft(sname);
                        
                        messageDialog.show("Draft Saved To New Name");
                        GUI.setRealName(sname);
                    } catch (FileNotFoundException ex) {
                        System.out.println("file broked");
                    }
                }
                /**
                if(fi.exists()){//file already exists ... this is for name changes
                    System.out.println("Name not changed");
                }
                else{
                    System.out.println("Name changed");
                }
                **/
            }
        }
        //System.out.println(sname);
    }
    
    
    
    
    public void handleLoadDraftRequest(FantasyGUI GUI){
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File("./data/drafts/"));
        File selectedFile = fc.showOpenDialog(GUI.getWindow());
        String name = selectedFile.getName();
        String bestname = name.substring(0, name.lastIndexOf('.'));
        
        //System.out.println(bestname);
        
        
        if(selectedFile!= null){
            try {
                GUI.getjcfm().loadDraft(name);
                GUI.setthisisanewfile(false);//not a newfile.
                GUI.setRealName(bestname);
                GUI.setNameTeamField(bestname);
                
                GUI.activateWorkspace();
                GUI.updateToolbarControls(false);
                GUI.FantasyTeam();
                messageDialog.show("Draft Has Been Loaded");
           } catch (IOException ex) {
                System.out.println("error loading file");
            }
        }
        
        GUI.getjcfm().loadDraftOrder();
    }
    
    public void handleRemovePlayerRequest(FantasyGUI GUI, Superplayer a){
        JsonDraftFileManager jcfm = GUI.getjcfm();
        jcfm.removeobpplayer(a);
        jcfm.removeplayer(a);
       
        
    }
    
    public void handleNewTeamRequest(FantasyGUI GUI){
        JsonDraftFileManager jcfm = GUI.getjcfm();
        Stage stage = GUI.getWindow();
        
        TeamDialog td = new TeamDialog(stage, messageDialog);
        FantasyTeam team = td.showAddTeamItemDialog();
        //System.out.println(team.getName());
        //System.out.println(team.getOwner());
        if(td.getSelection().equals("Complete")){
             jcfm.addTeam(team);
        }
        GUI.FantasyTeam();
    }
    
    public void handleEditTeamRequest(FantasyGUI GUI){
        JsonDraftFileManager jcfm = GUI.getjcfm();
        Stage stage = GUI.getWindow();
        
        
        TeamDialog td = new TeamDialog(stage, messageDialog);
        
        FantasyTeam lol = GUI.getCurrentTeam();
        try{
            FantasyTeam edit = td.showEditTeamDialog(lol);
            if(td.getSelection().equals("Complete")){
                lol.setName(edit.getName());
                lol.setOwner(edit.getOwner());
        }
        }
        catch(NullPointerException e){
            
        }
        
        
        
        
        GUI.FantasyTeam();
        
    }
    
    public void handleTeamSelectRequest(FantasyGUI GUI){
        String a = GUI.getTeamSelectComboBox();
        //String a = GUI.getCurrentTeam().getName();
        JsonDraftFileManager jcfm = GUI.getjcfm();
        ArrayList<FantasyTeam> gt = jcfm.getFantasyTeamList();
        FantasyTeam ftz = null;
        for(FantasyTeam ft : gt){
            if(ft.getName().equals(a)){
                ftz = ft;
            }
        }
        GUI.setCurrentTeam(ftz);
       // System.out.println(ftz.getName());
        //System.out.println(a);
        //GUI.getTSCB().setValue(a);
        GUI.FantasyTeam();
        
    }
    
    public void handleRemoveTeamRequest(FantasyGUI GUI){
        //String a = GUI.getTeamSelectComboBox();
        String a = GUI.getCurrentTeam().getName();
        JsonDraftFileManager jcfm = GUI.getjcfm();
        ArrayList<FantasyTeam> gt = jcfm.getFantasyTeamList();
        
        Iterator<FantasyTeam> iterT = gt.iterator();
        while(iterT.hasNext()){
            FantasyTeam ft = iterT.next();
            
            if(ft.getName().equals(a)){
                for(Superplayer sp : ft.getTeam()){
                    GUI.getjcfm().addPlayer(sp);
                    GUI.getjcfm().addobp(sp);
                }
                iterT.remove();
            }
        }
      
        GUI.setCurrentTeam(null);
        GUI.clearStartingTable();
        GUI.FantasyTeam();
    }
    
    public void handleAddPlayerToTeam(FantasyGUI GUI, Superplayer sp){
        
        //System.out.println(sp.getPlayer().getFirst());
        
        
        JsonDraftFileManager jcfm = GUI.getjcfm();
        ArrayList<FantasyTeam> aft = jcfm.getFantasyTeamList();
        Stage stage = GUI.getWindow();
        
        AddPlayerDialog ad = new AddPlayerDialog(stage, messageDialog, sp, aft);
        Superplayer splayer = ad.showAddPlayerTeamDialog();
        if(ad.getSelection().equals("Complete")){
            String yay = splayer.getFt();
            //System.out.println(yay);
            for(FantasyTeam fate : aft){
                if(fate.getName().equals(yay)){
                    //System.out.println(fate.getOwner());
                    if(!splayer.getContract().equals("XXX") && !splayer.getSalary().equals("XXX") && !splayer.getTruePosition().equals("XXX")){
                        if(fate.fullteam){
                            fate.addTaxiPlayer(splayer);
                            splayer.setTrueTeam(yay);
                            jcfm.getobp().remove(sp);
                            jcfm.removeplayer(sp);
                            GUI.AvailablePlayers();
                            jcfm.getDraftOrder().add(splayer);
                            splayer.setPick(1);
                            jcfm.resortDraftOrder();
                            splayer.setTruePositon(splayer.getPosition());
                        }
                        else{
                        fate.addPlayer(splayer); //adds the player to the list.
                        splayer.setTrueTeam(yay);
                        jcfm.getobp().remove(sp);
                        jcfm.removeplayer(sp);
                        GUI.AvailablePlayers();
                        jcfm.getDraftOrder().add(splayer);
                        splayer.setPick(1);
                        jcfm.resortDraftOrder();
                        }
                    }
                }
                
            }
        }
    }
    
    public void handleResetPlayer(FantasyGUI GUI, Superplayer sp){
        String org = sp.getFt();
        JsonDraftFileManager jcfm = GUI.getjcfm();
        ArrayList<FantasyTeam> aft = jcfm.getFantasyTeamList();
        Stage stage = GUI.getWindow();
        
        FantasyTeam deadteam = new FantasyTeam();
        deadteam.setName("Free Agent");
        ArrayList<FantasyTeam> lol = new ArrayList<FantasyTeam>();
        for(FantasyTeam qq : aft){
            lol.add(qq);
        }
        lol.add(deadteam);
        
        AddPlayerDialog ad = new AddPlayerDialog(stage, messageDialog, sp, lol);
        //sp original
        //ad changed version
        Superplayer splayer = ad.showAddPlayerTeamDialog();
        
        
        
        if(ad.getSelection().equals("Complete")){
            String yay = splayer.getFt();
            //System.out.println(yay);
            //System.out.println(yay);
            
           if(yay.equals("Free Agent")){
               GUI.getCurrentTeam().removePlayer(sp);
               jcfm.addPlayer(sp);
               jcfm.removeDraftOrder(sp);
               sp.setPick(0);
               jcfm.resortDraftOrder();
               
               
               
           }
           else{
               if(yay != org){
                   for(FantasyTeam ft : aft){
                       if(ft.getName().equals(yay)){
                           if(!splayer.getContract().equals("XXX") && !splayer.getSalary().equals("XXX") && !splayer.getTruePosition().equals("XXX")){
                             ft.addPlayer(splayer); 
                             jcfm.getDraftOrder().remove(splayer);
                             
                       }
                   }
                   }
                //System.out.println(yay);//yay.add
                //System.out.println(org);// org.delete
                   for(FantasyTeam ftt : aft){
                       if(ftt.getName().equals(org)){
                           ftt.removePlayer(sp);
                           jcfm.getDraftOrder().remove(splayer);
                       }
                   }
           }
               else{
                   jcfm.getDraftOrder().remove(splayer);
               }
               if(splayer.getContract().equals("S2")){
                   splayer.setTrueTeam(yay);
                   jcfm.getDraftOrder().add(splayer);
                   
                   sp.setPick(1);
                   jcfm.resortDraftOrder();
                   
               }
               
               
        }
    
        }
        GUI.FantasyTeam();
    }
    
    
    public void handleMlbSelectRequest(FantasyGUI GUI, String a){
        
        JsonDraftFileManager jcfm = GUI.getjcfm();
        ObservableList<Superplayer> obp = jcfm.getobp();
        ObservableList<Superplayer> mlbList = FXCollections.observableArrayList();
        
        
        for(Superplayer sp : obp){
            if(a.toString().equals(sp.getPROTEAM())){
                mlbList.add(sp);
            }
        }
        
        Collections.sort(mlbList);
        
        GUI.setMlbList(mlbList);
        
        GUI.MLBTeams();
    
    
    }
    //add a random player to random team...
    public void handleSTAR(FantasyGUI GUI){
        
        
        JsonDraftFileManager jcfm = GUI.getjcfm();
        ArrayList<FantasyTeam> aft = jcfm.getFantasyTeamList();
        //jcfm.getobp();
        
        int teams = aft.size();
        
        for(int i = 0; i<teams; i++){
            FantasyTeam ft = aft.get(i);
            boolean dummy = ft.positionOpen("none");
            if(!ft.fullteam){
                
                Superplayer sp = getPlayerFor(ft, GUI);
                
                //ft.addPlayer(sp);
                
                try{
                //System.out.println(sp.getFIRST());
                
                sp.setTrueTeam(ft.getName());
                jcfm.getobp().remove(sp);
                jcfm.removeplayer(sp);
                
                ft.addPlayer(sp);
                
                jcfm.getDraftOrder().add(sp);
                sp.setPick(1);
                jcfm.resortDraftOrder();
                
                i = teams;
                
                }
                
                catch(NullPointerException e){
                    System.out.println("No more players");
                }
                
                
                
            
                
                
                
                
                
            }
           if(!ft.taxifull() && ft.fullteam){
               Superplayer sp = getPlayerFor(ft, GUI);
               try{
               // System.out.println(sp.getFIRST());
                
                sp.setTrueTeam(ft.getName());
                jcfm.getobp().remove(sp);
                jcfm.removeplayer(sp);
                
                ft.addTaxiPlayer(sp);
                
                jcfm.getDraftOrder().add(sp);
                sp.setPick(1);
                jcfm.resortDraftOrder();
                
                i = teams;
                
                }
                
                catch(NullPointerException e){
                    //System.out.println("No more players TAXI");
                }
           } 
        }
        
        
        
        
    }
    
    public Superplayer getPlayerFor(FantasyTeam ft, FantasyGUI GUI){
        Superplayer zz = null;
        JsonDraftFileManager jcfm = GUI.getjcfm();
        jcfm.clearobp();
        GUI.AvailablePlayers();
        
        GUI.DraftSummary();
        
        ObservableList<Superplayer> playerlist = jcfm.getobp();
        ObservableList<Superplayer> placeholder = FXCollections.observableArrayList();
        
        boolean done = false;
        if(ft.positionOpen("C") && !done){
            placeholder.clear();
            for(Superplayer sp : playerlist){
                if(sp.getPosition().contains("C_")){
                    //System.out.println(sp.getFIRST());
                    placeholder.add(sp);
                }
            }
            
            //placeholder.clear();
            int a = placeholder.size();
            
            a++;
            //System.out.println(a);
            int b = (int) Math.floor(Math.random() * a);
            //System.out.println(b);
            zz = placeholder.get(b);
            //System.out.println(a);
            zz.setContract("S2");
            zz.setSalary("1");
            zz.setTruePositon("C");
        
        
            done = true;
        }
        
        if(ft.positionOpen("1B") && !done){
            placeholder.clear();
            for(Superplayer sp : playerlist){
                if(sp.getPosition().contains("1B")){
                    //System.out.println(sp.getFIRST());
                    placeholder.add(sp);
                }
            }
            
            //placeholder.clear();
            int a = placeholder.size();
            
            a++;
            //System.out.println(a);
            int b = (int) Math.floor(Math.random() * a);
            //System.out.println(b);
            zz = placeholder.get(b);
            //System.out.println(a);
            zz.setContract("S2");
            zz.setSalary("1");
            zz.setTruePositon("1B");
        
        
            done = true;
            
        }
        
        if(ft.positionOpen("CI") && !done){
            placeholder.clear();
            for(Superplayer sp : playerlist){
                if(sp.getPosition().contains("CI")){
                    //System.out.println(sp.getFIRST());
                    placeholder.add(sp);
                }
            }
            
            //placeholder.clear();
            int a = placeholder.size();
            
            a++;
            //System.out.println(a);
            int b = (int) Math.floor(Math.random() * a);
            //System.out.println(b);
            zz = placeholder.get(b);
            //System.out.println(a);
            zz.setContract("S2");
            zz.setSalary("1");
            zz.setTruePositon("CI");
        
        
            done = true;
            
        }
        
        if(ft.positionOpen("3B") && !done){
            placeholder.clear();
            for(Superplayer sp : playerlist){
                if(sp.getPosition().contains("3B")){
                    //System.out.println(sp.getFIRST());
                    placeholder.add(sp);
                }
            }
            
            //placeholder.clear();
            int a = placeholder.size();
            
            a++;
            //System.out.println(a);
            int b = (int) Math.floor(Math.random() * a);
            //System.out.println(b);
            zz = placeholder.get(b);
            //System.out.println(a);
            zz.setContract("S2");
            zz.setSalary("1");
            zz.setTruePositon("3B");
        
        
            done = true;
            
        }
        
        if(ft.positionOpen("2B") && !done){
            placeholder.clear();
            for(Superplayer sp : playerlist){
                if(sp.getPosition().contains("2B")){
                    //System.out.println(sp.getFIRST());
                    placeholder.add(sp);
                }
            }
            
            //placeholder.clear();
            int a = placeholder.size();
            
            a++;
            //System.out.println(a);
            int b = (int) Math.floor(Math.random() * a);
            //System.out.println(b);
            zz = placeholder.get(b);
            //System.out.println(a);
            zz.setContract("S2");
            zz.setSalary("1");
            zz.setTruePositon("2B");
        
        
            done = true;
            
        }
        
        if(ft.positionOpen("MI") && !done){
            placeholder.clear();
            for(Superplayer sp : playerlist){
                if(sp.getPosition().contains("MI")){
                    //System.out.println(sp.getFIRST());
                    placeholder.add(sp);
                }
            }
            
            //placeholder.clear();
            int a = placeholder.size();
            
            a++;
            //System.out.println(a);
            int b = (int) Math.floor(Math.random() * a);
            //System.out.println(b);
            zz = placeholder.get(b);
            //System.out.println(a);
            zz.setContract("S2");
            zz.setSalary("1");
            zz.setTruePositon("MI");
        
        
            done = true;
            
        }
        
        if(ft.positionOpen("SS") && !done){
            placeholder.clear();
            for(Superplayer sp : playerlist){
                if(sp.getPosition().contains("SS")){
                    //System.out.println(sp.getFIRST());
                    placeholder.add(sp);
                }
            }
            
            //placeholder.clear();
            int a = placeholder.size();
            
            a++;
            //System.out.println(a);
            int b = (int) Math.floor(Math.random() * a);
            //System.out.println(b);
            zz = placeholder.get(b);
            //System.out.println(a);
            zz.setContract("S2");
            zz.setSalary("1");
            zz.setTruePositon("SS");
        
        
            done = true;
            
        }
        
        if(ft.positionOpen("OF") && !done){
            placeholder.clear();
            for(Superplayer sp : playerlist){
                if(sp.getPosition().contains("OF")){
                    //System.out.println(sp.getFIRST());
                    placeholder.add(sp);
                }
            }
            
            //placeholder.clear();
            int a = placeholder.size();
            
            a++;
            //System.out.println(a);
            int b = (int) Math.floor(Math.random() * a);
            //System.out.println(b);
            zz = placeholder.get(b);
            //System.out.println(a);
            zz.setContract("S2");
            zz.setSalary("1");
            zz.setTruePositon("OF");
        
        
            done = true;
            
        }
        
        if(ft.positionOpen("U") && !done){
            placeholder.clear();
            for(Superplayer sp : playerlist){
                if(sp.getPosition().contains("U")){
                    //System.out.println(sp.getFIRST());
                    placeholder.add(sp);
                }
            }
            
            //placeholder.clear();
            int a = placeholder.size();
            
            a++;
            //System.out.println(a);
            int b = (int) Math.floor(Math.random() * a);
            //System.out.println(b);
            zz = placeholder.get(b);
            //System.out.println(a);
            zz.setContract("S2");
            zz.setSalary("1");
            zz.setTruePositon("U");
        
        
            done = true;
            
        }
        
        if(ft.positionOpen("P") && !done){
            placeholder.clear();
            for(Superplayer sp : playerlist){
                if(sp.getPosition().contains("P")){
                    //System.out.println(sp.getFIRST());
                    placeholder.add(sp);
                }
            }
            
            //placeholder.clear();
            int a = placeholder.size();
            
            a++;
            //System.out.println(a);
            int b = (int) Math.floor(Math.random() * a);
            //System.out.println(b);
            zz = placeholder.get(b);
            //System.out.println(a);
            zz.setContract("S2");
            zz.setSalary("1");
            zz.setTruePositon("P");
        
        
            done = true;
            
        }
        
        if(ft.fullteam && !done){
            placeholder.clear();
            for(Superplayer sp : playerlist){
                placeholder.add(sp);
            }
            
            int a = placeholder.size();
            
            a++;
            //System.out.println(a);
            int b = (int) Math.floor(Math.random() * a);
            //System.out.println(b);
            zz = placeholder.get(b);
            //System.out.println(a);
            zz.setContract("S2");
            zz.setSalary("1");
            if(zz.getPosition().equals("P")){
                zz.setTruePositon("P");
            }
            else{
                zz.setTruePositon(zz.getPosition());
            }
        }
        
        
        return zz;
    }
    
    public void handlePlay(FantasyGUI GUI, Button b){
     if(!running){
        running = true;
        new Thread(new Runnable(){
           @Override
           public void run(){
               try {
                   while(running){
               Platform.runLater(new Runnable(){
                   @Override
                   public void run(){
                       b.fire();
                   }
               });
               
                   Thread.sleep(500);
                   }
               } catch (InterruptedException ex) {
                   
               }
           }
        }).start();
        //b.fire();
     }
    }
    
    public void handlePause(){
        running = false;
    }
    
}
    
