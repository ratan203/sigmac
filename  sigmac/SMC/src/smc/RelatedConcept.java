/*
 * Copyright 2012 Jayaprabath Fernando
 *
 * This file is part of SigmaC.
 *
 * SigmaC is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SigmaC is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with SigmaC.  If not, see <http://www.gnu.org/licenses/>.
 */

package smc;

import java.io.Serializable;

/**
 *
 * @author user
 */
public class RelatedConcept implements Serializable, Comparable<RelatedConcept> {
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

    public int compareTo(RelatedConcept o) {
        if(this.strength==o.strength){
            return 0;
        }else if(this.strength>o.strength){
            return -1;
        }else{
            return 1;
        }
    }
}
