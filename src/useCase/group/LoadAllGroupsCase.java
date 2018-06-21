package useCase.group;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import models.Group;

public class LoadAllGroupsCase {

	public static void main(String[] args) {
		
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/programmers_school?"
				+ "useSSL=false&useUnicode=true&" + "useJDBCCompliantTimezoneShift=true&"
				+ "useLegacyDatetimeCode=false&" + "serverTimezone=Europe/Warsaw", "root", "root")) {

			List<Group> groups = Group.loadAllGroups(conn);
			for (Group gr : groups) {
				System.out.println(gr.getId());
				System.out.println(gr.getName());
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
