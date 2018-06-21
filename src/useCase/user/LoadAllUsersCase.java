package useCase.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import models.User;

public class LoadAllUsersCase {

	public static void main(String[] args) {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/programmers_school?"
				+ "useSSL=false&useUnicode=true&" + "useJDBCCompliantTimezoneShift=true&"
				+ "useLegacyDatetimeCode=false&" + "serverTimezone=Europe/Warsaw", "root", "root")) {

			List<User> users = User.loadAllUsers(conn);
			for (User us : users) {
				System.out.println(us.getId());
				System.out.println(us.getUserName());
				System.out.println(us.getEmail());
				System.out.println(us.getUserGroup());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}