package application.admin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import models.User;

public class UsersManagment {

	public static void manageUsers(Scanner scan, Connection conn) throws SQLException {

		String option = "";

		while (true) {
			List<User> users = User.loadAllUsers(conn);
			for (User u : users) {
				System.out.println("User id: " + u.getId());
				System.out.println("User name: " + u.getUserName());
				System.out.println("User email: " + u.getEmail());
				System.out.println("User userGroup: " + u.getUserGroup());
				System.out.println("------------------------");
			}

			System.out.println("Choose option: ");
			System.out.println("a - add user");
			System.out.println("e - edit user");
			System.out.println("d - delete user");
			System.out.println("q - quit user managment");

			option = scan.nextLine();
			if ("q".equals(option)) {
				System.out.println("Bye!");
				break;
			} else if ("a".equals(option)) {
				addUser(scan, conn);
			} else if ("e".equals(option)) {
				editUser(scan, conn);
			} else if ("d".equals(option)) {
				deleteUser(scan, conn);
			} else {
				System.out.println("Not a valid option.");
			}
		}
	}

	private static void addUser(Scanner scan, Connection conn) {
		System.out.println("Add new user.");

		User user = getUser(scan);

		try {
			user.saveToDB(conn);
			System.out.println("New user added to DataBase");
		} catch (SQLException e) {
			System.out.println("There is not a group with id: " + user.getUserGroup());
		}
	}

	private static void editUser(Scanner scan, Connection conn) throws SQLException{
		int id = 0;

		while (true) {
			try {
				System.out.println("Type user id you want to edit:");
				id = Integer.parseInt(scan.nextLine());
				break;
			} catch (NumberFormatException e) {
				System.out.println("Not a number");
			}
		}
			User user = User.loadUserById(conn, id);
			System.out.println("User new name: ");
			user.setUserName(scan.nextLine());
			System.out.println("User new email:");
			user.setEmail(scan.nextLine());
			System.out.println("User new password");
			user.setPassword(scan.nextLine());
			while (true) {
				try {
					System.out.println("User new group id:");
					user.setUserGroup(Integer.parseInt(scan.nextLine()));
					break;
				} catch (NumberFormatException e) {
					System.out.println("Not a number");
				}
			}
			user.saveToDB(conn);
			System.out.println("User edited");
	}

	private static void deleteUser(Scanner scan, Connection conn) {
		int id = 0;

		while (true) {
			try {
				System.out.println("Type user id you want to delete:");
				id = Integer.parseInt(scan.nextLine());
				break;
			} catch (NumberFormatException e) {
				System.out.println("Not a number");
			}
		}
		
		try {
			User user = User.loadUserById(conn, id);
			user.deleteUser(conn);
			System.out.println("User succesfully deleted");
		} catch (SQLException e) {
			System.out.println("There is not a user with this id");
		}
	}

	private static User getUser(Scanner scan) {

		System.out.println("New user name: ");
		String userName = scan.nextLine();
		System.out.println("New user email:");
		String email = scan.nextLine();
		System.out.println("New user password");
		String password = scan.nextLine();
		int groupId = 0;
		while (true) {
			try {
				System.out.println("New user group id:");
				groupId = Integer.parseInt(scan.nextLine());
				break;
			} catch (NumberFormatException e) {
				System.out.println("Not a number");
			}
		}
		return new User(userName, email, password, groupId);
	}
}
