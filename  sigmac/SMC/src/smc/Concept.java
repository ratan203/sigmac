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
    private int strength;

    public Concept(String name){
        this.name=name;
        relationships=new HashMap<String, ArrayList<RelatedConcept>>();
        strength=1;
    }

    public Concept(String name, int strength){
        this.name=name;
        relationships=new HashMap<String, ArrayList<RelatedConcept>>();
        this.strength=strength;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void modifyStrength(int amount){
        //System.out.println("Test2");
        this.setStrength(strength+amount);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addRelatedConcept(String name,String type,boolean isHead,int strength){
//        System.out.println(name +" : "+strength);
        ArrayList<RelatedConcept> list;
        RelatedConcept con=new RelatedConcept(type, name, isHead, strength);
        boolean match=false;
        if(this.relationships.containsKey(name)){
            list=relationships.get(name);
            for(RelatedConcept c : list){
                if(con.equals(c)){
//                    System.out.println("fffffffffffffffffffffffffffffffffff");
                    c.modifyStrength(strength);
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
        this.addRelatedConcept(rc.getRelatedConcept(), rc.getType(), rc.isHead(), rc.getStrength());
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
        System.out.println("Strength : "+ strength);
        System.out.println("*********Relationships*******************");
        Set<String> keys=relationships.keySet();
        for(String key : keys){
            System.out.println(key);
            ArrayList<RelatedConcept> rels=relationships.get(key);
            for(RelatedConcept rel : rels){
                System.out.println("Type : "+rel.getType());
                System.out.println("Strength : "+rel.getStrength());
                System.out.println("Is Head : "+ rel.isHead());
            }
        }
    }
}
