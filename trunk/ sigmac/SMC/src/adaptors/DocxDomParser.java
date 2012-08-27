/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adaptors;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

/**
 *
 * @author thilina
 */
public class DocxDomParser {

    public Document dom;
    ArrayList l1 = new ArrayList();
    public String docxPath;

    public void setXmlPath(String path) {
        docxPath = path;
        parseXmlFile();
        traverseDocument();
    }

    /**
     * Method for parsing XML file
     */
    public void parseXmlFile() {
        //get the factory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {

            //Using factory get an instance of document builder
            DocumentBuilder db = dbf.newDocumentBuilder();

            //Traverse .docx file content
            File myFile = new File(docxPath);
            ZipFile docxFile = new ZipFile(myFile);
            ZipEntry documentXML = docxFile.getEntry("word/document.xml");
            InputStream documentXMLIS = docxFile.getInputStream(documentXML);
            //parse using builder to get DOM representation of the XML file
            dom = db.parse(documentXMLIS);


        } catch (Exception exep) {
            System.out.println("Can not parse "+docxPath+"word/document.xml file");
        }
    }

    /**
     * Method to traverse document.xml
     */
    public void traverseDocument() {
        //get the root element
        Element docEle = dom.getDocumentElement();

        //get a nodelist of elements
        NodeList nl = docEle.getElementsByTagName("w:p");
        if (nl != null && nl.getLength() > 0) {
            for (int i = 0; i < nl.getLength(); i++) {

                //get the w:p element
                Element el = (Element) nl.item(i);

                //get the formatObj object
                DocxTrav e = new DocxTrav();
                DocxFormatObj fo = new DocxFormatObj();
                fo = e.getDocxFormat(el);

                //add it to list
                l1.add(fo);
            }
        }
    }

    public ArrayList getData() {
        return l1;
    }
}
