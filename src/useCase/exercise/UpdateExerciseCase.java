package useCase.exercise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import models.Exercise;

public class UpdateExerciseCase {

	public static void main(String[] args) {
		
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/programmers_school?"
				+ "useSSL=false&useUnicode=true&" + "useJDBCCompliantTimezoneShift=true&"
				+ "useLegacyDatetimeCode=false&" + "serverTimezone=Europe/Warsaw", "root", "root")) {

			Exercise ex = Exercise.loadExerciseById(conn, 1);
			ex.setDescription("ex1DescriptionEdit");
			ex.saveToDB(conn);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
