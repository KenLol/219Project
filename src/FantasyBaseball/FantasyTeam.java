/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FantasyBaseball;

import java.text.DecimalFormat;
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
    ObservableList<Superplayer> taxiteam = FXCollections.observableArrayList();
    boolean fullteam;
    
    String name = "Default_Name";
    String owner = "Default_Owner";
    
    int moneyleft = 260;
    int playersNeeded = 23;
    int R = 0;
    int HR = 0;
    int RBI = 0;
    int SB = 0;
    double BA = 0.0;
    double W = 0.0;
    int SV = 0;
    int K = 0;
    double ERA = 0.0;
    double WHIP = 0.0;
   
    int points;
    
    
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
    
    public void addTaxiPlayer(Superplayer p){
        taxiteam.add(p);
    }
    
    public void removeTaxiPlayer(Superplayer p){
        taxiteam.remove(p);
    }
    
    public ObservableList<Superplayer> getTaxiTeam(){
        return taxiteam;
    }
    
    public boolean taxifull(){
        boolean a = false;
        if(taxiteam.size() >= 8){
            a = true;
        }
        
        return a;
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
        
        if(Pc == 0 && P1b == 0 && Pci == 0 && P3b == 0 && Pmi == 0 && Pu == 0 && Pof == 0 && Pp == 0){
            fullteam = true;
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
    
    public int getR(){
        R = 0;
        for(Superplayer sp : team){
            if(!sp.getPosition().equals("P")){
                int j = sp.getRw();
                R = R + j;
            }
        }
        
        return R;
    }
    
    public int getHR(){
        HR = 0;
        for(Superplayer sp : team){
            if(!sp.getPosition().equals("P")){
                int j = sp.getHrsv();
                HR = HR + j;
            }
        }
        
        return HR;
    }
    
    public int getRBI(){
        RBI = 0;
        for(Superplayer sp : team){
            if(!sp.getPosition().equals("P")){
                int j = sp.getRbik();
                RBI = RBI + j;
            }
        }
        
        return RBI;
    }
    
    public int getSB(){
        SB = 0;
        for(Superplayer sp : team){
            if(!sp.getPosition().equals("P")){
                int j = (int) Math.round(sp.getSbera());
                SB = SB + j;
            }
        }
        return SB;
    }
    
    public double getBA(){
        BA = 0.0;
        double aa = 0;
        int num = 0;
        
        for(Superplayer sp : team){
            if(!sp.getPosition().equals("P")){
                double j = sp.getBawhip();
                if(Double.isNaN(j)){
                    j = 0;
                    num--;
                    //System.out.println("hit");
                }
                //System.out.println("" + j);
                aa = aa + j;
                num++;
            }
        }
        BA = aa/num;
        BA = Math.round(BA*1000)/1000.0d;
        //DecimalFormat df = new DecimalFormat("#.###");
        //BA = Double.valueOf(df.format(BA));
        if(BA == Double.NaN){
            BA = 0;
        }
        return BA;
    }
    
    
    public double getW(){
        W = 0.0;
        for(Superplayer sp : team){
            if(sp.getPosition().equals("P")){
                int j = sp.getRw();
                W = W + j;
            }
        }
        
        return W;
    }
    
    public int getSV(){
        SV = 0;
        for(Superplayer sp : team){
            if(sp.getPosition().equals("P")){
                int j = sp.getHrsv();
                SV = SV + j;
            }
        }
        return SV;
    }
    
    public int getK(){
        K = 0;
        for(Superplayer sp : team){
            if(sp.getPosition().equals("P")){
                int j = sp.getRbik();
                K = K + j;
            }
        }
        return K;
    }
    
    public double getERA(){
        ERA = 0.0;
        double aa = 0.0;
        int num = 0;
        for(Superplayer sp : team){
            if(sp.getPosition().equals("P")){
                double j = sp.getSbera();
                
                if(Double.isNaN(j)){
                    j = 0;
                    num--;
                }
                aa = aa + j;
                num++;
            }
        }
        ERA = aa/num;
        //DecimalFormat df = new DecimalFormat("#.##");
        ERA = Math.round(ERA*100)/100.0d;
        //ERA = Double.valueOf(df.format(ERA));
        if(ERA == Double.NaN){
            ERA = 0;
        }
        return ERA;
    }
    
    
    public double getWHIP(){
        WHIP = 0.0;
        double aa = 0.0;
        int num = 0;
        for(Superplayer sp : team){
            if(sp.getPosition().equals("P")){
                double j = sp.getBawhip();
                
                if(Double.isNaN(j)){
                    j = 0;
                    num--;
                }
                aa = aa + j;
                num++;
            }
        }
        
        WHIP = aa/num;
        //DecimalFormat df = new DecimalFormat("#.##");
        WHIP = Math.round(WHIP*100)/100.0d;
        //WHIP = Double.valueOf(df.format(WHIP));
        if(WHIP == Double.NaN){
            WHIP = 0;
        }
        return WHIP;
    }
    
    public void setPoints(int a){
        points = a;
    }
    
    public int getPoints(){
        return points;
    }
    
    public void addPoints(int a){
        points = points + a;
    }
}
