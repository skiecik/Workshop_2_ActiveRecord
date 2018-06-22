package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Exercise {

	private int id;
	private String title;
	private String description;

	public Exercise(String title, String description) {
		this.title = title;
		this.description = description;
	}

	public Exercise() {
	}

	public void saveToDB(Connection conn) throws SQLException {
		if (this.id == 0) {
			String query = "INSERT INTO exercises (title, description)" + "VALUES (?, ?)";
			String[] generatedColumns = { "id" };
			PreparedStatement prep = conn.prepareStatement(query, generatedColumns);
			prep.setString(1, this.title);
			prep.setString(2, this.description);
			prep.executeUpdate();
			ResultSet rs = prep.getGeneratedKeys();
			if (rs.next()) {
				this.id = rs.getInt(1);
			}
		} else {
			String query = "UPDATE exercises SET title=?, description=? WHERE id=?";
			PreparedStatement prep = conn.prepareStatement(query);
			prep.setString(1, this.title);
			prep.setString(2, this.description);
			prep.setInt(3, this.id);
			prep.executeUpdate();
		}
	}

	public static Exercise loadExerciseById(Connection conn, int id) throws SQLException {
		String query = "SELECT * FROM exercises WHERE id = ?";
		PreparedStatement prep = conn.prepareStatement(query);
		prep.setInt(1, id);
		ResultSet rs = prep.executeQuery();
		if (rs.next()) {
			Exercise loadedExercise = new Exercise();
			loadedExercise.id = rs.getInt("id");
			loadedExercise.title = rs.getString("title");
			loadedExercise.description = rs.getString("description");
			return loadedExercise;
		}
		return null;
	}

	public static List<Exercise> loadAllExercises(Connection conn) throws SQLException {
		String query = "SELECT * FROM exercises";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		List<Exercise> exercises = new ArrayList<>();
		while (rs.next()) {
			Exercise exercise = new Exercise();
			exercise.id = rs.getInt("id");
			exercise.title = rs.getString("title");
			exercise.description = rs.getString("description");
			exercises.add(exercise);
		}
		return exercises;
	}

	public void deleteExercise(Connection conn) throws SQLException {
		if (this.id != 0) {
			String query = "DELETE FROM exercises WHERE id = ?";
			PreparedStatement prep = conn.prepareStatement(query);
			prep.setInt(1, this.id);
			prep.executeUpdate();
			this.id = 0;
		}
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

}
