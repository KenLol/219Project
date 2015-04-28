/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FantasyBaseball;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Superclass of pitchers and hitters.
 * @author Kenneth
 */
public class Player {
    //protected String first; //FIRST_NAME
    //protected String last; //LAST_NAME
    
    public Player(){
       
    }

    void toSTRING() {
        
        throw new UnsupportedOperationException("Something has gone super wrong"); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    //overwritten methods should never be called.
    public String getFirst(){
        return "error";
    }
    
    public String getLast(){
        return "error";
    }
    
    public String getProteam(){
        return "error";
    }
    
    public String getPosition(){
        return "error";
    }
    
    public int getYear(){
        return 0;
    }
    
    public int getRW(){
        return 0;
    }
    
    public int getHRSV(){
        return 0;
    }
    
    public int getRBIK(){
        return 0;
    }
    
    public double getSBERA(){
        return 0.0;
    }
    
    public double getBAWHIP(){
        return 0.0;
    }
    
    public String getNote(){
        return "error";
    }
}
