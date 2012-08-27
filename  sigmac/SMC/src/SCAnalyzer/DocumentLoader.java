package SCAnalyzer;


import XMLParser.InXMLCreator;
import adaptors.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import database.DBConnector;
import database.DBManager;
import edu.stanford.nlp.process.DocumentPreprocessor.DocType;
import java.awt.Component;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
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
       JTextArea jt=(JTextArea) c;
       JLabel rejct=getRejectLable(jt);
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
                 //JTextArea jt=(JTextArea) c;
                 jt.append(newfilepath.trim()+"\n");
                 getLabelAll(c).setText("Processing " + (noOfFiles+noFiles) + " out of " + noOfAllFiles+" files");
                 getLabelOne(c).setText("Processing file");
                 filepath(newfilepath,extension,mpa,c,(noOfFiles+noFiles));
                 }
                else{
                rejct.setText(listOfFiles[j]+" is rejected");
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
       JLabel rejct=getRejectLable(jt);
       int limit=0;
       String name=jl2.getText().replace("Processing file : ", "");

       jl2.setText("Preprocessing file");
       ProgressSetter ps=new ProgressSetter(jp,jp1,100,fileNo);
       Thread progThrd=new Thread(ps);
       progThrd.start();
       SCDocument doc = null;
       File file=new File(path);
       long lastModified=file.lastModified();
       Date d = new Date(lastModified);
       SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
       String dateString = sdf.format(d);
       try{
       if(extension.equalsIgnoreCase(".doc")){
             DOCReader docreader = new DOCReader();
             doc=docreader.getDocument(path);    
       }
       else if(extension.equalsIgnoreCase(".ppt")){
             PPTReader pptreader = new PPTReader();
             doc=pptreader.getDocument(path);
       }
       else if(extension.equalsIgnoreCase(".pdf")){
           
           PDFAdapter adapter=new PDFAdapter();
            doc=adapter.getDocument(path);
       }else if(extension.equalsIgnoreCase(".pptx")){
             PPTXReader pptx = new PPTXReader();
             doc=pptx.getDocument(path);
       }
       else if(extension.equalsIgnoreCase(".docx")){
             DOCXReader docx = new DOCXReader();
             doc=docx.getDocument(path);
       }
       else if(extension.equalsIgnoreCase(".odt")){
             ODSReader odt = new ODSReader();
             doc=odt.getDocument(path);
       }
       else{
       rejct.setText(path+" is rejected");
       }
       }
       catch(Exception e){
            rejct.setText(path+" is rejected");
       }
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
            
            xmlcreater.createXML(doc, "InterXML//"+timestamp+".xml",path,dateString);
            String XMLPath="InterXML//"+timestamp+".xml";

            ps.stopProgress();
            progThrd.stop();

            jl2.setText("Extracting concepts/relationships ");
            ps.setLimit(600);
            progThrd=new Thread(ps);
            progThrd.start();
            Parser p=new Parser("grammar/englishPCFG.ser.gz");
            smc.Document doc2=p.parse(XMLPath, DocType.XML, "body");

            //testing code check the created document by the parser
            System.out.println("Testing doc returned by the parser::::::::::::::::::::::::::::::::::::: ");
            doc2.testDocForRelations();
            doc2.printDoc();
            System.out.println("Doc returned by the parser is done and dusted:::::::::::::::::::::::::::");
            System.out.println("Doc returned by the parser is done and dusted:::::::::::::::::::::::::::");
            System.out.println("Doc returned by the parser is done and dusted:::::::::::::::::::::::::::");
            System.out.println("Doc returned by the parser is done and dusted:::::::::::::::::::::::::::");
            //System.exit(0);
            //testing ranking of the doc created by the parser
            smc.ConceptRanker rkr=new ConceptRanker();
            doc2.resetImportance();
            rkr.rankConcepts(doc2);
            System.out.println("Testing rank doc returned by the parser");
            doc2.testDocForRelations();
            doc2.printDoc();
            System.out.println("Rnaked doc returned by the parser is done and dusted ::::::::");
            System.out.println("Rnaked doc returned by the parser is done and dusted ::::::::");
            System.out.println("Rnaked doc returned by the parser is done and dusted ::::::::");
            System.out.println("Rnaked doc returned by the parser is done and dusted ::::::::");

            //System.exit(0);

            ps.stopProgress();
            progThrd.stop();

            jl2.setText("Optimizing concepts/relationships ");
            ps.setLimit(1000);
            progThrd=new Thread(ps);
            progThrd.start();
            doc2.testDocForRelations();
            Optimizer opti=new Optimizer();
            smc.Document doc1=opti.optimizeDoc(doc2);
            //testing optimized document
            System.out.println("Testing the doc returned by the optimizer ::::::::::::::::::::::");
            doc1.testDocForRelations();
            doc1.printDoc();
            System.out.println("Doc returned byt the optimizer is done and dusted :::::::::::::");
            System.out.println("Doc returned byt the optimizer is done and dusted :::::::::::::");
            System.out.println("Doc returned byt the optimizer is done and dusted :::::::::::::");
            //System.exit(0);
            //testing the ranking of optimized document
            smc.ConceptRanker rkkr=new smc.ConceptRanker();
            doc1.resetImportance();
            rkkr.rankConcepts(doc1);
            System.out.println("Testing the ranked doc returned by the parser");
            doc1.testDocForRelations();
            doc1.printDoc();
            System.out.println("rnaked doc returned by the optimizer is done and dusted :::::::::::");
            System.out.println("rnaked doc returned by the optimizer is done and dusted :::::::::::");
            System.out.println("rnaked doc returned by the optimizer is done and dusted :::::::::::");
            System.out.println("rnaked doc returned by the optimizer is done and dusted :::::::::::");

            //System.exit(0);

            smc.ConceptRanker r=new smc.ConceptRanker();
            r.rankConcepts(doc1);

            mpa.setDocumentsObjects(doc1);
            System.out.println("Size of the document after optimizing and ranking");
            System.out.println(doc1.getDoc().size());
            System.out.println("Size is printed above");
            
            ps.stopProgress();
            progThrd.stop();

            DBConnector db1=new DBConnector();
            DBManager dbm=new DBManager();
            //Connection conn=(Connection) db1.getConnection();
            //dbm.updateDB(conn, doc1);

            //JButton jb=getNextButton(jt);
           // jb.setEnabled(true);

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
    
    public JLabel getRejectLable(final java.awt.Component c){
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
             if(jl.getName().equals("reject")){
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
