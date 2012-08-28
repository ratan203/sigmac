/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SCAnalyzer;

import java.io.*;
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
        }
        catch(Exception e){
        //JOptionPane.showMessageDialog(null, "Unable to connect to the SigmaC Server");

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
        
        
            try{    
                Socket newSock = new Socket("169.254.162.138",13267);
                System.out.println("Connecting to send file...");
                File myFile = new File (doc.getUri());
                byte [] mybytearray  = new byte [(int)myFile.length()];
                FileInputStream fis = new FileInputStream(myFile);
                BufferedInputStream bis = new BufferedInputStream(fis);
                bis.read(mybytearray,0,mybytearray.length);
                OutputStream os = newSock.getOutputStream();
                os.write(mybytearray,0,mybytearray.length);
                os.flush();
                newSock.close();
                JOptionPane.showMessageDialog(null, "File "+doc.getName()+" successfuly uploaded to the SigmaC Server");
       
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, "Unable to connect to the SigmaC Server");
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
                    if("turnOver".equals(message))
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
