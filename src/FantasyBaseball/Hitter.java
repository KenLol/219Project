/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FantasyBaseball;

/**
 *
 * @author Kenneth
 */
public class Hitter extends Player{
    
    private String first;
    private String last;
    private String proteam;         //TEAM
    private String position;        //QP
    private int year;            //YEAR_OF_BIRTH
    private int r_w;               //R
    private int hr_sw;              //HR
    private int rbi_k;             //RBI
    private double sb_era;              //SB
    private String note;            //NOTES
    private double ba_whip;              //H/AB
    
    private String ab;              //AB
    private String h;               //H
    private String nation;          //NATION_OF_BIRTH
    
    private String truePosition;
    private String salary;
    private String contract;
    
    public Hitter(){
        
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
     * @return the proteam
     */
    public String getProteam() {
        return proteam;
    }

    /**
     * @param proteam the proteam to set
     */
    public void setProteam(String proteam) {
        this.proteam = proteam;
    }

    /**
     * @return the position
     */
    public String getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * @return the r
     */
    public int getRW() {
        return r_w;
    }

    /**
     * @param r the r to set
     */
    public void setRW(int r) {
        this.r_w = r;
    }

    /**
     * @return the hr
     */
    public int getHRSV() {
        return hr_sw;
    }

    /**
     * @param hr the hr to set
     */
    public void setHRSV(int hr) {
        this.hr_sw = hr;
    }

    /**
     * @return the rbi
     */
    public int getRBIK() {
        return rbi_k;
    }

    /**
     * @param rbi the rbi to set
     */
    public void setRBIK(int rbi) {
        this.rbi_k = rbi;
    }

    /**
     * @return the sb
     */
    public double getSBERA() {
        return sb_era;
    }

    /**
     * @param sb the sb to set
     */
    public void setSBERA(double sb) {
        this.sb_era = sb;
    }

    /**
     * @return the note
     */
    public String getNote() {
        return note;
    }

    /**
     * @param note the note to set
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * @return the ba
     */
    public double getBAWHIP() {
        return ba_whip;
    }

    /**
     * @param ba the ba to set
     */
    public void setBAWHIP(double ba) {
        this.ba_whip = ba;
    }

    /**
     * @return the ab
     */
    public String getAb() {
        return ab;
    }

    /**
     * @param ab the ab to set
     */
    public void setAb(String ab) {
        this.ab = ab;
    }

    /**
     * @return the h
     */
    public String getH() {
        return h;
    }

    /**
     * @param h the h to set
     */
    public void setH(String h) {
        this.h = h;
    }

    /**
     * @return the nation
     */
    public String getNation() {
        return nation;
    }

    /**
     * @param nation the nation to set
     */
    public void setNation(String nation) {
        this.nation = nation;
    }
    
    public void toSTRING(){
        System.out.println("Team: " + proteam +
                "\nLAST_NAME: " + getLast() +
                "\nFIRST_NAME: " + first +
                "\nQP: " + getPosition() +
                "\nAB: " + ab +
                "\nR: " + r_w +
                "\nH: " + h +
                "\nHR: " + hr_sw +
                "\nRBI: " + rbi_k +
                "\nSB: " + sb_era +
                "\nNOTES: " + note +
                "\nYEAR_OF_BIRTH: " + year +
                "\nNATION_OF_BIRTH: " + nation +
                "\nBA: " + ba_whip +"\n");
                
    }
    
    
    // adds CI MI U tags 
    public void fixPostion(){
        if(getPosition().contains("1B") || getPosition().contains("3B")){
            setPosition(getPosition() + "_CI");
        }
        
        if(getPosition().contains("2B") || getPosition().contains("SS")){
            setPosition(getPosition() + "_MI");
        }
        
        setPosition(getPosition() + "_U");
    }

    /**
     * @return the last
     */
    public String getLast() {
        return last;
    }

    /**
     * @return the truePosition
     */
    public String getTruePosition() {
        return truePosition;
    }

    /**
     * @param truePosition the truePosition to set
     */
    public void setTruePosition(String truePosition) {
        this.truePosition = truePosition;
    }

    /**
     * @return the salary
     */
    public String getSalary() {
        return salary;
    }

    /**
     * @param salary the salary to set
     */
    public void setSalary(String salary) {
        this.salary = salary;
    }

    /**
     * @return the contract
     */
    public String getContract() {
        return contract;
    }

    /**
     * @param contract the contract to set
     */
    public void setContract(String contract) {
        this.contract = contract;
    }
}
