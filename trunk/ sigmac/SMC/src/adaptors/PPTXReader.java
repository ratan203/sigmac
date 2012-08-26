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
public class PPTXReader {

    /**
     * @param args the command line arguments
     */
    public SCDocument getDocument(String path) {
        // TODO code application logic here
        PptxDomParser dp=new PptxDomParser();
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
            ArrayList fo=new ArrayList();
            fo=(ArrayList) it.next();
            Iterator it1 = fo.iterator();
            while(it1.hasNext()) {
                ArrayList fo1=new ArrayList();
                fo1=(ArrayList) it1.next();
                Iterator it2 = fo1.iterator();
                while(it2.hasNext()) {
                    ArrayList fo2=new ArrayList();
                    fo2=(ArrayList) it2.next();
                    Iterator it3 = fo2.iterator();
                    while(it3.hasNext()) {
                        PptxformatObj pptxObj=new PptxformatObj();
                        pptxObj=(PptxformatObj) it3.next();

                        String styl=pptxObj.getStyle();
                        String[] txt=pptxObj.getText();
                        ArrayList bold=pptxObj.getBold();

                        if(txt.length>0 && (styl.equals("ctrTitle")||styl.equals("title")||styl.equals("subTitle"))){
                            for(int i=0;i<txt.length;i++){
                                if(txt[i]!=null && !txt[i].equals("") && styl.equals("ctrTitle")){
                                    title1.add(txt[i]);
                                }else if(txt[i]!=null && !txt[i].equals("") && styl.equals("title")){
                                    title2.add(txt[i]);
                                }else if(txt[i]!=null && !txt[i].equals("") && styl.equals("subTitle")){
                                    title3.add(txt[i]);
                                }
                            }

                        }else if(!styl.equals("sldNum")){
                            for(int i=0;i<txt.length;i++){
                                if(txt[i]!=null && !txt[i].equals("")){
                                    norText.add(txt[i]);
                                }
                            }
                        }
                        for(int j=0;j<bold.size();j++){
                            if(bold.get(j)!=null && !bold.get(j).equals("")){
                                title5.add(bold.get(j));
                            }
                        }

                    }
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
