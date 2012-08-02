/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package db;

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
public class DBConnector {

    public Connection getDBConnection(String url1,String dbName1,String driver1,String username1, String pass) {

      Connection conn = null;
      String url = url1;
      String dbName = dbName1;
      String driver = driver1;
      String userName = username1;
      String password = pass;
      try {
          Class.forName(driver).newInstance();
          conn = DriverManager.getConnection(url+dbName,userName,password);
          System.out.println("Connected to the database");
      } catch (Exception e) {
          e.printStackTrace();
      }
      return conn;
    }

    public void updateDB(Connection con, Document doc){
        HashMap<String,Concept> concept=doc.getDoc();
        HashMap<String,ArrayList<RelatedConcept>> relationships;
        try{
        Statement stmt = (Statement) con.createStatement();
        int updateQuery;

        con.setAutoCommit(false);
        String modDocUri=doc.getUri().trim();
        if(doc.getUri().contains("'")){
            modDocUri=doc.getUri().replace("'", "''");
        }
        String QueryString = "INSERT INTO document(docURI,name,size) VALUES (\'"+modDocUri+"\','name',55)";
        updateQuery= stmt.executeUpdate(QueryString);
        String getDocId = "SELECT * from document WHERE docURI=\'"+modDocUri+"\'";
        ResultSet rs1= stmt.executeQuery(getDocId);
        while (rs1.next()) {
            updateQuery= rs1.getInt("docId");
        }        

        PreparedStatement insertConcept = null;
        PreparedStatement insertConceptDoc = null;
//        con.setAutoCommit(false);
        String insertConceptString ="INSERT INTO concept(concept,frequency,strength) VALUES (?,?,?)";
        String insertConceptDocString ="INSERT INTO conceptdoc(id,docid) VALUES (?,?)";        
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
            String getConId = "SELECT * from concept WHERE concept=\'"+modConcept+"\'";
//            System.out.println(getConId);
//            System.exit(0);
            ResultSet rs2= stmt.executeQuery(getConId);
            int temp = 0;
            while (rs2.next()) {
                temp= rs2.getInt("id");
            }

            insertConceptDoc.setInt(1, temp);
            insertConceptDoc.setInt(2, updateQuery);
            insertConceptDoc.executeUpdate();
            con.commit();
        }
    

        String getconceptString = "SELECT * from concept";
        ResultSet rs= stmt.executeQuery(getconceptString);
        HashMap<String,Integer> conceptMap = new HashMap<String, Integer>();

         while (rs.next()) {
                String conName = rs.getString("concept");
                int conId=rs.getInt("id");
                conceptMap.put(conName, conId);
          } //end while

        PreparedStatement insertRelDoc = null;
        String insertConceptRelDocString ="INSERT INTO relationship(type,frequency,strength,relConceptId,isHead) VALUES "+"(?,?,?,?,?)";
        insertRelDoc = con.prepareStatement(insertConceptRelDocString);

        PreparedStatement insertRelConDoc = null;
        String insertRelConDocString ="INSERT INTO relatedconcept(id,relId) VALUES "+"(?,?)";
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
                   
                    String getRelId = "SELECT * from relationship WHERE type=\'"+g.getType()+"\' and relConceptId="+conceptMap.get(f.trim());
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
