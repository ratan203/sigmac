
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author COMPAQ
 */
public class DragFileProcessor implements Runnable{
java.io.File[] files;
JTextArea text;
MapAdjust mpa;
int noOfAllFiles;


    DragFileProcessor(File[] files,JTextArea text,MapAdjust mpa) {
        this.files=files;
        this.text=text;
        this.mpa=mpa;
        this.noOfAllFiles = noOFFiles(files);
    }

    DragFileProcessor() {
//        throw new UnsupportedOperationException("Not yet implemented");
    }

    private int noOFFiles(java.io.File[] files){
        int noFiles=0;
        for( int i = 0; i < files.length; i++ ){
            try{
            String filenameExtension = files[i].getCanonicalPath();
            int dotPos = filenameExtension.lastIndexOf(".");
            String extension=null;
            if(dotPos>-1){
                try {
                    extension = filenameExtension.substring(dotPos);
                    if(extension.equalsIgnoreCase(".doc")||extension.equalsIgnoreCase(".ppt")||extension.equalsIgnoreCase(".docx")||extension.equalsIgnoreCase(".pptx")||extension.equalsIgnoreCase(".pdf")||extension.equalsIgnoreCase(".odt")||extension.equalsIgnoreCase(".html")){
                        noFiles+=1;
                    }
                } catch (Exception ex) {
                    Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{
                try {
                    noFiles+=traverseFolder(files[i]);
                } catch (Exception ex) {
                    Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            }
            catch( java.io.IOException e ) {}
        }
        return noFiles;
    }

    private int traverseFolder(File file){
        int noFiles = 0;
       String filess;
       File folder = file;
       File[] listOfFiles = folder.listFiles();

       for (int j = 0; j < listOfFiles.length; j++) {

           if (listOfFiles[j].isFile())  {
                  filess = listOfFiles[j].getName();
                  int dotPos1 = filess.lastIndexOf(".");
                  String extension=null;
                  if(dotPos1>-1){
                           try {
                               extension = filess.substring(dotPos1);
                                if(extension.equalsIgnoreCase(".doc")||extension.equalsIgnoreCase(".ppt")||extension.equalsIgnoreCase(".docx")||extension.equalsIgnoreCase(".pptx")||extension.equalsIgnoreCase(".pdf")||extension.equalsIgnoreCase(".odt")||extension.equalsIgnoreCase(".html")){
                                    noFiles+=1;
                                }
                            } catch (Exception ex) {
                                Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                            }
                      }
           }
         else{
              traverseFolder(file);
             }
        }
       return noFiles;
    }

    public void run() {
        text.setText(null);
        int noOfFiles = 0;
        DocumentLoader dl=new DocumentLoader();
        JProgressBar jp=dl.getProgressAll(text);
        JProgressBar jp1=dl.getProgressOne(text);
        jp.setMinimum(0);
        jp.setMaximum(noOfAllFiles * 100);

        JLabel jl1=dl.getLabelAll(text);
        JLabel jl2=dl.getLabelOne(text);

        for (int i = 0; i < files.length; i++) {
                try {

                    final String filenameExtension = files[i].getCanonicalPath();

                    File filename = new File(filenameExtension);
                    String extension;
                    int dotPos = filenameExtension.lastIndexOf(".");
                    extension = null;
                    DocumentLoader inside = new DocumentLoader();
                    if (dotPos > -1) {
                        extension = filenameExtension.substring(dotPos);
                        try {
                            if(extension.equalsIgnoreCase(".doc")||extension.equalsIgnoreCase(".ppt")||extension.equalsIgnoreCase(".docx")||extension.equalsIgnoreCase(".pptx")||extension.equalsIgnoreCase(".pdf")||extension.equalsIgnoreCase(".odt")||extension.equalsIgnoreCase(".html")){
                                text.append(files[i].getCanonicalPath() + "\n");
                                noOfFiles += 1;
                                jl1.setText("Processing " + noOfFiles + " out of " + noOfAllFiles);
                                jl2.setText("Processing file : " + files[i].getName());
                                inside.filepath(filenameExtension, extension, mpa, text);
                            }
                        } catch (Exception ex) {
                            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        try {
                            noOfFiles += inside.insidefol(files[i].getCanonicalPath(), mpa, text,noOfAllFiles,noOfFiles);
                        } catch (Exception ex) {
                            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                catch (java.io.IOException e) {
                }
            }

            text.append("\n You have added " + noOfFiles + " files \n\n");
            text.append("Drop your files here \n");
            jl1.setText("All files succesfully processed");
            jl2.setText(jl2.getText().replace("Processing", "Processed"));
            jp.setValue(noOfAllFiles * 100);
            jp1.setValue(100);
    }
}
