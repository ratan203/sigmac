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
