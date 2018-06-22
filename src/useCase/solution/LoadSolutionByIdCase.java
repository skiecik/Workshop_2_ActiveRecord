package useCase.solution;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import models.Solution;

public class LoadSolutionByIdCase {

	public static void main(String[] args) {
		
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/programmers_school?"
				+ "useSSL=false&useUnicode=true&" + "useJDBCCompliantTimezoneShift=true&"
				+ "useLegacyDatetimeCode=false&" + "serverTimezone=Europe/Warsaw", "root", "root")) {

			Solution sl = Solution.loadSolutionById(conn, 1);
			System.out.println(sl.getId());
			System.out.println(sl.getDescription());
			System.out.println(sl.getExerciseId());
			System.out.println(sl.getUserId());
			System.out.println(sl.getCreated());
			System.out.println(sl.getUpdated());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
