package realTimeApplication_JDBC;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class DBConnection {
	
	static String url = "jdbc:mysql://localhost:3306/whatsApp";
	static String user = "root";
	static String password = "Dinesh@123";
	
	public static Connection getConnection() {
		
		//load class
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//estabilish Connection
		try {
			Connection con = DriverManager.getConnection(url , user , password);
			return con;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}

}
