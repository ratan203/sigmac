/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SCAnalyzer;

import java.util.ArrayList;
import java.util.HashMap;
import smc.Concept;
import smc.Document;
import smc.RelatedConcept;

/**
 *
 * @author Thilina
 */
public class DocumentFilter {
    
    private HashMap<String, Concept> filteredConceptList = new HashMap<String, Concept>();
    private HashMap<String, Concept> conceptList = new HashMap<String, Concept>();
    private ArrayList<Concept> sortedConceptList;
    private Concept concept;

    public Document filterDocument(Document doc, float percentage) {
        
        float asoThresh = (100 - percentage) / 100;
        Document filterDoc = new Document();
        filterDoc.setName(doc.getName());
        filterDoc.setUri(doc.getUri());
        filterDoc.setLastModifiedDate(doc.getLastModifiedDate());
        filterDoc.setSize(doc.getSize());
        //Coverage that shuold cover by relevent set of concepts
        float requiredCoverage = percentage / 100;
        float sumOfImportance = 0;
        conceptList = (HashMap<String, Concept>) doc.getDoc().clone();

        //Sort concepts by importance value 
        sortedConceptList = doc.getSortedConceptList();

        //Add importance value until cover required percentage
        for (Concept con : sortedConceptList) {
            if(!con.getUserAddedStatus()){
                if (sumOfImportance <= requiredCoverage) {
                    sumOfImportance += conceptList.get(con.getName()).getImportance();
                    //Filter out the relationships of concept into same ratio
                    addEditRelationships(con, asoThresh);
                }
            }else{
                addEditRelationships(con, asoThresh);
            }
        }
        filteredConceptList = optimizeRelationships(filteredConceptList);
        filterDoc.setDoc(filteredConceptList);
        return filterDoc;
    }    
    
    private void addEditRelationships(Concept c1,float asoThreshold){
        concept = filterRelationships(conceptList.get(c1.getName()), asoThreshold);
        if (concept.getRelationships().size() > 0) {
            if (filteredConceptList.containsKey(c1.getName())) {
                filteredConceptList.remove(c1.getName());
                filteredConceptList.put(c1.getName(), concept);
            } else {
                filteredConceptList.put(c1.getName(), concept);
            }
        }    
    }
    /**
     * This method is to filter out the relations of the concept
     * Here 'is_a' and 'part_of' relationships not removed 
     * Sorted 'aso' relationships are filtered by same percentage given
     * 
     * @param Concept (Need to filter relationships)
     * @param float (Percentage limit to filter 'aso' relationships )
     * @return Concept (Filtered)
     */
    private Concept filterRelationships(Concept con, float asoLim) {
        HashMap<String, ArrayList<RelatedConcept>> conceptRelations = con.getRelationships();
        HashMap<String, ArrayList<RelatedConcept>> filterRelations = new HashMap<String, ArrayList<RelatedConcept>>();
        ArrayList<RelatedConcept> filteredRelatedConcepts;
        
        for (String relCon : conceptRelations.keySet()) {
            //Select 'aso' relationships which have high strength value 
            filteredRelatedConcepts = new ArrayList<RelatedConcept>();
            ArrayList<RelatedConcept> rcs = con.getSortedRelatedConcepts(relCon);
            if(!con.getUserAddedStatus()){
                if (rcs.size() > 1) {
                    for (int i = 0; i < rcs.size() * (asoLim / 100); i++) {
                        filteredRelatedConcepts.add(rcs.get(i));
                        if (!filteredConceptList.containsKey(relCon)) {
                            filteredConceptList.put(relCon, conceptList.get(relCon));
                        }
                    }

                }else if(rcs.size()==1){
                    filteredRelatedConcepts.add(rcs.get(0));
                    if (!filteredConceptList.containsKey(relCon)) {
                        filteredConceptList.put(relCon, conceptList.get(relCon));
                    }
                }
                
                for (RelatedConcept rc : conceptRelations.get(relCon)) {
                    if (rc.getType().equals("is a") || rc.getType().equals("part of")) {
                        filteredRelatedConcepts.add(rc);
                        if (!filteredConceptList.containsKey(relCon)) {
                            filteredConceptList.put(relCon, conceptList.get(relCon));
                        }
                    }
                }
            }else{
                filteredRelatedConcepts.addAll(conceptRelations.get(relCon));
                if (!filteredConceptList.containsKey(relCon)) {
                    filteredConceptList.put(relCon, conceptList.get(relCon));
                }
            }
            if (filteredRelatedConcepts.size() > 0) {
                filterRelations.put(relCon, filteredRelatedConcepts);                
            }
        }
        con.setRelationships(filterRelations);
        return con;
    }
    
    /**
     * Method to optimize filtered concept HashMap by removing relationships if both concept of that relationships doesn't exist in 
     * concept HashMap
     * 
     * @param HashMap<String, Concept> (HashMap to modify)
     * @return HashMap<String, Concept> (Modified HashMap)
     */
    private HashMap<String, Concept> optimizeRelationships(HashMap<String, Concept> opitimizedConcept) {        
        for (String s : opitimizedConcept.keySet()) {
            String[] keyArray = opitimizedConcept.get(s).getRelationships().keySet().toArray(new String[0]);
            for (int i = 0; i < keyArray.length; i++) {
                if (!filteredConceptList.containsKey(keyArray[i])) {
                    opitimizedConcept.get(s).getRelationships().remove(keyArray[i]);
                }
            }
            
        }
        return opitimizedConcept;
    }
}

