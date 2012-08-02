/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package db;

import com.mysql.jdbc.*;
import com.sun.xml.internal.ws.encoding.soap.DeserializationException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.HashMap;
import smc.Document;

/**
 *
 * @author Thilina
 */
public class testMain {
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        DBConnector db1=new DBConnector();
        Connection conn=(Connection) db1.getDBConnection("jdbc:mysql://localhost:3306/", "sigmac", "com.mysql.jdbc.Driver", "root", "");

//        try{
//          Statement stmt = (Statement) conn.createStatement();
//          String getconceptString = "SELECT * from concept";
//        ResultSet rs= stmt.executeQuery(getconceptString);
//        HashMap<String,Integer> conceptMap = new HashMap<String, Integer>();
////        ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
////          int columnsNumber = rsmd.getColumnCount();
//
//          while (rs.next()) {
//                String conName = rs.getString("concept");
//                int conId=rs.getInt("id");
//                conceptMap.put(conName, conId);
//          }
//
//                for (String e : conceptMap.keySet()) {
//                    System.out.println(e+"   "+conceptMap.get(e));
//                }
//
//          conn.close();
//          System.out.println("Disconnected from database");
//        }catch (Exception e) {
//          e.printStackTrace();
//        }

        Deserialize des=new Deserialize();

        Document doc =des.deserDocument();
        db1.updateDB(conn, doc);
    }

}
