package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

public class User {

	 private int id;
	 private String userName;
	 private String email;
	 private String password;
	 private int userGroup;
	
	 public User(String userName, String email, String password) {
		this.userName = userName;
		this.email = email;
		this.setPassword(password);
	}

	public User() {}
	
	public void saveToDB(Connection conn) throws SQLException{
		if (this.id == 0) {
			String query = "INSERT INTO users (user_name, email, password) "
					+ "VALUES (?, ?, ?)";
			String[] generatedColumns = { "id" };
			PreparedStatement prep = conn.prepareStatement(query, generatedColumns);
			prep.setString(1, this.userName);
			prep.setString(2, this.email);
			prep.setString(3, this.password);
			prep.executeUpdate();
			ResultSet rs = prep.getGeneratedKeys();
			if (rs.next()) {
				this.id = rs.getInt(1);
			}
		}
	}
	
	public static User loadUserById(Connection conn, int id) throws SQLException{
		String query = "SELECT * FROM users WHERE id = ?";
		PreparedStatement prep = conn.prepareStatement(query);
		prep.setInt(1, id);
		ResultSet rs = prep.executeQuery();
		if (rs.next()) {
			User loadedUser = new User();
			loadedUser.id = rs.getInt("id");
			loadedUser.userName = rs.getString("user_name");
			loadedUser.email = rs.getString("email");
			loadedUser.password = rs.getString("password");
			loadedUser.userGroup = rs.getInt("user_group_id");
			return loadedUser;
		}
		return null;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = BCrypt.hashpw(password, BCrypt.gensalt());
	}

	public int getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(int userGroup) {
		this.userGroup = userGroup;
	}

	public int getId() {
		return id;
	}
	
}
