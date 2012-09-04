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
        float totalDocStrength=doc.getTotalDocumentValue();
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
                con.modifyImprotance((float)concept.getValue()*relValue/(totalOut*totalDocStrength));
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
