/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adaptors;

import java.util.ArrayList;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author thilina
 */
public class PptxTrav {

    public ArrayList getDocxFormat(Element empEl) {
        ArrayList allObj = new ArrayList();
        String style = "";
        NodeList styleTE = empEl.getElementsByTagName("p:nvSpPr");
        if (styleTE != null && styleTE.getLength() > 0) {
            for (int i = 0; i < styleTE.getLength(); i++) {

                //get the w:pPr element
                Element st = (Element) styleTE.item(i);

                NodeList styleE = st.getElementsByTagName("p:nvPr");
                if (styleE != null && styleE.getLength() > 0) {
                    for (int j = 0; j < styleE.getLength(); j++) {
                        //get the w:pStyle element
                        Element se = (Element) styleE.item(i);
                        NodeList styleE1 = se.getElementsByTagName("p:ph");
                        if (styleE1 != null && styleE1.getLength() > 0) {
                            Element spe = (Element) styleE1.item(0);
                            try {
                                style = spe.getAttribute("type");
                            } catch (Exception exep) {
                                System.out.println("Empty Style");
                            }
                        }

                    }
                }
            }
        }

        NodeList formattingParaE = empEl.getElementsByTagName("a:p");
        if (formattingParaE != null && formattingParaE.getLength() > 0) {
            for (int i = 0; i < formattingParaE.getLength(); i++) {

                String boldString = "";
                String allTxtString = "";
                String[] allTxt = new String[formattingParaE.getLength()];
                ArrayList isBold = new ArrayList();
                Element fpe = (Element) formattingParaE.item(i);
                NodeList formattingE = fpe.getElementsByTagName("a:r");
                if (formattingE != null && formattingE.getLength() > 0) {
                    for (int j = 0; j < formattingE.getLength(); j++) {

                        Element fe = (Element) formattingE.item(j);
                        String arText = getTextValue(fe, "a:t");
                        allTxtString += arText;

                        NodeList boldE1 = fe.getElementsByTagName("a:rPr");
                        if (boldE1 != null && boldE1.getLength() > 0) {
                            Element spe = (Element) boldE1.item(0);
                            try {
                                String boldS = spe.getAttribute("b");
                                if (boldS.equals("1")) {
                                    boldString += arText;
                                }
                                if (!boldS.equals("1") || j == formattingE.getLength() - 1) {
                                    if (!boldString.equals("")) {
                                        isBold.add(boldString);
                                        boldString = "";
                                    }
                                }
                            } catch (Exception exep) {
                                System.out.println("Empty Bold");
                            }
                        }
                    }
                    allTxt[i] = allTxtString;
                }
                //Create a new formatObj with the value read from the xml nodes
                PptxformatObj e = new PptxformatObj(style, allTxt, isBold);
                allObj.add(e);
            }
        }



        return allObj;
    }

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
