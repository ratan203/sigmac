package adaptors;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class ODSReader {
	
	String body = new String();
	ArrayList<Title> titles = new ArrayList<Title>();
	
	
	private static ODSReader instance = null;
	   
	   public static ODSReader getInstance() {
	      if(instance == null) {
	         instance = new ODSReader();
	      }
	      return instance;
	   }
	
	
	private String extract(String filePath){   
		
		StringBuffer bodybuff = new StringBuffer();
		
		// TODO Auto-generated method stub
		//final OpenDocument doc = new OpenDocument();

		 File file = new File(filePath);
		 ZipFile zFile = null;
		try {
			zFile = new ZipFile(file);
		} catch (ZipException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 
		 ZipEntry e = zFile.getEntry("content.xml");
		 InputStream in = null;
		try {
			in = zFile.getInputStream(e);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		 try {
			 	
		
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document docm = dBuilder.parse(in);
				docm.getDocumentElement().normalize();
		 
				//System.out.println("Root element :" + docm.getDocumentElement().getNodeName());
				NodeList nListParas = docm.getElementsByTagName("text:p");
				NodeList nListHeaders = docm.getElementsByTagName("text:h");
				
				//System.out.println(nList.getLength());
		 
				for (int temp = 0; temp < nListParas.getLength(); temp++) {

				   Node nNode = nListParas.item(temp);
				   bodybuff.append(nNode.getTextContent());
				 
				}
				
				for (int temp = 0; temp < nListParas.getLength(); temp++) {
					 Node nNode = nListHeaders.item(temp);
					 if(nNode != null){
					 titles.add(new Title(nNode.getTextContent()));
					 }
				}
				
			  } catch (Exception ez) {
				ez.printStackTrace();
			  }
			  
			  body = bodybuff.toString();
			  return body;

		  }

	public SCDocument getDocument(String filePath){
		extract(filePath);
		return new SCDocument(body, titles);
		
	}
	
	}


