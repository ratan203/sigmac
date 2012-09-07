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
 * @author jayaprabath
 */
public class ConceptRanker {

    public ConceptRanker(){
        
    }

    public void rankConcepts(Document doc){
        resetConceptValues(doc);
        System.out.println("Start Ranking **************");
        HashMap<String, Concept> cMap = doc.getDoc();
        float totalDocumentValue = doc.getTotalDocumentValue();
        Set<String> keySet = cMap.keySet();
        float total=0.0f;
        int i=0;
        do{
        totalDocumentValue = doc.getTotalDocumentValue();
        i++;
        for(String key : keySet){       //operate on each concept
//            System.out.println("***************************************************");
//            System.out.println("Analyzing related concepts of concept : "+key);
            //operate on each concept
            Concept concept = cMap.get(key);
//            System.out.println("Value of The Concepet : "+concept.getValue());
            int totalOutLinks=concept.getTotalOutboundCount();      //total number of links going out
//            System.out.println("Total num of out going links = "+totalOutLinks);
            //total number of links going out
            HashMap<String, ArrayList<RelatedConcept>> relationships = concept.getRelationships();
            Set<String> relKeySet = relationships.keySet();
            for(String relKey : relKeySet){     //operate on each related concept
//                System.out.println("Related Concept : "+ relKey);
                int outToConcept=concept.getOutboundCountTo(relKey); //links going to concept
//                System.out.println("NO of links to the related concept : "+ outToConcept);
                float linkFraction=(float)outToConcept/(float)totalOutLinks; //fraction of links
//                System.out.println("Link Fraction : "+linkFraction);
                float importance=(float)concept.getValue()*linkFraction/totalDocumentValue;
//                System.out.println("Change of importance : "+importance);
                Concept relatedConcept = cMap.get(relKey);
                relatedConcept.modifyValue(importance);
                total+=importance;
            }
        }
            System.out.println(doc.getTotalDocumentValue()-totalDocumentValue);
        }while(i<100 && (doc.getTotalDocumentValue()-totalDocumentValue)>1.0015);
        System.out.println("Total====="+total);
        System.out.println("End Ranking *******************8");
        updateImportance(doc);
    }

    private void updateImportance(Document doc){
        HashMap<String, Concept> cMap = doc.getDoc();
        float totalDocumentValue = doc.getTotalDocumentValue();
        Set<String> keySet = cMap.keySet();
        float tot=0.0f;
        for(String key : keySet){       //operate on each concept
//            System.out.println("***************************************************");
//            System.out.println("Analyzing related concepts of concept : "+key);
            //operate on each concept
            Concept concept = cMap.get(key);
//            System.out.println("Value of The Concepet : "+concept.getValue());
            int totalOutLinks=concept.getTotalOutboundCount();      //total number of links going out
//            System.out.println("Total num of out going links = "+totalOutLinks);
            //total number of links going out
            HashMap<String, ArrayList<RelatedConcept>> relationships = concept.getRelationships();
            Set<String> relKeySet = relationships.keySet();
            for(String relKey : relKeySet){     //operate on each related concept
//                System.out.println("Related Concept : "+ relKey);
                int outToConcept=concept.getOutboundCountTo(relKey); //links going to concept
//                System.out.println("NO of links to the related concept : "+ outToConcept);
                float linkFraction=(float)outToConcept/(float)totalOutLinks; //fraction of links
//                System.out.println("Link Fraction : "+linkFraction);
                float importance=(float)concept.getValue()*linkFraction/totalDocumentValue;
//                System.out.println("Change of importance : "+importance);
                tot+=importance;
                Concept relatedConcept = cMap.get(relKey);
                relatedConcept.modifyImprotance(importance);
            }
        }
        //resetConceptValues(doc);
        System.out.println("Total==="+tot);
    }
    private void resetConceptValues(Document doc){
        HashMap<String, Concept> cMap = doc.getDoc();
        Set<String> keySet = cMap.keySet();
        for(String key : keySet){
            cMap.get(key).resetValue();
        }
    }
}
