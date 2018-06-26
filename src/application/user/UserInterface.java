package application.user;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import org.mindrot.jbcrypt.BCrypt;

import models.Solution;
import models.User;

public class UserInterface {

	public static void manage(Scanner scan, Connection conn) throws SQLException {

		System.out.println("Type in email");
		String email = scan.nextLine();
		System.out.println("Type in password");
		String password = scan.nextLine();

		if (login(conn, email, password)) {
			User user = User.loadUserByEmail(conn, email);
			String option = "";
			while (true) {
				System.out.println("Choose one option");
				System.out.println("a - add solution");
				System.out.println("v - see your solution");
				System.out.println("q - quit");

				option = scan.nextLine();
				
				if ("q".equals(option)) {
					System.out.println("Bye!");
					break;
				} else if ("a".equals(option)) {
					addSolution(scan, conn, user.getId());
				} else if ("v".equals(option)) {
					showSolutions(conn, user.getId());
				} else {
					System.out.println("Not a valid option");
				}
			}
		}
	}

	private static void addSolution(Scanner scan, Connection conn, int id) throws SQLException {
		List<Solution> solutions = Solution.loadAllByUserId(conn, id);
		for (Solution s : solutions) {
			System.out.println("Solution id: " + s.getId());
			System.out.println("Exercise id: " + s.getExerciseId());
			System.out.println("----------------------");
		}

		int solutionId = 0;
		while (true) {
			try {
				System.out.println("Type in solution id to add description");
				solutionId = Integer.parseInt(scan.nextLine());
				break;
			} catch (NumberFormatException e) {
				System.out.println("Not a number");
			}
		}
		Solution s = Solution.loadSolutionById(conn, solutionId);
		if (!s.equals(null)) {
			System.out.println("Add description");
			s.setDescription(scan.nextLine());
			s.saveToDB(conn);
		}
	}
	
	private static void showSolutions(Connection conn, int id) throws SQLException {
		List<Solution> solutions = Solution.loadAllByUserId(conn, id);
		for (Solution s : solutions) {
			System.out.println("Solution id: " + s.getId());
			System.out.println("Exercise id: " + s.getExerciseId());
			System.out.println("Solution description: " + s.getDescription());
			System.out.println("Created: " + s.getCreated());
			System.out.println("Updated: " + s.getUpdated());
			System.out.println("----------------------");
		}
	}

	private static boolean login(Connection conn, String email, String password) throws SQLException {
		User user = User.loadUserByEmail(conn, email);
		if (!user.equals(null)) {
			return BCrypt.checkpw(password, user.getPassword());
		} else {
			return false;
		}
	}
}
