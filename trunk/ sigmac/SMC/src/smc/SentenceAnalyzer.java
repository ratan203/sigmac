/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package smc;

import edu.stanford.nlp.trees.LabeledScoredTreeNode;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.tregex.TregexMatcher;
import edu.stanford.nlp.trees.tregex.tsurgeon.Tsurgeon;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author user
 */
public class SentenceAnalyzer {
    private ArrayList<Pattern> patterns;
    private HashMap<String,Concept> cMap;
    private ArrayList<String> matchedRelTypes;

    public SentenceAnalyzer(){
        loadPatterns();
        cMap=new HashMap<String, Concept>();
        matchedRelTypes=new ArrayList<String>();
    }

    private void loadPatterns(){
        tmpMethod1();
//        tmpMethod2();
    }

    public ArrayList<Concept> analyze(Tree parse){
        ArrayList<Concept> concepts=new ArrayList<Concept>();
        for(Pattern p : patterns){
//            System.out.println(p.getTregexPattern());
            Tree tree=parse.deepCopy();
            TregexMatcher matcher=p.getTregexPattern().matcher(tree);
            if(p.getType().equals("none")){
                analyzeConceptPatterns(p,matcher);
            }else{
//                if(!matchedRelTypes.contains(p.getType())){
                    analyzeRelationshipPatterns(p,matcher);
                    matchedRelTypes.add(p.getType());
//                }
            }
        }
        addAssociations();
        Set<String> keySet = cMap.keySet();
        for(String key : keySet){
            concepts.add(cMap.get(key));
        }
        cMap.clear();
        matchedRelTypes.clear();
        if(concepts.size()<2){
            concepts.clear();
        }
        return concepts;
    }

    private void analyzeRelationshipPatterns(Pattern p,TregexMatcher matcher){
            while(matcher.find()){
                ArrayList<String> hd=new ArrayList<String>();
                ArrayList<String> dp=new ArrayList<String>();
                for(String key : p.getHeadKeys()){
                    //System.out.print("Head : ");
                    Tree head=matcher.getNode(key);
                    ArrayList<ModifierPattern> ptns=p.getSurgeonScripts(key);
                    if(ptns!=null){
                        for(ModifierPattern mptn : ptns){
                            head=Tsurgeon.processPattern(mptn.getTreg(), mptn.getTsur(), head);
                        }
                    }
                    if(head.firstChild()==null){
                        continue;
                    }
                    List<LabeledScoredTreeNode> leaves=head.getLeaves();
                    String hConcept="";
                    for(LabeledScoredTreeNode leave : leaves){
                        hConcept+=leave.nodeString()+" ";
                    }
                    hConcept=filterConcept(hConcept);
                    if(cMap.containsKey(hConcept)){
                        //Concept c=cMap.get(hConcept);
                        //c.modifyFreequency(1);
                        System.out.println("not expected");
                    }else{
                        cMap.put(hConcept, new Concept(hConcept));
                    }
                    System.out.println(hConcept);
                    hd.add(hConcept);
                    //System.out.println(hConcept);
                }
                for(String key : p.getdependentKeys()){
                    //System.out.print("Dependent : ");
                    Tree dependent=matcher.getNode(key);
                    ArrayList<ModifierPattern> ptns=p.getSurgeonScripts(key);
                    if(ptns!=null){
//                        System.out.println("***************");
                        for(ModifierPattern mptn : ptns){
                            dependent=Tsurgeon.processPattern(mptn.getTreg(), mptn.getTsur(), dependent);
                        }
                    }
                    if(dependent.firstChild()==null){
                        continue;
                    }
                    List<LabeledScoredTreeNode> leaves=dependent.getLeaves();
                    String dConcept="";
                    for(LabeledScoredTreeNode leave : leaves){
                        dConcept+=leave.nodeString()+" ";
                    }
                    dConcept=filterConcept(dConcept);
                    if(cMap.containsKey(dConcept)){
                        //Concept c=cMap.get(dConcept);
                        //c.modifyFreequency(1);
                    }else{
                        cMap.put(dConcept, new Concept(dConcept));
                    }
                    System.out.println(dConcept);
                    dp.add(dConcept);
                    //System.out.println(dConcept);
                }
                for(String h: hd){
                    Concept head=cMap.get(h);
                    for(String d : dp){
                        head.addRelatedConcept(d, p.getType(), false, 1);
                    }
                }
                for(String d : dp){
                    Concept dependent=cMap.get(d);
                    for(String h : hd){
                        dependent.addRelatedConcept(h, p.getType(), true, 1);
                    }
                }
            }
    }

