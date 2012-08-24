package clientGUI;


import XMLParser.InXMLCreator;
import adaptors.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.jdbc.Connection;
import database.DBConnector;
import database.DBManager;
import edu.stanford.nlp.process.DocumentPreprocessor.DocType;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.Timer;
import optimization.Optimizer;
import smc.ConceptRanker;
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
   
   
   public int insidefol(String paths,MapAdjust mpa,final java.awt.Component c,int noOfAllFiles,int noOfFiles) throws Exception{

       int noFiles=0;
       String path = paths; 
       String filess,foldername; //intialize the file and foler name variables
       File folder = new File(path);
       File[] listOfFiles = folder.listFiles(); //list of all the files array
    
       for (int j = 0; j < listOfFiles.length; j++) {
     
           if (listOfFiles[j].isFile())  {            // if it is a file (not a folder)
                 String extension=null;
                  filess = listOfFiles[j].getName();    //for each file get the file name
                  String newfilepath=path+"\\"+filess;
                  int dotPos = filess.lastIndexOf(".");
                  
                  
                  if(dotPos>-1){
                           try {
                                extension = filess.substring(dotPos);
                                if(extension.equalsIgnoreCase(".doc")||extension.equalsIgnoreCase(".ppt")||extension.equalsIgnoreCase(".docx")||extension.equalsIgnoreCase(".pptx")||extension.equalsIgnoreCase(".pdf")||extension.equalsIgnoreCase(".odt")||extension.equalsIgnoreCase(".html")){
                                    noFiles+=1;
                                    JTextArea jt=(JTextArea) c;
                                    jt.append(newfilepath.trim()+"\n");
                                    getLabelAll(c).setText("Processing " + (noOfFiles+noFiles) + " out of " + noOfAllFiles+" files");
                                    getLabelOne(c).setText("Processing file");
                                    filepath(newfilepath,extension,mpa,c,(noOfFiles+noFiles));
                                }
                            } catch (Exception ex) {
                                Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                            }
                      }
                  
                  
               
           }
             else{
                  foldername=listOfFiles[j].getName(); // if it is a folder
                  String newpath=path+"\\"+foldername;
                  insidefol(newpath,mpa,c,noOfAllFiles,noOfFiles);                  //recall to the same function itselef
                 }
        }
       return noFiles;
   }
   
   
   
   
   
   public void filepath(String path, String extension,MapAdjust mpa,final java.awt.Component c,int fileNo) throws Exception{

       
       //System.out.println("File Path ="+path+"   Extension is ="+extension);
       JTextArea jt=(JTextArea) c;
       JProgressBar jp=getProgressAll(jt);
       JProgressBar jp1=getProgressOne(jt);
       JLabel jl2=getLabelOne(jt);
       int limit=0;
       String name=jl2.getText().replace("Processing file : ", "");

       jl2.setText("Preprocessing file");
       ProgressSetter ps=new ProgressSetter(jp,jp1,100,fileNo);
       Thread progThrd=new Thread(ps);
       progThrd.start();
       SCDocument doc = null;
//       if(extension.equalsIgnoreCase(".doc")){
//             DocReader docreader = new DocReader();
//             doc=docreader.getDocument(path);    
//       }
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
        if(extension.equalsIgnoreCase(".docx")){
             DocxSCD docx = new DocxSCD();
             doc=docx.getDocument(path);
       }
//       else if(extension.equalsIgnoreCase(".odt")){
//             OdsExtractor odt = new OdsExtractor();
//             doc=odt.getDocument(path);
//       }
       ps.stopProgress();
       progThrd.stop();

       jl2.setText("Creating intermediate XML");
       ps.setLimit(150);
       progThrd=new Thread(ps);
       progThrd.start();
       if(doc!=null){
            mpa.showLocation(path);
            InXMLCreator xmlcreater=new InXMLCreator();
            java.util.Date date= new java.util.Date();
            long timestamp=date.getTime();
            
            xmlcreater.createXML(doc, "InterXML//"+timestamp+".xml",path);
            String XMLPath="InterXML//"+timestamp+".xml";

            ps.stopProgress();
            progThrd.stop();

            jl2.setText("Extracting concepts/relationships ");
            ps.setLimit(600);
            progThrd=new Thread(ps);
            progThrd.start();
            Parser p=new Parser("grammar/englishPCFG.ser.gz");
            smc.Document doc2=p.parse(XMLPath, DocType.XML, "body");

            ps.stopProgress();
            progThrd.stop();

            jl2.setText("Optimizing concepts/relationships ");
            ps.setLimit(1000);
            progThrd=new Thread(ps);
            progThrd.start();
            Optimizer opti=new Optimizer();
            smc.Document doc1=opti.optimizeDoc(doc2);

            smc.ConceptRanker r=new smc.ConceptRanker();
            r.rankConcepts(doc1);

            mpa.setDocumentsObjects(doc1);
            
            ps.stopProgress();
            progThrd.stop();

            DBConnector db1=new DBConnector();
            DBManager dbm=new DBManager();
            //Connection conn=(Connection) db1.getConnection();
            //dbm.updateDB(conn, doc1);

            JButton jb=getNextButton(jt);
            jb.setEnabled(true);

       }
   }

   public JLabel getLabelAll(final java.awt.Component c){
       JTextArea jt=(JTextArea)c;
       ArrayList<JLabel> jLabels = new ArrayList<JLabel>();
        for (Component jb : jt.getParent().getComponents()){
            if((jb instanceof JLabel) ){
                    JLabel jl = (JLabel)jb;
                    jLabels.add(jl);
            }
        }
        JLabel jlAll = null;
        for(JLabel jl:jLabels){
            if(jl.getName().equals("labelAll")){
                jlAll=jl;
            }
        }
        return jlAll;
   }

    public JLabel getLabelOne(final java.awt.Component c){
       JTextArea jt=(JTextArea)c;
       ArrayList<JLabel> jLabels = new ArrayList<JLabel>();
        for (Component jb : jt.getParent().getComponents()){
            if((jb instanceof JLabel) ){
                    JLabel jl = (JLabel)jb;
                    jLabels.add(jl);
            }
        }
        JLabel jlOne = null;
        for(JLabel jl:jLabels){
             if(jl.getName().equals("labelOne")){
                jlOne=jl;
            }
        }
        return jlOne;
   }

   public JProgressBar getProgressAll(final java.awt.Component c){
       JTextArea jt=(JTextArea)c;
       ArrayList<JProgressBar> jProgressBars=new ArrayList<JProgressBar>();
        for (Component jb : jt.getParent().getComponents()){
            if((jb instanceof JProgressBar) ){
                    JProgressBar jp = (JProgressBar)jb;
                    jProgressBars.add(jp);
            }
        }

        JProgressBar jpAll = null;
        for(JProgressBar jl:jProgressBars){
            if(jl.getName().equals("proAll")){
                jpAll=jl;
            }
        }
        return jpAll;
   }

   public JProgressBar getProgressOne(final java.awt.Component c){
       JTextArea jt=(JTextArea)c;
       ArrayList<JProgressBar> jProgressBars=new ArrayList<JProgressBar>();
        for (Component jb : jt.getParent().getComponents()){
            if((jb instanceof JProgressBar) ){
                    JProgressBar jp = (JProgressBar)jb;
                    jProgressBars.add(jp);
            }
        }

        JProgressBar jpOne = null;
        for(JProgressBar jl:jProgressBars){
               if(jl.getName().equals("proOne")){
                jpOne=jl;
            }
        }
        return jpOne;
   }

   public JButton getNextButton(final java.awt.Component c){
       JTextArea jt=(JTextArea)c;
       ArrayList<JButton> jbutton=new ArrayList<JButton>();
        for (Component jb : jt.getParent().getComponents()){
            if((jb instanceof JButton) ){
                    JButton jp = (JButton)jb;
                    jbutton.add(jp);
            }
        }

        JButton jpOne = null;
        for(JButton jl:jbutton){
               if(jl.getName().equals("next")){
                jpOne=jl;
            }
        }
        return jpOne;
   }   
}
