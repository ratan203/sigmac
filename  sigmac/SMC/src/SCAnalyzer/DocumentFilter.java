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
    
    private HashMap<String,Concept> filteredConceptList=new HashMap<String, Concept>();
    private HashMap<String,Concept> conceptList=new HashMap<String, Concept>();
    private ArrayList<Concept> sortedConceptList;
    public Document filterDocument(Document doc,float percentage){
      
        float asoThresh=(100-percentage)/100;
        Document filterDoc = new Document();
        Concept concept;
        filterDoc.setName(doc.getName());
        filterDoc.setUri(doc.getUri());
        filterDoc.setLastModifiedDate(doc.getLastModifiedDate());
        filterDoc.setSize(doc.getSize());
        float requiredCoverage=percentage/100;
        float sumOfImportance=0;

        conceptList=(HashMap<String, Concept>) doc.getDoc().clone();

        //Sort concepts by importance value 
        sortedConceptList=doc.getSortedConceptList();
        
        //Add importance value until cover required percentage
        for(Concept con:sortedConceptList){
            if(sumOfImportance<=requiredCoverage){
                sumOfImportance+=conceptList.get(con.getName()).getImportance();
                concept=filterRelationships(conceptList.get(con.getName()),asoThresh);
                if(concept.getRelationships().size()>0){
                    if(filteredConceptList.containsKey(con.getName())){
                        filteredConceptList.remove(con.getName());
                        filteredConceptList.put(con.getName(), concept);
                    }else{
                        filteredConceptList.put(con.getName(), concept);
                    }
                }
            }
        }
        filteredConceptList=optimizeRelationships(filteredConceptList);
        filterDoc.setDoc(filteredConceptList);
        return filterDoc;
    }         

    private Concept filterRelationships(Concept con, float asoLim) {
        String conceptName=con.getName();
        HashMap<String, ArrayList<RelatedConcept>> conceptRelations=con.getRelationships();
        HashMap<String, ArrayList<RelatedConcept>> filterRelations=new HashMap<String, ArrayList<RelatedConcept>>();
        ArrayList<RelatedConcept> filteredRelatedConcepts;
        
        for(String relCon:conceptRelations.keySet()){
            filteredRelatedConcepts=new ArrayList<RelatedConcept>();
            ArrayList<RelatedConcept> rcs=con.getSortedRelatedConcepts(relCon);
            if(rcs.size()>1){
                for(int i=0;i<rcs.size()*(asoLim/100);i++){
                    filteredRelatedConcepts.add(rcs.get(i));
                    if(!filteredConceptList.containsKey(relCon)){
                        filteredConceptList.put(relCon, conceptList.get(relCon));
                    }
                }
            }else if(rcs.size()==1){
                filteredRelatedConcepts.add(rcs.get(0));
                filteredConceptList.put(relCon, conceptList.get(relCon));
            }
            for(RelatedConcept rc:conceptRelations.get(relCon)){
                if(rc.getType().equals("is a")||rc.getType().equals("part of")){
                    filteredRelatedConcepts.add(rc);
                    if(!filteredConceptList.containsKey(relCon)){
                        filteredConceptList.put(relCon, conceptList.get(relCon));
                    }


                }
            }
            if(filteredRelatedConcepts.size()>0){
                filterRelations.put(relCon, filteredRelatedConcepts);  
            }
        }
        con.setRelationships(filterRelations);
        return con;
    }

    private HashMap<String, Concept> optimizeRelationships(HashMap<String, Concept> opitimizedConcept) {        
        for(String s:opitimizedConcept.keySet()){
//            for(String r:opitimizedConcept.get(s).getRelationships().keySet()){
            String[] keyArray=opitimizedConcept.get(s).getRelationships().keySet().toArray(new String[0]);
            for(int i=0;i<keyArray.length;i++){
                if(!filteredConceptList.containsKey(keyArray[i])){
                    opitimizedConcept.get(s).getRelationships().remove(keyArray[i]);
                }
            }
            
        }
        return opitimizedConcept;
    }

}