    private void analyzeConceptPatterns(Pattern p,TregexMatcher matcher){
        while(matcher.find()){
            for(String key : p.getHeadKeys()){
                Tree match=matcher.getNode(key);
                for(ModifierPattern ptn : p.getSurgeonScripts(key)){
                    match=Tsurgeon.processPattern(ptn.getTreg(), ptn.getTsur(), match);
                }
                Tree t=match.firstChild();
                if(t==null){
                    continue;
                }
                List<LabeledScoredTreeNode> leaves=match.getLeaves();
                String concept="";
                for(LabeledScoredTreeNode leave : leaves){
                    concept+=leave.nodeString()+" ";
                }
                concept=filterConcept(concept);
                if(cMap.containsKey(concept)){
                    //Concept c=cMap.get(concept);
                    //c.modifyFreequency(1);
                }else{
                    if(concept.length()>2){
                        cMap.put(concept, new Concept(concept));
                    }
                }
            }
        }
    }

    private void addAssociations(){
//        System.out.println("Adding association");
        Set<String> keySet = cMap.keySet();
        for(String key : keySet){
            for(String aso : keySet){
                if(!key.equals(aso)){
                    Concept con = cMap.get(key);
                    con.addRelatedConcept(aso, "aso", true, 1);
                }else{
//                    System.out.println("###################################"+key);
                }
            }
        }
//        System.out.println("end adding associations");
    }

