package dbconnection2;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DbConnection {
  // JDBC driver name and database URL

    public String testDbConnectionDS() {
        DataSource ds = null;
        Connection conn = null;
        Statement stmt = null;
        StringBuffer rows = new StringBuffer("");
        
        try {
            Context ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:jboss/datasources/PostgreSQLDS");
            conn = ds.getConnection();
            
            DatabaseMetaData md = conn.getMetaData();
            ResultSet rs = md.getTables(null, null, "%", null);
            rows.append("Tables in the current database: " + "\n");
            while (rs.next()) {
              //System.out.println(rs.getString(3));
              rows.append(rs.getString(3) + "\n");
            }            

            /*
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Show tables");
            rows.append("Tables in the current database: " + "\n");
            while(rs.next()) {
               //System.out.print(rs.getString(1));
               //System.out.println();
               rows.append(rs.getString(1) + "\n");
            }
            */
        
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }


        if (ds == null) {
            throw new IllegalArgumentException("No Control Database could be found");
        }
        
        return rows.toString();	  
    }    
    
	
  public static void main(String[] args) {
	  
	  DbConnection dbConnection = new DbConnection();
	  String rows = dbConnection.testDbConnectionDS();
	  
	  System.out.println(rows);
	  
	  
  }//end main
}//end JDBCExample