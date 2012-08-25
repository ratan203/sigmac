/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package smc;

import java.io.Serializable;

/**
 *
 * @author user
 */
public class RelatedConcept implements Serializable {
    private String type;
    private String rConcept;
    private boolean isHead;
    private int freequency;
    private float strength;
    private int isStrength;
    private int partStrength;
    public static final int headStrengthMultiplier=2;
    public static final int tailStrengthMultiplier=1;

    public RelatedConcept(String type,String rConcept,boolean isHead,int freequency){
        this.type=type;
        this.rConcept=rConcept;
        this.isHead=isHead;
        this.freequency=freequency;
    }

    public String getRelatedConcept() {
        return rConcept;
    }

    public void setRelatedConcept(String rConcept) {
        this.rConcept = rConcept;
    }

    public int getFreequency() {
        return freequency;
    }

    public float getStrength(){
        return strength;
    }

    public void setStrength(float strength) {
        this.strength = strength;
    }

    public float getValue(){
        if(this.type.equals("aso")){
            return this.strength+this.freequency;
        }
        if(this.isHead){
            return this.strength+this.freequency*headStrengthMultiplier;
        }else{
            return this.strength+this.freequency*tailStrengthMultiplier;
        }
    }

    public void setFreequency(int freequency) {
        this.freequency = freequency;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getIsStrength() {
        return isStrength;
    }

    public void setIsStrength(int val) {
        this.isStrength = val;
    }

    public int getPartStrength() {
        return partStrength;
    }

    public void setPartStrength(int val) {
        this.partStrength = val;
    }

    public boolean isHead(){
        return isHead;
    }

    public void setHead(Boolean head){
        this.isHead=head;
    }

    public void modifyFreequency(int amount){
//        System.out.println(this.rConcept);
//        System.out.println(this.strength);
//        System.out.println(amount);
        this.freequency+=amount;
    }

    public void modifyStrength(int amount){
        this.strength+=amount;
    }

    public boolean equals(RelatedConcept rc){
        if(this.isHead==rc.isHead && this.rConcept.equals(rc.getRelatedConcept()) && this.type.equals(rc.getType())){
            //System.out.println(this.rConcept);
            return true;
        }else{
            return false;
        }
    }

    public RelatedConcept getCopy(){
        RelatedConcept rc=new RelatedConcept(this.type, this.rConcept, this.isHead, this.freequency);
        rc.setIsStrength(this.isStrength);
        rc.setPartStrength(this.partStrength);
        rc.setStrength(this.strength);
        return rc;
    }
}
