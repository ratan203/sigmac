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
    Socket requestSocket,newSock;
	ObjectOutputStream out,out1;
 	ObjectInputStream in,in1;
 	String message,message1,serverPath;
 	
 	boolean myTurn = true;
    
        public DocumentUploader(Document doc){
        ConceptRanker cr=new ConceptRanker();
        doc.resetImportance();
        cr.rankConcepts(doc);
        this.doc=doc;
    }
    
    @Override
    public void run(){
   
//        
//            try{ 
//                String docName=doc.getName().replace("\\", "");
//                newSock = new Socket("169.254.162.138",13267);
//                System.out.println("Connecting to send file...");
//                
//                
//            out1 = new ObjectOutputStream(newSock.getOutputStream());
//            out1.flush();
//            in1 = new ObjectInputStream(newSock.getInputStream());
//
//            do{
//                    message ="";
//                    if(myTurn)
//                    {
//
//                            sendMessage2(doc.getUri());
//                            myTurn = false;
//                            Thread.sleep(1000);
//                    }
//                    else
//                    {
//                            recvMessage1();
//                    }
//            }while(!message.equals("bye"));
//        
//                System.out.println(serverPath+"    Server path");
//                doc.setServerPath(serverPath);
//                File myFile = new File (doc.getUri());
//                byte [] mybytearray  = new byte [(int)myFile.length()];
//                FileInputStream fis = new FileInputStream(myFile);
//                BufferedInputStream bis = new BufferedInputStream(fis);
//                bis.read(mybytearray,0,mybytearray.length);
//                OutputStream os = newSock.getOutputStream();
//                os.write(mybytearray,0,mybytearray.length);
//                os.flush();
//                Thread.sleep(2000);
//               
//                
////                JOptionPane.showMessageDialog(null, "File "+docName+" successfuly uploaded to the SigmaC Server");
//       
//            }
//            catch(Exception e){
//                e.printStackTrace();
//                JOptionPane.showMessageDialog(null, "Unable to Upload the document to the SigmaC Server");
//            }
//            finally{
//             try{
//                        in1.close();
//                        out1.close();
//                        newSock.close();
//                }
//                catch(Exception er){
//                        // Do not want to handle
//                }
//            }
//
//        
            
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
             JOptionPane.showMessageDialog(null, "File Uploaded");
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
        void sendMessage2(String uri)
	{
            try{
                    out1.writeObject(uri);
                    out1.flush();
            }
            catch(IOException ioException){
                    ioException.printStackTrace();
            }
	}
	
	void recvMessage()
	{
            try {
                    message = (String)in.readObject();
                    if(message=="turnOver")
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
        
        void recvMessage1()
	{
            try {
                    message = (String)in1.readObject();
                    if(!message.equals("bye")){
                        serverPath=message;
                        System.out.println(message);
                    }
                    if(message=="turnOver")
                    {
                            myTurn = true;
                    }
                    
            } catch (IOException e) {
                    e.printStackTrace();
            } catch (ClassNotFoundException e) {
                    e.printStackTrace();
            }
	}
    
}
