/*
 * Copyright 2012 Thilina Chathuranga
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

    private HashMap<String, Concept> doc;
    private HashMap<String, ArrayList<RelatedConcept>> relationships1;
    private Concept concept;
    private JwnlOperations wn = new JwnlOperations();
    public static final float FREEQUENCY_CONTRIBUTION_CONST=1;
    public static final float TITLE_CONTRIBUTION_CONST=1;

    /**
     * Method to filter concepts by morphological root and set strength value for each concepts
     * @param docc (Document)
     * @return Document (Optimized document)
     * @throws FileNotFoundException
     * @throws JWNLException 
     */
    private Document optimizeDocument(Document docc) throws FileNotFoundException, JWNLException {
        float conceptStrength;
        String morphRoot;
        doc = docc.getDoc();
        HashMap<String, Concept> doc1 = new HashMap<String, Concept>();
        for (String con : doc.keySet()) {
            morphRoot = wn.getMorphologicalRoot(con);
            //Modify existing entry if there is a concept with the morphological root of concept
            if (doc1.containsKey(morphRoot)) {
                concept = doc1.get(morphRoot);
                concept.setFreequency(concept.getFreequency() + doc.get(con).getFreequency());
                concept.setName(morphRoot);
                relationships1 = new HashMap<String, ArrayList<RelatedConcept>>();

                //Join relationship HashMaps 
                relationshipJoin(concept.getRelationships());
                relationshipJoin(doc.get(con).getRelationships());

                concept.setRelationships(relationships1);
                //Set strength value for concept
                if (!docc.getTitleInfo().isEmpty()) {
                    conceptStrength = setConceptStrength((float) concept.getFreequency(), (float) docc.getSize(), (float) concept.getTitleStrength(), (float) docc.getTitleInfo().size());
                } else {
                    conceptStrength = setConceptStrtength((float) concept.getFreequency(), (float) docc.getSize());
                }
                concept.setStrength(conceptStrength);
                doc1.remove(morphRoot);
                doc1.put(morphRoot, concept);
            } else {
                doc.get(con).setName(morphRoot);
                relationships1 = new HashMap<String, ArrayList<RelatedConcept>>();
                relationshipJoin(doc.get(con).getRelationships());
                doc.get(con).setRelationships(relationships1);
                
                //Set strength value
                if (!docc.getTitleInfo().isEmpty()) {
                    doc.get(con).setStrength(setConceptStrength((float) doc.get(con).getFreequency(), (float) docc.getSize(), (float) doc.get(con).getTitleStrength(), (float) docc.getTitleInfo().size()));
                } else {
                    doc.get(con).setStrength(setConceptStrtength((float) doc.get(con).getFreequency(), (float) docc.getSize()));
                }
                //Add new entry for concept HashMap
                doc1.put(morphRoot, doc.get(con));
            }
        }
        docc.setDoc(doc1);
        return docc;
    }

    /**
     * Method to optimize relationship HashMap of the concept by getting Morphological root 
     * Here RelatedConcept ArrayList joined
     * @param relationshipsCommon (HashMap<String, ArrayList<RelatedConcept>>)
     * @throws FileNotFoundException
     * @throws JWNLException 
     */
    private void relationshipJoin(HashMap<String, ArrayList<RelatedConcept>> relationshipsCommon) throws FileNotFoundException, JWNLException {
        ArrayList<RelatedConcept> relListCommon = new ArrayList<RelatedConcept>();
        ArrayList<RelatedConcept> relList;
        String morphRootRel;
        for (String rel : relationshipsCommon.keySet()) {
            morphRootRel = wn.getMorphologicalRoot(rel);
            if (relationships1.containsKey(morphRootRel)) {
                relList = new ArrayList<RelatedConcept>();
                relListCommon = relationships1.get(morphRootRel);
                if (relationshipsCommon.get(rel) != null) {
                    for (RelatedConcept re : relationshipsCommon.get(rel)) {
                        re.setRelatedConcept(morphRootRel);
                        relList.add(re);
                    }
                    //Join RelatedConcept ArrayLists by considering relationship type
                    for (RelatedConcept rc : relListCommon) {
                        rc.setRelatedConcept(morphRootRel);
                        for (int i = 0; i < relList.size(); i++) {
                            if (relList.get(i).getType().equals(rc.getType())) {
                                relList.get(i).setFreequency(relList.get(i).getFreequency() + rc.getFreequency());
                            } else if (!relList.contains(rc)) {
                                relList.add(rc);
                            }
                        }
                    }
                }
            } else {
                relList = relationshipsCommon.get(rel);
            }
            if (relationships1.containsKey(morphRootRel)) {
                relationships1.remove(morphRootRel);
            }
            relationships1.put(morphRootRel, relList);
        }
    }

    /**
     * Method to set strength value for each relationships
     * @param docc (Document)
     * @return Document (Optimized document)
     * @throws FileNotFoundException
     * @throws JWNLException 
     */
    private Document optimizeRelationships(Document d1) throws FileNotFoundException, JWNLException {
        HashMap<String, Concept> dd1 = d1.getDoc();
        HashMap<String, ArrayList<RelatedConcept>> rel1 = new HashMap<String, ArrayList<RelatedConcept>>();
        for (String con : dd1.keySet()) {
            rel1 = dd1.get(con).getRelationships();
            if (!rel1.isEmpty()) {
                for (String f : rel1.keySet()) {
                    if (rel1.get(f) != null) {
                        for (RelatedConcept g : rel1.get(f)) {
                            //Modify 'aso' relationships if there is 'is_a' relationship (with head and tail properties) identified between same concepts
                            if (g.getType().equals("aso")) {
                                if (wn.assertIsRel(con, g.getRelatedConcept())) {
                                    g.setType("is a");
                                    g.setIsStrength(1);
                                    g.setStrength(1);
                                    g.setHead(Boolean.FALSE);
                                } else if (wn.assertIsRel(g.getRelatedConcept(), con)) {
                                    g.setType("is a");
                                    g.setIsStrength(1);
                                    g.setStrength(1);
                                    g.setHead(Boolean.TRUE);
                                } else if (g.getFreequency() != 0 && d1.getSize() != 0) {
                                    g.setStrength(setAsoStrength(g.getFreequency()));
                                }
                            } else if (g.getType().equals("is a")) {
                                g.setStrength(1);
                                if (wn.assertIsRel(con, g.getRelatedConcept())) {
                                    g.setIsStrength(1);
                                    g.setStrength(2);
                                } else if (wn.assertIsRel(g.getRelatedConcept(), con)) {
                                    g.setIsStrength(1);
                                    g.setStrength(2);
                                }
                            }

                            //Modify 'aso' relationships if there is 'part_of' relationship (with head and tail properties) identified between same concepts
                            if (g.getType().equals("aso")) {
                                if (wn.assertPartOfRel(con, g.getRelatedConcept())) {
                                    g.setType("part of");
                                    g.setPartStrength(1);
                                    g.setStrength(1);
                                    g.setHead(Boolean.FALSE);
                                } else if (wn.assertPartOfRel(g.getRelatedConcept(), con)) {
                                    g.setType("part of");
                                    g.setPartStrength(1);
                                    g.setStrength(1);
                                    g.setHead(Boolean.TRUE);
                                }
                            } else if (g.getType().equals("part of")) {
                                g.setStrength(1);
                                if (wn.assertPartOfRel(con, g.getRelatedConcept())) {
                                    g.setPartStrength(1);
                                    g.setStrength(2);
                                } else if (wn.assertPartOfRel(g.getRelatedConcept(), con)) {
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

    /**
     * Method to set TitleStrength
     * @param d1 (Document)
     * @return Document (Modified Document)
     * @throws FileNotFoundException
     * @throws JWNLException 
     */
    private Document optimizedTitleStrengthSet(Document d1) throws FileNotFoundException, JWNLException {
        float totTitleStr;
        HashMap<String, Title> titleList = d1.getTitleInfo();
        //Modify concepts extracted from titles by getting morphological root
        if (!titleList.isEmpty()) {
            for (String a : titleList.keySet()) {
                Set<String> titlSet = new HashSet<String>();
                for (String b : titleList.get(a).getTitleSet()) {
                    titlSet.add(wn.getMorphologicalRoot(b));
                }
                titleList.get(a).setTitleSet(titlSet);
            }

            HashMap<String, Concept> dd1 = d1.getDoc();
            for (String con : dd1.keySet()) {
                totTitleStr = 0;
                for (String a : titleList.keySet()) {
                    if (titleList.get(a).getTitleSet().contains(con)) {
                        totTitleStr += setTitleStrength(titleList.get(a).getTitleStrength(),con.trim().split(" ").length , a.trim().split(" ").length);
                    }
                }
                dd1.get(con).setTitleStrength(totTitleStr);
            }
        }
        return d1;
    }

    //Optimize Document
    public Document optimizeDoc(Document doc) throws FileNotFoundException, JWNLException {
        return optimizedTitleStrengthSet(optimizeRelationships(optimizeDocument(doc)));
    }

    /**
     * Setting Strength value for concepts
     * @param frequency
     * @param docSize
     * @param titleStrength
     * @param noOfTitles
     * @return Strength Value (float)
     */
    private float setConceptStrength(float frequency, float docSize, float titleStrength, float noOfTitles) {
        float strength;
        strength = FREEQUENCY_CONTRIBUTION_CONST*(frequency / docSize) + TITLE_CONTRIBUTION_CONST*(titleStrength / noOfTitles);
        return strength;
    }

    /**
     * Setting Strength value for concepts
     * @param frequency
     * @param docSize
     * @return Strength Value (float)
     */
    private float setConceptStrtength(float frequency, float docSize) {
        float strength;
        strength = FREEQUENCY_CONTRIBUTION_CONST*(frequency / docSize);
        return strength;
    }

    /**
     * Setting Strength value for 'aso' relationships
     * @param frequency
     * @return Strength Value (float)
     */
    private float setAsoStrength(float f) {
        float strength;
        strength = (float) Math.pow(Math.E, -(1 / (double) f));
        return strength;
    }
    
    /**
     * Setting Strength value for concept by considering title existance of concept
     * @param titleScale
     * @param wordsInConcept
     * @param wordsInTitle
     * @return TitleStrength Value (float)
     */
    private float setTitleStrength(float titleScale, float wordsInConcept, float wordsInTitle) {
        float titleStrength;
        titleStrength = titleScale * (wordsInConcept / wordsInTitle);
        return titleStrength;
    }
}
