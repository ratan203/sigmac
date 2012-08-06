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
        String insertConceptString ="INSERT INTO concepts(conceptName,frequency,titleStrength,strength) VALUES (?,?,?,?)";
        String insertConceptDocString ="INSERT INTO concept_doc(conceptId,docId) VALUES (?,?)";
        insertConcept = con.prepareStatement(insertConceptString);
        insertConceptDoc= con.prepareStatement(insertConceptDocString);
        for (String e : concept.keySet()) {
            insertConcept.setString(1, e.trim());
            insertConcept.setInt(2, concept.get(e).getFreequency());
            insertConcept.setInt(3, concept.get(e).getTitleStrength());
            insertConcept.setInt(4, concept.get(e).getStrength());
            insertConcept.executeUpdate();
            con.commit();

            String modConcept=e.trim();
            if(e.contains("'")){
                modConcept=e.replace("'", "''");
            }
            String getConId = "SELECT * from concepts WHERE conceptName=\'"+modConcept+"\'";
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
                String conName = rs.getString("conceptName");
                int conId=rs.getInt("conceptId");
                conceptMap.put(conName.trim(), conId);
          } //end while

        PreparedStatement insertRelDoc = null;
        String insertConceptRelDocString ="INSERT INTO relationships(type,frequency,is_aStrength, part_ofStrength, strength,conceptId,isHead) VALUES "+"(?,?,?,?,?,?,?)";
        insertRelDoc = con.prepareStatement(insertConceptRelDocString);

        PreparedStatement insertRelConDoc = null;
        String insertRelConDocString ="INSERT INTO related_concept(conceptId,relId) VALUES "+"(?,?)";
        insertRelConDoc = con.prepareStatement(insertRelConDocString);
        for (String e : concept.keySet()) {
            relationships=concept.get(e).getRelationships();
            if(!relationships.isEmpty()){
                for (String f : relationships.keySet()) {
                    if(relationships.get(f)!=null){
                    for(RelatedConcept g:relationships.get(f)){
                            insertRelDoc.setString(1, g.getType().trim());
                            insertRelDoc.setInt(2, g.getFreequency());
                            insertRelDoc.setInt(3, g.getIsStrength());
                            insertRelDoc.setInt(4, g.getPartStrength());
                            insertRelDoc.setInt(5, g.getStrength());
                            int conceptId=0;
                            if(conceptMap.get(f.trim())!=null){
                                conceptId=conceptMap.get(f.trim());
                            }
                            insertRelDoc.setInt(6,conceptId );
                            insertRelDoc.setBoolean(7, g.isHead());
                            insertRelDoc.executeUpdate();
                            con.commit();

                            String getRelId = "SELECT * from relationships WHERE type=\'"+g.getType()+"\' and conceptId="+conceptId;
                            ResultSet rs3= stmt.executeQuery(getRelId);
                            int temp1 = 0;
                            while (rs3.next()) {
                                temp1= rs3.getInt("relId");
                            }

                            int oriCon=0;
                            if(conceptMap.get(e.trim())!=null){
                                oriCon=conceptMap.get(e.trim());
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
