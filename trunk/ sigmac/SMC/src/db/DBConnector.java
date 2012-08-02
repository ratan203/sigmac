/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
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

    public void updateDB(Connection con, Document doc) throws SQLException{
        HashMap<String,Concept> concept=doc.getDoc();
        HashMap<String,ArrayList<RelatedConcept>> relationships;
        Statement stmt = (Statement) con.createStatement();
        int updateQuery;

         String QueryString = "INSERT INTO document(docURI,name,size(kb)) VALUES "+"("+doc.getUri()+",name,size)";
         updateQuery= stmt.executeUpdate(QueryString);

        PreparedStatement insertConcept = null;
        PreparedStatement insertConceptDoc = null;
        
        String insertConceptString ="INSERT INTO concept(concept,strength) VALUES "+"(?,?)";
        String insertConceptDocString ="INSERT INTO conceptdoc(id,docid) VALUES "+"(?,?)";
        con.setAutoCommit(false);
        insertConcept = con.prepareStatement(insertConceptString);
        insertConceptDoc= con.prepareStatement(insertConceptDocString);

        for (String e : concept.keySet()) {
            insertConcept.setString(1, e);
            insertConcept.setInt(2, concept.get(e).getStrength());
            int temp=insertConcept.executeUpdate();
            insertConceptDoc.setInt(1, temp);
            insertConceptDoc.setInt(2, updateQuery);
            insertConceptDoc.executeUpdate();
            con.commit();
        }
    

        String getconceptString = "SELECT * from concept";
        ResultSet rs= stmt.executeQuery(getconceptString);
        for (String e : concept.keySet()) {
//            relationships=concept.get(e).getRelationships();
//            for (String f : relationships.keySet()) {
//
        }

            con.close();
            System.out.println("Disconnected from database");
       

    }
}
