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
import java.util.HashMap;
import java.util.Set;

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
        HashMap<String, Title> a = doc.getTitleInfo();
        System.out.println("ook");
        for(String x:a.keySet()){
            System.out.println(a.get(x).getTitleStrength());
            for(String z:a.get(x).getTitleSet()){
                System.out.println(z);
            }
            System.out.println("");
            System.out.println("");
        }

        //for testing by TCH--Changed parse method of parser in order to return the Documrnt object
        FileOutputStream fo=new FileOutputStream(new File("C:\\Users\\Thilina\\Documents\\NetBeansProjects\\deserialize\\src\\abc.ser"));
        ObjectOutputStream oo=new ObjectOutputStream(fo);
        oo.writeObject(doc);
        oo.flush();
        fo.flush();


    }

}
