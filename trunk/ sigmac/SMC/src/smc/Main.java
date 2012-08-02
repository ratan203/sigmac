/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package smc;

import edu.stanford.nlp.process.DocumentPreprocessor.DocType;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 *
 * @author user
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        Parser p=new Parser("grammar/englishPCFG.ser.gz");
        Document doc=p.parse("test.xml", DocType.XML, "Body");

        //for testing by TCH--Changed parse method of parser in order to return the Documrnt object
        FileOutputStream fo=new FileOutputStream(new File("C:\\Users\\Thilina\\Documents\\NetBeansProjects\\deserialize\\src\\abc.ser"));
        ObjectOutputStream oo=new ObjectOutputStream(fo);
        oo.writeObject(doc);
        oo.flush();
        fo.flush();

    }

}
