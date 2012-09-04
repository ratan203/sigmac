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

package smc;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Thilina
 */
public class Title implements Serializable{
    Set<String> m1=new HashSet();
    int titleStrength;

    public Title(Set<String> m11,int titleStrength1){
        this.m1 = m11;
        this.titleStrength = titleStrength1;
    }
    public int getTitleStrength() {
        return titleStrength;
    }

    public void setTitleStrength(int val) {
        this.titleStrength = val;
    }

    public Set<String> getTitleSet() {
        return m1;
    }

    public void setTitleSet(Set<String> val) {
        this.m1 = val;
    }
}
