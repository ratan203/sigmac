/*
 * Copyright 2012 Jayaprabath Fernando
 *
 * This file is part of SigmaC.
 *
 * SigmaC is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SigmaC is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with SigmaC.  If not, see <http://www.gnu.org/licenses/>.
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
 * @author jayaprabath
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
        //tmpMethod2();
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
            if(matcher.find()){
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
                    ArrayList<String> spawnConcepts = spawnConcepts(hConcept);
                    for(String spc: spawnConcepts){
                        if(spc.matches(".*[A-Za-z]+.*") && spc.length()>3){
                            hd.add(spc);
                            if(cMap.containsKey(hConcept)){
                                //Concept c=cMap.get(hConcept);
                                //c.modifyFreequency(1);
                                System.out.println("not expected");
                            }else{
                                cMap.put(hConcept, new Concept(hConcept));
                            }
                            System.out.println(spc);
                        }
                    }
                    //spawnConcepts.addAll(hd);
                    //hd.add(hConcept);
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
                    ArrayList<String> spawnConcepts = spawnConcepts(dConcept);
                    for(String spc : spawnConcepts){
                        if(spc.matches(".*[A-Za-z]+.*") && spc.length()>3){
                            dp.add(spc);
                            if(cMap.containsKey(dConcept)){
                                //Concept c=cMap.get(dConcept);
                                //c.modifyFreequency(1);
                            }else{
                                cMap.put(dConcept, new Concept(dConcept));
                            }
                            System.out.println(spc);
                        }
                    }
                    //dp.add(dConcept);
                    //System.out.println(dConcept);
                }
                if(!hd.isEmpty() && !dp.isEmpty()){
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
                ArrayList<String> spawnConcepts = spawnConcepts(concept);
                for(String con : spawnConcepts){
                    if(!con.matches(".*[A-Za-z]+.*") || con.length()<=3){
                        continue;
                    }
                    if(cMap.containsKey(concept)){
                        //Concept c=cMap.get(concept);
                        //c.modifyFreequency(1);
                    }else{
                        cMap.put(con, new Concept(con));
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
        //Java is a programming language .
        //Java is a general-purpose , concurrent , class-based , object-oriented language
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

        //For generic concepts
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

        //Java is a programming language originally developed by James Gosling at Sun Microsystems .
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

        //PHP is a component of WAMP and LAMP .
        //PHP is a part of WAMP and LAMP .
        //SPL is a part of PHP .
        //SPL is a component of PHP .
        //Transaction Manager is a sub system of Aura
        HashMap<String,ArrayList<ModifierPattern>> map3=new HashMap<String,
                ArrayList<ModifierPattern>>();
        ArrayList<ModifierPattern> lst3=new ArrayList<ModifierPattern>();
        ArrayList<ModifierPattern> lst32=new ArrayList<ModifierPattern>();
        ModifierPattern ptn13=new ModifierPattern("NP <, DT=tmp","delete tmp","c1");
        ModifierPattern ptn132=new ModifierPattern("NP <, DT=tmp","delete tmp","c2");
        lst3.add(ptn13);
        lst32.add(ptn132);
        map3.put("c1",lst3);
        map3.put("c2", lst32);
        Pattern p3=new Pattern("part of", "S <, (NP=c1 !<<NP & $+ (VP <, (VBZ <: is & $+"
                + " (NP <, (NP <, (DT <: a & $+ (NN <: component | <: part | $+ (NN <: "
                + "system & $- (NN<: sub))) ) & $+ (PP <, (IN <: of & $+ (NP=c2))))))))",
                map2, new String[]{"c2"}, new String[]{"c1"});
        patterns.add(p3);
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
        cp=cp.replaceAll("[^A-Za-z0-9]+$", "");
        cp=cp.replaceAll("  +", " ");
        return cp;
    }

    private ArrayList<String> spawnConcepts(String bigConcept){
        ArrayList<String> concepts=new ArrayList<String>();
        concepts.add(filterConcept(bigConcept));
        return concepts;
//        String[] split=null;
//        if(bigConcept.contains(",") && bigConcept.contains(" and ")){
//            split=processAndSeperatedConcepts(bigConcept);
//            for(int i=0;i<split.length;i++){
//               ArrayList<String> tmp=spawnConcepts(split[i]);
//               tmp.addAll(concepts);
//            }
//        }else if(bigConcept.contains(",")){
//            split=processCommaSeperatedConcept(bigConcept);
//            for(int i=0; i<split.length;i++){
//                if(split[i].length()>2){
//                    concepts.add(split[i]);
//                }
//            }
//        }else if(bigConcept.contains(" and ")){
//            split=processAndSeperatedConcepts(bigConcept);
//            for(int i=0; i<split.length;i++){
//                if(split[i].length()>2){
//                    concepts.add(split[i]);
//                }
//            }
//        }else{
//            concepts.add(bigConcept);
//        }
//        return concepts;
    }


    private String[] processCommaSeperatedConcept(String bigConcept){
        String[] split = bigConcept.split(",");
        if(split.length<2){

        }else{
            String last=split[split.length-1];
            String[] split1 = last.split(" ");
            if(split1.length<2){
                
            }else{
                String lastWord=split1[split1.length-1];
                for(int i=0;i<split.length-1;i++){
                    split[i]=filterConcept(split[i]+" "+lastWord);
                }
            }
        }
        return split;
    }

    private String[] processAndSeperatedConcepts(String concept){
        String[] split = concept.split(" and ");
        for(int i=0; i<split.length;i++){
            split[i]=filterConcept(split[i]);
        }
        return split;
    }
}
