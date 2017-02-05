package com.jwan2_sizebook;

import java.text.DecimalFormat;

import static java.lang.Math.round;


/**
 * Created by Jia on 2017/2/2.
 */


public class Record {

    private String firstName;
    private String lastName;
    private String date;
    private String neck;
    private String bust;
    private String chest;
    private String waist;
    private String hip;
    private String inseam;
    private String comment;

    public Record() {
        this.firstName = null;
        this.lastName = null;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNeck() {
        return neck;
    }

    public void setNeck(String neck) {
        this.neck = neck;
    }

    public String getBust() {
        return bust;
    }

    public void setBust(String bust) {
        this.bust = bust;
    }

    public String getChest() {
        return chest;
    }

    public void setChest(String chest) {
        this.chest = chest;
    }

    public String getWaist() {
        return waist;
    }

    public void setWaist(String waist) {
        this.waist = waist;
    }

    public String getHip() {
        return hip;
    }

    public void setHip(String hip) {
        this.hip = hip;
    }

    public String getInseam() {
        return inseam;
    }

    public void setInseam(String inseam) {
        this.inseam = inseam;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString(){
        String out;
        out = "Name: " + lastName + " " + firstName + "\n"
                +"Bust: " + bust +"\n"
                +"Chest: " + chest +"\n"
                +"Waist: " + waist +"\n"
                +"Inseam: " + inseam +"\n";
        return out;
    }

//    public String Round(String num){
//        String output;
//        Float float1;
//        float1 = Float.parseFloat(num);
//        output = new DecimalFormat("#.##").format(float1);
//        return output;
//    }




}
