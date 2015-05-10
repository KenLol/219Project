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
    
    int moneyleft = 260;
    int playersNeeded = 23;
    
    
    
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
    
    public boolean positionOpen(String a){
        boolean answer = false;
        
        int Pc = 2;
        int P1b = 1;
        int Pci = 1;
        int P3b = 1;
        int P2b = 1;
        int Pmi = 1;
        int Pss = 1;
        int Pu = 1;
        int Pof = 5;
        int Pp = 9;
        
        
        
        for(Superplayer sp : team){
            
            String k = sp.getTruePosition();
            if(k.equals("C")){
                Pc = Pc -1;
            }
            if(k.equals("1B")){
                P1b = P1b -1;
            }
            if(k.equals("CI")){
                Pci = Pci -1;
            }
            if(k.equals("3B")){
                P3b = P3b -1;
            }
            if(k.equals("2B")){
                P2b = P2b -1;
            }
            if(k.equals("MI")){
                Pmi = Pmi -1;
            }
            if(k.equals("SS")){
                Pss = Pss -1;
            }
            if(k.equals("U")){
                Pu = Pu -1;
            }
            if(k.equals("OF")){
                Pof = Pof -1;
            }
            if(k.equals("P")){
                Pp = Pp -1;
            }
            
        }
        
        if (a.equals("C")){
            if(Pc > 0){
                answer = true;
            }
        }
        if (a.equals("1B")){
            if(P1b > 0){
                answer = true;
            }
        }
        if (a.equals("CI")){
            if(Pci > 0){
                answer = true;
            }
        }
        if (a.equals("3B")){
            if(P3b > 0){
                answer = true;
            }
        }
        if (a.equals("2B")){
            if(P2b > 0){
                answer = true;
            }
        }
        if (a.equals("MI")){
            if(Pmi > 0){
                answer = true;
            }
        }
        if (a.equals("SS")){
            if(Pss > 0){
                answer = true;
            }
        }
        if (a.equals("U")){
            if(Pu > 0){
                answer = true;
            }
        }
        if (a.equals("OF")){
            if(Pof > 0){
                answer = true;
            }
        }
        if (a.equals("P")){
            if(Pp > 0){
                answer = true;
            }
        }
        
        return answer;
    }
    
    
    public void updateMoney(){
        moneyleft = 260;
        for(Superplayer p : team){
            int a = 0;
            try{
             a = Integer.parseInt(p.getSalary());
            }
            catch(NumberFormatException e){
               
            }
            moneyleft = moneyleft - a;
            
        }
    }
    
    public void updatePlayersNeeded(){
        playersNeeded = 23;
        playersNeeded = 23 - team.size();
    }
    
    public int getMoney(){
        return moneyleft;
    }
    
    public int getPlayersNeeded(){
        return playersNeeded;
    }
    
    
    
}
