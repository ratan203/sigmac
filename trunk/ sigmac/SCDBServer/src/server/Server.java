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
    Connection conn=(Connection) dbc.getConnection();
    final Logger logger = LoggerFactory.getLogger(Server.class);
	
	Server(){}
	
	void run()
	{
		try{
			//1. creating a server socket
			providerSocket = new ServerSocket(2004, 10);
			//2. Wait for connection
			//System.out.println("Waiting for connection");
			logger.info("Waiting for connection");
			connection = providerSocket.accept();
			logger.debug("Connection received from {}",connection.getInetAddress().getHostName());
			//3. get Input and Output streams
			out = new ObjectOutputStream(connection.getOutputStream());
			out.flush();
			in = new ObjectInputStream(connection.getInputStream());
			logger.info("Connection successful");
			//4. The two parts communicate via the input and output streams
			do{
				try{
					doc = (Document)in.readObject();
					logger.info("DocObject Readed");
					logger.info("Got a Document named "+ doc.getName());
					dbm.updateDB(conn, doc);
					
					if (doc.equals("bye"))
						sendMessage("bye");
				}
				catch(ClassNotFoundException classnot){
					logger.error("Data received in unknown format");
				}catch(IOException e){
					//System.out.println("EOFExeption");
				}
			}while(!doc.equals("bye"));
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