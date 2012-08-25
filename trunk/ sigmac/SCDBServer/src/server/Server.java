package server;

import java.io.*;
import java.net.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mysql.jdbc.Connection;

import smc.Document;
public class Server{
	ServerSocket providerSocket;
	Socket connection = null;
	ObjectOutputStream out;
	ObjectInputStream in;
	Document doc;
	
	DBConnector dbc=new DBConnector();
    DBManager dbm=new DBManager();
    Connection dbConn=(Connection) dbc.getConnection();
    final Logger logger = LoggerFactory.getLogger(Server.class);
	
	Server(){}
	
	void run()
	{
		try{
			//1. creating a server socket
			providerSocket = new ServerSocket(2002, 10);
			//2. Wait for connection
			//System.out.println("Waiting for connection");
			logger.info("Waiting for connection");
			connection = providerSocket.accept();
			logger.info("Connection received from {}"+connection.getInetAddress());
			//3. get Input and Output streams
			out = new ObjectOutputStream(connection.getOutputStream());
			out.flush();
			in = new ObjectInputStream(connection.getInputStream());
			logger.info("Connection successful");
			//4. The two parts communicate via the input and output streams
			do{
				try{
					doc = (Document)in.readObject();
					if (doc != null){
					out.writeObject("Document recieved"+doc.getName());	
					out.writeObject("bye");	
					logger.info("DocObject Readed");
					//doc.setUri(connection.getInetAddress()+":"+doc.getUri());
					
					logger.info("Got a Document named "+ doc.getName());
					
					dbm.updateDB(dbConn,doc,connection.getInetAddress().toString());
					}
					
				}
				catch(ClassNotFoundException classnot){
					logger.error("Data received in unknown format");
				}catch(IOException e){
					//System.out.println("EOFExeption");
				}
			}while(true);
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
		finally{
			//4: Closing connection
			try{
				in.close();
				out.close();
				providerSocket.close();
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
			logger.info("server>" + msg);
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
	public static void main(String args[])
	{
		Server server = new Server();
		while(true){
			server.run();
		}
	}
}