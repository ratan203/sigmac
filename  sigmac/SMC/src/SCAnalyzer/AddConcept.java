/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SCAnalyzer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import smc.Concept;
import smc.Document;
import smc.RelatedConcept;

/**
 *
 * @author COMPAQ
 */
public class AddConcept extends javax.swing.JFrame {

    /**
     * Creates new form AddConcept
     */
     DefaultListModel dm1,dm2;
     String concept=null;
     HashMap<String, Integer> relconcept=new HashMap<String, Integer>();
     Document newDoc;
     String conceptName;
     MapAdjust mpa;
     Document originalDoc;
    public AddConcept() {
        initComponents();
        jList1.setModel(dm1=new DefaultListModel());
        jList2.setModel(dm2=new DefaultListModel());
        
        
    }

    
    AddConcept(String message) {
        this.concept=concept;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Add Concept");

        jScrollPane1.setViewportView(jList1);

        jScrollPane2.setViewportView(jList2);

        jButton2.setText("<");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText(">");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText(">>");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("<<");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel1.setText("All Concepts");

        jLabel2.setText("Ralative Concepts");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Add Relationship ");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ASO", "IS A", "PART OF" }));

        jLabel4.setText("Relationship");

        jButton1.setText("Sava Changes");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(235, 235, 235)
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(89, 89, 89)
                                .addComponent(jLabel1)
                                .addGap(108, 108, 108))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addGap(67, 67, 67))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton1)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(22, 22, 22)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel3)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel4))
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        Object[] temp = jList1.getSelectedValues();
    if(temp.length<1)
       JOptionPane.showMessageDialog(rootPane, "Please select an item...");
   else{
        
        for(int i=0;i<temp.length;i++)
    {
        dm2.addElement(temp[i]);
        dm1.removeElement(temp[i]);
        relconcept.put(temp[i].toString(), jComboBox1.getSelectedIndex());
    }
    }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
         Object[] temp = jList2.getSelectedValues();
      if(temp.length>1)
       JOptionPane.showMessageDialog(rootPane, "Please select only one item...");
   else if(temp.length<1)
       JOptionPane.showMessageDialog(rootPane, "Please select an item...");
   else{
    dm1.addElement(temp[0]);
    dm2.removeElement(temp[0]);
    relconcept.remove(temp[0]);
   }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
          Object[] temp = jList1.getSelectedValues();
        if(temp.length>1)
            JOptionPane.showMessageDialog(rootPane, "Please select only one item...");
        else if(temp.length<1)
            JOptionPane.showMessageDialog(rootPane, "Please select an item...");
        else
        {
            System.out.println(temp[0]);
            dm2.addElement(temp[0]);
            dm1.removeElement(temp[0]);
            relconcept.put(temp[0].toString(), jComboBox1.getSelectedIndex());

        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
         Object[] temp = jList2.getSelectedValues();
    if(temp.length<1)
       JOptionPane.showMessageDialog(rootPane, "Please select an item...");
   else{
        
    
    for(int i=0;i<temp.length;i++)
    {
        dm1.addElement(temp[i]);
        dm2.removeElement(temp[i]);
        relconcept.remove(temp[i]);
    }}
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        ArrayList<Concept> concepts=new ArrayList<Concept>();
        Concept neWconcept=new Concept(conceptName);
       
        Set<String> relConceptSet=relconcept.keySet();
        ArrayList<String> relationshipTypes=new ArrayList<String>();
        relationshipTypes.add("aso");
        relationshipTypes.add("is a");
        relationshipTypes.add("part of");
        for(String relcocepts:relConceptSet){
            neWconcept.addRelatedConcept(relcocepts, relationshipTypes.get(relconcept.get(relcocepts)), false, 1);   
        }
      
        neWconcept.setStrength(1.0f);
        neWconcept.setUserAddedStatus(true);
        Set<String> keySet = neWconcept.getRelationships().keySet();
        System.out.println(neWconcept.getName());
        System.out.println("Rels are printed below");
        neWconcept.printConcept();
        concepts.add(neWconcept);
        ArrayList<Concept> cons=new ArrayList<Concept>();
        cons.add(neWconcept.getCopy());
        System.out.println("iiiiiiinew doc");
        newDoc.testDocForRelations();
        System.out.println("tested new doc");
        
        
        newDoc.addUnmatchedConcepts(concepts);
        System.out.println("iiiiiiinew doc-2");
        newDoc.testDocForRelations();
        System.out.println("tested new doc-2");
        for(Concept cptt : concepts){
            Concept get = newDoc.getDoc().get(cptt.getName());
            System.out.println("print the given one");
            cptt.printConcept();
            System.out.println("now printing the one found in the new do");
            get.printConcept();
            HashMap<String, ArrayList<RelatedConcept>> relationships = cptt.getRelationships();
            Set<String> keySet1 = relationships.keySet();
            for(String key1 : keySet1){
                Concept get1 = newDoc.getDoc().get(key1);
                System.out.println("printing concepts related to added one");
                get1.printConcept();
            }
            //System.out.println("ORRRRRRRRRINNNNNNNNNNNNNNNNNNNn");
            System.out.println("now printin the one in the original doc");
            Concept get1 = originalDoc.getDoc().get(cptt.getName());
            //get1.printConcept();
            HashMap<String, ArrayList<RelatedConcept>> relationships1 = cptt.getRelationships();
            Set<String> keySet11 = relationships1.keySet();
            for(String key1 : keySet1){
                Concept get11 = originalDoc.getDoc().get(key1);
                System.out.println("printing concepts related to added one");
                get11.printConcept();
            }
        }
        
        
        System.out.println("iiiiiiinew doc-original");
        originalDoc.testDocForRelations();
        System.out.println("tested new doc-original");
        
        
        originalDoc.addUnmatchedConcepts(concepts);
        
        System.out.println("iiiiiiinew doc-modif");
        originalDoc.testDocForRelations();
        System.out.println("tested new doc-modif");
        for(Concept cptt : concepts){
            Concept get = originalDoc.getDoc().get(cptt.getName());
            get.printConcept();
        }

        System.out.println("after modifying original doc");
        for(Concept cptt : concepts){
            Concept get = newDoc.getDoc().get(cptt.getName());
            get.printConcept();
        }
        newDoc.testDocForRelations();
        System.out.println("after modifying original doc");

        mpa.mapReload(newDoc);
        
        this.setVisible(false);
        mpa.setVisible(true);
        

        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    
    public void analyzeData(Document newDoc, String conceptName, MapAdjust mpa,Document doc){
        this.newDoc=newDoc;
        this.mpa=mpa;
        this.originalDoc=doc;
        this.conceptName=conceptName;
        Set<String> concepts=newDoc.getDoc().keySet();
        for(int i=0;i<concepts.size();i++){
            dm1.addElement(concepts.toArray()[i]);
        
        }
    
    }
    
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JList jList1;
    private javax.swing.JList jList2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}