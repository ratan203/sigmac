/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package optimization;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
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
    private ArrayList<String> l1 = new ArrayList<String>();
    private String XMLPath="";

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
            NodeList nl = docEle.getElementsByTagName("titles");
            if(nl != null && nl.getLength() > 0) {
                    for(int i = 0 ; i < nl.getLength();i++) {

                            //get the title element
                            Element el = (Element)nl.item(i);

                            //get the title text and add to list
                            getTextValue(el, "title");                            
                    }
            }
    }

    private void getTextValue(Element ele, String tagName) {
            NodeList nl = ele.getElementsByTagName(tagName);
            if(nl != null && nl.getLength() > 0) {
                for(int i = 0 ; i < nl.getLength();i++) {
                    Element el = (Element)nl.item(i);
                    l1.add(el.getFirstChild().getNodeValue());
                }
            }

    }

    public ArrayList<String> getXMLFormatting(String path){
        XMLPath=path;
        parseXMLFile();
        parseDocument();
        return l1;
    }

}
