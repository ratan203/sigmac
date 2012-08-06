package adaptors;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.poi.hslf.HSLFSlideShow;
import org.apache.poi.hslf.extractor.PowerPointExtractor;
import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.model.TextRun;
import org.apache.poi.hslf.usermodel.RichTextRun;
import org.apache.poi.hslf.usermodel.SlideShow;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author COMPAQ
 */
public class pptextractor {
    String body=null;
    public SCDocument textextractor(HSLFSlideShow slideshow,SlideShow ppss) throws IOException{
        
        PowerPointExtractor ppte=new PowerPointExtractor(slideshow);
       // System.out.println(ppte.getText());
        Slide[] slide = ppss.getSlides();
        HashMap hm=new HashMap(); 
          for (int j = 0; j < slide.length; j++) {

        //read hyperlinks from the text runs
        TextRun[] txt = slide[j].getTextRuns();
       
        for (int k = 0; k < txt.length; k++) {
            String text = txt[k].getText();
                RichTextRun[] richTextRuns = txt[k].getRichTextRuns();
           
             for (int l = 0; l < richTextRuns.length; l++) {
                 //System.out.println( richTextRuns[l].getText()+richTextRuns[l].getFontSize());
                 
                 hm.put(richTextRuns[l].getText(), richTextRuns[l].getFontSize());
                                
             }
           
        
            }
         
        
        } 
        
         Object[] ofontsize=hm.values().toArray();
         Integer [] afontsize=new Integer[ofontsize.length];
        
         for(int k=0;k<ofontsize.length;k++){
             afontsize[k]= (Integer) ofontsize[k];
         }
         
          SetLike holder = new SetLike();
          for( Integer value : afontsize ) {
            holder.add( value );
         }
          
         int bodyfontsize=holder.getCommaonFontSize();
         ArrayList<Title> ppttitles= holder.getTittles(slide,bodyfontsize);
        // System.out.println(ppttitles);
        
         String alltext=ppte.getText();
          body=alltext;
        
         for(int k=0;k<ppttitles.size();k++){
             String kk= (String)ppttitles.get(k).getTitleText();
             System.out.println(kk);
             if(kk!=null||kk!=" "||kk!="\t"){
             body=body.replace(kk," ");
            }
         }
        // System.out.println(body);
      //   System.out.println(ppttitles);
        // System.out.println(body);
         return new SCDocument(body, ppttitles);
         
    }
    
    
    
    
}
