/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import smc.Concept;
import smc.Document;
import smc.RelatedConcept;
/**
 *
 * @author Thilina
 */
public class DBManager {
public void updateDB(Connection con, Document doc){
        HashMap<String,Concept> concept=doc.getDoc();
        HashMap<String,ArrayList<RelatedConcept>> relationships=new HashMap<String, ArrayList<RelatedConcept>>();
        try{
        Statement stmt = (Statement) con.createStatement();

        con.setAutoCommit(false);
        String modDocUri=doc.getUri();
        if(modDocUri.contains("'")){
            modDocUri=modDocUri.replace("'", "''");
        }
        String modDocName=doc.getName();
        if(modDocName.contains("'")){
            modDocName=modDocName.replace("'", "''");
        }
        String docInsertingQuery = "INSERT INTO documents(docURI,name,size) VALUES (\'"+modDocUri+"\',\'"+modDocName+"\',"+doc.getSize()+")";
        stmt.executeUpdate(docInsertingQuery);
        String getDocId = "SELECT * from documents WHERE docURI=\'"+modDocUri+"\'";
        ResultSet rs1= stmt.executeQuery(getDocId);
        int updateQuery = 0;
        while (rs1.next()) {
            updateQuery= rs1.getInt("docId");
        }

        PreparedStatement insertConcept = null;
        PreparedStatement insertConceptDoc = null;
//        con.setAutoCommit(false);
        String insertConceptQuery ="INSERT INTO concepts(conceptName) VALUES (?)";
        String insertConceptDocQuery ="INSERT INTO concept_doc(conceptId,docId,frequency,titleStrength,importance,strength) VALUES (?,?,?,?,?)";
        insertConcept = con.prepareStatement(insertConceptQuery);
        insertConceptDoc= con.prepareStatement(insertConceptDocQuery);
        for (String e : concept.keySet()) {

            String getConceptQuery = "SELECT conceptId from concepts WHERE conceptName=\'"+e.replaceAll("'", "''")+"\'";
            ResultSet rsCon= stmt.executeQuery(getConceptQuery);
            int existConceptId=0;
            if(rsCon.next())
            while (rsCon.next()) {
                existConceptId= rsCon.getInt("conceptId");
            }
            int temp = 0;
            
            if(existConceptId==0){
                insertConcept.setString(1, e);                
                insertConcept.executeUpdate();
                con.commit();

                String getConId ="SELECT LAST_INSERT_ID()";
                ResultSet rs2= stmt.executeQuery(getConId);
                while (rs2.next()) {
                    temp= rs2.getInt(1);
                }
            }else{
                temp=existConceptId;
            }

            insertConceptDoc.setInt(1, temp);
            insertConceptDoc.setInt(2, updateQuery);
            insertConceptDoc.setInt(3, concept.get(e).getFreequency());
            insertConceptDoc.setInt(4, concept.get(e).getTitleStrength());
            insertConceptDoc.setInt(5, concept.get(e).getStrength());
            insertConceptDoc.executeUpdate();
            con.commit();
        }


        String getconceptQuery = "SELECT concepts.conceptName, concepts.conceptId FROM concepts JOIN concept_doc ON concepts.conceptId= concept_doc.conceptId WHERE concept_doc.docId="+updateQuery;
        ResultSet rs= stmt.executeQuery(getconceptQuery);
        HashMap<String,Integer> conceptMap = new HashMap<String, Integer>();

         while (rs.next()) {
                String conName = rs.getString("conceptName");
                int conId=rs.getInt("conceptId");
                conceptMap.put(conName, conId);
          } //end while

        PreparedStatement insertRelDoc = null;
        String insertConceptRelDocQuery ="INSERT INTO relationships(type,frequency,is_aStrength, part_ofStrength, strength,conceptId,isHead) VALUES "+"(?,?,?,?,?,?,?)";
        insertRelDoc = con.prepareStatement(insertConceptRelDocQuery);

        PreparedStatement insertRelConDoc = null;
        String insertRelConDocQuery ="INSERT INTO related_concept(conceptId,relId) VALUES "+"(?,?)";
        insertRelConDoc = con.prepareStatement(insertRelConDocQuery);
        for (String e : concept.keySet()) {
            relationships=concept.get(e).getRelationships();
            if(!relationships.isEmpty()){
                for (String f : relationships.keySet()) {
                    if(relationships.get(f)!=null){
                        for(RelatedConcept g:relationships.get(f)){
                            insertRelDoc.setString(1, g.getType());
                            insertRelDoc.setInt(2, g.getFreequency());
                            insertRelDoc.setInt(3, g.getIsStrength());
                            insertRelDoc.setInt(4, g.getPartStrength());
                            insertRelDoc.setInt(5, g.getStrength());
                            int conceptId=0;
                            if(conceptMap.get(f)!=null){
                                conceptId=conceptMap.get(f);
                            }
                            
                            insertRelDoc.setInt(6,conceptId );
                            insertRelDoc.setBoolean(7, g.isHead());
                            insertRelDoc.executeUpdate();
                            con.commit();

                            String getConId ="SELECT LAST_INSERT_ID()";
                            ResultSet rs2= stmt.executeQuery(getConId);
                            int temp1 = 0;
                            while (rs2.next()) {
                                temp1= rs2.getInt(1);
                            }

                            int oriCon=0;
                            if(conceptMap.get(e)!=null){
                                oriCon=conceptMap.get(e);
                            }
                            insertRelConDoc.setInt(1, oriCon);
                            insertRelConDoc.setInt(2, temp1);
                            insertRelConDoc.executeUpdate();
                            con.commit();
                        }
                    }
                }
            }
        }


        }catch (Exception e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
            }
            e.printStackTrace();
        }finally{
            try {
                con.setAutoCommit(true);
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Disconnected from database");
        }


    }
}
