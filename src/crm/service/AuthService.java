package crm.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import crm.dbconnection.MySqlConnection;

public class AuthService {
	public boolean isLogin(String email, String pass) {
		boolean success = false;
		String query = "select email, password from user where email=? and password=?";
		try (Connection conn = MySqlConnection.getConnection(); 
				PreparedStatement ps = conn.prepareStatement(query)) {

			ps.setString(1, email);
			ps.setString(2, pass);
			ResultSet rs = ps.executeQuery();
			success = rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
	}
}
