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
public class ConceptRanker {

    public ConceptRanker(){
        
    }

    public void rankConcepts(Document doc){
        HashMap<String, Concept> cMap = doc.getDoc();
        int totalDocumentStrength = doc.getTotalDocumentStrength();
        Set<String> keySet = cMap.keySet();
        float total=0.0f;
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
                float importance=(float)concept.getValue()*linkFraction/totalDocumentStrength;
//                System.out.println("Change of importance : "+importance);
                Concept relatedConcept = cMap.get(relKey);
                relatedConcept.modifyImprotance(importance);
                total+=importance;
            }
        }
//        System.out.println("Total====="+total);
    }
}
