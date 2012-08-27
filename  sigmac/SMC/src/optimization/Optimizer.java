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
 private HashMap<String,ArrayList<RelatedConcept>> relationships1;
 private Concept concept;
 private JwnlOperations wn=new JwnlOperations();

private Document optimizeDocument(Document docc) throws FileNotFoundException, JWNLException{
    float conceptStrength;
    String morphRoot;
    doc=docc.getDoc();
    HashMap<String,Concept> doc1 = new HashMap<String, Concept>();
    for(String con:doc.keySet()){
        morphRoot=wn.getMorphologicalRoot(con);
        if(doc1.containsKey(morphRoot)){
            concept=doc1.get(morphRoot);
            concept.setFreequency(concept.getFreequency()+doc.get(con).getFreequency());
            concept.setName(morphRoot);
            relationships1=new HashMap<String, ArrayList<RelatedConcept>>();

            relationshipJoin(concept.getRelationships());
            relationshipJoin(doc.get(con).getRelationships());            

            concept.setRelationships(relationships1);
            if(!docc.getTitleInfo().isEmpty()){
                conceptStrength= setConceptStrength((float)concept.getFreequency(),(float)docc.getSize(),(float)concept.getTitleStrength(),(float)docc.getTitleInfo().size());
            }else{
                conceptStrength=setConceptStrtength((float)concept.getFreequency(),(float)docc.getSize());
            }
            concept.setStrength(conceptStrength);
            doc1.remove(morphRoot);
            doc1.put(morphRoot, concept);
        }else{
            doc.get(con).setName(morphRoot);
            relationships1=new HashMap<String, ArrayList<RelatedConcept>>();
            relationshipJoin(doc.get(con).getRelationships());
            doc.get(con).setRelationships(relationships1);
            if(!docc.getTitleInfo().isEmpty()){
                doc.get(con).setStrength(setConceptStrength((float)doc.get(con).getFreequency(),(float)docc.getSize(),(float)doc.get(con).getTitleStrength(),(float)docc.getTitleInfo().size()));
            }else{
                doc.get(con).setStrength(setConceptStrtength((float)doc.get(con).getFreequency(),(float)docc.getSize()));
            }
            doc1.put(morphRoot,doc.get(con));
        }
    }
    docc.setDoc(doc1);
    return docc;
}

private void relationshipJoin(HashMap<String,ArrayList<RelatedConcept>> relationshipsCommon) throws FileNotFoundException, JWNLException{
    ArrayList<RelatedConcept> relListCommon=new ArrayList<RelatedConcept>();
    ArrayList<RelatedConcept> relList;
    String morphRootRel;
    for(String rel:relationshipsCommon.keySet()){
        morphRootRel=wn.getMorphologicalRoot(rel);
        if(relationships1.containsKey(morphRootRel)){
            relList = new ArrayList<RelatedConcept>();
            relListCommon=relationships1.get(morphRootRel);
            if(relationshipsCommon.get(rel)!=null){
                for(RelatedConcept re:relationshipsCommon.get(rel)){
                    re.setRelatedConcept(morphRootRel);
                    relList.add(re);
                }
                for(RelatedConcept rc:relListCommon){
                    rc.setRelatedConcept(morphRootRel);
                    for(int i=0;i<relList.size();i++){
                        if(relList.get(i).getType().equals(rc.getType())){
                            relList.get(i).setFreequency(relList.get(i).getFreequency()+rc.getFreequency());
                        }else if(!relList.contains(rc)){
                            relList.add(rc);
                        }
                    }                    
                }
            }
        }else{
            relList=relationshipsCommon.get(rel);
        }
        if(relationships1.containsKey(morphRootRel)){
            relationships1.remove(morphRootRel);
        }
        relationships1.put(morphRootRel, relList);
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
                                g.setType("is a");
                                g.setIsStrength(1);
                                g.setStrength(1);
                                g.setHead(Boolean.FALSE);
                            }else if(wn.assertIsRel(g.getRelatedConcept(), con)){
                                g.setType("is a");
                                g.setIsStrength(1);
                                g.setStrength(1);
                                g.setHead(Boolean.TRUE);
                            }else if(g.getFreequency()!=0&&d1.getSize()!=0){
                                g.setStrength(setAsoStrength(g.getFreequency()));
                            }
                        }else if(g.getType().equals("is a")){
                            g.setStrength(1);
                            if(wn.assertIsRel(con, g.getRelatedConcept())){
                                g.setIsStrength(1);
                                g.setStrength(2);
                            }else if(wn.assertIsRel(g.getRelatedConcept(), con)){
                                g.setIsStrength(1);
                                g.setStrength(2);
                            }
                        }

                        if(g.getType().equals("aso")){
                        if(wn.assertPartOfRel(con, g.getRelatedConcept())){
                                g.setType("part of");
                                g.setPartStrength(1);
                                g.setStrength(1);
                                g.setHead(Boolean.FALSE);
                            }else if(wn.assertPartOfRel(g.getRelatedConcept(), con)){
                                g.setType("part of");
                                g.setPartStrength(1);
                                g.setStrength(1);
                                g.setHead(Boolean.TRUE);
                            }
                        }else if(g.getType().equals("part of")){
                            g.setStrength(1);
                            if(wn.assertPartOfRel(con, g.getRelatedConcept())){
                                g.setPartStrength(1);
                                g.setStrength(2);
                            }else if(wn.assertPartOfRel(g.getRelatedConcept(), con)){
                                g.setPartStrength(1);
                                g.setStrength(2);
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
        float totTitleStr;
        HashMap<String,Title> titleList=d1.getTitleInfo();
        if(!titleList.isEmpty()){
        for(String a:titleList.keySet()){
            Set<String> titlSet=new HashSet<String>();
            for(String b:titleList.get(a).getTitleSet()){
                titlSet.add(wn.getMorphologicalRoot(b));
            }
            titleList.get(a).setTitleSet(titlSet);
        }

        HashMap<String, Concept> dd1=d1.getDoc();
        for(String con:dd1.keySet()){
            totTitleStr=0;
            for(String a:titleList.keySet()){
                if(titleList.get(a).getTitleSet().contains(con)){
                    totTitleStr+=titleList.get(a).getTitleStrength()*(con.trim().split(" ").length/a.trim().split(" ").length);
                }
            }
            dd1.get(con).setTitleStrength(totTitleStr);
        }
        }
        return d1;
    }

    public Document optimizeDoc(Document doc) throws FileNotFoundException, JWNLException{
        return optimizeConcept(optimizeRelationships(optimizeDocument(doc)));
    }

    private float setConceptStrength(float frequency, float docSize, float titleStrength, float noOfTitles) {
        float strength;
        strength=(frequency/docSize)+(titleStrength/noOfTitles);
        return strength;
    }

    private float setConceptStrtength(float frequency, float docSize) {
        float strength;
        strength=(frequency/docSize);
        return strength;
    }

    private float setAsoStrength(float f) {
        float strength;
        strength=(float) Math.pow(Math.E, -(1/(double)f));
        return strength;
    }
}
