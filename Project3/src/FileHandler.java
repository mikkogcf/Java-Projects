import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Utility class for handling file input/output operations related to vehicles,
 * drivers, customers, and scheduled transportation.
 */
public class FileHandler {

	/**
	 * Loads vehicle data from a CSV file and returns it as an ArrayList of
	 * Vehicles.
	 *
	 * @param fileName The name of the CSV file containing vehicle data.
	 * @return An ArrayList of Vehicle objects loaded from the file.
	 */
	public static ArrayList<Vehicle> loadVehicleData(String fileName) {
		ArrayList<Vehicle> vehicleQueue = new ArrayList<Vehicle>();
		File f = new File(fileName);
		try {
			Scanner fileRead = new Scanner(f);
			fileRead.nextLine(); // Skip header
			while (fileRead.hasNextLine()) {
				String line = fileRead.nextLine();
				String[] data = line.split(",");
				// Create Vehicle from data: description, seats, isLuxury
				Vehicle v = new Vehicle(Integer.valueOf(data[1]), Boolean.valueOf(data[2]), data[0]);
				vehicleQueue.add(v);
			}
			fileRead.close();
		} catch (IOException e) {
			e.getMessage(); // Ideally should log or handle the exception properly
		}
		return vehicleQueue;
	}

	/**
	 * Loads driver data from a CSV file and returns it as a Queue of Drivers.
	 *
	 * @param fileName The name of the CSV file containing driver data.
	 * @return A Queue of Driver objects loaded from the file.
	 */
	public static Queue<Driver> loadDriverData(String fileName) {
		Queue<Driver> driverQueue = new LinkedList<Driver>();
		File f = new File(fileName);
		try {
			Scanner fileRead = new Scanner(f);
			fileRead.nextLine(); // Skip header
			while (fileRead.hasNextLine()) {
				String line = fileRead.nextLine();
				String[] data = line.split(",");
				// Create Driver from data: name, email, age, license
				Driver d = new Driver(data[0], data[1], Integer.valueOf(data[2]), data[3]);
				driverQueue.add(d);
			}
			fileRead.close();
		} catch (IOException e) {
			e.getMessage(); // Ideally should log or handle the exception properly
		}
		return driverQueue;
	}

	/**
	 * Loads customer data from a CSV file and returns it as a Queue of Customers.
	 * Assumes all customers are VIP and creates VIPCustomer instances.
	 *
	 * @param fileName The name of the CSV file containing customer data.
	 * @return A Queue of Customer (specifically VIPCustomer) objects.
	 */
	public static Queue<Customer> loadCustomerData(String fileName) {
		Queue<Customer> customerQueue = new LinkedList<Customer>();
		File f = new File(fileName);
		try {
			Scanner fileRead = new Scanner(f);
			fileRead.nextLine(); // Skip header
			while (fileRead.hasNextLine()) {
				String line = fileRead.nextLine();
				String[] data = line.split(",");
				// If points = -1, create regular customer
				if (Integer.valueOf(data[4]) == -1) {
					Customer c = new Customer(data[0], data[1], Integer.valueOf(data[2]), Integer.valueOf(data[3]));
					customerQueue.add(c);
				} else {
					// Otherwise, create VIP customer
					VIPCustomer cVIP = new VIPCustomer(data[0], data[1], Integer.valueOf(data[2]),
							Integer.valueOf(data[3]), Integer.valueOf(data[4]));
					customerQueue.add(cVIP);
				}
			}
			fileRead.close();
		} catch (IOException e) {
			e.getMessage(); // Ideally should log or handle the exception properly
		}
		return customerQueue;
	}

	/**
	 * Writes scheduled transportation data to a CSV file.
	 *
	 * @param fileName The name of the CSV file to write to.
	 * @param schedule An ArrayList of ScheduledTransportation objects to be
	 *                 written.
	 */
	public static void writeScheduleToFile(String fileName, ArrayList<ScheduledTransportation> schedule) {
		try {
			FileWriter newFile = new FileWriter(fileName);
			PrintWriter out = new PrintWriter(newFile);
			// Write CSV header
			out.print(
					"CustName,CustAge,CustEmail,CustPartySize,CustPoints,DriverName,DriverAge,DriverEmail,DriverLicense,VehicleDescription,VehicleSeats,VehicleIsLuxury\r\n"
							+ "");
			// Write each scheduled transportation record
			for (ScheduledTransportation s : schedule) {
				out.println(s.toCSV());
			}
			out.close();
		} catch (IOException e) {
			e.getMessage(); // Ideally should log or handle the exception properly
		}
	}
}
