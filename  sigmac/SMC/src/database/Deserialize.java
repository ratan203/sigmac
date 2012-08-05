/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import smc.Document;

/**
 *
 * @author Thilina
 */
public class Deserialize {
public Document deserDocument() throws FileNotFoundException, IOException, ClassNotFoundException{
    // TODO code application logic here
        FileInputStream fi=new FileInputStream("src//database//abc.ser");
        ObjectInputStream oi=new ObjectInputStream(fi);
        Document doc=(Document) oi.readObject();
        return doc;
}
}
