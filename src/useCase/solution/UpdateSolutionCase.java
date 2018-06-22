package useCase.solution;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import models.Solution;

public class UpdateSolutionCase {

	public static void main(String[] args) {
		
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/programmers_school?"
				+ "useSSL=false&useUnicode=true&" + "useJDBCCompliantTimezoneShift=true&"
				+ "useLegacyDatetimeCode=false&" + "serverTimezone=Europe/Warsaw", "root", "root")) {

			Solution sl = Solution.loadSolutionById(conn, 1);
			sl.setDescription("description updated");
			sl.saveToDB(conn);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
