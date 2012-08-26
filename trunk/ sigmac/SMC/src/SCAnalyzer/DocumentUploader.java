/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SCAnalyzer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;
import smc.ConceptRanker;
import smc.Document;

/**
 *
 * @author COMPAQ
 */
public class DocumentUploader extends Thread{
    Document doc=null;
    Socket requestSocket;
	ObjectOutputStream out;
 	ObjectInputStream in;
 	String message;
 	
 	boolean myTurn = true;
    
        public DocumentUploader(Document doc){
        ConceptRanker cr=new ConceptRanker();
        cr.rankConcepts(doc);
        this.doc=doc;
    }
    
    @Override
    public void run(){
   
        try{
                requestSocket = new Socket("169.254.162.138", 2002);
                out = new ObjectOutputStream(requestSocket.getOutputStream());
                out.flush();
                in = new ObjectInputStream(requestSocket.getInputStream());

                do{
                        message ="";
                        if(myTurn)
                        {

                                sendMessage(doc);
                                myTurn = false;
                                Thread.sleep(1000);
                        }
                        else
                        {
                                recvMessage();
                        }
                }while(!message.equals("bye"));
                JOptionPane.showMessageDialog(null, "File "+doc.getName()+" successfuly uploaded to the SigmaC Server");
        }
        catch(Exception e){
        JOptionPane.showMessageDialog(null, "Unable to connect to the SigmaC Server");

        }
        finally{
                try{
                        in.close();
                        out.close();
                        requestSocket.close();
                }
                catch(Exception er){
                        // Do not want to handle
                }
        }
	}
	void sendMessage(Document doc)
	{
            try{
                    out.writeObject(doc);
                    out.flush();
            }
            catch(IOException ioException){
                    ioException.printStackTrace();
            }
	}
	
	void recvMessage()
	{
            try {
                    message = (String)in.readObject();
                    if(message == "turnOver")
                    {
                            myTurn = true;
                    }
                    System.out.println(message);
            } catch (IOException e) {
                    e.printStackTrace();
            } catch (ClassNotFoundException e) {
                    e.printStackTrace();
            }
	}
    
}
