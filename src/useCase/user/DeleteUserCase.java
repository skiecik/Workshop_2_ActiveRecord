package useCase.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import models.User;

public class DeleteUserCase {

	public static void main(String[] args) {
		
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/programmers_school?"
				+ "useSSL=false&useUnicode=true&" + "useJDBCCompliantTimezoneShift=true&"
				+ "useLegacyDatetimeCode=false&" + "serverTimezone=Europe/Warsaw", "root", "root")) {

			User user = User.loadUserById(conn, 1);
			user.delteUser(conn);
			System.out.println("success");
			System.out.println(user.getId());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
