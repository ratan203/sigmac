package adaptors;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.model.TextRun;
import org.apache.poi.hslf.usermodel.RichTextRun;

public class DOCExtractor {
    private Map<Integer, Integer> map = new HashMap<Integer,Integer>();

    public void add( Integer s ) {
        if( !map.containsKey( s ) ){
            map.put( s, 0 );
        }
        map.put( s, map.get( s ) + 1 );
    }

    public int getCommaonFontSize() {
            Map.Entry<Integer, Integer> maxEntry = null;

            for (Map.Entry<Integer, Integer> entry : map.entrySet())
            {
                 if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0){
                         maxEntry = entry;
                     }
            }
            return maxEntry.getKey();
        }
    
    
    public ArrayList getTittles(Slide[] slide,int bodyfontsize){
        ArrayList<Title> titles = new ArrayList();
        for (int j = 0; j < slide.length; j++) {
            TextRun[] txt = slide[j].getTextRuns();
       
               for (int k = 0; k < txt.length; k++) {
                   String text = txt[k].getText();
                   RichTextRun[] richTextRuns = txt[k].getRichTextRuns();
           
                     for (int l = 0; l < richTextRuns.length; l++) {
                         if(richTextRuns[l].getFontSize()>bodyfontsize){
                            titles.add(new Title(richTextRuns[l].getText()));
                         }
                          
                 }
            }
             
        }
        return titles;
    }
    
    
        
    

   
}