package model;

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnection {
    private DBConnection() {} //생성자를 private으로 -> 객체 생성하지 못하도록
    public static Connection getConnection() {
    	Connection con = null;
    	try {
    		Class.forName("org.mariadb.jdbc.Driver");
    		con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/gdudb", "gdu", "1234");
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	return con;
    }
    public static void close(Connection con, Statement stmt, ResultSet rs) {
    	try {
    		if(rs!=null) rs.close();
    		if(stmt!=null) stmt.close();
    		if(con!=null) con.close();
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }
}
