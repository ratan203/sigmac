/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package optimization;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
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
 private HashMap<String,ArrayList<RelatedConcept>> relationshipsold;
 private HashMap<String,ArrayList<RelatedConcept>> relationshipsnew;
 private Concept concept;
 private ArrayList<RelatedConcept> relListOld;
 private RelatedConcept relatedConcept;
 private ArrayList<String> formatting;
 private JwnlOperations wn=new JwnlOperations();

public Document optimizeDocument(Document docc) throws FileNotFoundException, JWNLException{
    doc=docc.getDoc();
    HashMap<String,Concept> doc1 = new HashMap<String, Concept>();
    for(String con:doc.keySet()){
        String morphRoot;
        morphRoot=wn.getMorphologicalRoot(con);
        if(doc1.containsKey(morphRoot)){
            concept=doc1.get(morphRoot);
            concept.setFreequency(concept.getFreequency()+doc.get(con).getFreequency());
            concept.setName(morphRoot);

            HashMap<String,ArrayList<RelatedConcept>> relationships1=new HashMap<String, ArrayList<RelatedConcept>>();
            relationshipsold=concept.getRelationships();
            relationshipsnew=doc.get(con).getRelationships();
            for(String rel:relationshipsold.keySet()){
                String morphRootRel=wn.getMorphologicalRoot(rel);
                if(relationships1.containsKey(morphRootRel)){
                    ArrayList<RelatedConcept> relList = new ArrayList<RelatedConcept>();
                    relListOld=relationships1.get(morphRootRel);
                    relList=relationshipsold.get(rel);
                    for(RelatedConcept rc:relListOld){
                        for(RelatedConcept rc1:relList){
                            if(rc1.getType().equals(rc.getType())){
                                relatedConcept=rc1;
                                relatedConcept.setFreequency(relatedConcept.getFreequency()+rc.getFreequency());
                                relatedConcept.setRelatedConcept(morphRootRel);
                                relList.remove(rc1);
                                relList.add(relatedConcept);
                            }else{
                                relList.add(rc);
                            }
                        }
                    }
                    relationships1.remove(morphRootRel);
                    relationships1.put(morphRootRel, relList);
                }else{
                    relationships1.put(morphRootRel, relationshipsold.get(rel));
                }
            }

            for(String rel:relationshipsnew.keySet()){
                String morphRootRel=wn.getMorphologicalRoot(rel);
                if(relationships1.containsKey(morphRootRel)){
                    ArrayList<RelatedConcept> relList = new ArrayList<RelatedConcept>();
                    relListOld=relationships1.get(morphRootRel);
                    relList=relationshipsold.get(rel);
                    for(RelatedConcept rc:relListOld){
                        for(RelatedConcept rc1:relList){
                            if(rc1.getType().equals(rc.getType())){
                                relatedConcept=rc1;
                                relatedConcept.setFreequency(relatedConcept.getFreequency()+rc.getFreequency());
                                relatedConcept.setRelatedConcept(morphRootRel);
                                relList.remove(rc1);
                                relList.add(relatedConcept);
                            }else{
                                relList.add(rc);
                            }
                        }
                    }
                    relationships1.remove(morphRootRel);
                    relationships1.put(morphRootRel, relList);
                }else{
                    relationships1.put(morphRootRel, relationshipsold.get(rel));
                }
            }

            concept.setRelationships(relationships1);
            doc1.remove(morphRoot);
            doc1.put(morphRoot, concept);
        }else{
            doc1.put(morphRoot, doc.get(con));
        }
    }
    docc.setDoc(doc);
    return docc;
}

    public void assertRelationships(Document d1) throws FileNotFoundException, JWNLException{
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
    }

    public void optimizeConcept(Document d1){
        HashMap<String,Title> titleList=d1.getTitleInfo();

    }
}
