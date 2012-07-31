package smc;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author user
 */
public class Document implements java.io.Serializable{

    private HashMap<String,Concept> doc;
    private String uri;

    public Document(){
        doc=new HashMap<String, Concept>();
    }
    
    public Document(String uri){
        doc=new HashMap<String, Concept>();
        this.uri=uri;
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

    public void addConcepts(ArrayList<Concept> concepts){
        for(Concept c : concepts){
            //System.out.println(c.getName());
            if(doc.containsKey(c.getName())){                
                Concept con=doc.get(c.getName());
                doc.remove(c.getName());
                con.modifyStrength(c.getStrength());
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

}
