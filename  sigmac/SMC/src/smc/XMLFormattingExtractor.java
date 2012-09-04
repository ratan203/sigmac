/*
 * Copyright 2012 Thilina Chathuranga
 *
 * This file is part of SigmaC.
 *
 * SigmaC is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SigmaC is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with SigmaC.  If not, see <http://www.gnu.org/licenses/>.
 */
package smc;

import com.sun.org.apache.xerces.internal.dom.CoreDocumentImpl;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
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
    private String XMLPath = "";
    private String docPath = "";
    private String lastModifedDate = "";
    private Parser p = new Parser("grammar/englishPCFG.ser.gz");
    private HashMap<String, Title> titleInfo = new HashMap<String, Title>();

    private void parseXMLFile() {
        //get the factory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {

            //Using factory get an instance of document builder
            DocumentBuilder db = dbf.newDocumentBuilder();

            File myFile = new File(XMLPath);
            InputStream documentXMLIS = new FileInputStream(myFile);
            //parse using builder to get DOM representation of the XML file
            dom = db.parse(documentXMLIS);

            }catch(Exception exep){
                dom=new CoreDocumentImpl();
                System.out.println("Can not parse XML file");
            }
    }

    private void parseDocument() {
        //get the root element
        Element docEle = dom.getDocumentElement();

        //get a nodelist of elements
        NodeList nl = docEle.getElementsByTagName("titles");
        if (nl != null && nl.getLength() > 0) {
            for (int i = 0; i < nl.getLength(); i++) {

                //get the title element
                Element el = (Element) nl.item(i);

                //get the title text and add to list
                setTitleInfo(el, "title");

            }
        }

        //get a nodelist of path
        NodeList nl1 = docEle.getElementsByTagName("Url");
        if (nl1 != null && nl1.getLength() > 0) {
            for (int i = 0; i < nl1.getLength(); i++) {
                //get the Url element
                Element el1 = (Element) nl1.item(i);
                docPath = el1.getFirstChild().getNodeValue();
            }
        }

        //get a nodelist of LastModified date
        NodeList nl2 = docEle.getElementsByTagName("LastModified");
        if (nl2 != null && nl2.getLength() > 0) {
            for (int i = 0; i < nl2.getLength(); i++) {
                //get the LastModified element
                Element el2 = (Element) nl2.item(i);
                lastModifedDate = el2.getFirstChild().getNodeValue();
            }
        }
    }

    private void setTitleInfo(Element ele, String tagName) {
        String finTitle = null;
        int titleVal = 0;
        NodeList nl = ele.getElementsByTagName(tagName);
        if (nl != null && nl.getLength() > 0) {
            for (int i = 0; i < nl.getLength(); i++) {
                Element el = (Element) nl.item(i);
                if (!el.getAttribute("scale").equals("") && !el.getAttribute("scale").equals("")) {
                    titleVal = Integer.parseInt(el.getAttribute("scale"));
                }
                finTitle = el.getFirstChild().getNodeValue();
                if (finTitle != null || finTitle.length() < 3) {

                    Set<String> set = p.parseTitle(finTitle);
                    Title titl = new Title(set, titleVal);
                    titleInfo.put(finTitle, titl);
                }
            }
        }
    }

    public HashMap<String, Title> getXMLFormatting(String path) {
        XMLPath = path;
        parseXMLFile();
        parseDocument();
        return titleInfo;
    }

    public String getDocPath() {
        return docPath;
    }

    public String getLastModifiedDate() {
        return lastModifedDate;
    }
}
