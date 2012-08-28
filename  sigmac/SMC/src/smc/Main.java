/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package smc;

import com.mysql.jdbc.Connection;
import database.DBConnector;
import database.DBManager;
import edu.stanford.nlp.process.DocumentPreprocessor.DocType;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import net.didion.jwnl.JWNLException;
import optimization.Optimizer;

/**
 *
 * @author user
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, JWNLException {
        // TODO code application logic here
        Parser p=new Parser("grammar/englishPCFG.ser.gz");        
        Document doc=p.parse("test.xml", DocType.XML, "body");
        doc.testDocForRelations();
        HashMap<String, Concept> docCopy = doc.getDocCopy();
        Document d=new Document();
        d.setDoc(docCopy);
        d.printDoc();
        System.exit(0);
        Optimizer opti=new Optimizer();
        Document doc1=opti.optimizeDoc(doc);
        
         System.out.println(doc.getSize());
        //ConceptRanker ranker=new ConceptRanker();
        
       // ranker.rankConcepts(doc1);
        doc1.printDoc();
//        doc1.resetImportance();
//        ArrayList<Concept> sortedConceptList = doc1.getSortedConceptList();
//        for(Concept c : sortedConceptList){
//            System.out.println("+++++"+c.getName()+"+++++++++");
//            c.printConcept();
//        }
        //doc1.printDoc();
        //doc1.deleteConcept("java");
        //doc1.printDoc();
        System.exit(0);

        DBConnector db1=new DBConnector();
        DBManager dbm=new DBManager();
        Connection conn=(Connection) db1.getConnection();
        //dbm.updateDB(conn, doc1);

        //for testing by TCH--Changed parse method of parser in order to return the Documrnt object
//        FileOutputStream fo=new FileOutputStream(new File("C:\\Users\\Thilina\\Documents\\NetBeansProjects\\deserialize\\src\\abc.ser"));
//        ObjectOutputStream oo=new ObjectOutputStream(fo);
//        oo.writeObject(doc);
//        oo.flush();
//        fo.flush();


    }

}
