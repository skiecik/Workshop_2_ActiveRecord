package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

public class User {

	 private int id;
	 private String userName;
	 private String email;
	 private String password;
	 private int userGroup;
	
	 public User(String userName, String email, String password, int userGroup) {
		this.userName = userName;
		this.email = email;
		this.setPassword(password);
		this.userGroup = userGroup;
	}

	public User() {}
	
	public void saveToDB(Connection conn) throws SQLException{
		if (this.id == 0) {
			String query = "INSERT INTO users (user_name, email, password, user_group_id) "
					+ "VALUES (?, ?, ?, ?)";
			String[] generatedColumns = { "id" };
			PreparedStatement prep = conn.prepareStatement(query, generatedColumns);
			prep.setString(1, this.userName);
			prep.setString(2, this.email);
			prep.setString(3, this.password);
			prep.setInt(4, this.userGroup);
			prep.executeUpdate();
			ResultSet rs = prep.getGeneratedKeys();
			if (rs.next()) {
				this.id = rs.getInt(1);
			}
		} else {
			String query = "UPDATE users SET user_name=?, email=?, password=?, user_group_id=? WHERE id=?";
			PreparedStatement prep = conn.prepareStatement(query);
			prep.setString(1, this.userName);
			prep.setString(2, this.email);
			prep.setString(3, this.password);
			prep.setInt(4, this.userGroup);
			prep.setInt(5, this.id);
			prep.executeUpdate();
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
	
	public static List<User> loadAllUsers(Connection conn) throws SQLException {
		String query = "SELECT * FROM users";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		List<User> users = new ArrayList<>();
		while (rs.next()) {
			User user = new User();
			user.id = rs.getInt("id");
			user.userName = rs.getString("user_name");
			user.email = rs.getString("email");
			user.password = rs.getString("password");
			user.userGroup = rs.getInt("user_group_id");
			users.add(user);
		}
		return users;
	}

	public void deleteUser(Connection conn) throws SQLException {
		if (this.id != 0) {
			String query = "DELETE FROM users WHERE id = ?";
			PreparedStatement prep = conn.prepareStatement(query);
			prep.setInt(1, this.id);
			prep.executeUpdate();
			this.id = 0;
		}
	}

	public static List<User> loadAllByGroupId (Connection conn, int id) throws SQLException {
		List<User> users = new ArrayList<>();
		String query = "SELECT * FROM users WHERE user_group_id = ?";
		PreparedStatement prep = conn.prepareStatement(query);
		prep.setInt(1, id);
		ResultSet rs = prep.executeQuery();
		while (rs.next()) {
			User user = new User();
			user.id = rs.getInt("id");
			user.userName = rs.getString("user_name");
			user.email = rs.getString("email");
			user.password = rs.getString("password");
			user.userGroup = rs.getInt("user_group_id");
			users.add(user);
		}
		return users;		
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
