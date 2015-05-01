/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FantasyBaseball;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Kenneth
 */
public class FantasyTeam {
    
   // ArrayList<Player> team = new ArrayList<Player>();
    ObservableList<Superplayer> team = FXCollections.observableArrayList();
    String name = "Default_Name";
    String owner = "Default_Owner";
    
    public FantasyTeam(){
        
    }
    
    public void addPlayer(Superplayer p){
        team.add(p);
    }
    
    public void removePlayer(Player p){
        team.remove(p);
    }
    
    public ObservableList<Superplayer> getTeam(){
        return team;
    }
    
    public String getName(){
        return name;
       
    }
    
    public void setName(String s){
        name = s;
    }
    
    public String getOwner(){
        return owner;
    }
    public void setOwner(String s){
        owner = s;
    }
}
