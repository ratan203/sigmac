/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smc;

import edu.stanford.nlp.trees.tregex.TregexPattern;
import edu.stanford.nlp.trees.tregex.tsurgeon.Tsurgeon;
import edu.stanford.nlp.trees.tregex.tsurgeon.TsurgeonPattern;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author user
 */
public class Pattern {

    private String type;
    private TregexPattern tregexPattern;
    private String[] dependentKeys;
    private String[] headKeys;
    private HashMap<String,ArrayList<ModifierPattern>> tsurScripts;

    public Pattern(String name, String trptn,
            HashMap<String,ArrayList<ModifierPattern>> tsurScripts, String[] dependenKeys,
            String[] headKeys) {
        this.type = name;
        this.dependentKeys = dependenKeys;
        this.headKeys = headKeys;
        this.tregexPattern = TregexPattern.compile(trptn);
        this.tsurScripts=tsurScripts;
    }

    public String[] getdependentKeys() {
        return dependentKeys;
    }

    public String getType() {
        return type;
    }

    public TregexPattern getTregexPattern() {
        return tregexPattern;
    }

    public String[] getHeadKeys() {
        return this.headKeys;
    }

    public ArrayList<ModifierPattern> getSurgeonScripts(String conceptVar){
        return this.tsurScripts.get(conceptVar);
    }
}