    private void tmpMethod1(){
        patterns=new ArrayList<Pattern>();
        HashMap<String,ArrayList<ModifierPattern>> map=new HashMap<String,
                ArrayList<ModifierPattern>>();
        ArrayList<ModifierPattern> lst=new ArrayList<ModifierPattern>();
        ModifierPattern ptn1=new ModifierPattern("NP : DT=tmp", "delete tmp", "c2");
        lst.add(ptn1);
        map.put("c2", lst);
        Pattern p=new Pattern("is a",
                "S: (NP=c1 $+ (VP=var1 <, (VBZ <: is & $+ (NP=c2 <, (DT=tmp <:"
                + " a | <: an) & !<< NP & > =var1))))",
                map, new String[]{"c2"}, new String[]{"c1"});
        patterns.add(p);
        HashMap<String,ArrayList<ModifierPattern>> map1=new HashMap<String,
                ArrayList<ModifierPattern>>();
        ArrayList<ModifierPattern> lst1=new ArrayList<ModifierPattern>();
        ModifierPattern ptn11=new ModifierPattern("NP <, DT=tmp | <, CD=tmp | <, JJR=tmp | <, PRP=tmp", "delete tmp", "c1");
        ModifierPattern ptn112=new ModifierPattern("NP < (ADJP=tmp !<- (VBN $- RB))", "delete tmp", "c1");
        ModifierPattern ptn113=new ModifierPattern("NP <, JJR=tmp", "delete tmp", "c1");
        lst1.add(ptn11);
        lst1.add(ptn112);
        lst1.add(ptn113);
        map1.put("c1", lst1);
        Pattern p1=new Pattern("none",
                "NP=c1  !<:JJS & !<:CD & !<:RB & !<< NP",
                map1, new String[]{"c1"}, new String[]{"c1"});
        patterns.add(p1);

        HashMap<String,ArrayList<ModifierPattern>> map2=new HashMap<String,
                ArrayList<ModifierPattern>>();
        ArrayList<ModifierPattern> lst2=new ArrayList<ModifierPattern>();
        ArrayList<ModifierPattern> lst22=new ArrayList<ModifierPattern>();
        ModifierPattern ptn12=new ModifierPattern("NP <, DT=tmp","delete tmp","c1");
        ModifierPattern ptn122=new ModifierPattern("NP <, DT=tmp","delete tmp","c2");
        lst2.add(ptn12);
        lst22.add(ptn122);
        map2.put("c1", lst2);
        map2.put("c2", lst22);
        Pattern p2=new Pattern("is a", "S: (NP=c1 $+ (VP <, (VBZ <: is & $+ (NP <<, "
                + "(DT=tmp >( NP=c2 !$+ CC & !$+PP & !<< NP & >NP) & [<: a | <: an]  )))))",
                map2, new String[]{"c2"}, new String[]{"c1"});
        patterns.add(p2);
    }
    private void tmpMethod2(){
        patterns=new ArrayList<Pattern>();
        HashMap<String,ArrayList<ModifierPattern>> map=new HashMap<String,
                ArrayList<ModifierPattern>>();
        ArrayList<ModifierPattern> lst=new ArrayList<ModifierPattern>();
        PatternXMLReader pxr=new PatternXMLReader();
        int noOfRleations=pxr.readNoOfPattern();//get the number of relations

        for(int i=0;i<noOfRleations;i++){
            int noOfConcepts=pxr.initializeData(i);
            String old=pxr.getPattern();
            String newone=old.trim();
            newone=newone.substring(1,newone.length()-1);

                for(int j=0;j<noOfConcepts;j++){
                    int noOfmodfr=pxr.getConceptsSets(j);

                        for(int z=0;z<noOfmodfr;z++){
                             pxr.finalinitialization(z);
                             ModifierPattern ptn1=new ModifierPattern(pxr.getTreg(),pxr.getTsur(),pxr.getConceptVar());
            lst.add(ptn1);
            map.put(pxr.getConceptVar(), lst);
                        }

                }

            String[] dep = new String[pxr.getDepe().size()];
            dep= pxr.getDepe().toArray(dep);
            String[] head = new String[pxr.getHead().size()];
            head= pxr.getHead().toArray(head);
            Pattern p=new Pattern(pxr.getType(), newone,map, dep, head);
            patterns.add(p);
//            System.out.println(p.getTregexPattern());
        }
    }

    public Set<String> analyzeTitle(Tree title){
        //ArrayList<String> tmp=new ArrayList<String>();
        Set<String> titleConcept=new HashSet<String>();
        for(Pattern p : patterns){
            if(p.getType().equals("none")){
                TregexMatcher matcher=p.getTregexPattern().matcher(title);
                while(matcher.find()){
                    for(String key : p.getHeadKeys()){
                        Tree match=matcher.getNode(key);
                        for(ModifierPattern ptn : p.getSurgeonScripts(key)){
                            match=Tsurgeon.processPattern(ptn.getTreg(), ptn.getTsur(), match);
                        }
                        Tree t=match.firstChild();
                        if(t==null){
                            continue;
                        }
                        List<LabeledScoredTreeNode> leaves=match.getLeaves();
                        String concept="";
                        for(LabeledScoredTreeNode leave : leaves){
                            concept+=leave.nodeString()+" ";
                        }
                        titleConcept.add(concept);
                    }
                }
            }
        }
        return titleConcept;
    }

    // this method needs to be implemented to prevent ugly concepts but
    // this dose not have any effect on concepts returned by relationship patterns
    private String filterConcept(String concept){
        String cp="";
        if(concept.length()>2){
            cp=concept;
            if(concept.endsWith("\'s") || concept.endsWith("s\'")){
                cp=concept.substring(0, concept.length()-2);
            }
        }
        cp=cp.replaceAll("^[^A-Za-z]", "");
        cp=cp.replaceAll("[^A-Za-z]$", "");
        return cp;
    }
}
