/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package smc;

import com.sun.media.sound.HeadspaceMixerProvider;
import edu.stanford.nlp.trees.LabeledScoredTreeNode;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.tregex.TregexMatcher;
import edu.stanford.nlp.trees.tregex.tsurgeon.Tsurgeon;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author user
 */
public class SentenceAnalyzerr {

    private ArrayList<Pattern> patterns;

    public SentenceAnalyzerr(){
        loadPatterns();
    }

    public ArrayList<Concept> analyze(Tree parse){
        ArrayList<Concept> concepts=new ArrayList<Concept>();
        for(Pattern p : patterns){
            ArrayList<Concept> pc=new ArrayList<Concept>();
            //System.out.println(p.getType());
            Tree tree=parse.deepCopy();
            TregexMatcher matcher=p.getTregexPattern().matcher(tree);
            if(p.getType().equals("none")){
                //System.out.println("Test");
                pc=analyzeConceptPattern(p, matcher);
            }else{
                pc=analyzeRelationshipPattern(p, matcher);
            }
            concepts.addAll(pc);
//            Collection<Tree> result = Tsurgeon.processPatternOnTrees(p.getTregexPattern(),
//                    Tsurgeon.collectOperations(p.getTsurgeonScript()),
//                    tree);
//            processResults(result);
        }
//        for(Concept cc : concepts){
//            System.out.println(cc.getName());
//        }
        generateAssociations(concepts);
        return concepts;
    }

    private void generateAssociations(ArrayList<Concept> concepts){
        for(Concept pick : concepts){
            for(Concept tmp : concepts){
                if(!pick.getName().equals(tmp.getName())){
                    pick.addRelatedConcept(tmp.getName(), "aso", true, 1);
                }
            }
            pick.printConcept();
        }
        System.out.println("ddddddddddddddddddddddddddd");
    }

    private void loadPatterns(){


        //manual for now
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
        }
      

        //manual for now
//        patterns=new ArrayList<Pattern>();
//        HashMap<String,ArrayList<ModifierPattern>> map=new HashMap<String,
//                ArrayList<ModifierPattern>>();
//        ArrayList<ModifierPattern> lst=new ArrayList<ModifierPattern>();
//        ModifierPattern ptn1=new ModifierPattern("NP : DT=tmp", "delete tmp", "c2");
//        lst.add(ptn1);
//        map.put("c2", lst);
//        Pattern p=new Pattern("is a",
//                "S: (NP=c1 $+ (VP=var1 <, (VBZ <: is & $+ (NP=c2 <, (DT=tmp <:"
//                + " a | <: an) & !<< NP & > =var1))))",
//                map, new String[]{"c2"}, new String[]{"c1"});
//        patterns.add(p);
//        HashMap<String,ArrayList<ModifierPattern>> map1=new HashMap<String,
//                ArrayList<ModifierPattern>>();
//        ArrayList<ModifierPattern> lst1=new ArrayList<ModifierPattern>();
//        ModifierPattern ptn11=new ModifierPattern("NP <, DT=tmp | <, CD=tmp | <, JJR=tmp | <, PRP=tmp", "delete tmp", "c1");
//        ModifierPattern ptn112=new ModifierPattern("NP < (ADJP=tmp !<- (VBN $- RB))", "delete tmp", "c1");
//        //ModifierPattern ptn113=new ModifierPattern("NP <, JJR=tmp", "delete tmp", "c1");
//        lst1.add(ptn11);
//        lst1.add(ptn112);
//        //lst1.add(ptn113);
//        map1.put("c1", lst1);
//        Pattern p1=new Pattern("none",
//                "NP=c1  !<:JJS & !<:CD & !<:RB & !<< NP",
//                map1, new String[]{"c1"}, new String[]{"c1"});
//        patterns.add(p1);
    }

    private void processResults(Collection<Tree> result){
        for(Tree t :result){
            System.out.println("*****************************");
            t.pennPrint();
        }
//        Iterator<Tree> itr=result.iterator();
//        Tree next;
//        while(itr.hasNext()){
//         next=itr.next();
//            next.pennPrint();
//        }
    }

    private ArrayList<Concept> analyzeRelationshipPattern(Pattern p,TregexMatcher matcher){
        ArrayList<Concept> concepts=new ArrayList<Concept>();
            while(matcher.find()){
//                Tree match=matcher.getMatch();
                ArrayList<Concept> heads=new ArrayList<Concept>();
                ArrayList<Concept> dependents=new ArrayList<Concept>();
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
                    Concept hc=new Concept(hConcept);
                    heads.add(hc);
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
                    Concept dc=new Concept(dConcept);
                    dependents.add(dc);
                    //System.out.println(dConcept);
                }
                for(Concept h : heads){
                    for(Concept d : dependents){
                        h.addRelatedConcept(d.getName(), p.getType(), false, 1);
                    }
                }
                for(Concept d : dependents){
                    for(Concept h : heads){
                        d.addRelatedConcept(h.getName(), p.getType(), true, 1);
                    }
                }
                concepts.addAll(heads);
                concepts.addAll(dependents);
            }

            return concepts;
    }

    private ArrayList<Concept> analyzeConceptPattern(Pattern p,TregexMatcher matcher){
        ArrayList<Concept> concepts=new ArrayList<Concept>();
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
                concepts.add(new Concept(concept));
            }            
        }
        return concepts;
    }
}
