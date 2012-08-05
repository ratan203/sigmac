/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package smc;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author Thilina
 */
public class XMLFormattingExtractor {

    private Document dom;
    private String XMLPath="";
    private Parser p=new Parser("grammar/englishPCFG.ser.gz");
    private HashMap<String,Title> titleInfo=new HashMap<String, Title>();

    private void parseXMLFile(){
            //get the factory
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

            try {

                    //Using factory get an instance of document builder
                    DocumentBuilder db = dbf.newDocumentBuilder();

                    File myFile = new File(XMLPath);
                    InputStream documentXMLIS = new FileInputStream(myFile);
                    //parse using builder to get DOM representation of the XML file
                    dom= db.parse(documentXMLIS);


            }catch(Exception exep){
                System.out.println("Can not parse XML file");
            }
    }

    private void parseDocument(){
            //get the root element
            Element docEle = dom.getDocumentElement();

            //get a nodelist of elements
            NodeList nl = docEle.getElementsByTagName("Title");
            if(nl != null && nl.getLength() > 0) {
                    for(int i = 0 ; i < nl.getLength();i++) {

                            //get the title element
                            Element el = (Element)nl.item(i);
                            int titleVal=Integer.parseInt(el.getAttribute("Scale"));
                            //get the title text and add to list
                            String titleStr=getTextValue(el);
                            if(titleStr!=null){
                                Set<String> set=p.parseTitle(titleStr);
                                Title titl=new Title(set, titleVal);
                                titleInfo.put(titleStr, titl);
                            }
                    }
            }
    }

    private String getTextValue(Element ele) {
        String finTitle = null;
//        NodeList nl = ele.getElementsByTagName(tagName);
//        if(nl != null && nl.getLength() > 0) {
//            for(int i = 0 ; i < nl.getLength();i++) {
//                Element el = (Element)nl.item(i);
                finTitle=ele.getFirstChild().getNodeValue();
//            }
//        }
        return finTitle;   
    }

    public HashMap<String,Title> getXMLFormatting(String path){
        XMLPath=path;
        parseXMLFile();
        parseDocument();
        return titleInfo;
    }

}
