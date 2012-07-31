/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package smc;

import edu.stanford.nlp.process.DocumentPreprocessor.DocType;

/**
 *
 * @author user
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Parser p=new Parser("grammar/englishPCFG.ser.gz");
        p.parse("test.xml", DocType.XML, "Body");
    }

}
