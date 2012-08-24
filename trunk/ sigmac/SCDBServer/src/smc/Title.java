/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
