/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package optimization;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import net.didion.jwnl.JWNLException;
import smc.Concept;
import smc.Document;
import smc.RelatedConcept;
import smc.Title;

/**
 *
 * @author Thilina
 */
public class Optimizer {
 private HashMap<String,Concept> doc;
 private HashMap<String,ArrayList<RelatedConcept>> relationships1=new HashMap<String, ArrayList<RelatedConcept>>();
 private Concept concept;
 private JwnlOperations wn=new JwnlOperations();

private Document optimizeDocument(Document docc) throws FileNotFoundException, JWNLException{
    doc=docc.getDoc();
    HashMap<String,Concept> doc1 = new HashMap<String, Concept>();
    for(String con:doc.keySet()){
        String morphRoot;
        morphRoot=wn.getMorphologicalRoot(con);
        if(doc1.containsKey(morphRoot)){
            concept=doc1.get(morphRoot);
            concept.setFreequency(concept.getFreequency()+doc.get(con).getFreequency());
            concept.setName(morphRoot);

            HashMap<String,ArrayList<RelatedConcept>> relationshipsold=new HashMap<String, ArrayList<RelatedConcept>>();
            HashMap<String,ArrayList<RelatedConcept>> relationshipsnew=new HashMap<String, ArrayList<RelatedConcept>>();
            relationshipsold=concept.getRelationships();
            relationshipsnew=doc.get(con).getRelationships();
            relationshipJoin(relationshipsold);
            relationshipJoin(relationshipsnew);

            concept.setRelationships(relationships1);
            doc1.remove(morphRoot);
            doc1.put(morphRoot, concept);
        }else{
            doc1.put(morphRoot, doc.get(con));
        }
    }
    docc.setDoc(doc1);
    return docc;
}

private void relationshipJoin(HashMap<String,ArrayList<RelatedConcept>> relationshipsCommon) throws FileNotFoundException, JWNLException{
    ArrayList<RelatedConcept> relListCommon=new ArrayList<RelatedConcept>();
    for(String rel:relationshipsCommon.keySet()){
        String morphRootRel=wn.getMorphologicalRoot(rel);
        if(relationships1.containsKey(morphRootRel)){
            ArrayList<RelatedConcept> relList = new ArrayList<RelatedConcept>();
            relListCommon=relationships1.get(morphRootRel);
            if(relationshipsCommon.get(rel)!=null){
                relList.addAll(relationshipsCommon.get(rel));
                for(RelatedConcept rc:relListCommon){
                    for(int i=0;i<relList.size();i++){
                        if(relList.get(i).getType().equals(rc.getType())){
                            relList.get(i).setFreequency(relList.get(i).getFreequency()+rc.getFreequency());
                            relList.get(i).setRelatedConcept(morphRootRel);
                        }else{
                            rc.setRelatedConcept(morphRootRel);
                            relList.add(rc);
                        }
                    }
                }
            }
            relationships1.remove(morphRootRel);
            relationships1.put(morphRootRel, relList);
        }else{
            relationships1.put(morphRootRel, relationshipsCommon.get(rel));
        }
    }
}
    private Document optimizeRelationships(Document d1) throws FileNotFoundException, JWNLException{
        HashMap<String, Concept> dd1=d1.getDoc();
        HashMap<String,ArrayList<RelatedConcept>> rel1=new HashMap<String, ArrayList<RelatedConcept>>();
        for(String con:dd1.keySet()){
            rel1=dd1.get(con).getRelationships();
            if(!rel1.isEmpty()){
                for (String f : rel1.keySet()) {
                    if(rel1.get(f)!=null){
                    for(RelatedConcept g:rel1.get(f)){
                        if(g.getType().equals("aso")){
                            if(wn.assertIsRel(con, g.getRelatedConcept())){
                                g.setType("is_a");
                                g.setIsStrength(1);
                                g.setHead(Boolean.FALSE);
                            }else if(wn.assertIsRel(g.getRelatedConcept(), con)){
                                g.setType("is_a");
                                g.setIsStrength(1);
                                g.setHead(Boolean.TRUE);
                            }
                        }else if(g.getType().equals("is_a")){
                            if(wn.assertIsRel(con, g.getRelatedConcept())){
                                g.setIsStrength(1);
                            }else if(wn.assertIsRel(g.getRelatedConcept(), con)){
                                g.setIsStrength(1);
                            }
                        }

                        if(g.getType().equals("aso")){
                        if(wn.assertPartOfRel(con, g.getRelatedConcept())){
                                g.setType("part_of");
                                g.setPartStrength(1);
                                g.setHead(Boolean.FALSE);
                            }else if(wn.assertPartOfRel(g.getRelatedConcept(), con)){
                                g.setType("part_of");
                                g.setPartStrength(1);
                                g.setHead(Boolean.TRUE);
                            }
                        }else if(g.getType().equals("part_of")){
                            if(wn.assertPartOfRel(con, g.getRelatedConcept())){
                                g.setPartStrength(1);
                            }else if(wn.assertPartOfRel(g.getRelatedConcept(), con)){
                                g.setPartStrength(1);
                            }
                        }
                    }
                    }
                }
            }
            dd1.get(con).setRelationships(rel1);
        }
        return d1;
    }

    private Document optimizeConcept(Document d1) throws FileNotFoundException, JWNLException{
        HashMap<String,Title> titleList=d1.getTitleInfo();
        for(String a:titleList.keySet()){
            Set<String> titlSet=new HashSet<String>();
            for(String b:titleList.get(a).getTitleSet()){
                titlSet.add(wn.getMorphologicalRoot(b));
            }
            titleList.get(a).setTitleSet(titlSet);
        }

        HashMap<String, Concept> dd1=d1.getDoc();
        int totTitleStr=0;
        for(String con:dd1.keySet()){
            for(String a:titleList.keySet()){
                if(titleList.get(a).getTitleSet().contains(con)){
                    totTitleStr+=titleList.get(a).getTitleStrength();
                }
            }
            dd1.get(con).setTitleStrength(totTitleStr);
        }
        return d1;
    }

    public Document optimizeDoc(Document doc) throws FileNotFoundException, JWNLException{
        return optimizeConcept(optimizeRelationships(optimizeDocument(doc)));
    }
}
