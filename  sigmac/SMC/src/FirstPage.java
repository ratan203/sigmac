
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author COMPAQ
 */
public class FirstPage extends javax.swing.JFrame {

    /**
     * Creates new form FirstPage
     */
    public FirstPage() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        initComponents();
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (UnsupportedLookAndFeelException e) {
            // handle exception
        } catch (ClassNotFoundException e) {
            // handle exception
        } catch (InstantiationException e) {
            // handle exception
        } catch (IllegalAccessException e) {
            // handle exception
        }
    }

    private int noOFFiles(java.io.File[] files){
        int noFiles=0;
        for( int i = 0; i < files.length; i++ ){
            try{
            String filenameExtension = files[i].getCanonicalPath();
            int dotPos = filenameExtension.lastIndexOf(".");
                if(dotPos>-1){
                    try {
                        noFiles+=1;
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
       String filess,foldername;
       File folder = file;
       File[] listOfFiles = folder.listFiles(); 

       for (int j = 0; j < listOfFiles.length; j++) {

           if (listOfFiles[j].isFile())  {    
                  filess = listOfFiles[j].getName();  
                  int dotPos1 = filess.lastIndexOf(".");
                  if(dotPos1>-1){
                           try {
                                noFiles+=1;
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SigmaC");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Lucida Calligraphy", 1, 24));
        jLabel1.setText("SigmaC");

        jLabel2.setFont(new java.awt.Font("Khmer UI", 1, 14));
        jLabel2.setText("Document Analysis Based Automatic Generation of Concpet Map for Enterprices");

        jButton1.setText("Browse Map");

        jButton2.setText("Add Documents");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adaptors/images/thejit.JPG"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(167, 167, 167)
                .addComponent(jLabel3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(156, 156, 156)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)))
                .addContainerGap(167, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(147, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(130, 130, 130))
            .addGroup(layout.createSequentialGroup()
                .addGap(342, 342, 342)
                .addComponent(jLabel1)
                .addContainerGap(368, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
                        .addGap(143, 143, 143))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
         String a;
    /** Runs a sample program that shows dropped files */
        this.setVisible(false);
        String path;
        final javax.swing.JFrame frame = new javax.swing.JFrame( "SigmaC File Drop" );

        JButton jButton1  = new JButton();
        jButton1.setText("Next");
        jButton1.setBounds(170, 370, 70, 25);
        jButton1.setOpaque(true);
        jButton1.setVisible(true);
        frame.getContentPane().add(jButton1);

        //for all files
        final JLabel jl1=new JLabel();
        jl1.setText("All Files Processing Progress");
        jl1.setBounds(25, 320, 340, 20);
        jl1.setName("labelAll");
        jl1.setOpaque(true);
        jl1.setVisible(true);
        frame.getContentPane().add(jl1);

        final JProgressBar jp=new JProgressBar();
        jp.setForeground(Color.GREEN);
        jp.setBounds(25, 345, 340, 18);
        jp.setIndeterminate(true);
        jp.setName("proAll");
        jp.setVisible(true);
        frame.getContentPane().add(jp);

        //for a single file
        final JLabel jl2=new JLabel();
        jl2.setText("Single File Processing Progress");
        jl2.setBounds(25, 270, 340, 20);
        jl2.setName("labelOne");
        jl2.setOpaque(true);
        jl2.setVisible(true);
        frame.getContentPane().add(jl2);

        final JProgressBar jp1=new JProgressBar(0, 100);
        jp1.setForeground(Color.GREEN);
        jp1.setBounds(25, 295, 340, 18);
        jp1.setIndeterminate(true);
        jp1.setName("proOne");
        jp1.setVisible(true);
        frame.getContentPane().add(jp1);

          int delay = 900; //milliseconds
          ActionListener taskPerformer = new ActionListener() {
             public void actionPerformed(ActionEvent evt) {
                  jp.setIndeterminate(false);
                  jp1.setIndeterminate(false);
              }
          };
        new Timer(delay, taskPerformer).start();
        
        //javax.swing.border.TitledBorder dragBorder = new javax.swing.border.TitledBorder( "Drop 'em" );
        final javax.swing.JTextArea text = new javax.swing.JTextArea();
        text.append("Drop your files here");
        text.setEditable(false);
        text.setWrapStyleWord(true);
        text.setLineWrap(true);
        text.setBounds(20,20,350,230);
        text.setVisible(true);
        text.setWrapStyleWord(true);
        text.setBackground(Color.lightGray);
        frame.getContentPane().add(text);
        JTextPane jtp=new JTextPane();
        jtp.setEditable(false);
        
        frame.getContentPane().add(jtp);
       
        
         final MapAdjust mpa =new MapAdjust();
         jButton1.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e)
            {
                try {
                    //Execute when button is pressed
                    frame.setVisible(false);
                    mpa.setVisible(true);
                    mpa.setLocationRelativeTo(null);
                    mpa.showPaths();
                } catch (IOException ex) {
                    Logger.getLogger(FirstPage.class.getName()).log(Level.SEVERE, null, ex);
                }
               
            }
        });   
        FileDrop fileDrop = new FileDrop(System.out, text, new FileDrop.Listener() {

            public void filesDropped(java.io.File[] files) {
                text.setText(null);
                int noOfAllFiles = noOFFiles(files);                
                int noOfFiles = 0;
                jp.setMinimum(0);
                jp.setMaximum(noOfAllFiles * 100);
                for (int i = 0; i < files.length; i++) {
                    try {
                        // mpa.showLocation(files[i].getCanonicalPath());
                        // mpa.showLocation(files[i].getCanonicalPath());
                        String filenameExtension = files[i].getCanonicalPath();
                        File filename = new File(filenameExtension);
                        String extension;
                        int dotPos = filenameExtension.lastIndexOf(".");
                        extension = null;
                        DocumentLoader inside = new DocumentLoader();
                        if (dotPos > -1) {
                            extension = filenameExtension.substring(dotPos);
                            try {
                                text.append(files[i].getCanonicalPath() + "\n");
                                noOfFiles += 1;
                                jl1.setText("Processing " + noOfFiles + " out of " + noOfAllFiles);
                                jl2.setText("Processing file : " + files[i].getName());
                                inside.filepath(filenameExtension, extension, mpa, text);
                            } catch (Exception ex) {
                                Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            try {
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
                } // end for: through each dropped file
                // end for: through each dropped file
                // end for: through each dropped file
                // end for: through each dropped file
                text.append("\n You have added " + noOfFiles + " files \n\n");
                text.append("Drop your files here \n");
            } // end filesDropped
            // end FileDrop.Listener
            // end filesDropped
            // end filesDropped
        });

        frame.setBounds( 100, 100, 400, 450 );
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation( frame.EXIT_ON_CLOSE );
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        
   // end main

    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws InstantiationException, ClassNotFoundException, IllegalAccessException, UnsupportedLookAndFeelException {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FirstPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FirstPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FirstPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FirstPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
//          UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                FirstPage fp = null;
                try {
                    try {
                        fp = new FirstPage();
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(FirstPage.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InstantiationException ex) {
                        Logger.getLogger(FirstPage.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(FirstPage.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(FirstPage.class.getName()).log(Level.SEVERE, null, ex);
                }
                fp.setVisible(true);
                fp.setLocationRelativeTo(null);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables
}
