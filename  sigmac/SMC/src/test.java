
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author COMPAQ
 */
public class test implements Runnable{
java.io.File[] files;
JTextArea text;
MapAdjust mpa;
    
    test(File[] files,JTextArea text,MapAdjust mpa) {
        this.files=files;
        this.text=text;
        this.mpa=mpa;
    }
   

    public void run() {
        for (int i = 0; i < files.length; i++) {
                    try {
                        
                        // mpa.showLocation(files[i].getCanonicalPath());
                        // mpa.showLocation(files[i].getCanonicalPath());
                        final String filenameExtension = files[i].getCanonicalPath();
                        
                        
                        File filename = new File(filenameExtension);
                        String extension;
                        int dotPos = filenameExtension.lastIndexOf(".");
                        extension = null;
                        DocumentLoader inside = new DocumentLoader();
                        if (dotPos > -1) {
                            extension = filenameExtension.substring(dotPos);
                            
                                text.append(files[i].getCanonicalPath() + "\n");
                    try {
                        inside.filepath(filenameExtension, extension, mpa, text);
                    } catch (Exception ex) {
                        Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
                    }
                            
                        } else {
                            try {
                                int noOfFiles = 0;
                                // Directory path here
                                // Directory path here
                                 noOfFiles += inside.insidefol(files[i].getCanonicalPath(), mpa, text);
                            } catch (Exception ex) {
                                Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    } // end try
                    // end try
                    catch (java.io.IOException e) {
                    }
                }
    }
}
