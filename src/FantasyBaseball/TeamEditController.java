/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FantasyBaseball;

import csb.gui.MessageDialog;
import csb.gui.YesNoCancelDialog;
import javafx.stage.Stage;

/**
 *
 * @author Kenneth
 */
public class TeamEditController {
    MessageDialog messageDialog;
    YesNoCancelDialog yesNoCancelDialog;
    FantasyTeam team;
    public TeamEditController(Stage initPrimaryStage, FantasyTeam team, MessageDialog initMessageDialog, YesNoCancelDialog initYesNoCancelDialog){
        messageDialog = initMessageDialog;
        yesNoCancelDialog = initYesNoCancelDialog;
        this.team = team;
    }
    
    public void handleAddTeamRequest(FantasyGUI GUI){
        JsonDraftFileManager jcfm = GUI.getjcfm();
    }
    
}
