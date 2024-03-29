package SCAnalyzer;


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
import javax.swing.text.DefaultCaret;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author COMPAQ
 */
public class FirstPage extends javax.swing.JFrame  {

    /**
     * Creates new form FirstPage
     */
    public FirstPage() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        initComponents();
        
//        try {
//            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (UnsupportedLookAndFeelException e) {
//            // handle exception
//        } catch (ClassNotFoundException e) {
//            // handle exception
//        } catch (InstantiationException e) {
//            // handle exception
//        } catch (IllegalAccessException e) {
//            // handle exception
//        }
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

        jLabel1.setFont(new java.awt.Font("Lucida Calligraphy", 1, 24)); // NOI18N
        jLabel1.setText("SigmaC");

        jLabel2.setFont(new java.awt.Font("Khmer UI", 1, 14)); // NOI18N
        jLabel2.setText("Document Analysis Based Automatic Generation of Concpet Map for Enterprices");

        jButton1.setText("Browse Map");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addGap(61, 61, 61)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 249, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(127, 127, 127))))
            .addGroup(layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(jLabel2)
                .addContainerGap(111, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(322, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(312, 312, 312))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                        .addGap(155, 155, 155))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel4)
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2))
                        .addContainerGap(39, Short.MAX_VALUE))))
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

        final JButton jButton1  = new JButton();
        jButton1.setText("Next");
        jButton1.setName("next");
        jButton1.setBounds(170, 370, 70, 25);
        jButton1.setOpaque(true);
        jButton1.setVisible(true);
        jButton1.setEnabled(false);
        frame.getContentPane().add(jButton1);

        final JLabel reject=new JLabel();
        reject.setText(null);
        reject.setBounds(30, 390, 340, 20);
        
        reject.setVisible(true);
        reject.setName("reject");
        reject.setForeground(Color.red);
        frame.getContentPane().add(reject);
        
        
        
        
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

        final JProgressBar jp1=new JProgressBar(0, 1000);
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
       
        DefaultCaret caret = (DefaultCaret)text.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
//        JScrollPane sbrText = new JScrollPane(text);
//        sbrText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        text.setBackground(Color.lightGray);
//        frame.getContentPane().add(sbrText);
        frame.getContentPane().add(text);
       
//        JTextPane jtp=new JTextPane();
//        jtp.add(sbrText,BorderLayout.CENTER);
//        jtp.setEditable(false);        
//        frame.getContentPane().add(jtp);
        
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
           
                DragFileProcessor tet=new DragFileProcessor(files,text,mpa,text);
                Thread tt=new Thread(tet);
                tt.start();
                
            } 
        });

        frame.setBounds( 100, 100, 400, 450 );
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation( frame.EXIT_ON_CLOSE );
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        
   // end main

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
       
        
    }//GEN-LAST:event_jButton1ActionPerformed

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
