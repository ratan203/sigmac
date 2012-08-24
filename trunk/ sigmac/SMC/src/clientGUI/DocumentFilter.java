/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clientGUI;

import edu.stanford.nlp.linalg.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import smc.Concept;
import smc.Document;
import smc.RelatedConcept;

/**
 *
 * @author Thilina
 */
public class DocumentFilter {
    private HashMap<String,Concept> filteredConceptList=new HashMap<String, Concept>();
    private HashMap<String,Concept> conceptList=new HashMap<String, Concept>();
    private String[] sortedConceptList;
    public Document filterDocument(Document doc,float percentage,float asoThresh){
        Document filterDoc = new Document();
        Concept concept;
        float requiredCoverage=percentage/100;
        float sumOfImportance=0;
        conceptList=doc.getDoc();
        //Sort concepts by importance value 
        sortHashMap(conceptList);
        
        //Add importance value until cover required percentage
        for(String con:sortedConceptList){
            if(sumOfImportance<=requiredCoverage){
                sumOfImportance+=conceptList.get(con).getImportance();
                concept=filterRelationships(conceptList.get(con),asoThresh);
                if(concept.getRelationships().size()>0){
                    if(filteredConceptList.containsKey(con)){
                        filteredConceptList.remove(con);
                        filteredConceptList.put(con, concept);
                    }else{
                        filteredConceptList.put(con, concept);
                    }
                }
            }
        }
        filterDoc.setDoc(filteredConceptList);
        return filterDoc;
    }     
    
    private HashMap<String, Concept> sortHashMap(HashMap<String, Concept> input){
    Map<String, Float> importanceMap = new HashMap<String, Float>();
    List<Concept> mapValues = new ArrayList<Concept>(input.values());
    for(Concept c:mapValues){
        importanceMap.put(c.getName(), c.getStrength());
    }
    List<Float> mapValuesImp = new ArrayList<Float>(importanceMap.values());
    List<String> mapKeysImp = new ArrayList<String>(importanceMap.keySet());
    
    HashMap<String, Concept> sortedMap = new LinkedHashMap<String, Concept>();

    for(int i=0;i<importanceMap.size();i++){
        
    }
    
    TreeSet<Float> sortedSet = new TreeSet<Float>(mapValuesImp);
    Object[] sortedArray = sortedSet.toArray();
    int size = sortedArray.length;
        System.out.println(size);
    for (int i=0; i<size; i++){
        sortedMap.put(mapKeysImp.get(mapValuesImp.indexOf(sortedArray[size-1-i])),input.get(mapKeysImp.get(mapValuesImp.indexOf(sortedArray[size-1-i]))));
    }
    return sortedMap;
}

    private Concept filterRelationships(Concept con, float asoLim) {
        String conceptName=con.getName();
        HashMap<String, ArrayList<RelatedConcept>> conceptRelations=con.getRelationships();
        HashMap<String, ArrayList<RelatedConcept>> filterRelations=new HashMap<String, ArrayList<RelatedConcept>>();
        ArrayList<RelatedConcept> filteredRelatedConcepts;
        
        for(String relCon:conceptRelations.keySet()){
            filteredRelatedConcepts=new ArrayList<RelatedConcept>();
            for(RelatedConcept rc:conceptRelations.get(relCon)){
                if(rc.getType().equals("is a")||rc.getType().equals("part of")){
                    filteredRelatedConcepts.add(rc);
                    updateConceptList(conceptName,relCon,rc);
                }else if(rc.getStrength()>=asoLim){
                    filteredRelatedConcepts.add(rc);
                    updateConceptList(conceptName,relCon,rc);
                }
            }
            if(filteredRelatedConcepts.size()>0){
                filterRelations.put(relCon, filteredRelatedConcepts);  
            }
        }
        con.setRelationships(filterRelations);
        return con;
    }

    private void updateConceptList(String conceptName, String relCon, RelatedConcept rc) {
        Concept c=conceptList.get(relCon);
        ArrayList<RelatedConcept> rcList=c.getRelationships().get(conceptName);
        ArrayList<RelatedConcept> releventRCList=new ArrayList<RelatedConcept>();
        HashMap<String,ArrayList<RelatedConcept>> releventRels=new HashMap<String, ArrayList<RelatedConcept>>();
        for(RelatedConcept rc1:rcList){
            if(rc1.getType().equals(rc.getType())){
                releventRCList.add(rc1);
            }
        }
        
        if(releventRCList.size()>0){
            releventRels.put(conceptName, releventRCList);

            if(filteredConceptList.containsKey(relCon)){
                if(filteredConceptList.get(relCon).getRelationships().containsKey(conceptName)){
                    filteredConceptList.get(relCon).getRelationships().get(conceptName).removeAll(releventRCList);
                    filteredConceptList.get(relCon).getRelationships().get(conceptName).addAll(releventRCList);
                }else{
                    filteredConceptList.get(relCon).getRelationships().put(conceptName, releventRCList);
                }
            }else{
                c.setRelationships(releventRels);
                filteredConceptList.put(relCon, c);
            }
        }
        
    }

}


