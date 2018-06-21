package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Initialization {

	private static final String queryUserGroup = "CREATE TABLE user_group (id INT(11) AUTO_INCREMENT, name VARCHAR(255), PRIMARY KEY(id))";
	private static final String queryUser = "CREATE TABLE users (id BIGINT(20) AUTO_INCREMENT, user_name VARCHAR(255), email VARCHAR(255) NOT NULL UNIQUE, password VARCHAR(255), user_group_id INT(11), PRIMARY KEY(id), FOREIGN KEY(user_group_id) REFERENCES user_group(id))";
	private static final String queryExercise = "CREATE TABLE exercises (id INT(11) AUTO_INCREMENT, title VARCHAR(255), description TEXT, PRIMARY KEY(id))";
	private static final String querySolution = "CREATE TABLE solutions (id INT(11) AUTO_INCREMENT, created DATETIME DEFAULT CURRENT_TIMESTAMP, updated DATETIME ON UPDATE CURRENT_TIMESTAMP, description TEXT, exercise_id INT(11), user_id BIGINT(20), PRIMARY KEY(id), FOREIGN KEY(exercise_id) REFERENCES exercises(id), FOREIGN KEY(user_id) REFERENCES users(id))";

	public static void main(String[] args) {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/programmers_school?"
				+ "useSSL=false&useUnicode=true&" + "useJDBCCompliantTimezoneShift=true&"
				+ "useLegacyDatetimeCode=false&" + "serverTimezone=Europe/Warsaw", "root", "root")) {

			try {
				conn.setAutoCommit(false);
				Statement stmt = conn.createStatement();
				stmt.executeUpdate(queryUserGroup);
				stmt.executeUpdate(queryUser);
				stmt.executeUpdate(queryExercise);
				stmt.executeUpdate(querySolution);
				conn.commit();
			} catch (Exception e) {
				conn.rollback();
				e.printStackTrace();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
