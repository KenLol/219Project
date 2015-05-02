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
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
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
                GUI.FantasyTeam();
                messageDialog.show("Draft Has Been Loaded");
           } catch (IOException ex) {
                System.out.println("error loading file");
            }
        }
        
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
        GUI.FantasyTeam();
        
    }
    
    public void handleRemoveTeamRequest(FantasyGUI GUI){
        String a = GUI.getTeamSelectComboBox();
        JsonDraftFileManager jcfm = GUI.getjcfm();
        ArrayList<FantasyTeam> gt = jcfm.getFantasyTeamList();
        
        Iterator<FantasyTeam> iterT = gt.iterator();
        while(iterT.hasNext()){
            FantasyTeam ft = iterT.next();
            
            if(ft.getName().equals(a)){
                iterT.remove();
            }
        }
      
        GUI.setCurrentTeam(null);
        GUI.FantasyTeam();
    }
    
    public void handleAddPlayerToTeam(FantasyGUI GUI, Superplayer sp){
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
                        fate.addPlayer(splayer); //adds the player to the list.
                        jcfm.getobp().remove(sp);
                        jcfm.removeplayer(sp);
                        GUI.AvailablePlayers();
                    }
                }
                
            }
        }
    }
    
}
