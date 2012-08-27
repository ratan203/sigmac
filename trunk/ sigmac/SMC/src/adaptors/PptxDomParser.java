/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adaptors;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
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
public class PptxDomParser {

    public Document dom;
    public String pptxPath;
    ArrayList lAll = new ArrayList();

    public void setXmlPath(String path) {
        pptxPath = path;
        parseXmlFile();
    }

    public void parseXmlFile() {
        //get the factory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {

            //Using factory get an instance of document builder
            DocumentBuilder db = dbf.newDocumentBuilder();

            File myFile = new File(pptxPath);

            ZipFile docxFile = new ZipFile(myFile);
            Enumeration zipEntries = docxFile.entries();

            while (zipEntries.hasMoreElements()) {
                String zipName = ((ZipEntry) zipEntries.nextElement()).getName();

                int mid = zipName.lastIndexOf(".");
                String fName = "";
                String fNoteName = "";
                try {
                    fName = zipName.substring(0, 16);
                    fNoteName = zipName.substring(0, 26);
                } catch (Exception exep) {
                    System.out.println("");
                }

                String ext = zipName.substring(mid + 1, zipName.length());
                if ((ext.equals("xml") && fName.equals("ppt/slides/slide")) || (ext.equals("xml") && fNoteName.equals("ppt/notesSlides/notesSlide"))) {
                    ZipEntry documentXML = docxFile.getEntry(zipName);
                    InputStream documentXMLIS = docxFile.getInputStream(documentXML);
                    dom = db.parse(documentXMLIS);
                    lAll.add(parseDocument());
                }
            }

            //parse using builder to get DOM representation of the XML file


        } catch (Exception exep) {
            System.out.println("Can not parse XML file");
        }
    }

    public ArrayList parseDocument() {
        ArrayList l1 = new ArrayList();
        //get the root element
        Element docEle = dom.getDocumentElement();
        //get a nodelist of elements
        NodeList nl = docEle.getElementsByTagName("p:sp");
        if (nl != null && nl.getLength() > 0) {
            for (int i = 0; i < nl.getLength(); i++) {

                //get the w:p element
                Element el = (Element) nl.item(i);

                //get the formatObj object
                PptxTrav e = new PptxTrav();
                ArrayList fo = new ArrayList();
                fo.add(e.getDocxFormat(el));

                //add it to list
                l1.add(fo);
            }
        }

        return l1;
    }

    public ArrayList getData() {
        return lAll;
    }
}
