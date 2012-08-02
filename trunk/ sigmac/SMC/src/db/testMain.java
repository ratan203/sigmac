/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package db;

import com.mysql.jdbc.*;
import java.sql.ResultSet;

/**
 *
 * @author Thilina
 */
public class testMain {
    public static void main(String[] args) {
        DBConnector db1=new DBConnector();
        Connection conn=(Connection) db1.getDBConnection("jdbc:mysql://localhost:3306/", "sigmac", "com.mysql.jdbc.Driver", "root", "");

        try{
          Statement stmt = (Statement) conn.createStatement();
          String query = "Select * FROM concept";
          ResultSet rs = stmt.executeQuery(query);

          ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
          int columnsNumber = rsmd.getColumnCount();

          while (rs.next()) {
              for(int i=1;i<=columnsNumber;i++){
                String dbtime = rs.getString(i);
                System.out.print(dbtime+"  ");
              }
              System.out.println("");
          } //end while

          conn.close();
          System.out.println("Disconnected from database");
        }catch (Exception e) {
          e.printStackTrace();
        }
    }

}
