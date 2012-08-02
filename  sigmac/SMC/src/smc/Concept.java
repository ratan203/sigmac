/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package smc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author user
 */
public class Concept implements Serializable {
    private String name;
    private HashMap<String,ArrayList<RelatedConcept>> relationships;
    private int freequency;
    private int strength;
    private float importance;
    public static final int FREEQUENCY_STRENTH_MULTIPLIER=1;

    public Concept(String name){
        this.name=name;
        relationships=new HashMap<String, ArrayList<RelatedConcept>>();
        freequency=1;
        strength=0;
    }

    public int getFreequency() {
        return freequency;
    }

    public void setFreequency(int freequency) {
        this.freequency = freequency;
    }



    public void modifyFreequency(int amount){
        //System.out.println("Test2");
        freequency+=amount;
    }

    public void modifyImprotance(float amount){
        importance+=amount;
        System.out.println("modify importance of");
        System.out.println(this.name);
        System.out.println(importance);
    }

    public String getName() {
        return name;
    }

    public int getStrength(){
        return this.strength;
    }

    public int getValue(){
        return this.strength + this.freequency*FREEQUENCY_STRENTH_MULTIPLIER;
    }

    public void setStrength(int strength){
        this.strength=strength;
    }

    public void updateStrength(int amount){
        this.strength+=amount;
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
}
