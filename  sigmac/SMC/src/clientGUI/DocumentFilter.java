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
    Map<String, Float> importanceMap = new HashMap<String, Float>();
    List<Concept> mapValues = new ArrayList<Concept>(input.values());
    for(Concept c:mapValues){
        importanceMap.put(c.getName(), c.getStrength());
    }
    List<Float> mapValuesImp = new ArrayList<Float>(importanceMap.values());
    List<String> mapKeysImp = new ArrayList<String>(importanceMap.keySet());
    
    HashMap<String, Concept> sortedMap = new LinkedHashMap<String, Concept>();
    conceptData[] cd=new conceptData[importanceMap.size()];
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
}


