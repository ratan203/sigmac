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
