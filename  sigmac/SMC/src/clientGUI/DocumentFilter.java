/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clientGUI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import smc.Concept;
import smc.Document;

/**
 *
 * @author Thilina
 */
public class DocumentFilter {
    private Document filterDoc;
    public Document filterDocument(Document doc,float percentage,float asoThresh){
        HashMap<String,Concept> conceptList;
        conceptList=doc.getDoc();
        conceptList=sortHashMap(conceptList);
        System.out.println(conceptList.size());
        for(String a:conceptList.keySet()){
            System.out.println(a+"-------------------------"+conceptList.get(a).getImportance());
        }
        System.exit(0);
        return filterDoc;
    }     
    
    private HashMap<String, Concept> sortHashMap(HashMap<String, Concept> input){
    Map<Float,String> importanceMap = new TreeMap<Float, String>();
    List<Concept> mapValues = new ArrayList<Concept>(input.values());
    for(Concept c:mapValues){
        importanceMap.put( c.getStrength(),c.getName());
    }
//    List<Float> mapValuesImp = new ArrayList<Float>(importanceMap.values());
//    List<String> mapKeysImp = new ArrayList<String>(importanceMap.keySet());
    
    HashMap<String, Concept> sortedMap = new LinkedHashMap<String, Concept>();
//    TreeSet<Float> sortedSet = new TreeSet<Float>(mapValuesImp);
//            System.out.println(sortedSet.size());
//
//    Object[] sortedArray = sortedSet.toArray();
//    int size = sortedArray.length;
//        System.out.println(size);
    for (String s:importanceMap.values()){
        sortedMap.put(s,input.get(s));
    }
    return sortedMap;
}
}
