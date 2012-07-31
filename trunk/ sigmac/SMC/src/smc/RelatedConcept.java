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
    private int strength;

    public RelatedConcept(String type,String rConcept,boolean isHead,int strength){
        this.type=type;
        this.rConcept=rConcept;
        this.isHead=isHead;
        this.strength=strength;
    }

    public String getRelatedConcept() {
        return rConcept;
    }

    public void setRelatedConcept(String rConcept) {
        this.rConcept = rConcept;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isHead(){
        return isHead;
    }

    public void modifyStrength(int amount){
//        System.out.println(this.rConcept);
//        System.out.println(this.strength);
//        System.out.println(amount);
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
}
