/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package smc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author user
 */
public class Document implements Serializable {

    private HashMap<String,Concept> doc;
    private HashMap<String,Title> title=new HashMap<String, Title>();
    private String uri;
    private String name="";
    private int size=0;

    public Document(){
        doc=new HashMap<String, Concept>();
    }

    public Document(String uri){
        doc=new HashMap<String, Concept>();
        this.uri=uri;
        createName();
    }

    public HashMap<String, Concept> getDoc() {
        return doc;
    }

    public void setDoc(HashMap<String, Concept> doc) {
        this.doc = doc;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public HashMap<String,Title> getTitleInfo() {
        return title;
    }

    public void setTitleInfo(HashMap<String, Title> titles) {
        this.title=titles;
    }

    public void addConcepts(ArrayList<Concept> concepts){
//        System.out.println("sssssssssssssssssssssssssssssssssssssssssssssssssssssssssss");
        for(Concept c : concepts){
//            System.out.println(c.getName());
            if(doc.containsKey(c.getName())){
                Concept con=doc.get(c.getName());
                doc.remove(c.getName());
                if(con.getFreequency()<=0){
                    con.modifyFreequency(1);
                }else{
                    con.modifyFreequency(c.getFreequency());
                }
                con.addRelatedConcepts(c.getRelationships());
                doc.put(c.getName(), con);
            }else{
                doc.put(c.getName(), c);
            }
        }
    }

    public void printDoc(){
        Set<String> keys=doc.keySet();
        for(String key : keys){
            System.out.println("------------------------Concept-----------------------");
            Concept con=doc.get(key);
            con.printConcept();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size){
        this.size=size;
    }

    public float getTotalDocumentValue(){
        float val=0;
        Set<String> keys=doc.keySet();
        for(String key : keys){
            val+=doc.get(key).getValue();
        }
        return val;
    }

    private void createName(){
        int index1=this.uri.lastIndexOf("/");
        if(index1!=-1){
            this.name=this.uri.substring(index1);
        }else{
            this.name=this.uri;
        }
    }

    public void deleteConcept(String concept){
        Concept con=this.doc.get(concept);
        if(con==null){
            return;
        }
        this.doc.remove(concept);
        HashMap<String, ArrayList<RelatedConcept>> relationships = con.getRelationships();
        Set<String> keySet = relationships.keySet();
        for(String key : keySet){
            Concept rel=doc.get(key);
            if(rel!=null){
                boolean isolated = rel.deleteRelationshipsToConcept(concept);
                if(isolated){
                    deleteConcept(key);
                }
            }
        }
    }

    public void deleteRelationship(String c1,String c2){
        Concept con1=this.doc.get(c1);
        Concept con2=this.doc.get(c2);
        boolean isolated1=con1.deleteRelationshipsToConcept(c2);
        boolean isolated2=con2.deleteRelationshipsToConcept(c1);
        if(isolated1){
            deleteConcept(c1);
        }
        if(isolated2){
            deleteConcept(c2);
        }
    }

    public ArrayList<Concept> getSortedConceptList(){
        ArrayList<Concept> concepts=getConcepList();
        Collections.sort(concepts);
        return concepts;
    }

    private ArrayList<Concept> getConcepList(){
        ArrayList<Concept> concepts=new ArrayList<Concept>(this.doc.size());
        Set<String> keySet = this.doc.keySet();
        int index=0;
        for(String key : keySet){
            concepts.add(this.doc.get(key));
        }
        return concepts;
    }

    private void quickSort(){
        
    }

    public void resetImportance(){ // this one for testing
        Set<String> keySet = this.doc.keySet();
        for(String key : keySet){
            this.doc.get(key).setImportance(0);
        }
    }

    public HashMap<String,Concept> getNewDoc(ArrayList<Concept> concepts, int end){
        HashMap<String,Concept> map=new HashMap<String, Concept>();
        for(int i=0;i<end;i++){
            Concept concept=concepts.get(i);
            map.put(concept.getName(), concept);
        }
        Set<String> keySet = map.keySet();
        for(String key : keySet){
            Concept concept=map.get(key);
            HashMap<String, ArrayList<RelatedConcept>> relationships = concept.getRelationships();
            Set<String> relKeySet = relationships.keySet();
            for(String relKey : relKeySet){
                if(!map.containsKey(relKey) && doc.containsKey(relKey)){
                    Concept rel=doc.get(relKey);
                    map.put(rel.getName(), rel);
                }
            }
        }
        reArrangeMap(map);
        return map;
    }

    public HashMap<String,Concept> getNewDoc(ArrayList<Concept> concepts, float coverage){
        float covered=0.0f;
        int end=0;
        for(Concept c : concepts){
            if(covered >= coverage){
                break;
            }else{
                covered+=c.getImportance();
            }
            end++;
        }
        return getNewDoc(concepts, end);
    }

    private void reArrangeMap(HashMap<String,Concept> map){
        Set<String> keySet = map.keySet();
        for(String key : keySet){
            Concept con=map.get(key);
            HashMap<String, ArrayList<RelatedConcept>> relationships = con.getRelationships();
            Set<String> relKeySet = relationships.keySet();
            for(String relKey : relKeySet){
                if(!map.containsKey(relKey)){
                    con.deleteRelationshipsToConcept(relKey);
                }
            }
        }
    }
}
