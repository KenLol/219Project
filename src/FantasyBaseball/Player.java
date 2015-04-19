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
    protected String first; //FIRST_NAME
    protected String last; //LAST_NAME
    final StringProperty FIRST;
    final StringProperty LAST;
    public static final String default_name = "default_name";
    
    
    public Player(){
        FIRST = new SimpleStringProperty(default_name);
        LAST = new SimpleStringProperty(default_name);
    }

    void toSTRING() {
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the first
     */
    public String getFirst() {
        return first;
    }

    /**
     * @param first the first to set
     */
    public void setFirst(String first) {
        this.first = first;
    }

    /**
     * @param last the last to set
     */
    public void setLast(String last) {
        this.last = last;
    }

    /**
     * @return the last
     */
    public String getLast() {
        return last;
    }
    
}
