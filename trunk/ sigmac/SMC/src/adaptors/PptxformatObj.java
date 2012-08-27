/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adaptors;

import java.util.ArrayList;

/**
 *
 * @author thilina
 */
public class PptxformatObj {
        String OStyle;
        String[] OallText;
        ArrayList OisBold;
    public PptxformatObj(String style, String[] text, ArrayList bold) {
        OStyle=style;
        OallText=text;
        OisBold=bold;
    }
    public PptxformatObj() {
        OStyle="";
        OallText=null;
        OisBold=null;
    }
    public String getStyle(){
        return OStyle;
    }

    public ArrayList getBold(){
        return OisBold;
    }

    public String[] getText(){
        return OallText;
    }
    
}
