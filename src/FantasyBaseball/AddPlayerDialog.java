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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    
    Image flag;
    ImageView iv;
    
    Label pos;
    
    Image pot;
    ImageView ip;
    
    String selection;
    
    public static final String COMPLETE = "Complete";
    public static final String CANCEL = "Cancel";
    public static final String title = "Edit Player";
    public static final String heading = "Player Details";
    public static final String one = "Fantasy Team:";
    public static final String two = "Position:";
    public static final String three = "Contract:";
    public static final String four = "Salary:";
    
    FantasyTeam currentTeam;
    
    
    
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
        
        String flagName = sp.getPlayer().getNation();
        
        //System.out.println("/data/flags/" + flagName + ".png");
        //flag = new Image("file:"+flagName+".png");
        
        
        
     
        try{
         flag = new Image("/flags/" +flagName + ".png");
                }
        catch(IllegalArgumentException e){
            
        }
        iv = new ImageView(flag);

        String posString = sp.getPosition();
        pos = new Label(posString);
        pos.getStyleClass().add(CLASS_HEADING_LABEL);
        
        String last = sp.getLAST();
        String first = sp.getFIRST();
        
        try{
           // System.out.println("/players/" +last +first+ ".jpg");
            pot = new Image("/players/" +last +first+ ".jpg");
            
        }
        catch(IllegalArgumentException e){
            pot = new Image("/players/AAA_PhotoMissing.jpg");
            
        }
        ip = new ImageView(pot);
        
        
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
        
        positionComboBox = new ComboBox();
        
        fantasyComboBox.setOnAction(e -> {
            String a = fantasyComboBox.getSelectionModel().getSelectedItem().toString();
            superplayer.setFt(a);
            
            for(FantasyTeam zz : aft){
                if(zz.getName().equals(a)){
                    currentTeam = zz;
                }
            }
            
            
            
            
        //positionComboBox = new ComboBox();
        String ps;
        ps = superplayer.getPosition();
        positionComboBox.getItems().clear(); // wipe old team selection
        
        
        //System.out.println(currentTeam.getName());
       
        
        if(ps.contains("C_") && currentTeam.positionOpen("C")){
            positionComboBox.getItems().add("C");
        }
        if(ps.contains("1B") && currentTeam.positionOpen("1B")){
            positionComboBox.getItems().add("1B");
        }
        if(ps.contains("CI") && currentTeam.positionOpen("CI")){
            positionComboBox.getItems().add("CI");
        }
        if(ps.contains("3B") && currentTeam.positionOpen("3B")){
            positionComboBox.getItems().add("3B");
        }
        if(ps.contains("2B") && currentTeam.positionOpen("2B")){
            positionComboBox.getItems().add("2B");
        }
        if(ps.contains("MI") && currentTeam.positionOpen("MI")){
            positionComboBox.getItems().add("MI");
        }
        if(ps.contains("SS") && currentTeam.positionOpen("SS")){
            positionComboBox.getItems().add("SS");
        }
        if(ps.contains("OF") && currentTeam.positionOpen("OF")){
            positionComboBox.getItems().add("OF");
        }
        if(ps.contains("U") && currentTeam.positionOpen("U")){
            positionComboBox.getItems().add("U");
        }
        if(ps.contains("P") && currentTeam.positionOpen("P")){
            positionComboBox.getItems().add("P");
        }
        
        
        
        positionComboBox.setOnAction(x -> {
            try{
            String ab = positionComboBox.getSelectionModel().getSelectedItem().toString();
            superplayer.setTruePositon(ab);
            }
            catch(NullPointerException c){
                
            }
        });
        
            
            
            
            
            
            
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
        gridPane.add(ip, 0, 1, 1, 3);
        gridPane.add(iv, 1, 1, 1, 1);
        gridPane.add(nameLabel, 1, 2, 1, 1);
        gridPane.add(pos, 1, 3, 1, 1);
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
