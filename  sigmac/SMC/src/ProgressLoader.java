
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JProgressBar;
import org.apache.commons.collections.functors.NOPClosure;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author COMPAQ
 */
public class ProgressLoader implements Runnable{
JProgressBar progressBar;
int noOfFiles;
    public ProgressLoader(JProgressBar progressBar,int noOfFiles){
   
        this.progressBar=progressBar;
        this.noOfFiles=noOfFiles;
        
    }
    public void run() {
        int temp=100/noOfFiles;
        for(int i=0;i<noOfFiles;i++){
            
        progressBar.setValue(temp);
        temp=temp*2;
            try {
                Thread.sleep(10000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ProgressLoader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
}
