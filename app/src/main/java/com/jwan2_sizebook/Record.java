package com.jwan2_sizebook;

import java.text.DecimalFormat;

import static java.lang.Math.round;


/**
 * Created by Jia on 2017/2/2.
 */
public class Record {

    private String Name;
    private String date;
    private String neck;
    private String bust;
    private String chest;
    private String waist;
    private String hip;
    private String inseam;
    private String comment;

    /**
     * Instantiates a new Record.
     *
     * @param Name the name
     */
    public Record(String Name) {
        this.Name = Name;
        this.date = null;
        this.neck = null;
        this.bust = null;
        this.chest = null;
        this.waist = null;
        this.hip = null;
        this.inseam = null;
        this.comment = null;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(String date) {
        this.date = date;
    }


    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return Name;
    }

    /**
     * Sets name.
     *
     * @param Name the name
     */
    public void setName(String Name) {
        this.Name = Name;
    }

    /**
     * Gets neck.
     *
     * @return the neck
     */
    public String getNeck() {
        return neck;
    }

    /**
     * Sets neck.
     *
     * @param neck the neck
     */
    public void setNeck(String neck) {
        this.neck = Round(neck);
    }

    /**
     * Gets bust.
     *
     * @return the bust
     */
    public String getBust() {
        return bust;
    }

    /**
     * Sets bust.
     *
     * @param bust the bust
     */
    public void setBust(String bust) {
        this.bust = Round(bust);
    }

    /**
     * Gets chest.
     *
     * @return the chest
     */
    public String getChest() {
        return chest;
    }

    /**
     * Sets chest.
     *
     * @param chest the chest
     */
    public void setChest(String chest) {
        this.chest = Round(chest);
    }

    /**
     * Gets waist.
     *
     * @return the waist
     */
    public String getWaist() {
        return waist;
    }

    /**
     * Sets waist.
     *
     * @param waist the waist
     */
    public void setWaist(String waist) {
        this.waist = Round(waist);
    }

    /**
     * Gets hip.
     *
     * @return the hip
     */
    public String getHip() {
        return hip;
    }

    /**
     * Sets hip.
     *
     * @param hip the hip
     */
    public void setHip(String hip) {
        this.hip = Round(hip);
    }

    /**
     * Gets inseam.
     *
     * @return the inseam
     */
    public String getInseam() {
        return inseam;
    }

    /**
     * Sets inseam.
     *
     * @param inseam the inseam
     */
    public void setInseam(String inseam) {
        this.inseam = Round(inseam);
    }

    /**
     * Gets comment.
     *
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets comment.
     *
     * @param comment the comment
     */
    public void setComment(String comment) {
        if (comment.length() > 100){
            this.comment = comment.substring(0, 100);
        }
        else {
            this.comment = comment;
        }
    }

    /**
     * Round string.
     *
     * @param num the num
     * @return the string
     */
//Round
    public String Round(String num){
        if(num.equals("")){
            return num;
        }
        Double output = Double.parseDouble(num);
        output = Math.round(output * 10.0 ) / 10.0;
        return ""+output;
    }

    @Override
    public String toString(){
        String out;
        out = "Name: " + Name + "\n"
                +"Neck: " + neck +"\n"
                +"Bust: " + bust +"\n"
                +"Chest: " + chest +"\n"
                +"Waist: " + waist +"\n"
                + "Hip " + hip +"\n"
                +"Inseam: " + inseam +"\n";
        return out;
    }



}
