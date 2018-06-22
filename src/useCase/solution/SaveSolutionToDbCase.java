package useCase.solution;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import models.Solution;

public class SaveSolutionToDbCase {

	public static void main(String[] args) {
		
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/programmers_school?"
				+ "useSSL=false&useUnicode=true&" + "useJDBCCompliantTimezoneShift=true&"
				+ "useLegacyDatetimeCode=false&" + "serverTimezone=Europe/Warsaw", "root", "root")) {

			Solution sl = new Solution("solution", 2, 1);
			sl.saveToDB(conn);
			System.out.println(sl.getId());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
