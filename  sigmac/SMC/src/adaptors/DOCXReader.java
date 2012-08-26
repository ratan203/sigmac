/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adaptors;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author thilina
 */
public class DOCXReader {

    /**
     * @param args the command line arguments
     */
    public SCDocument getDocument(String path) {
        // TODO code application logic here
        DocxDomParser dp=new DocxDomParser();
        dp.setXmlPath(path);
        ArrayList ll=dp.getData();
        Iterator it = ll.iterator();
        ArrayList title1=new ArrayList();
        ArrayList title2=new ArrayList();
        ArrayList title3=new ArrayList();
        ArrayList title4=new ArrayList();
        ArrayList title5=new ArrayList();

        ArrayList norText=new ArrayList();
        while(it.hasNext()) {
                DocxformatObj fo=new DocxformatObj();
                fo=(DocxformatObj) it.next();
                String styl=fo.getStyle();
                String[] txt=fo.getText();
                String[] space=fo.getSpace();
                String[] bold=fo.getBold();

                String wpText="";
                String wpTitle="";
                String boldText="";
                if(txt.length>0 && (styl.equals("Heading1")||styl.equals("Heading2")||styl.equals("Heading3")||styl.equals("Heading4")||styl.equals("Heading5"))){
                    for(int i=0;i<txt.length;i++){
                        if(txt[i]!=null){
                            wpTitle+=txt[i];
                        }
                    }
                    if(!wpTitle.equals("")&& styl.equals("Heading1")){
                        title1.add(wpTitle);
                    }else if(!wpTitle.equals("")&& styl.equals("Heading2")){
                        title2.add(wpTitle);
                    }else if(!wpTitle.equals("")&& styl.equals("Heading3")){
                        title3.add(wpTitle);
                    }else if(!wpTitle.equals("")&& styl.equals("Heading4")){
                        title4.add(wpTitle);
                    }
                }
                else if(!styl.equals("TOC1") && !styl.equals("TOC2") && !styl.equals("TOC3") && !styl.equals("TOC4") && !styl.equals("TOC5") && !styl.equals("TOCHeading") && !styl.equals("TableofFigures") && !styl.equals("Caption")){
                    for(int i=0;i<txt.length;i++){
                        if(txt[i]!=null){
                            wpText+=txt[i];
                        }
                        if(bold[i]!=null && bold[i].equals("Bold")){
                            boldText+=txt[i];                                                     
                        }
                        if(bold[i]==null || i==txt.length-1){
                            if(!boldText.equals("")){
                                title5.add(boldText);
                                boldText="";
                            }
                        }
                    }
                    if(!wpText.equals("")){
                        norText.add(wpText);
                    }

                    
                    
                }

        }

        Iterator titleit1 = title1.iterator();
        Iterator titleit2 = title2.iterator();
        Iterator titleit3 = title3.iterator();
        Iterator titleit4 = title4.iterator();
        Iterator titleit5 = title5.iterator();
        Iterator textit = norText.iterator();
        ArrayList titles=new ArrayList();
        while(titleit1.hasNext()) {
            Title t1=new Title((String) titleit1.next(), 5);
            titles.add(t1);
        }
        while(titleit2.hasNext()) {
            Title t2=new Title((String) titleit2.next(), 4);
            titles.add(t2);
        }
        while(titleit3.hasNext()) {
            Title t3=new Title((String) titleit3.next(), 3);
            titles.add(t3);
        }
        while(titleit4.hasNext()) {
            Title t4=new Title((String) titleit4.next(), 2);
            titles.add(t4);
        }
        while(titleit5.hasNext()) {
            Title t5=new Title((String) titleit5.next(), 1);
            titles.add(t5);
        }
        String bodyText="";
        while(textit.hasNext()){
            bodyText=bodyText+". \n"+textit.next();
        }
        System.out.println(bodyText);

        SCDocument scd=new SCDocument(bodyText, titles);
        return scd;

    }

}
