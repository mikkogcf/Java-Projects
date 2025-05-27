/*
 * Student Name: John Micah Mercado
 * Student ID Number: N01611717
 * Submission Date: 12 April 2025
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Main class that handles scheduling transportation based on customer, vehicle,
 * and driver data.
 */
public class Main {

	// File names for data input/output
	public static final String VEHICLE_FILE_NAME = "Vehicles.csv";
	public static final String CUSTOMER_FILE_NAME = "Customers.csv";
	public static final String DRIVER_FILE_NAME = "Drivers.csv";
	public static final String LOG_FILE_NAME = "Schedule.csv";

	/**
	 * Main method to load data, schedule rides, and save the results.
	 *
	 * @param args Command-line arguments
	 */
	public static void main(String[] args) {
		// Lists and queues to store data
		ArrayList<ScheduledTransportation> allScheduleTransportation = new ArrayList<>();
		Queue<Customer> customerQueue = new LinkedList<>();
		ArrayList<Vehicle> vehicleQueue = new ArrayList<>();
		Queue<Driver> driverQueue = new LinkedList<>();

		// Load data from CSV files
		customerQueue = FileHandler.loadCustomerData(CUSTOMER_FILE_NAME);
		vehicleQueue = FileHandler.loadVehicleData(VEHICLE_FILE_NAME);
		driverQueue = FileHandler.loadDriverData(DRIVER_FILE_NAME);

		// Schedule rides and write to file
		allScheduleTransportation = scheduleAllRides(customerQueue, vehicleQueue, driverQueue);
		FileHandler.writeScheduleToFile(LOG_FILE_NAME, allScheduleTransportation);
	}

	/**
	 * Schedules transportation for all customers using available vehicles and
	 * drivers.
	 *
	 * @param customers Queue of customers waiting for rides
	 * @param vehicles  List of available vehicles
	 * @param drivers   Queue of available drivers
	 * @return A list of scheduled transportation entries
	 */
	public static ArrayList<ScheduledTransportation> scheduleAllRides(Queue<Customer> customers,
			ArrayList<Vehicle> vehicles, Queue<Driver> drivers) {

		ArrayList<ScheduledTransportation> allScheduledRides = new ArrayList<>();
		int vehicleCount = 0;

		// Loop through all customers in the queue
		while (!customers.isEmpty()) {
			for (Customer c : customers) {
				// Handle VIP customers
				if (c instanceof VIPCustomer) {
					int rightSeatNumber = 0;
					int count = 0;
					boolean isStillLooking = true;
					boolean isLuxuryFound = false;

					// Search for a suitable vehicle
					for (Vehicle v : vehicles) {
						if (c.getPartySize() <= v.getSeats() && rightSeatNumber == 0 && v.isLuxury()) {
							// First match found
							rightSeatNumber = v.getSeats();
							isStillLooking = false;
							isLuxuryFound = true;
						} else if (c.getPartySize() <= v.getSeats() && v.getSeats() < rightSeatNumber && v.isLuxury()) {
							// Prefer luxury vehicle with fewer seats
							rightSeatNumber = v.getSeats();
							vehicleCount = count;
							isStillLooking = false;
						} else if (c.getPartySize() <= v.getSeats() && v.getSeats() > rightSeatNumber && !v.isLuxury()
								&& !isLuxuryFound) {
							// Check Non-luxury if no luxury available (prioritizing the best)
							rightSeatNumber = v.getSeats();
							isStillLooking = false;
							vehicleCount = count;
						}
						count++;
					}

					if (!isStillLooking) {
						// Assign vehicle and driver
						Driver driverTemp = drivers.peek();
						if (!vehicles.get(vehicleCount).isLuxury()) {
							System.out.println(
									customers.peek().getName() + ": VIP customer is assigned to a non-luxury vehicle");
						}
						ScheduledTransportation scheduledRide = new ScheduledTransportation(customers.peek(),
								drivers.remove(), vehicles.remove(vehicleCount));
						drivers.add(driverTemp); // Rotate driver
						vehicleCount = 0;
						allScheduledRides.add(scheduledRide);
						break;
					} else {
						System.out.println(customers.peek().getName() + ": No vehicle remains that can hold the party");
						break;
					}
				}
				// Handle regular customers
				else {
					int count = 0;
					boolean isStillLooking = true;

					// Find a suitable non-luxury or available vehicle (prioritizing the least best)
					for (Vehicle v : vehicles) {
						if (c.getPartySize() <= v.getSeats()) {
							vehicleCount = count;
							isStillLooking = false;
						}
						count++;
					}

					if (!isStillLooking) {
						// Assign vehicle and driver
						Driver driverTemp = drivers.peek();
						if (vehicles.get(vehicleCount).isLuxury()) {
							System.out.println(
									customers.peek().getName() + ": Regular customer is assigned to a luxury vehicle");
						}
						ScheduledTransportation scheduledRide = new ScheduledTransportation(customers.peek(),
								drivers.remove(), vehicles.remove(vehicleCount));
						drivers.add(driverTemp); // Rotate driver
						vehicleCount = 0;
						allScheduledRides.add(scheduledRide);
						break;
					} else {
						System.out.println(customers.peek().getName() + ": No vehicle remains that can hold the party");
						break;
					}
				}
			}
			// Remove customer from queue after processing
			customers.remove();
		}

		return allScheduledRides;
	}
}
