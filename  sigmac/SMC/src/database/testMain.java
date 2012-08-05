/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package database;

import com.mysql.jdbc.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import net.didion.jwnl.JWNLException;
import optimization.Optimizer;
import smc.Document;

/**
 *
 * @author Thilina
 */
public class testMain {
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException, JWNLException {
        DBConnector db1=new DBConnector();
        DBManager dbm=new DBManager();
        Connection conn=(Connection) db1.getConnection();

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
        Optimizer op=new Optimizer();
        Document doc =op.optimizeDocument(des.deserDocument());
        dbm.updateDB(conn, doc);
    }

}
