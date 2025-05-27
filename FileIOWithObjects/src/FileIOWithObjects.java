import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIOWithObjects {

	public static void main(String[] args) throws IOException {
		Scanner scn = new Scanner(System.in);
		ArrayList<Course> allCourses = new ArrayList<Course>();

		// Create a file object representing "courses.txt"
		File f = new File("courses.txt");

		// Check if the file exists before trying to read it
		if (f.exists()) {
			Scanner fileIn = new Scanner(f);

			// Read the file line by line
			while (fileIn.hasNextLine()) {
				String line = fileIn.nextLine();
				String[] data = line.split(","); // Split the line by commas

				// Create a Course object from the read data
				Course c = new Course(data[0], data[1], Integer.valueOf(data[2]));
				allCourses.add(c); // Add the course to the list

				fileIn.close(); // Close the file scanner (should be outside the loop)
			}
		} else {
			System.out.println("File not found");
		}

		ArrayList<Student> allStudents = new ArrayList<Student>();

		// Prompt user for student name input
		System.out.println("What is the student's name?");
		String name = scn.nextLine();

		// Create a new student object
		Student s = new Student(name);
		allStudents.add(s);

		// Prompt user for course registration
		System.out.println("Register the student to which course code?");
		String code = scn.nextLine();

		Course c = null;
		// Search for the course in the available courses list
		for (Course ec : allCourses) {
			if (ec.getCode().equalsIgnoreCase(code)) {
				c = ec;
			}
		}

		// If the course exists, register the student; otherwise, show an error
		if (c != null) {
			s.register(c);
		} else {
			System.out.println("Course not found");
		}

		// Uncomment this line if you want to print all courses
		// printCourses(allCourses);

		scn.close(); // Close scanner to prevent resource leak
	}

	/**
	 * Prints all the courses in the given list.
	 * 
	 * @param courses The list of courses.
	 */
	public static void printCourses(ArrayList<Course> courses) {
		for (Course c : courses) {
			System.out.println(c.toString());
		}
	}

	/**
	 * Allows the user to add courses to the list and updates the "courses.txt"
	 * file.
	 * 
	 * @param scn        The scanner object to take user input.
	 * @param allCourses The list to store course objects.
	 * @throws IOException If an error occurs while writing to the file.
	 */
	public static void addCoursesToList(Scanner scn, ArrayList<Course> allCourses) throws IOException {
		while (true) {
			System.out.println("What is the course code, course name, and course credits?");
			String code = scn.next();
			if (code.equals("q")) // Exit condition
				break;

			String name = scn.next();
			int credits = scn.nextInt();
			scn.nextLine(); // Consume newline

			// Create and add the new course
			Course c = new Course(code, name, credits);
			allCourses.add(c);
		}

		// Write the updated courses list to the file
		FileWriter fw = new FileWriter("courses.txt");
		PrintWriter pw = new PrintWriter(fw);

		// Save each course as a line in the file
		for (Course c : allCourses) {
			pw.println(c.getCode() + "," + c.getName() + "," + c.getCredits());
		}
		pw.flush();
		pw.close(); // Close the file writer
	}
}
