package tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import application.admin.GroupManagment;

public class GroupManagmentTest {

	public static void main(String[] args) {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/programmers_school?"
				+ "useSSL=false&useUnicode=true&" + "useJDBCCompliantTimezoneShift=true&"
				+ "useLegacyDatetimeCode=false&" + "serverTimezone=Europe/Warsaw", "root", "root")) {
			
			Scanner scan = new Scanner(System.in);
			GroupManagment.manageGroup(scan, conn);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
