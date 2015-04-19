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
    
    
    private String proteam;         //TEAM
    private String position;        //QP
    private String year;            //YEAR_OF_BIRTH
    private String r;               //R
    private String hr;              //HR
    private String rbi;             //RBI
    private String sb;              //SB
    private String note;            //NOTES
    private Double ba;              //H/AB
    
    private String ab;              //AB
    private String h;               //H
    private String nation;          //NATION_OF_BIRTH
    
    public Hitter(String first, String last){
        this.first = first;
        this.last = last;
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
     * @return the r
     */
    public String getR() {
        return r;
    }

    /**
     * @param r the r to set
     */
    public void setR(String r) {
        this.r = r;
    }

    /**
     * @return the hr
     */
    public String getHr() {
        return hr;
    }

    /**
     * @param hr the hr to set
     */
    public void setHr(String hr) {
        this.hr = hr;
    }

    /**
     * @return the rbi
     */
    public String getRbi() {
        return rbi;
    }

    /**
     * @param rbi the rbi to set
     */
    public void setRbi(String rbi) {
        this.rbi = rbi;
    }

    /**
     * @return the sb
     */
    public String getSb() {
        return sb;
    }

    /**
     * @param sb the sb to set
     */
    public void setSb(String sb) {
        this.sb = sb;
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
    public Double getBa() {
        return ba;
    }

    /**
     * @param ba the ba to set
     */
    public void setBa(Double ba) {
        this.ba = ba;
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
                "\nLAST_NAME: " + last +
                "\nFIRST_NAME: " + first +
                "\nQP: " + position +
                "\nAB: " + ab +
                "\nR: " + r +
                "\nH: " + h +
                "\nHR: " + hr +
                "\nRBI: " + rbi +
                "\nSB: " + sb +
                "\nNOTES: " + note +
                "\nYEAR_OF_BIRTH: " + year +
                "\nNATION_OF_BIRTH: " + nation +
                "\nBA: " + ba);
                
    }
    
    
    // adds CI MI U tags 
    public void fixPostion(){
        if(position.contains("1B") || position.contains("3B")){
            position = position + "_CI";
        }
        
        if(position.contains("2B") || position.contains("SS")){
            position = position + "_MI";
        }
        
        position = position + "_U";
    }
}
