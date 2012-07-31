/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package smc;

import edu.stanford.nlp.trees.tregex.TregexPattern;
import edu.stanford.nlp.trees.tregex.tsurgeon.Tsurgeon;
import edu.stanford.nlp.trees.tregex.tsurgeon.TsurgeonPattern;

/**
 *
 * @author user
 */
public class ModifierPattern {
    private TregexPattern treg;
    private TsurgeonPattern tsur;
    private String conceptVar;

    public ModifierPattern(String treg,String tsur, String conceptVar){
        this.treg=TregexPattern.compile(treg);
        this.tsur=Tsurgeon.parseOperation(tsur);
        this.conceptVar=conceptVar;
    }

    public String getConceptVar() {
        return conceptVar;
    }

    public TregexPattern getTreg() {
        return treg;
    }

    public TsurgeonPattern getTsur() {
        return tsur;
    }


}
