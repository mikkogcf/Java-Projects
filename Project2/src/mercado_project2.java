/*
 * Student Name: John Micah Mercado
 * Student ID Number: N01611717
 * Submission Date: 11 March 2025
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Class mercado_project2.
 */
public class mercado_project2 {

	/**
	 * The main method - program entry point
	 * 
	 * @param args command-line arguments
	 * @throws IOException if an I/O error occurs
	 */
	public static void main(String[] args) {
		// Create a Scanner object for user input
		Scanner in = new Scanner(System.in);

		// Create an ArrayList to store all the fish catch records
		ArrayList<Catch> allCatch = new ArrayList<Catch>();

		// Flag to track whether the program should quit
		boolean quit = false;

		// Read data from file into the allCatch list
		readDataFromFile(in, allCatch);

		// Display the menu and get the user's choice
		String choice = menuChoice(in);

		// Start the loop that keeps the program running until quit is true
		while (!quit) {
			// Switch based on the user's menu choice
			switch (choice) {

			// Case 1: Record a new catch
			case "1":
				recodNewCatch(in, allCatch); // Call method to record new catch
				choice = menuChoice(in); // Ask for menu choice again after action
				break;

			// Case 2: Determine the fish records
			case "2":
				determineFishRecords(allCatch); // Call method to determine fish records
				choice = menuChoice(in); // Ask for menu choice again after action
				break;

			// Case 3: Print all the catch data
			case "3":
				printAllData(allCatch); // Call method to print all catch records
				choice = menuChoice(in); // Ask for menu choice again after action
				break;

			// Case 4: Save data to file and quit
			case "4":
				saveAndQuit(allCatch); // Call method to save data and quit program
				quit = true; // Set quit flag to true to break the loop
				break;

			// Default case for invalid input
			default:
				System.out.println("\nInvalid input."); // Inform user of invalid input
				choice = menuChoice(in); // Ask for menu choice again after invalid input

				break;
			}
		}

		// Close the scanner to free resources
		in.close();
	}

	/**
	 * Reads fish catch data from a file or starts a new file if not found.
	 * 
	 * @param in       Scanner object for user input
	 * @param allCatch List to store catch records
	 * @throws FileNotFoundException if the file is not found
	 */
	public static void readDataFromFile(Scanner in, ArrayList<Catch> allCatch) {
		// Prompt the user to enter the file name
		System.out.println("What is the name of the data file?");
		String fileName = in.next(); // Read the file name entered by the user
		File f = new File(fileName); // Create a File object using the provided name
		String choice = ""; // Variable to store the user's choice when a file is not found

		while (true) {
			try {
				// If the user has already chosen to start a new file, exit the loop
				if (choice.equalsIgnoreCase("y")) {
					break;
				}

				// Create a Scanner object to read the file
				Scanner fileRead = new Scanner(f);

				// Skip the first line (header row) as it contains column names
				fileRead.nextLine();

				// Read and parse each line of the file
				while (fileRead.hasNextLine()) {
					String line = fileRead.nextLine(); // Read a line from the file
					String[] data = line.split(","); // Split the line by commas to get individual values

					// Create a new Catch object using parsed values from the file
					Catch c = new Catch(data[0], data[1], Float.valueOf(data[2]), Integer.valueOf(data[3]),
							Integer.valueOf(data[6]), Integer.valueOf(data[5]), Integer.valueOf(data[4]));

					// Add the catch object to the allCatch list
					allCatch.add(c);
				}
				// Close the file scanner after reading the data
				fileRead.close();
				break; // Exit the loop after successfully reading the file

			} catch (FileNotFoundException e) {
				// If the file is not found, prompt the user to create a new file
				System.out.println("File not found. Start new file? (Y/N)");
				choice = in.next(); // Read the user's choice

				while (true) {
					if (choice.equalsIgnoreCase("n")) {
						// If the user chooses 'N', prompt them for another file name
						System.out.println("What is the name of the data file?");
						fileName = in.next(); // Read the new file name
						f = new File(fileName); // Update the File object with the new name
						break; // Break inner loop to retry with the new file
					} else if (choice.equalsIgnoreCase("y")) {
						// If the user chooses 'Y', exit the method to start a new file
						break;
					} else {
						// If the input is invalid, prompt the user again
						System.out.println("Invalid input. Start new file? (Y/N)");
						choice = in.next(); // Read the user's choice again
					}
				}
			}
		}
	}

