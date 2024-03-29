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
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author jayaprabath
 */
public class Concept implements Serializable, Comparable<Concept> {
    private String name;
    private HashMap<String,ArrayList<RelatedConcept>> relationships;
    private ArrayList<RelatedConcept> asoRelationships;
    private int freequency;
    private float titleStrength;
    private float strength;
    private float importance;
    private float value;
    private boolean isUserAdded=false;
    public static final int FREEQUENCY_STRENTH_MULTIPLIER=1;

    public Concept(String name){
        this.name=name;
        relationships=new HashMap<String, ArrayList<RelatedConcept>>();
        freequency=1;
        strength=0;
        value=this.strength + this.freequency*FREEQUENCY_STRENTH_MULTIPLIER;
    }

    public int getFreequency() {
        return freequency;
    }

    public void setFreequency(int freequency) {
        this.freequency = freequency;
        value=this.strength + this.freequency*FREEQUENCY_STRENTH_MULTIPLIER;
    }



    public void modifyFreequency(int amount){
        //System.out.println("Test2");
        freequency+=amount;
        value=this.strength + this.freequency*FREEQUENCY_STRENTH_MULTIPLIER;
    }

    public void modifyStrength(float amount){
        this.strength+=amount;
        value=this.strength + this.freequency*FREEQUENCY_STRENTH_MULTIPLIER;
    }

    public void modifyImprotance(float amount){
        importance+=amount;
//        System.out.println("modify importance of");
//        System.out.println(this.name);
//        System.out.println(importance);
    }

    public float getImportance(){
        return importance;
    }

    public void setImportance(float val){
        this.importance=val;
    }
    
    public boolean getUserAddedStatus(){
        return isUserAdded;
    }

    public void setUserAddedStatus(boolean userAdded){
        this.isUserAdded=userAdded;
    }

    public String getName() {
        return name;
    }

    public float getStrength(){
        return this.strength;
    }

    public float getValue(){
        return this.value;
    }

    public void modifyValue(float amount){
        this.value+=amount;
    }

    public void resetValue(){
        value=this.strength + this.freequency*FREEQUENCY_STRENTH_MULTIPLIER;
    }

    public void setStrength(float strength){
        this.strength=strength;
        value=this.strength + this.freequency*FREEQUENCY_STRENTH_MULTIPLIER;
    }

    public void updateStrength(float amount){
        this.strength+=amount;
        value=this.strength + this.freequency*FREEQUENCY_STRENTH_MULTIPLIER;
    }

    public void setTitleStrength(float tStrength){
        this.titleStrength=tStrength;
    }

    public float getTitleStrength(){
        return this.titleStrength;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addRelatedConcept(String name,String type,boolean isHead,int freequency){
//        System.out.println(name +" : "+strength);
        ArrayList<RelatedConcept> list;
        RelatedConcept con=new RelatedConcept(type, name, isHead, freequency);
        boolean match=false;
        if(this.relationships.containsKey(name)){
            list=relationships.get(name);
            for(RelatedConcept c : list){
                if(con.equals(c)){
//                    System.out.println("fffffffffffffffffffffffffffffffffff");
                    c.modifyFreequency(freequency);
                    match=true;
                    break;
                }
            }
            if(!match){
                list.add(con);
            }
        }else{
            list=new ArrayList<RelatedConcept>();
            list.add(con);
            relationships.put(name, list);
        }
    }

    public HashMap<String, ArrayList<RelatedConcept>> getRelationships() {
        return relationships;
    }

    public void setRelationships(HashMap<String, ArrayList<RelatedConcept>> relationships) {
        this.relationships = relationships;
    }

    public void addRelatedConcept(RelatedConcept rc){
        this.addRelatedConcept(rc.getRelatedConcept(), rc.getType(), rc.isHead(), rc.getFreequency());
    }

    public void addRelatedConcepts(HashMap<String, ArrayList<RelatedConcept>> rcs){
//        System.out.println("lllllllllllllllllllllllllllllllllllllllll");
        Set<String> keys=rcs.keySet();
        for(String key : keys){
//            System.out.println("KEYEYEYEYYEY:"+key);
            ArrayList<RelatedConcept> rcons=rcs.get(key);
            for(RelatedConcept r : rcons){
//                System.out.println(r.getRelatedConcept());
//                System.out.println(r.getType());
//                System.out.println(r.getStrength());
                this.addRelatedConcept(r);
            }
        }
    }

    public void resetRelatedConcepts(HashMap<String,ArrayList<RelatedConcept>> map){
        this.relationships=map;
    }

    public void printConcept(){
        
        System.out.println("Concept : "+ name);
        System.out.println("Freequency : "+ freequency);
        System.out.println("Strength : "+strength);
        System.out.println("Importance : "+importance);
        System.out.println("*********Relationships*******************");
        Set<String> keys=relationships.keySet();
        for(String key : keys){
            System.out.println(key);
            ArrayList<RelatedConcept> rels=relationships.get(key);
            for(RelatedConcept rel : rels){
                System.out.println("Type : "+rel.getType());
                System.out.println("Strength : "+rel.getFreequency());
                System.out.println("Is Head : "+ rel.isHead());
            }
        }
    }
	
	public int getTotalOutboundCount(){
        int value=0;
        Set<String> keySet=this.relationships.keySet();
        for(String key : keySet){
            value+=getOutboundCountTo(key);
        }
        return value;
    }
	
	public int getOutboundCountTo(String concept){
        int value=0;
        ArrayList<RelatedConcept> rels = this.relationships.get(concept);
        for(RelatedConcept rel : rels){
            value+=rel.getValue();
        }
        return value;
    }

    public boolean deleteRelationshipsToConcept(String concept){
        System.out.println("find relation to : "+concept);
        if(this.relationships.containsKey(concept)){
            this.relationships.remove(concept);
        }else{
            System.out.println("ohh no relationship found this is a bug");
        }
        if(this.relationships.isEmpty()){
            System.out.println("oh im not related to any one im : "+this.name);
            return true;
        }else{
            return false;
        }
    }

    public int compareTo(Concept o) { // this is to sort in decending order
        if(this.importance==o.importance){
            return 0;
        }else if(this.importance>o.importance){
            return -1;
        }else{
            return 1;
        }
    }

    public Concept getCopy(){
        Concept c=new Concept(this.name);
        c.setFreequency(this.freequency);
        c.setImportance(this.importance);
        c.setTitleStrength(this.titleStrength);
        c.setStrength(this.strength);
        HashMap<String,ArrayList<RelatedConcept>> relations=new
                HashMap<String, ArrayList<RelatedConcept>>();
        Set<String> keySet = this.relationships.keySet();
        for(String key : keySet){
            ArrayList<RelatedConcept> rc=this.relationships.get(key);
            ArrayList<RelatedConcept> list=new ArrayList<RelatedConcept>();
            for(RelatedConcept r : rc){
                list.add(r.getCopy());
            }
            relations.put(key, list);
        }
        c.resetRelatedConcepts(relations);
        return c;
    }
    public ArrayList<RelatedConcept> getAsoRelatedConcepts(ArrayList<RelatedConcept> all){
        asoRelationships=new ArrayList<RelatedConcept>();
        for(RelatedConcept rc:all){
            if(rc.getType().equals("aso")){
                asoRelationships.add(rc);
            }
        }
        return asoRelationships;
    }
    public ArrayList<RelatedConcept> getSortedRelatedConcepts(String con){
        ArrayList<RelatedConcept> relationshipSort=getAsoRelatedConcepts(relationships.get(con));
        Collections.sort(relationshipSort);
        return relationshipSort;
    }
}
