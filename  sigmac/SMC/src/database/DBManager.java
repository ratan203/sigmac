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
        HashMap<String,ArrayList<RelatedConcept>> relationships;
        try{
        Statement stmt = (Statement) con.createStatement();
        int updateQuery;

        con.setAutoCommit(false);
        String modDocUri=doc.getUri().trim();
        if(modDocUri.contains("'")){
            modDocUri=modDocUri.replace("'", "''");
        }
        String modDocName=doc.getName().trim();
        if(modDocName.contains("'")){
            modDocName=modDocName.replace("'", "''");
        }
        String QueryString = "INSERT INTO documents(docURI,name,size) VALUES (\'"+modDocUri+"\',\'"+modDocName+"\',"+doc.getSize()+")";
        updateQuery= stmt.executeUpdate(QueryString);
        String getDocId = "SELECT * from documents WHERE docURI=\'"+modDocUri+"\'";
        ResultSet rs1= stmt.executeQuery(getDocId);
        while (rs1.next()) {
            updateQuery= rs1.getInt("docId");
        }

        PreparedStatement insertConcept = null;
        PreparedStatement insertConceptDoc = null;
//        con.setAutoCommit(false);
        String insertConceptString ="INSERT INTO concepts(concept,frequency,strength) VALUES (?,?,?)";
        String insertConceptDocString ="INSERT INTO concept_doc(conceptId,docid) VALUES (?,?)";
        insertConcept = con.prepareStatement(insertConceptString);
        insertConceptDoc= con.prepareStatement(insertConceptDocString);
        for (String e : concept.keySet()) {
            insertConcept.setString(1, e.trim());
            insertConcept.setInt(2, concept.get(e).getFreequency());
            insertConcept.setInt(3, concept.get(e).getStrength());
            insertConcept.executeUpdate();
            con.commit();

            String modConcept=e.trim();
            if(e.contains("'")){
                modConcept=e.replace("'", "''");
            }
            String getConId = "SELECT * from concepts WHERE concept=\'"+modConcept+"\'";
//            System.out.println(getConId);
//            System.exit(0);
            ResultSet rs2= stmt.executeQuery(getConId);
            int temp = 0;
            while (rs2.next()) {
                temp= rs2.getInt("conceptId");
            }

            insertConceptDoc.setInt(1, temp);
            insertConceptDoc.setInt(2, updateQuery);
            insertConceptDoc.executeUpdate();
            con.commit();
        }


        String getconceptString = "SELECT * from concepts";
        ResultSet rs= stmt.executeQuery(getconceptString);
        HashMap<String,Integer> conceptMap = new HashMap<String, Integer>();

         while (rs.next()) {
                String conName = rs.getString("concept");
                int conId=rs.getInt("conceptId");
                conceptMap.put(conName, conId);
          } //end while

        PreparedStatement insertRelDoc = null;
        String insertConceptRelDocString ="INSERT INTO relationships(type,frequency,strength,relConceptId,isHead) VALUES "+"(?,?,?,?,?)";
        insertRelDoc = con.prepareStatement(insertConceptRelDocString);

        PreparedStatement insertRelConDoc = null;
        String insertRelConDocString ="INSERT INTO related_concept(conceptId,relId) VALUES "+"(?,?)";
        insertRelConDoc = con.prepareStatement(insertRelConDocString);
        for (String e : concept.keySet()) {
            relationships=concept.get(e).getRelationships();
            for (String f : relationships.keySet()) {
                for(RelatedConcept g:relationships.get(f)){
                    insertRelDoc.setString(1, g.getType().trim());
                    insertRelDoc.setInt(2, g.getFreequency());
                    insertRelDoc.setInt(3, g.getStrength());
                    insertRelDoc.setInt(4, conceptMap.get(f.trim()));
                    insertRelDoc.setBoolean(5, g.isHead());
                    insertRelDoc.executeUpdate();
                    con.commit();

                    String getRelId = "SELECT * from relationships WHERE type=\'"+g.getType()+"\' and relConceptId="+conceptMap.get(f.trim());
                    ResultSet rs3= stmt.executeQuery(getRelId);
                    int temp1 = 0;
                    while (rs3.next()) {
                        temp1= rs3.getInt("relId");
                    }

                    insertRelConDoc.setInt(1, conceptMap.get(e.trim()));
                    insertRelConDoc.setInt(2, temp1);
                    insertRelConDoc.executeUpdate();
                    con.commit();
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
