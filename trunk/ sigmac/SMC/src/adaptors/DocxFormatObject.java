/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adaptors;

/**
 *
 * @author Thilina
 */
public class DocxFormatObject {
    String OStyle;
        String[] OallText;
        String[] OhasSpace;
        String[] OisBold;
    public DocxFormatObject(String style, String[] text, String[] space, String[] bold) {
        OStyle=style;
        OallText=text;
        OhasSpace=space;
        OisBold=bold;
    }
    public DocxFormatObject() {
        OStyle="";
        OallText=null;
        OhasSpace=null;
        OisBold=null;
    }
    public String getStyle(){
        return OStyle;
    }

    public String[] getSpace(){
        return OhasSpace;
    }

    public String[] getBold(){
        return OisBold;
    }

    public String[] getText(){
        return OallText;
    }
    
}
