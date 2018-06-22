package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Solution {

	private int id;
	private Timestamp created;
	private Timestamp updated;
	private String description;
	private int exerciseId;
	private int userId;
	
	public Solution(String description, int exerciseId, int userId) {
		this.description = description;
		this.exerciseId = exerciseId;
		this.userId = userId;
	}

	public Solution() {	}

	public void saveToDB(Connection conn) throws SQLException {
		if (this.id == 0) {
			String query = "INSERT INTO solutions (description, exercise_id, user_id)" + "VALUES (?, ?, ?)";
			String[] generatedColumns = { "id" };
			PreparedStatement prep = conn.prepareStatement(query, generatedColumns);
			prep.setString(1, this.description);
			prep.setInt(2, this.exerciseId);
			prep.setInt(3, this.userId);
			prep.executeUpdate();
			ResultSet rs = prep.getGeneratedKeys();
			if (rs.next()) {
				this.id = rs.getInt(1);
			}
		} else {
			String query = "UPDATE solutions SET description=? WHERE id=?";
			PreparedStatement prep = conn.prepareStatement(query);
			prep.setString(1, this.description);
			prep.setInt(2, this.id);
			prep.executeUpdate();
		}
	}

	public static Solution loadSolutionById(Connection conn, int id) throws SQLException {
		String query = "SELECT * FROM solutions WHERE id = ?";
		PreparedStatement prep = conn.prepareStatement(query);
		prep.setInt(1, id);
		ResultSet rs = prep.executeQuery();
		if (rs.next()) {
			Solution loadedSolution = new Solution();
			loadedSolution.id = rs.getInt("id");
			loadedSolution.description = rs.getString("description");
			loadedSolution.created = rs.getTimestamp("created");
			loadedSolution.updated = rs.getTimestamp("updated");
			loadedSolution.exerciseId = rs.getInt("exercise_id");
			loadedSolution.userId = rs.getInt("user_id");
			return loadedSolution;
		}
		return null;
	}

	public static List<Solution> loadAllSolutions(Connection conn) throws SQLException {
		String query = "SELECT * FROM solutions";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		List<Solution> solutions = new ArrayList<>();
		while (rs.next()) {
			Solution solution = new Solution();
			solution.id = rs.getInt("id");
			solution.description = rs.getString("description");
			solution.created = rs.getTimestamp("created");
			solution.updated = rs.getTimestamp("updated");
			solution.exerciseId = rs.getInt("exercise_id");
			solution.userId = rs.getInt("user_id");
			solutions.add(solution);
		}
		return solutions;
	}

	public void deleteSolution(Connection conn) throws SQLException {
		if (this.id != 0) {
			String query = "DELETE FROM solutions WHERE id = ?";
			PreparedStatement prep = conn.prepareStatement(query);
			prep.setInt(1, this.id);
			prep.executeUpdate();
			this.id = 0;
		}
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getExerciseId() {
		return exerciseId;
	}

	public int getUserId() {
		return userId;
	}

	public int getId() {
		return id;
	}

	public Timestamp getCreated() {
		return created;
	}

	public Timestamp getUpdated() {
		return updated;
	}	
}
