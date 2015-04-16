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
public class Pitcher {
    
    private String first;           //FIRST_NAME
    private String last;            //LAST_NAME
    private String proteam;         //TEAM
    private String positions = "P"; 
    private String year;            //YEAR_OF_BIRTH
    private String w; //wins        //W
    private String sv;              //SV
    private String k;               //K
    private Double era;             //ER * 9 / IP
    private String whip;            //BB+H / IP
    private String note;            //NOTES
    private String nation;          //NATION_OF_BIRTHS
    
    
    private String ip;//inningspitch//IP
    private String er;//earnedruns
    
    private String bb;//walks       //BB
    
    //constructor for a Pitcher
    public Pitcher(String last, String first){
        this.last = last;
        this.first = first;
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
     * @return the last
     */
    public String getLast() {
        return last;
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
     * @return the positions
     */
    public String getPositions() {
        return positions;
    }

    /**
     * @param positions the positions to set
     */
    public void setPositions(String positions) {
        this.positions = positions;
    }

    /**
     * @return the year
     */
    public String getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * @return the w
     */
    public String getW() {
        return w;
    }

    /**
     * @param w the w to set
     */
    public void setW(String w) {
        this.w = w;
    }

    /**
     * @return the sv
     */
    public String getSv() {
        return sv;
    }

    /**
     * @param sv the sv to set
     */
    public void setSv(String sv) {
        this.sv = sv;
    }

    /**
     * @return the k
     */
    public String getK() {
        return k;
    }

    /**
     * @param k the k to set
     */
    public void setK(String k) {
        this.k = k;
    }

    /**
     * @return the era
     */
    public Double getEra() {
        return era;
    }

    /**
     * @param era the era to set
     */
    public void setEra(Double era) {
        this.era = era;
    }

    /**
     * @return the whip
     */
    public String getWhip() {
        return whip;
    }

    /**
     * @param whip the whip to set
     */
    public void setWhip(String whip) {
        this.whip = whip;
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
    
}
