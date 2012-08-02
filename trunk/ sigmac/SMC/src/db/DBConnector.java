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

         String QueryString = "INSERT INTO document(docURI,name,size(kb)) VALUES "+"("+doc.getUri()+",name,size)";
         updateQuery= stmt.executeUpdate(QueryString);

        PreparedStatement insertConcept = null;
        PreparedStatement insertConceptDoc = null;
        
        String insertConceptString ="INSERT INTO concept(concept,frequency,strength) VALUES "+"(?,?,?)";
        String insertConceptDocString ="INSERT INTO conceptdoc(id,docid) VALUES "+"(?,?)";
        con.setAutoCommit(false);
        insertConcept = con.prepareStatement(insertConceptString);
        insertConceptDoc= con.prepareStatement(insertConceptDocString);

        for (String e : concept.keySet()) {
            insertConcept.setString(1, e);
            insertConcept.setInt(2, concept.get(e).getFreequency());
            insertConcept.setInt(2, concept.get(e).getStrength());
            int temp=insertConcept.executeUpdate();
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
                    insertRelDoc.setString(1, g.getType());
                    insertRelDoc.setInt(2, g.getFreequency());
                    insertRelDoc.setInt(3, g.getStrength());
                    insertRelDoc.setInt(4, conceptMap.get(f));
                    insertRelDoc.setBoolean(5, g.isHead());
                    int temp=insertRelDoc.executeUpdate();
                    insertRelConDoc.setInt(1, conceptMap.get(e));
                    insertRelConDoc.setInt(2, temp);
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
