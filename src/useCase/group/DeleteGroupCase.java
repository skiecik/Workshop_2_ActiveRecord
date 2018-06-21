package useCase.group;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import models.Group;

public class DeleteGroupCase {

	public static void main(String[] args) {
		
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/programmers_school?"
				+ "useSSL=false&useUnicode=true&" + "useJDBCCompliantTimezoneShift=true&"
				+ "useLegacyDatetimeCode=false&" + "serverTimezone=Europe/Warsaw", "root", "root")) {

			Group group = Group.loadGroupById(conn, 1);
			group.deleteGroup(conn);
			System.out.println(group.getId());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
