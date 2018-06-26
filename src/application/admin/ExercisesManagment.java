package application.admin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import models.Exercise;

public class ExercisesManagment {

	public static void manageExercise(Scanner scan, Connection conn) throws SQLException {
		String option = "";

		while (true) {
			List<Exercise> exercises = Exercise.loadAllExercises(conn);
			for (Exercise e : exercises) {
				System.out.println("Exercise id = " + e.getId());
				System.out.println("Exercise title = " + e.getTitle());
				System.out.println("Exercise description = " + e.getDescription());
				System.out.println("-------------------");
			}

			System.out.println("Choose option: ");
			System.out.println("a - add exercise");
			System.out.println("e - edit exercise");
			System.out.println("d - delete exercise");
			System.out.println("q - quit exercise managment");

			option = scan.nextLine();
			if ("q".equals(option)) {
				System.out.println("Bye!");
				break;
			} else if ("a".equals(option)) {
				addExercise(scan, conn);
			} else if ("e".equals(option)) {
				editExercise(scan, conn);
			} else if ("d".equals(option)) {
				deleteExercise(scan, conn);
			} else {
				System.out.println("Not a valid option.");
			}
		}
	}

	private static void addExercise(Scanner scan, Connection conn) throws SQLException {
		Exercise ex = new Exercise();

		System.out.println("Type exercise title:");
		ex.setTitle(scan.nextLine());
		System.out.println("Type exercise description");
		ex.setDescription(scan.nextLine());

		ex.saveToDB(conn);
	}

	private static void editExercise(Scanner scan, Connection conn) throws SQLException {
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

		Exercise ex = Exercise.loadExerciseById(conn, id);
		System.out.println("Edit title:");
		ex.setTitle(scan.nextLine());
		System.out.println("Edit description");
		ex.setDescription(scan.nextLine());
		ex.saveToDB(conn);
	}

	private static void deleteExercise(Scanner scan, Connection conn) throws SQLException{
		int id = 0;

		while (true) {
			try {
				System.out.println("Type exercise id you want to delete:");
				id = Integer.parseInt(scan.nextLine());
				break;
			} catch (NumberFormatException e) {
				System.out.println("Not a number");
			}
		}

			Exercise ex = Exercise.loadExerciseById(conn, id);
			ex.deleteExercise(conn);
			System.out.println("Exercise successfully deleted");
	}
}
