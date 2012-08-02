/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package optimization;

import java.util.ArrayList;
import java.util.HashMap;
import smc.Concept;
import smc.Document;
import smc.RelatedConcept;

/**
 *
 * @author Thilina
 */
public class Optimizer {
 private Document document;
 private HashMap<String,Concept> doc;
 private HashMap<String,ArrayList<RelatedConcept>> relationships;
 private Concept concept;
 private RelatedConcept relatedConcept;
 private ArrayList<String> formatting;
}
