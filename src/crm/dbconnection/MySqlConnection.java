package crm.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {
	private static Connection connection = null;
	
	private static final String URL = "jdbc:mysql://localhost:3307/crm1";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "1234";
	
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			if(connection == null)
				return DriverManager.getConnection(URL, USERNAME, PASSWORD);
			return connection;
		} catch (ClassNotFoundException e) {
			System.out.println("Driver could not found.");
		} catch (SQLException ex) {
			System.out.println("Database connection could not establish.");
		}
		return null;
	}
}
