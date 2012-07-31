/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smc;

import edu.stanford.nlp.trees.tregex.TregexPattern;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author COMPAQ
 */
public class PatternXMLReader {
    org.w3c.dom.Document doc;
    DocumentBuilder docBuilder;
    private String treg=null;
    private String tsur=null;
    private String conceptVar;
    private String pattern;
    private String type;
    NodeList concept_att;
    Element basePattern;
    NodeList modifirepattern;
    private ArrayList<String> dependentKeys=new ArrayList<String>();
    private ArrayList<String> headKeys=new ArrayList<String>();
 
    public PatternXMLReader(){
      DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = docBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(PatternXMLReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            doc = docBuilder.parse (new File("patterns.xml"));
        } catch (SAXException ex) {
            Logger.getLogger(PatternXMLReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PatternXMLReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int readNoOfPattern(){
           
            doc.getDocumentElement ().normalize ();
            NodeList listOfrel = doc.getElementsByTagName("relationship");          
            int totalrl = listOfrel.getLength();
            return totalrl;
    }
    
    public int initializeData(int relation){
    
            NodeList listOfrel = doc.getElementsByTagName("relationship");
            Node firstPersonNode = listOfrel.item(relation);
            String types=listOfrel.item(relation).getAttributes().getNamedItem("type").getTextContent(); 
            
            basePattern = (Element)firstPersonNode;
            NodeList firstNameList = basePattern.getElementsByTagName("basePattern");
            Element firstNameElement = (Element)firstNameList.item(0);
            String tpattern=firstNameElement.getTextContent();
                       
            concept_att = basePattern.getElementsByTagName("concept");
            this.type=types;
            this.pattern=tpattern;
            return concept_att.getLength();
    }
    
    public int getConceptsSets(int noOfConcpets){
            Element concept = (Element)concept_att.item(noOfConcpets);
            String concept_name=concept.getAttributes().getNamedItem("name").getTextContent(); 
            String conpet_type=concept.getAttributes().getNamedItem("ishead").getTextContent();
            if(conpet_type!=null){
                   if(conpet_type.equalsIgnoreCase("true")){
                       headKeys.add(concept_name);
                   }
                   else if(conpet_type.equalsIgnoreCase("false")){
                       dependentKeys.add(concept_name);
                   }
            }
            this.conceptVar=concept_name;
            modifirepattern = concept.getElementsByTagName("modifierPattern");
           
        
    return modifirepattern.getLength();
    }
    
    
    public void finalinitialization(int Modifier){
        Element finalini = (Element)modifirepattern.item(Modifier);
        String treg1=finalini.getElementsByTagName("tregex").item(0).getTextContent();
        String tsur1=finalini.getElementsByTagName("tsurgeon").item(0).getTextContent(); 
        this.treg=treg1;
        this.tsur=tsur1;
        
        
    }
    
  
    public String getType() {
        return type;
    }
    
    
    public String getPattern() {
        return pattern;
    }
    
    public String getConceptVar() {
        return conceptVar;
    }
    
    
    public String getTreg() {
        return treg;
    }
    
    
    public String getTsur() {
        return tsur;
    }
    
    public ArrayList<String> getDepe(){
        return dependentKeys;
    }
    
    public ArrayList<String> getHead(){
        return headKeys;
    }
}
