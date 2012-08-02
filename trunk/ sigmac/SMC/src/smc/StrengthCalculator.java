/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package smc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author user
 */
public class StrengthCalculator {

    public StrengthCalculator(){

    }

    public void calculateImportance(Document doc){
        HashMap<String, Concept> conceptMap = doc.getDoc();
        Set<String> keySet = conceptMap.keySet();
        for(String key : keySet){
            Concept concept=conceptMap.get(key);
            HashMap<String, ArrayList<RelatedConcept>> relationships = concept.getRelationships();
            Set<String> relKeys = relationships.keySet();
            int totalOut=getTotalOutbound(relationships);
            System.out.println("Total from "+key+" is :"+totalOut);
            for(String relKey : relKeys){
                ArrayList<RelatedConcept> list = relationships.get(relKey);
                int relValue=getRelationshipValue(list);
                System.out.println("RelValue : "+relValue);
                Concept con=conceptMap.get(relKey);
                System.out.println("Concept value : "+concept.getValue());
                con.modifyImprotance((float)concept.getValue()*relValue/totalOut);
            }
        }
    }

    private int getRelationshipValue(ArrayList<RelatedConcept> list){
        int value=0;
        for(RelatedConcept rel : list){
            value+=rel.getValue();
        }
        return value;
    }

    private int getTotalOutbound(HashMap<String, ArrayList<RelatedConcept>> relationships){
        int value=0;
        Set<String> keySet = relationships.keySet();
        for(String key : keySet){
            value+=getRelationshipValue(relationships.get(key));
        }
        return value;
    }
}
