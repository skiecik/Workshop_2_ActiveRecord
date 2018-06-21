package useCase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import models.User;

public class SaveToDbCase {

	public static void main(String[] args) {

		User user = new User("skiecik", "skiecik@skietowsky.com", "password");

		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/programmers_school?"
				+ "useSSL=false&useUnicode=true&" + "useJDBCCompliantTimezoneShift=true&"
				+ "useLegacyDatetimeCode=false&" + "serverTimezone=Europe/Warsaw", "root", "root")) {

			user.saveToDB(conn);
			System.out.println(user.getId());

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
