package useCase.group;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import models.Group;

public class SaveGroupToDbCase {

	public static void main(String[] args) {
		
		Group group = new Group("Group_1");
		
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/programmers_school?"
				+ "useSSL=false&useUnicode=true&" + "useJDBCCompliantTimezoneShift=true&"
				+ "useLegacyDatetimeCode=false&" + "serverTimezone=Europe/Warsaw", "root", "root")) {
			
			group.saveToDB(conn);
			System.out.println(group.getId());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
