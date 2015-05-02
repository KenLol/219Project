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
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 *
 * @author Kenneth
 */
public class AddPlayerDialog extends Stage{
    
    Superplayer superplayer;
    ArrayList<FantasyTeam> aft = new ArrayList<FantasyTeam>();
    
    GridPane gridPane;
    Scene dialogScene;
    Label headingLabel;
    Label nameLabel;
    Label fantasyLabel;
    ComboBox fantasyComboBox;
    Label positionLabel;
    ComboBox positionComboBox;
    Label contractLabel;
    ComboBox contractComboBox;
    Label salaryLabel;
    TextField salaryTextField;
    Button completeButton;
    Button cancelButton;
    
    String selection;
    
    public static final String COMPLETE = "Complete";
    public static final String CANCEL = "Cancel";
    public static final String title = "Edit Player";
    public static final String heading = "Player Details";
    public static final String one = "Fantasy Team:";
    public static final String two = "Position:";
    public static final String three = "Contract:";
    public static final String four = "Salary:";
    
    
    
    public AddPlayerDialog(Stage primaryStage, MessageDialog messageDialog, Superplayer sp, ArrayList<FantasyTeam> aft){
        this.superplayer = sp;
        this.aft = aft;
        
        initModality(Modality.WINDOW_MODAL);
        initOwner(primaryStage);
        
        gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 20, 20, 20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        
        headingLabel = new Label(heading);
        headingLabel.getStyleClass().add(CLASS_HEADING_LABEL);
        fantasyLabel = new Label(one);
        fantasyLabel.getStyleClass().add(CLASS_PROMPT_LABEL);
        positionLabel = new Label(two);
        positionLabel.getStyleClass().add(CLASS_PROMPT_LABEL);
        contractLabel = new Label(three);
        contractLabel.getStyleClass().add(CLASS_PROMPT_LABEL);
        salaryLabel = new Label(four);
        salaryLabel.getStyleClass().add(CLASS_PROMPT_LABEL);
        nameLabel = new Label(sp.getFIRST() + " " + sp.getLAST());
        nameLabel.getStyleClass().add(CLASS_HEADING_LABEL);
        
        salaryTextField = new TextField();
        salaryTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            superplayer.setSalary(newValue);
        });
        
        completeButton = new Button(COMPLETE);
        cancelButton = new Button(CANCEL);
        
        EventHandler completeCancelHandler = (EventHandler<ActionEvent>) (ActionEvent ae) -> {
            Button sourceButton = (Button)ae.getSource();
            AddPlayerDialog.this.selection = sourceButton.getText();
            AddPlayerDialog.this.hide();
        };
        completeButton.setOnAction(completeCancelHandler);
        cancelButton.setOnAction(completeCancelHandler);
        
        
        fantasyComboBox = new ComboBox();
        for(FantasyTeam f : aft){
            fantasyComboBox.getItems().add(f.getName());
        }
        
        fantasyComboBox.setOnAction(e -> {
            String a = fantasyComboBox.getSelectionModel().getSelectedItem().toString();
            superplayer.setFt(a);
        });
        
        positionComboBox = new ComboBox();
        String ps;
        ps = superplayer.getPosition();
        if(ps.contains("C_")){
            positionComboBox.getItems().add("C");
        }
        if(ps.contains("1B")){
            positionComboBox.getItems().add("1B");
        }
        if(ps.contains("CI")){
            positionComboBox.getItems().add("CI");
        }
        if(ps.contains("3B")){
            positionComboBox.getItems().add("3B");
        }
        if(ps.contains("2B")){
            positionComboBox.getItems().add("2B");
        }
        if(ps.contains("MI")){
            positionComboBox.getItems().add("MI");
        }
        if(ps.contains("SS")){
            positionComboBox.getItems().add("SS");
        }
        if(ps.contains("OF")){
            positionComboBox.getItems().add("OF");
        }
        if(ps.contains("U")){
            positionComboBox.getItems().add("U");
        }
        if(ps.contains("P")){
            positionComboBox.getItems().add("P");
        }
        
        positionComboBox.setOnAction(e -> {
            String a = positionComboBox.getSelectionModel().getSelectedItem().toString();
            superplayer.setTruePositon(a);
        });
        
        
        
        
        contractComboBox = new ComboBox();
        contractComboBox.getItems().add("X");
        contractComboBox.getItems().add("S1");
        contractComboBox.getItems().add("S2");
        
        contractComboBox.setOnAction(e -> {
            String a = contractComboBox.getSelectionModel().getSelectedItem().toString();
            superplayer.setContract(a);
        });
        
        
        
        gridPane.add(headingLabel, 0, 0, 2, 1);
        gridPane.add(nameLabel, 1, 2, 1, 1);
        gridPane.add(fantasyLabel, 0, 4, 1 ,1);
        gridPane.add(fantasyComboBox, 1, 4, 1, 1);
        gridPane.add(positionLabel, 0, 5, 1, 1);
        gridPane.add(positionComboBox, 1, 5, 1, 1);
        gridPane.add(contractLabel, 0, 6, 1, 1);
        gridPane.add(contractComboBox, 1, 6, 1, 1);
        gridPane.add(salaryLabel, 0, 7, 1, 1);
        gridPane.add(salaryTextField, 1, 7, 1, 1);
        gridPane.add(completeButton, 0, 8, 1, 1);
        gridPane.add(cancelButton, 1, 8, 1, 1);
        
        
        dialogScene = new Scene(gridPane);
        dialogScene.getStylesheets().add(PRIMARY_STYLE_SHEET);
        this.setScene(dialogScene);
        
    }
    
    public String getSelection() {
        return selection;
    }
    
    public Superplayer getSuperplayer(){
        return superplayer;
    }
    
    public Superplayer showAddPlayerTeamDialog(){
        setTitle(title);
        //superplayer.setTruePositon(true);
        
        
        this.showAndWait();
        return superplayer;
    }
    
}
