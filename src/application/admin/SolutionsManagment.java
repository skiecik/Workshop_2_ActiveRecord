package application.admin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import models.Exercise;
import models.Solution;
import models.User;

public class SolutionsManagment {

	public static void manageSolutions(Scanner scan, Connection conn) throws SQLException {
		String option = "";

		while (true) {
			System.out.println("Choose option");
			System.out.println("a - assign exercise to user");
			System.out.println("v - view all assigned exercises to specified user");
			System.out.println("q - quit solution managment");

			option = scan.nextLine();
			if ("q".equals(option)) {
				System.out.println("Bye!");
				break;
			} else if ("a".equals(option)) {
				assignExercise(scan, conn);
			} else if ("v".equals(option)) {
				viewExercises(scan, conn);
			} else {
				System.out.println("Not a valid option.");
			}
		}
	}

	private static void assignExercise(Scanner scan, Connection conn) throws SQLException {
		List<User> users = User.loadAllUsers(conn);
		for (User u : users) {
			System.out.println("User id: " + u.getId());
			System.out.println("User name: " + u.getUserName());
			System.out.println("User email: " + u.getEmail());
			System.out.println("User userGroup: " + u.getUserGroup());
			System.out.println("------------------------");
		}

		int userId = 0;

		while (true) {
			try {
				System.out.println("Type user id to assing exercise");
				userId = Integer.parseInt(scan.nextLine());
				break;
			} catch (NumberFormatException e) {
				System.out.println("Not a number");
			}
		}
		User user = User.loadUserById(conn, userId);
		if (user.equals(null)) {
			System.out.println("There is not a user with this id");
		} else {
			List<Exercise> exercises = Exercise.loadAllExercises(conn);
			for (Exercise e : exercises) {
				System.out.println("Exercise id = " + e.getId());
				System.out.println("Exercise title = " + e.getTitle());
				System.out.println("Exercise description = " + e.getDescription());
				System.out.println("-------------------");
			}
			int exerciseId = 0;
			while (true) {
				try {
					System.out.println("Type in exercise id to assign");
					exerciseId = Integer.parseInt(scan.nextLine());
					break;
				} catch (NumberFormatException e) {
					System.out.println("Not a number");
				}
			}
			Solution sl = new Solution("", exerciseId, userId);
			sl.saveToDB(conn);
		}
	}
	
	private static void viewExercises(Scanner scan, Connection conn) throws SQLException {
		List<User> users = User.loadAllUsers(conn);
		for (User u : users) {
			System.out.println("User id: " + u.getId());
			System.out.println("User name: " + u.getUserName());
			System.out.println("User email: " + u.getEmail());
			System.out.println("User userGroup: " + u.getUserGroup());
			System.out.println("------------------------");
		}

		int userId = 0;

		while (true) {
			try {
				System.out.println("Type user id to view solutions");
				userId = Integer.parseInt(scan.nextLine());
				break;
			} catch (NumberFormatException e) {
				System.out.println("Not a number");
			}
		}
		List<Solution> solutions = Solution.loadAllByUserId(conn, userId);
		for (Solution sl : solutions) {
			System.out.println("Solution id: " + sl.getId());
			System.out.println("Exercise id: " + sl.getExerciseId());
			System.out.println("Created: " + sl.getCreated());
			System.out.println("Updated: " + sl.getUpdated());
			System.out.println("--------------------");
		}
	}
}
