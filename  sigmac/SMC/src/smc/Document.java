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
}
