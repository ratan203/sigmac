package Test;

import java.io.*;
import java.net.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import smc.Document;

public class TestClient{
	Socket requestSocket;
	ObjectOutputStream out;
 	ObjectInputStream in;
 	String message;
 	
 	boolean myTurn = true;
 	final Logger logger = LoggerFactory.getLogger(TestClient.class);
 	
	TestClient(){}
	
	void run()
	{
		try{
			requestSocket = new Socket("localhost", 2004);
			logger.info("Connected to localhost in port 2004");
			
			out = new ObjectOutputStream(requestSocket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(requestSocket.getInputStream());
			
			do{
				message ="";
				if(myTurn)
				{
					
					DocObjectCreator dococ = new DocObjectCreator();
					Document sob = dococ.getDoc("InterXML//1344510832982.xml");
					sendMessage(sob);
					myTurn = false;
					Thread.sleep(1000);
				}
				else
				{
					recvMessage();
				}
			}while(!message.equals("bye"));
		}
		catch(UnknownHostException unknownHost){
			logger.info("You are trying to connect to an unknown host!");
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try{
				in.close();
				out.close();
				requestSocket.close();
			}
			catch(IOException ioException){
				ioException.printStackTrace();
			}
		}
	}
	void sendMessage(String msg)
	{
		try{
			out.writeObject(msg);
			out.flush();
			logger.info("I said " + msg);
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
	
	void sendMessage(Document sob){
		try{
			out.writeObject(sob);
			out.flush();
			logger.info("Sending Document" + sob.getName() );
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
	public static void main(String args[])
	{
		TestClient client = new TestClient();
		client.run();
	}
}
