/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FantasyBaseball;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Kenneth
 */
public class Superplayer {
    
    final StringProperty PROTEAM;
    final StringProperty FIRST;
    final StringProperty LAST;
    final StringProperty position;
    public static final String default_first = "<First_Name>";
    public static final String default_last = "<Last_Name>";
    public static final String Defaultproteam = "<Default_Team>";
    public static final String Defaultposition = "<Default_Position>";
    
    final IntegerProperty year;
    public static final int Defaultyear = 0;
    
    public Superplayer(){
        PROTEAM = new SimpleStringProperty(Defaultproteam);
        FIRST = new SimpleStringProperty(default_first);
        LAST = new SimpleStringProperty(default_last);
        position = new SimpleStringProperty(Defaultposition);
        year = new SimpleIntegerProperty(Defaultyear);
    }
    
    public void reset(){
        setProteam(Defaultproteam);
    }

    public String getPROTEAM(){
        return PROTEAM.get();
    }
    public void setProteam(String initTeam){
        PROTEAM.set(initTeam);
    }
    public StringProperty proteamProperty(){
        return PROTEAM;
    }
    
    public String getFIRST(){
        return FIRST.get();
    }
    public void setFIRST(String initFirst){
        FIRST.set(initFirst);
    }
    public StringProperty FIRSTProperty(){
        return FIRST;
    }
    
    public String getLAST(){
        return LAST.get();
    }
    public void setLAST(String initLast){
        LAST.set(initLast);
    }
    public StringProperty LASTProperty(){
        return LAST;
    }
    
    public String getPosition(){
        return position.get();
    }
    public void setPosition (String initPos){
        position.set(initPos);
    }
    public StringProperty positionProperty(){
        return position;
    }
    
    public int getYear(){
        return year.get();
    }
    public void setYear(int initYear){
        year.set(initYear);
    }
    public IntegerProperty yearProperty(){
        return year;
    }
    
}