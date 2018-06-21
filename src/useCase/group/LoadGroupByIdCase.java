package useCase.group;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import models.Group;

public class LoadGroupByIdCase {

	public static void main(String[] args) {

		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/programmers_school?"
				+ "useSSL=false&useUnicode=true&" + "useJDBCCompliantTimezoneShift=true&"
				+ "useLegacyDatetimeCode=false&" + "serverTimezone=Europe/Warsaw", "root", "root")) {
		
			Group group = Group.loadGroupById(conn, 1);
			System.out.println(group.getId());
			System.out.println(group.getName());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} 
	
}
