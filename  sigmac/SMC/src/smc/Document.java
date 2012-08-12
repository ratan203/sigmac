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
        this.setTitleInfo(this.uri);
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

    public void setTitleInfo(String uri) {
        XMLFormattingExtractor xe=new XMLFormattingExtractor();
        title=xe.getXMLFormatting(uri);
    }

    public void addConcepts(ArrayList<Concept> concepts){
//        System.out.println("sssssssssssssssssssssssssssssssssssssssssssssssssssssssssss");
        for(Concept c : concepts){
//            System.out.println(c.getName());
            if(doc.containsKey(c.getName())){
                Concept con=doc.get(c.getName());
                doc.remove(c.getName());
                con.modifyFreequency(c.getStrength());
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

    public int getTotalDocumentStrength(){
        int val=0;
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
}
