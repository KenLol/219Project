/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FantasyBaseball;

import static csb.CSB_PropertyType.NEW_COURSE_CREATED_MESSAGE;
import csb.data.CourseDataManager;
import csb.error.ErrorHandler;
import csb.gui.CSB_GUI;
//import csb.file.CourseFileManager;
//import csb.file.CourseSiteExporter;
import csb.gui.MessageDialog;
import csb.gui.ProgressDialog;
import csb.gui.YesNoCancelDialog;
import java.io.IOException;
import properties_manager.PropertiesManager;

/**
 *
 * @author Kenneth
 */
public class FantasyFileController {
    private boolean saved;
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
                gui.activateWorkspace();

                // TELL THE USER THE COURSE HAS BEEN CREATED
                messageDialog.show(properties.getProperty(NEW_COURSE_CREATED_MESSAGE));
            }
        //} catch (IOException ioe) {
        //    // SOMETHING WENT WRONG, PROVIDE FEEDBACK
        //    errorHandler.handleNewCourseError();
        }
    

    private boolean promptToSave(FantasyGUI gui) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
}
