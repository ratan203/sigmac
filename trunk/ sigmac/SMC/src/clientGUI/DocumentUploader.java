/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clientGUI;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import smc.Document;

/**
 *
 * @author COMPAQ
 */
public class DocumentUploader extends Thread{
    Document doc=null;
    
    public DocumentUploader(Document doc){
        this.doc=doc;
    }
    
    public void run(){
     try{  
            System.out.println("Socket initiation");
            Socket s = new Socket("192.168.1.3",2002);
            System.out.println("connected to server");
            OutputStream os = s.getOutputStream();  
            ObjectOutputStream oos = new ObjectOutputStream(os);  
            oos.writeObject(doc);  
            oos.close();  
            os.close();  
            s.close();  
        }
         catch(Exception e){
            System.out.println(e);
         }
    }
            
    
}
