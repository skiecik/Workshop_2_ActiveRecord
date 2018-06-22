package useCase.exercise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import models.Exercise;

public class LoadAllExercisesCase {

	public static void main(String[] args) {
		
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/programmers_school?"
				+ "useSSL=false&useUnicode=true&" + "useJDBCCompliantTimezoneShift=true&"
				+ "useLegacyDatetimeCode=false&" + "serverTimezone=Europe/Warsaw", "root", "root")) {

			List<Exercise> exercises = Exercise.loadAllExercises(conn);
			for (Exercise e : exercises) {
				System.out.println(e.getId());
				System.out.println(e.getTitle());
				System.out.println(e.getDescription());
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
