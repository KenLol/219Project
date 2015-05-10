/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FantasyBaseball;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Kenneth
 */
public class SuperFantasyTeam {
    
    final StringProperty TEAMNAME;
    final IntegerProperty PLAYERSNEEDED;
    final IntegerProperty LEFT;
    final IntegerProperty PP;
    final IntegerProperty R;
    final IntegerProperty HR;
    final IntegerProperty RBI;
    final IntegerProperty SB;
    final DoubleProperty BA;
    final DoubleProperty W;
    final IntegerProperty SV;
    final IntegerProperty K;
    final DoubleProperty ERA;
    final DoubleProperty WHIP;
    final DoubleProperty TOTALPOINTS;
    
    public static final String defaultstring = "default";
    public static final int defaultint = 0;
    public static final double defaultdouble = 0.0;
    
    FantasyTeam ft;
    
    public SuperFantasyTeam(){
        TEAMNAME = new SimpleStringProperty(defaultstring);
        PLAYERSNEEDED = new SimpleIntegerProperty(defaultint);
        PP = new SimpleIntegerProperty(defaultint);
        R = new SimpleIntegerProperty(defaultint);
        HR = new SimpleIntegerProperty(defaultint);
        RBI = new SimpleIntegerProperty(defaultint);
        SB = new SimpleIntegerProperty(defaultint);
        BA = new SimpleDoubleProperty(defaultdouble);
        W = new SimpleDoubleProperty(defaultdouble);
        SV = new SimpleIntegerProperty(defaultint);
        K = new SimpleIntegerProperty(defaultint);
        WHIP = new SimpleDoubleProperty(defaultdouble);
        TOTALPOINTS = new SimpleDoubleProperty(defaultdouble);
        LEFT = new SimpleIntegerProperty(defaultint);
        ERA = new SimpleDoubleProperty(defaultdouble);
    }
    
    public void setTeam(FantasyTeam ft){
        this.ft = ft;
    }
    public FantasyTeam getFt(){
        return ft;
    }
    
    public String getTEAMNAME(){
        return TEAMNAME.get();
    }
    public void setTEAMNAME(String init){
        TEAMNAME.set(init);
    }
    
    public int getPLAYERSNEEDED(){
        return PLAYERSNEEDED.get();
    }
    public void setPLAYERSNEEDED(int init){
       PLAYERSNEEDED.set(init);
    }
    
    public int getPP(){
        return PP.get();
    }
    public void setPP(int init){
        PP.set(init);
    }
    
    public int getR(){
        return R.get();
    }
    public void setR(int init){
        R.set(init);
    }
    
    public int getHR(){
        return HR.get();
    }
    public void setHR(int init){
        HR.set(init);
    }
    
    public int getRBI(){
        return RBI.get();
    }
    public void setRBI(int init){
        RBI.set(init);
    }
    
    public int getSB(){
        return SB.get();
    }
    public void setSB(int init){
        SB.set(init);
    }
    
    public double getBA(){
        return BA.get();
    }
    public void setBA(double init){
        BA.set(init);
    }
    
    public double getW(){
        return W.get();
    }
    public void setW(double init){
        W.set(init);
    }
    
    public int getSV(){
        return SV.get();
    }
    public void setSV(int init){
        SV.set(init);
    }
    
    public int getK(){
        return K.get();
    }
    public void setK(int init){
        K.set(init);
    }
    
    public double getWHIP(){
        return WHIP.get();
    }
    public void setWHIP(double init){
        WHIP.set(init);
    }
    
    public double getTOTALPOINTS(){
        return TOTALPOINTS.get();
    }
    public void setTOTALPOINTS(double init){
        TOTALPOINTS.set(init);
    }
    
    public int getLEFT(){
        return LEFT.get();
    }
    public void setLEFT(int init){
        LEFT.set(init);
    }
    
    public double getERA(){
        return ERA.get();
    }
    public void setERA(double init){
        ERA.set(init);
    }
}
