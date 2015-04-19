/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FantasyBaseball;

/**
 * Superclass of pitchers and hitters.
 * @author Kenneth
 */
public class Player {
    protected String first; //FIRST_NAME
    protected String last; //LAST_NAME

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
