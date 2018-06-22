package useCase.exercise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import models.Exercise;

public class SaveExerciseToDbCase {

	public static void main(String[] args) {

		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/programmers_school?"
				+ "useSSL=false&useUnicode=true&" + "useJDBCCompliantTimezoneShift=true&"
				+ "useLegacyDatetimeCode=false&" + "serverTimezone=Europe/Warsaw", "root", "root")) {

			Exercise exercise = new Exercise("ex1", "ex1Desription");
			exercise.saveToDB(conn);
			System.out.println(exercise.getId());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
