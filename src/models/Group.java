package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Group {

	private int id;
	private String name;

	public Group(String name) {
		this.name = name;
	}

	public Group() {}
	
	public void saveToDB(Connection conn) throws SQLException{
		if (this.id == 0) {
			String query = "INSERT INTO user_group (name)"
					+ "VALUES (?)";
			String[] generatedColumns = { "id" };
			PreparedStatement prep = conn.prepareStatement(query, generatedColumns);
			prep.setString(1, this.name);
			prep.executeUpdate();
			ResultSet rs = prep.getGeneratedKeys();
			if (rs.next()) {
				this.id = rs.getInt(1);
			}
		} else {
			String query = "UPDATE user_group SET name=? WHERE id=?";
			PreparedStatement prep = conn.prepareStatement(query);
			prep.setString(1, this.name);
			prep.setInt(2, this.id);
			prep.executeUpdate();
		}
	}
	
	public static Group loadGroupById(Connection conn, int id) throws SQLException{
		String query = "SELECT * FROM user_group WHERE id = ?";
		PreparedStatement prep = conn.prepareStatement(query);
		prep.setInt(1, id);
		ResultSet rs = prep.executeQuery();
		if (rs.next()) {
			Group loadedGroup = new Group();
			loadedGroup.id = rs.getInt("id");
			loadedGroup.name = rs.getString("name");
			return loadedGroup;
		}
		return null;
	}

	public static List<Group> loadAllGroups(Connection conn) throws SQLException {
		String query = "SELECT * FROM user_group";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		List<Group> groups = new ArrayList<>();
		while (rs.next()) {
			Group group = new Group();
			group.id = rs.getInt("id");
			group.name = rs.getString("name");
			groups.add(group);
		}
		return groups;
	}

	public void deleteGroup(Connection conn) throws SQLException {
		if (this.id != 0) {
			String query = "DELETE FROM user_group WHERE id = ?";
			PreparedStatement prep = conn.prepareStatement(query);
			prep.setInt(1, this.id);
			prep.executeUpdate();
			this.id = 0;
		}
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}
	
}
