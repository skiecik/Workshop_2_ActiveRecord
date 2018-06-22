package useCase.exercise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import models.Exercise;

public class DeleteExerciseCase {

	public static void main(String[] args) {
		
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/programmers_school?"
				+ "useSSL=false&useUnicode=true&" + "useJDBCCompliantTimezoneShift=true&"
				+ "useLegacyDatetimeCode=false&" + "serverTimezone=Europe/Warsaw", "root", "root")) {

			Exercise ex = Exercise.loadExerciseById(conn, 1);
			ex.deleteExercise(conn);
			System.out.println(ex.getId());

		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
}
