/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FantasyBaseball;

import static csb.CSB_StartupConstants.PATH_CSS;
import static csb.CSB_StartupConstants.PATH_DATA;
import static csb.CSB_StartupConstants.PROPERTIES_FILE_NAME;
import static csb.CSB_StartupConstants.PROPERTIES_SCHEMA_FILE_NAME;
import csb.error.ErrorHandler;
import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;
import xml_utilities.InvalidXMLFileFormatException;

/**
 *
 * @author Kenneth
 */
public class FantasyBaseball extends Application{
     FantasyGUI GUI;
     
     /**
     * This is where our Application begins its initialization, it will
     * create the GUI and initialize all of its components.
     * 
     * @param primaryStage This application's window.
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        
        ErrorHandler eH = ErrorHandler.getErrorHandler();
        eH.initMessageDialog(primaryStage);
        boolean success = loadProperties();
        
        GUI = new FantasyGUI(primaryStage);
        GUI.initGUI("FantasyBaseball");
       
        
        //JsonDraftFileManager jm = new JsonDraftFileManager();
        //jm.loadALL(); // loads the hitters and pitchers
        
        //jm.loadA();  // choose ALL radio button
        //jm.loadC();  // choose C radio button
        
        //jm.displayArr(); display
        
        //jm.startWith("J"); choose what is in searchbox
        //jm.displayAll();  display
        
       // JsonDraftFileManager jcfm = GUI.getjcfm();
        //jcfm.saveDraft("hello");
        
        
    }
    
    /**
     * Loads this application's properties file, which has a number of settings
     * for initializing the user interface.
     * 
     * @return true if the properties file was loaded successfully, false otherwise.
     */
    public boolean loadProperties() {
        try {
            // LOAD THE SETTINGS FOR STARTING THE APP
            PropertiesManager props = PropertiesManager.getPropertiesManager();
            props.addProperty(PropertiesManager.DATA_PATH_PROPERTY, PATH_DATA);
            props.loadProperties(PROPERTIES_FILE_NAME, PROPERTIES_SCHEMA_FILE_NAME);
            return true;
       } catch (InvalidXMLFileFormatException ixmlffe) {
            // SOMETHING WENT WRONG INITIALIZING THE XML FILE
            ErrorHandler eH = ErrorHandler.getErrorHandler();
            eH.handlePropertiesFileError();
            return false;
        }        
    }
    
}
