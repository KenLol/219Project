/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FantasyBaseball;

import static FantasyBaseball.FantasyGUI.CLASS_HEADING_LABEL;
import static FantasyBaseball.FantasyGUI.CLASS_PROMPT_LABEL;
import static FantasyBaseball.FantasyGUI.PRIMARY_STYLE_SHEET;
import csb.gui.MessageDialog;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Kenneth
 */
public class TeamDialog extends Stage{
    FantasyTeam team;
    
    GridPane gridPane;
    Scene dialogScene;
    Label headingLabel;
    Label nameLabel;
    TextField nameField;
    Label ownerLabel;
    TextField ownerField;
    Button completeButton;
    Button cancelButton;

    
    String selection;
    
    public static final String COMPLETE = "Complete";
    public static final String CANCEL = "Cancel";
    public static final String NAME = "Name:";
    public static final String OWNER = "Owner";
    
    public static final String heading1 = "Fantasy Team Details";
    public static final String heading3 = "Add New Fantasy Team";
    public static final String heading2 = "Edit Fantasy Team Details";
    
    public TeamDialog(Stage primaryStage, MessageDialog messageDialog){
        initModality(Modality.WINDOW_MODAL);
        initOwner(primaryStage);
        
        gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 20, 20, 20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        
        headingLabel = new Label(heading1);
        headingLabel.getStyleClass().add(CLASS_HEADING_LABEL);
        
        nameLabel = new Label(NAME);
        nameLabel.getStyleClass().add(CLASS_PROMPT_LABEL);
        ownerLabel = new Label(OWNER);
        ownerLabel.getStyleClass().add(CLASS_PROMPT_LABEL);
        
        nameField = new TextField();
        nameField.textProperty().addListener((observable, oldValue, newValue) -> {
            team.setName(newValue);
        });
        ownerField = new TextField();
        ownerField.textProperty().addListener((observable, oldValue, newValue) -> {
            team.setOwner(newValue);
        });
        
        completeButton = new Button(COMPLETE);
        cancelButton = new Button(CANCEL);
        
        EventHandler completeCancelHandler = (EventHandler<ActionEvent>) (ActionEvent ae) -> {
            Button sourceButton = (Button)ae.getSource();
            TeamDialog.this.selection = sourceButton.getText();
            TeamDialog.this.hide();
        };
        completeButton.setOnAction(completeCancelHandler);
        cancelButton.setOnAction(completeCancelHandler);
        
        gridPane.add(headingLabel, 0, 0, 2, 1);
        gridPane.add(nameLabel, 0, 1, 1, 1);
        gridPane.add(nameField, 1, 1, 1, 1);
        gridPane.add(ownerLabel, 0, 2, 1, 1);
        gridPane.add(ownerField, 1, 2, 1, 1);
        gridPane.add(completeButton, 0, 3, 1, 1);
        gridPane.add(cancelButton, 1, 3, 1, 1);
        
        
        dialogScene = new Scene(gridPane);
        dialogScene.getStylesheets().add(PRIMARY_STYLE_SHEET);
        this.setScene(dialogScene);
        
    }
    
    public String getSelection() {
        return selection;
    }
    
    public FantasyTeam getTeam(){
        return team;
    }
    
    public void loadGUIData(){
        nameField.setText(team.getName());
        ownerField.setText(team.getOwner());
    }
    
    public boolean wasCompleteSelected() {
        return selection.equals(COMPLETE);
    }
    
    public FantasyTeam showAddTeamItemDialog(){
        setTitle(heading3);
        team = new FantasyTeam();
        loadGUIData();
        
        
        this.showAndWait();
        return team;
    }
    
    public FantasyTeam showEditTeamDialog(FantasyTeam teamk){
        setTitle(heading2);
       // this.team = team;
        team = new FantasyTeam();
        team.setName(teamk.getName());
        team.setOwner(teamk.getOwner());
        
        loadGUIData();
        
        this.showAndWait();
        
        
        
        return team;
    }
}

