package useCase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import models.User;

public class LoadByIdCase {

	public static void main(String[] args) {

		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/programmers_school?"
				+ "useSSL=false&useUnicode=true&" + "useJDBCCompliantTimezoneShift=true&"
				+ "useLegacyDatetimeCode=false&" + "serverTimezone=Europe/Warsaw", "root", "root")) {

			User user = User.loadUserById(conn, 1);
			System.out.println(user.getId());
			System.out.println(user.getUserName());
			System.out.println(user.getEmail());
			System.out.println(user.getUserGroup());
			
			user = User.loadUserById(conn, 5);
			System.out.println(user);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
