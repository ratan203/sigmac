package adaptors;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Range;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author COMPAQ
 */
public class DocReaderAnalizer {
   
    public Object getBodyFont(Range overall,HWPFDocument doc,Range range,CharacterRun cr){
       
        ArrayList listfontsizes = new ArrayList();
        Set setfontsize = new HashSet();
        
        for(int i=0;i<overall.getEndOffset();i++){
             range = new Range(i, i+1, doc);
             cr = range.getCharacterRun(0);
             listfontsizes.add(cr.getFontSize());
             setfontsize.add(cr.getFontSize());
        }
       
        Object aArray[]=setfontsize.toArray();
        int bodyfontoccurance=0;
        Object BodyFONTsize=null;
        
        for(int i=0;i<setfontsize.size();i++){
              int occurrences = Collections.frequency(listfontsizes, aArray[i]);
                
              if(occurrences>bodyfontoccurance){
                   bodyfontoccurance=occurrences;
                   BodyFONTsize= aArray[i];
                }
         }
       
        return BodyFONTsize;
    }
    
    
    
    
}
