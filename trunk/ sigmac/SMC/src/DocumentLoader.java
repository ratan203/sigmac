
import XMLParser.InXMLCreator;
import adaptors.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.jdbc.Connection;
import database.DBConnector;
import database.DBManager;
import edu.stanford.nlp.process.DocumentPreprocessor.DocType;
import java.io.File;
import optimization.Optimizer;
import smc.Parser;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author COMPAQ
 */
public class DocumentLoader {
   
   
   
   public void insidefol(String paths,MapAdjust mpa) throws Exception{
      
       String path = paths; 
       String filess,foldername; //intialize the file and foler name variables
       File folder = new File(path);
       File[] listOfFiles = folder.listFiles(); //list of all the files array
    
       for (int j = 0; j < listOfFiles.length; j++) {
     
           if (listOfFiles[j].isFile())  {            // if it is a file (not a folder)
                 String extension=null;
                  filess = listOfFiles[j].getName();    //for each file ge the file name
                  String newfilepath=path+"\\"+filess;
                  int dotPos = filess.lastIndexOf(".");
                  
                  
                  if(dotPos>-1){
                           try {
                                extension = filess.substring(dotPos);
                                filepath(newfilepath,extension,mpa);
                            } catch (Exception ex) {
                                Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                            }
                      }
                  
                  
               
           }
             else{
                  foldername=listOfFiles[j].getName(); // if it is a folder
                  String newpath=path+"\\"+foldername;
                  insidefol(newpath,mpa);                  //recall to the same function itselef
                 }
        }
   }
   
   
   
   
   
   public void filepath(String path, String extension,MapAdjust mpa) throws Exception{
       //System.out.println("File Path ="+path+"   Extension is ="+extension);
       SCDocument doc = null;
       if(extension.equalsIgnoreCase(".doc")){
             DocReader docreader = new DocReader();
             doc=docreader.getDocument(path);    
       }
       else if(extension.equalsIgnoreCase(".ppt")){
             pptreader pptreader = new pptreader();
             doc=pptreader.getDocument(path);
       }
       else if(extension.equalsIgnoreCase(".pdf")){
            File f=new File(path);
            PDFAdapter adapter=new PDFAdapter(f);
            doc=adapter.getDocument();
       }else if(extension.equalsIgnoreCase(".pptx")){
             PpptxSCD pptx = new PpptxSCD();
             doc=pptx.getDocument(path);
       }
       else if(extension.equalsIgnoreCase(".docx")){
             DocxSCD docx = new DocxSCD();
             doc=docx.getDocument(path);
       }
       else if(extension.equalsIgnoreCase(".odt")){
             OdsExtractor odt = new OdsExtractor();
             doc=odt.getDocument(path);
       }
//       
       if(doc!=null){
            mpa.showLocation(path);
            InXMLCreator xmlcreater=new InXMLCreator();
            java.util.Date date= new java.util.Date();
            System.out.println(date.getTime());
            long timestamp=date.getTime();
            
            xmlcreater.createXML(doc, "InterXML//"+timestamp+".xml",path);
            String XMLPath="InterXML//"+timestamp+".xml";
            Parser p=new Parser("grammar/englishPCFG.ser.gz");
            smc.Document doc2=p.parse(XMLPath, DocType.XML, "body");
            
            Optimizer opti=new Optimizer();
            smc.Document doc1=opti.optimizeDoc(doc2);
            mpa.setDocumentsObjects(doc1);
            

            DBConnector db1=new DBConnector();
            DBManager dbm=new DBManager();
            Connection conn=(Connection) db1.getConnection();
            dbm.updateDB(conn, doc1);
       }
   }
   
   
   
}
