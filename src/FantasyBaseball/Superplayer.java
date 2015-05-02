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
public class Superplayer {
    
    final StringProperty PROTEAM;
    final StringProperty FIRST;
    final StringProperty LAST;
    final StringProperty position;
    final StringProperty note;
    
    public static final String default_first = "<First_Name>";
    public static final String default_last = "<Last_Name>";
    public static final String Defaultproteam = "<Default_Team>";
    public static final String Defaultposition = "<Default_Position>";
    public static final String Defaultnote = "<Default_Note>";
    
    final IntegerProperty year;
    final IntegerProperty rw;
    final IntegerProperty hrsv;
    final IntegerProperty rbik;
    public static final int Defaultyear = 0;
    public static final int Defaultrw = 0;
    public static final int Defaulthrsv = 0;
    public static final int Defaultrbik = 0;
    
    final DoubleProperty sbera;
    final DoubleProperty bawhip;
    public static final double Defaultsbera = 0.0;
    public static final double Defaultbawhip = 0.0;
    
    
    final StringProperty truePosition;
    final StringProperty contract;
    final StringProperty salary;
    public static final String default_true_position = "XXX";
    public static final String default_contract = "XXX";
    public static final String default_salary = "XXX";
    
    
    Player p;
    String ft;
    
    public Superplayer(){
        PROTEAM = new SimpleStringProperty(Defaultproteam);
        FIRST = new SimpleStringProperty(default_first);
        LAST = new SimpleStringProperty(default_last);
        position = new SimpleStringProperty(Defaultposition);
        year = new SimpleIntegerProperty(Defaultyear);
        rw = new SimpleIntegerProperty(Defaultrw);
        hrsv = new SimpleIntegerProperty(Defaulthrsv);
        rbik = new SimpleIntegerProperty(Defaultrbik);
        sbera = new SimpleDoubleProperty(Defaultsbera);
        bawhip = new SimpleDoubleProperty(Defaultbawhip);
        note = new SimpleStringProperty(Defaultnote);
        truePosition = new SimpleStringProperty(default_true_position);
        contract = new SimpleStringProperty(default_contract);
        salary = new SimpleStringProperty(default_salary);
    }
    
    public void reset(){
        setProteam(Defaultproteam);
    }
    
    public void setPlayer(Player p){
        this.p = p;
    }
    
    public Player getPlayer(){
        return p;
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
    
    public int getRw(){
        return rw.get();
    }
    public void setRw(int initRw){
        rw.set(initRw);
    }
    public IntegerProperty rwProperty(){
        return rw;
    }
    
    public int getHrsv(){
        return hrsv.get();
    }
    public void setHrsv(int initHrsv){
        hrsv.set(initHrsv);
    }
    public IntegerProperty hrsvProperty(){
        return hrsv;
    }
    
    public int getRbik(){
        return rbik.get();
    }
    public void setRbik(int initRbik){
        rbik.set(initRbik);
    }
    public IntegerProperty rbikProperty(){
        return rbik;
    }
    
    public double getSbera(){
        return sbera.get();
    }
    public void setSbera(double initSbera){
        sbera.set(initSbera);
    }
    public DoubleProperty sberaProperty(){
        return sbera;
    }
    
    public double getBawhip(){
        return bawhip.get();
    }
    public void setBawhip(double initBawhip){
        bawhip.set(initBawhip);
    }
    public DoubleProperty bawhipProperty(){
        return bawhip;
    }
    
    public String getNote(){
        return note.get();
    }
    public void setNote(String initNote){
        note.set(initNote);
    }
    public StringProperty noteProperty(){
        return note;
    }
    
    public String getTruePosition(){
        return truePosition.get();
    }
    public void setTruePositon(String initPos){
        truePosition.set(initPos);
    }
    public StringProperty truePositionProperty(){
        return truePosition;
    }
    
    public String getContract(){
        return contract.get();
    }
    public void setContract(String initContract){
        contract.set(initContract);
    }
    public StringProperty contractProperty(){
        return contract;
    }
    
    public String getSalary(){
        return salary.get();
    }
    public void setSalary(String initSalary){
        salary.set(initSalary);
    }
    public StringProperty salaryProperty(){
        return salary;
    }

    /**
     * @return the ft
     */
    public String getFt() {
        return ft;
    }

    /**
     * @param ft the ft to set
     */
    public void setFt(String ft) {
        this.ft = ft;
    }
}
