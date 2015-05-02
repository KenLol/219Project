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
    
    public void removePlayer(Superplayer p){
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
    
    public void sortTeam(){
        ObservableList<Superplayer> sorter = FXCollections.observableArrayList();
        for(Superplayer sp : team){
           String k = sp.getTruePosition();
           if(k.equals("C")){
               sorter.add(sp);
           }
        }
        
        for(Superplayer sp : team){
           String k = sp.getTruePosition();
           if(k.equals("1B")){
               sorter.add(sp);
           }
        }
        
        for(Superplayer sp : team){
           String k = sp.getTruePosition();
           if(k.equals("CI")){
               sorter.add(sp);
           }
        }
        
        for(Superplayer sp : team){
           String k = sp.getTruePosition();
           if(k.equals("3B")){
               sorter.add(sp);
           }
        }
        
        for(Superplayer sp : team){
           String k = sp.getTruePosition();
           if(k.equals("2B")){
               sorter.add(sp);
           }
        }
        
        for(Superplayer sp : team){
           String k = sp.getTruePosition();
           if(k.equals("MI")){
               sorter.add(sp);
           }
        }
        
        for(Superplayer sp : team){
           String k = sp.getTruePosition();
           if(k.equals("SS")){
               sorter.add(sp);
           }
        }
        for(Superplayer sp : team){
           String k = sp.getTruePosition();
           if(k.equals("OF")){
               sorter.add(sp);
           }
        }
        for(Superplayer sp : team){
           String k = sp.getTruePosition();
           if(k.equals("U")){
               sorter.add(sp);
           }
        }
        for(Superplayer sp : team){
           String k = sp.getTruePosition();
           if(k.equals("P")){
               sorter.add(sp);
           }
        }
        team = sorter;
    }
    
    
}
