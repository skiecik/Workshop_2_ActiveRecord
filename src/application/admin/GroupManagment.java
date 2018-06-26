package application.admin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import models.Group;


public class GroupManagment {

	public static void manageGroup(Scanner scan, Connection conn) throws SQLException {
		String option = "";

		while (true) {
			List<Group> groups = Group.loadAllGroups(conn);
			for (Group g : groups) {
				System.out.println("Group id = " + g.getId());
				System.out.println("Group name = " + g.getName());
				System.out.println("------------------");
			}

			System.out.println("Choose option: ");
			System.out.println("a - add group");
			System.out.println("e - edit group");
			System.out.println("d - delete group");
			System.out.println("q - quit group managment");

			option = scan.nextLine();
			if ("q".equals(option)) {
				System.out.println("Bye!");
				break;
			} else if ("a".equals(option)) {
				addGroup(scan, conn);
			} else if ("e".equals(option)) {
				editGroup(scan, conn);
			} else if ("d".equals(option)) {
				deleteGroup(scan, conn);
			} else {
				System.out.println("Not a valid option.");
			}
		}
	}
	
	private static void addGroup(Scanner scan, Connection conn) throws SQLException {
		Group gr = new Group();
		
		System.out.println("Type name for new group");
		gr.setName(scan.nextLine());
		gr.saveToDB(conn);
	}
	
	private static void editGroup(Scanner scan, Connection conn) throws SQLException {
		int id = 0;

		while (true) {
			try {
				System.out.println("Type exercise id you want to edit:");
				id = Integer.parseInt(scan.nextLine());
				break;
			} catch (NumberFormatException e) {
				System.out.println("Not a number");
			}
		}
		
		Group gr = Group.loadGroupById(conn, id);
		System.out.println("Edit name for group:");
		gr.setName(scan.nextLine());
		gr.saveToDB(conn);
	}
	
	private static void deleteGroup(Scanner scan, Connection conn) throws SQLException {
		int id = 0;

		while (true) {
			try {
				System.out.println("Type exercise id you want to edit:");
				id = Integer.parseInt(scan.nextLine());
				break;
			} catch (NumberFormatException e) {
				System.out.println("Not a number");
			}
		}
		Group gr = Group.loadGroupById(conn, id);
		gr.deleteGroup(conn);
	}
}
