
import XMLParser.InXMLCreator;
import adaptors.*;
import java.io.File;
import java.io.IOException;
import java.security.Timestamp;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author COMPAQ
 */
public class DocumentLoader {
   public void insidefol(String paths) throws Exception{
      
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
                                filepath(newfilepath,extension);
                            } catch (Exception ex) {
                                Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                            }
                      }
                  
                  
               
           }
             else{
                  foldername=listOfFiles[j].getName(); // if it is a folder
                  String newpath=path+"\\"+foldername;
                  insidefol(newpath);                  //recall to the same function itselef
                 }
        }
   }
   
   
   
   
   
   public void filepath(String path, String extension) throws Exception{
       //System.out.println("File Path ="+path+"   Extension is ="+extension);
       SCDocument doc = null;
       if(extension.equalsIgnoreCase(".ppt")){
//             DocReader docreader = new DocReader();
//             doc=docreader.getDocument(path);
             pptreader pptreader = new pptreader();
             doc=pptreader.getDocument(path);
            
       }
//       else if(extension.equalsIgnoreCase(".ppt")){
//             pptreader pptreader = new pptreader();
//             doc=pptreader.getDocument(path);
//       }
//       else if(extension.equalsIgnoreCase(".pdf")){
//            File f=new File(path);
//            PDFAdapter adapter=new PDFAdapter(f);
//            doc=adapter.getDocument();
//       }else if(extension.equalsIgnoreCase(".pptx")){
//             PpptxSCD pptx = new PpptxSCD();
//             doc=pptx.getDocument(path);
//       }
//       else if(extension.equalsIgnoreCase(".docx")){
//             DocxSCD docx = new DocxSCD();
//             doc=docx.getDocument(path);
//       }
//       else if(extension.equalsIgnoreCase(".odt")){
//             OdsExtractor odt = new OdsExtractor();
//             doc=odt.getDocument(path);
//       }
//       
       if(doc!=null){
            InXMLCreator xmlcreater=new InXMLCreator();
            java.util.Date date= new java.util.Date();
            System.out.println(date.getTime());
            xmlcreater.createXML(doc, "C:\\Users\\COMPAQ\\Desktop\\Project\\XML\\"+date.getTime()+".xml");
       }
   }
   
   
   
}
