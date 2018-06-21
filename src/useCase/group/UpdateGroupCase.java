package useCase.group;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import models.Group;

public class UpdateGroupCase {

	public static void main(String[] args) {

		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/programmers_school?"
				+ "useSSL=false&useUnicode=true&" + "useJDBCCompliantTimezoneShift=true&"
				+ "useLegacyDatetimeCode=false&" + "serverTimezone=Europe/Warsaw", "root", "root")) {
			
			Group group = Group.loadGroupById(conn, 1);
			group.setName("Group1");
			group.saveToDB(conn);
			
			System.out.println("success");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
