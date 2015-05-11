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
public class Pitcher extends Player{
    
    private String first;
    private String last;
    private String proteam;         //TEAM
    private String position; 
    private int year;            //YEAR_OF_BIRTH
    private int r_w; //wins        //W
    private int hr_sv;              //SV
    private int rbi_k;               //K
    private double sb_era;             //ER * 9 / IP
    private double ba_whip;            //BB+H / IP
    private String note;            //NOTES
    private String nation;          //NATION_OF_BIRTH
    
    
    private String ip;//inningspitch//IP
    private String er;//earnedruns  //ER
    
    private String bb;//walks       //BB
    private String h;               //H
    
    
    private String truePosition;
    private String salary;
    private String contract;
    
   
    
    //constructor for a Pitcher
    public Pitcher(){
        
    }
    
    public void setPick(int a){
        pick = a;
    }
    
    public int getPick(){
        return pick;
    }


    /**
     * @return the last
     */
    public String getLast() {
        return last;
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
     * @return the positions
     */
    public String getPosition() {
        return position;
    }

    /**
     * @param positions the positions to set
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
     * @return the w
     */
    public int getRW() {
        return r_w;
    }

    /**
     * @param w the w to set
     */
    public void setRW(int w) {
        this.r_w = w;
    }

    /**
     * @return the sv
     */
    public int getHRSV() {
        return hr_sv;
    }

    /**
     * @param sv the sv to set
     */
    public void setHRSV(int sv) {
        this.hr_sv = sv;
    }

    /**
     * @return the k
     */
    public int getRBIK() {
        return rbi_k;
    }

    /**
     * @param k the k to set
     */
    public void setRBIK(int k) {
        this.rbi_k = k;
    }

    /**
     * @return the era
     */
    public double getSBERA() {
        return sb_era;
    }

    /**
     * @param era the era to set
     */
    public void setSBERA(Double era) {
        this.sb_era = era;
    }

    /**
     * @return the whip
     */
    public double getBAWHIP() {
        return ba_whip;
    }

    /**
     * @param whip the whip to set
     */
    public void setBAWHIP(double whip) {
        this.ba_whip = whip;
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

    /**
     * @return the ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip the ip to set
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * @return the er
     */
    public String getEr() {
        return er;
    }

    /**
     * @param er the er to set
     */
    public void setEr(String er) {
        this.er = er;
    }

    /**
     * @return the bb
     */
    public String getBb() {
        return bb;
    }

    /**
     * @param bb the bb to set
     */
    public void setBb(String bb) {
        this.bb = bb;
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
    
    
    public void toSTRING(){
        System.out.println("Team: " + proteam +
                "\nLAST_NAME: " + last +
                "\nFIRST_NAME: " + getFirst() +
                "\nIP: " + ip +
                "\nER: " + er +
                "\nW: " + r_w +
                "\nSV: " + hr_sv +
                "\nH: " + h +
                "\nBB: " + bb +
                "\nK: " + rbi_k +
                "\nNOTES: " + note +
                "\nYEAR_OF_BIRTH: " + year +
                "\nNATION_OF_BIRTH: " + nation +
                "\nERA: " + sb_era +
                "\nWHIP: " + ba_whip +
                "\nPOSITION: " + position + "\n");
        
                
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
