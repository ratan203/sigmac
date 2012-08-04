
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author COMPAQ
 */
public class MainPage {

static String a;
    /** Runs a sample program that shows dropped files */
    public static void main( String[] args )
    {
        String path;
        javax.swing.JFrame frame = new javax.swing.JFrame( "FileDrop" );
        JButton jButton1  = new JButton();;

jButton1.setText("Next");
jButton1.setBounds(120, 300, 70, 25);
jButton1.setOpaque(true);
jButton1.setVisible(true);
frame.getContentPane().add(jButton1);
        //javax.swing.border.TitledBorder dragBorder = new javax.swing.border.TitledBorder( "Drop 'em" );
        final javax.swing.JTextArea text = new javax.swing.JTextArea();
        frame.getContentPane().add( 
            new javax.swing.JScrollPane( text ), 
            java.awt.BorderLayout.CENTER );
        
        final MapAdjust mpa =new MapAdjust();
         jButton1.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
                mpa.setVisible(true);
               
            }
        });   
        
        new FileDrop( System.out, text, /*dragBorder,*/ new FileDrop.Listener()
        {   public void filesDropped( java.io.File[] files )
            { 
                for( int i = 0; i < files.length; i++ )
                {   try
                    {  
                       text.append( files[i].getCanonicalPath() + "\n" );
                       mpa.showLocation(files[i].getCanonicalPath());
                       String filenameExtension = files[i].getCanonicalPath();
                       File filename = new File(filenameExtension);
                       String extension;
                       int dotPos = filenameExtension.lastIndexOf(".");
                       extension=null;
                       DocumentLoader inside=new DocumentLoader();
                            if(dotPos>-1){
                                    extension = filenameExtension.substring(dotPos);
                            try {
                                inside.filepath(filenameExtension,extension);
                                
                            } catch (Exception ex) {
                                Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            }
                            else{
                            try {
                                // Directory path here
                                
                                      inside.insidefol(files[i].getCanonicalPath());
                            } catch (Exception ex) {
                                Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                            }
                           
                            }
                       
                    }   // end try
                    catch( java.io.IOException e ) {}
                }   // end for: through each dropped file
            }   // end filesDropped
        }); // end FileDrop.Listener

        frame.setBounds( 100, 100, 300, 400 );
        frame.setDefaultCloseOperation( frame.EXIT_ON_CLOSE );
        frame.setVisible(true);
        
    }   // end main



}
