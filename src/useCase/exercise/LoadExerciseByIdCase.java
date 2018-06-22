package useCase.exercise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import models.Exercise;

public class LoadExerciseByIdCase {

	public static void main(String[] args) {
		
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/programmers_school?"
				+ "useSSL=false&useUnicode=true&" + "useJDBCCompliantTimezoneShift=true&"
				+ "useLegacyDatetimeCode=false&" + "serverTimezone=Europe/Warsaw", "root", "root")) {

			Exercise exercise = Exercise.loadExerciseById(conn, 1);
			System.out.println(exercise.getId());
			System.out.println(exercise.getTitle());
			System.out.println(exercise.getDescription());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