	/**
	 * Displays the menu and gets the user's choice.
	 * 
	 * @param in Scanner object for user input
	 * @return String representing user choice
	 */
	public static String menuChoice(Scanner in) {
		System.out.println(); // Print a blank line for better readability

		// Display menu options
		System.out.println("What would you like to do?");
		System.out.println("1 = Record New Catch");
		System.out.println("2 = Determine Fish Records");
		System.out.println("3 = Print All Data to Screen");
		System.out.println("4 = Save and Quit");

		// Read and return the user's menu choice
		String choice = in.next();
		return choice;
	}

	/**
	 * Records a new fish catch and adds it to the list.
	 * 
	 * @param in       Scanner object for user input
	 * @param allCatch List to store catch records
	 */
	public static void recodNewCatch(Scanner in, ArrayList<Catch> allCatch) {
		// Variables to store user input for the new catch
		String license = "";
		String name = "";
		float weight = 0;
		int length = 0;
		int year = 0;
		int month = 0;
		int day = 0;

		// Prompt user for angler's license number
		System.out.println("\nWhich angler caught the fish? Enter their license.");
		license = in.next();

		// Prompt user for the fish species
		System.out.println("What was the fish species?");
		name = in.next().toLowerCase();

		// Prompt user for the fish weight (must be a float)
		System.out.println("What was the weight?");
		while (true) {
			if (in.hasNextFloat()) {
				weight = in.nextFloat();
				break; // Exit loop if input is valid
			} else {
				System.out.println("Please enter a float.");
				in.next(); // Discard invalid input
			}
		}

		// Prompt user for the fish length (must be an integer)
		System.out.println("What was the length?");
		while (true) {
			if (in.hasNextInt()) {
				length = in.nextInt();
				break; // Exit loop if input is valid
			} else {
				System.out.println("Please enter an integer.");
				in.next(); // Discard invalid input
			}
		}

		// Prompt user for the year of the catch (must be an integer)
		System.out.println("What was the year?");
		while (true) {
			if (in.hasNextInt()) {
				year = in.nextInt();
				break; // Exit loop if input is valid
			} else {
				System.out.println("Please enter an integer.");
				in.next(); // Discard invalid input
			}
		}

		// Prompt user for the month of the catch (must be an integer)
		System.out.println("What was the month (number)?");
		while (true) {
			if (in.hasNextInt()) {
				month = in.nextInt();
				break; // Exit loop if input is valid
			} else {
				System.out.println("Please enter an integer.");
				in.next(); // Discard invalid input
			}
		}

		// Prompt user for the day of the catch (must be an integer)
		System.out.println("What was the day (number)?");
		while (true) {
			if (in.hasNextInt()) {
				day = in.nextInt();
				break; // Exit loop if input is valid
			} else {
				System.out.println("Please enter an integer.");
				in.next(); // Discard invalid input
			}
		}

		// Create a new Catch object with the collected data
		Catch c = new Catch(license, name, weight, length, day, month, year);

		// Add the new catch to the list
		allCatch.add(c);
	}

