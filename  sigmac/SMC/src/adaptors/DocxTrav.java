/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adaptors;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author thilina
 */
public class DocxTrav {

    /**
     * Method to traverse through all <w:p> nodes and extract titles and body text
     * @param Element
     * @return DocxFormatObj
     */
    public DocxFormatObject getDocxFormat(Element empEl) {
        String style = "";
        NodeList styleTE = empEl.getElementsByTagName("w:pPr");
        if (styleTE != null && styleTE.getLength() > 0) {
            for (int i = 0; i < styleTE.getLength(); i++) {

                //get the w:pPr element
                Element st = (Element) styleTE.item(i);

                NodeList styleE = st.getElementsByTagName("w:pStyle");
                if (styleE != null && styleE.getLength() > 0) {
                    for (int j = 0; j < styleE.getLength(); j++) {
                        //get the w:pStyle element
                        Element se = (Element) styleE.item(i);
                        try {
                            style = se.getAttribute("w:val");
                        } catch (Exception exep) {
                            System.out.println("Empty Style");
                        }
                    }
                }
            }
        }

        //Getting body text and bold text
        NodeList formattingE = empEl.getElementsByTagName("w:r");
        String[] allTxt = new String[formattingE.getLength()];
        String[] hasSpace = new String[formattingE.getLength()];
        String[] isBold = new String[formattingE.getLength()];
        if (formattingE != null && formattingE.getLength() > 0) {
            for (int j = 0; j < formattingE.getLength(); j++) {

                Element fe = (Element) formattingE.item(j);
                allTxt[j] = getTextValue(fe, "w:t");

                try {
                    NodeList spaceE = fe.getElementsByTagName("w:t");
                    if (spaceE != null && spaceE.getLength() > 0) {
                        Element spe = (Element) spaceE.item(0);
                        hasSpace[j] = spe.getAttribute("xml:space");
                    }
                } catch (Exception exep) {
                    System.out.println("Empty Txt");
                }

                try {
                    NodeList boldE = fe.getElementsByTagName("w:rPr");
                    if (boldE != null && boldE.getLength() > 0) {
                        Element be = (Element) boldE.item(0);
                        NodeList ll1 = be.getElementsByTagName("w:b");
                        if (ll1 != null && ll1.getLength() > 0) {
                            isBold[j] = "Bold";
                        }
                    }
                } catch (Exception exep) {
                    System.out.println("Empty Bold");
                }
            }
        }

        //Create a new formatObj with the value read from the xml nodes
        DocxFormatObject e = new DocxFormatObject(style, allTxt, hasSpace, isBold);

        return e;
    }

    /**
     * Method to get node content
     * @param Element
     * @param String (Tag name)
     * @return String (node content)
     */
    private String getTextValue(Element ele, String tagName) {
        String textVal = "";
        NodeList nl = ele.getElementsByTagName(tagName);
        if (nl != null && nl.getLength() > 0) {
            Element el = (Element) nl.item(0);
            textVal = el.getFirstChild().getNodeValue();
        }

        return textVal;
    }
}
