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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getNeck() {
        return neck;
    }

    public void setNeck(String neck) {
        this.neck = Round(neck);
    }

    public String getBust() {
        return bust;
    }

    public void setBust(String bust) {
        this.bust = Round(bust);
    }

    public String getChest() {
        return chest;
    }

    public void setChest(String chest) {
        this.chest = Round(chest);
    }

    public String getWaist() {
        return waist;
    }

    public void setWaist(String waist) {
        this.waist = Round(waist);
    }

    public String getHip() {
        return hip;
    }

    public void setHip(String hip) {
        this.hip = Round(hip);
    }

    public String getInseam() {
        return inseam;
    }

    public void setInseam(String inseam) {
        this.inseam = Round(inseam);
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        if (comment.length() > 100){
            this.comment = comment.substring(0, 100);
        }
        else {
            this.comment = comment;
        }
    }

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