	/**
	 * Determines the heaviest catch for each fish species.
	 * 
	 * @param allCatch List of fish catches
	 */
	public static void determineFishRecords(ArrayList<Catch> allCatch) {
		// Variable to track the heaviest fish weight for a species
		float mostHeavy = 0;
		// Variables to store the oldest date for the heaviest catch of a species
		int oldestYear = 0;
		int oldestMonth = 0;
		int oldestDay = 0;
		// List to store the heaviest catches per species
		ArrayList<Catch> fishList = new ArrayList<Catch>();
		System.out.println(); // Print a blank line for formatting

		// Check if there are any recorded catches
		if (allCatch.isEmpty()) {
			System.out.println("No data found."); // If no data, print a message and exit the method
			return; // Exit early since there is no data to process
		}

		// Iterate through the list to find the heaviest catch for each species
		for (int i = 0; i < allCatch.size(); i++) {
			// Reset the heaviest weight and oldest date for each species iteration
			mostHeavy = 0;
			oldestYear = 0;
			oldestMonth = 0;
			oldestDay = 0;

			// Find the heaviest fish for the current species
			for (int j = 0; j < allCatch.size(); j++) {
				// Check if the fish species matches the current species being evaluated
				if (allCatch.get(j).getName().equalsIgnoreCase(allCatch.get(i).getName())) {
					// If the fish is heavier than the current heaviest, update records
					if (allCatch.get(j).getWeight() > mostHeavy) {
						mostHeavy = allCatch.get(j).getWeight();
						oldestYear = allCatch.get(j).getCatchYear();
						oldestMonth = allCatch.get(j).getCatchMonth();
						oldestDay = allCatch.get(j).getCatchDay();
					}

					// If the weight is the same but the year is older, update to the older catch
					if (allCatch.get(j).getCatchYear() < oldestYear && allCatch.get(j).getWeight() == mostHeavy) {
						oldestYear = allCatch.get(j).getCatchYear();
						oldestMonth = allCatch.get(j).getCatchMonth();
						oldestDay = allCatch.get(j).getCatchDay();
					}

					// If the weight is the same and the year is the same but the month is older,
					// update
					if (allCatch.get(j).getCatchMonth() < oldestMonth && allCatch.get(j).getCatchYear() == oldestYear
							&& allCatch.get(j).getWeight() == mostHeavy) {
						oldestMonth = allCatch.get(j).getCatchMonth();
						oldestDay = allCatch.get(j).getCatchDay();
					}
					// If the weight, year, and month are the same but the day is older, update
					if (allCatch.get(j).getCatchDay() < oldestDay && allCatch.get(j).getCatchMonth() == oldestMonth
							&& allCatch.get(j).getCatchYear() == oldestYear
							&& allCatch.get(j).getWeight() == mostHeavy) {
						oldestDay = allCatch.get(j).getCatchDay();
					}
				}
			}

			// Add the heaviest fish of each species to the fishList if not already added
			for (Catch c : allCatch) {
				// Ensure the catch matches the heaviest and oldest record for the species
				if (mostHeavy == c.getWeight() && oldestYear == c.getCatchYear() && oldestMonth == c.getCatchMonth()
						&& oldestDay == c.getCatchDay() && !fishList.contains(c)
						&& c.getName().equalsIgnoreCase(allCatch.get(i).getName())) {
					fishList.add(c); // Add the catch to the list of record catches
				}
			}
		}

		// Print out the records of the heaviest catches per species
		for (Catch c : fishList) {
			System.out.printf("Biggest %s had weight %.1f and length %d and was caught by %s on %d/%d/%d\n",
					c.getName(), c.getWeight(), c.getLength(), c.getLicense(), c.getCatchYear(), c.getCatchMonth(),
					c.getCatchDay());
		}
	}

	/**
	 * Prints all fish catch data.
	 * 
	 * @param allCatch List of fish catches
	 */
	public static void printAllData(ArrayList<Catch> allCatch) {
		System.out.println(); // Print a blank line for spacing

		// Check if the list is empty and notify the user if no data is available
		if (allCatch.isEmpty()) {
			System.out.println("No data found.");
		}

		// Iterate through the list and print each fish catch entry
		for (Catch c : allCatch) {
			System.out.println(c); // Print the details of the current fish catch
		}
	}

	/**
	 * Saves all recorded fish catch data to a file and exits the program.
	 *
	 * @param allCatch List of recorded fish catches
	 * @throws IOException if an error occurs during file writing
	 */
	public static void saveAndQuit(ArrayList<Catch> allCatch) {
		// Create a Scanner object to read user input for the file name
		Scanner in = new Scanner(System.in);

		// Prompt the user to enter a file name for saving data
		System.out.println("\nEnter the file name to store data.");
		String fileName = in.next(); // Read user input for file name

		while (true) {
			try {
				// Create a FileWriter object to write data to the specified file
				FileWriter newFile = new FileWriter(fileName);
				PrintWriter out = new PrintWriter(newFile);

				// Write the CSV header line for structured data storage
				out.println("License,Species,Weight,Length,Year,Month,Day");

				// Iterate through the list of recorded fish catches and write each to the file
				for (Catch c : allCatch) {
					out.println(c.getLicense() + "," + c.getName() + "," + c.getWeight() + "," + c.getLength() + ","
							+ c.getCatchYear() + "," + c.getCatchMonth() + "," + c.getCatchDay());
				}

				// Close the PrintWriter to save the file properly
				out.close();

				// Close the Scanner to prevent resource leaks
				in.close();
				break; // Exit program after successfully writing data
			} catch (IOException e) {
				// Handle file writing error
				System.out.println("Error encountered while saving the file.");
				System.out.println("Enter a valid file name to store data.");
				fileName = in.next(); // Prompt user for a new file name
			}
		}
	}

}
