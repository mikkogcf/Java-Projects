/**
 * The Class ScheduledTransportation. Represents a scheduled transportation
 * event which involves a customer, a driver, and a vehicle.
 */
public class ScheduledTransportation implements Printable {

	/** The customer involved in the transportation. */
	private Customer customer;

	/** The driver assigned to the transportation. */
	private Driver driver;

	/** The vehicle used for the transportation. */
	private Vehicle vehicle;

	/**
	 * Instantiates a new scheduled transportation with the given customer, driver,
	 * and vehicle.
	 *
	 * @param c the customer
	 * @param d the driver
	 * @param v the vehicle
	 */
	public ScheduledTransportation(Customer c, Driver d, Vehicle v) {
		this.setCustomer(c);
		this.setDriver(d);
		this.setVehicle(v);
	}

	/**
	 * Gets the customer associated with the scheduled transportation.
	 *
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * Sets the customer for the scheduled transportation.
	 *
	 * @param customer the new customer
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * Gets the driver assigned to the scheduled transportation.
	 *
	 * @return the driver
	 */
	public Driver getDriver() {
		return driver;
	}

	/**
	 * Sets the driver for the scheduled transportation.
	 *
	 * @param driver the new driver
	 */
	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	/**
	 * Gets the vehicle used in the scheduled transportation.
	 *
	 * @return the vehicle
	 */
	public Vehicle getVehicle() {
		return vehicle;
	}

	/**
	 * Sets the vehicle to be used for the scheduled transportation.
	 *
	 * @param vehicle the new vehicle
	 */
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	/**
	 * Returns a string representation of the scheduled transportation. (Currently
	 * returns an empty string. Can be expanded as needed.)
	 *
	 * @return an empty string
	 */
	public String toString() {
		return "";
	}

	/**
	 * Converts the scheduled transportation object to a CSV-formatted string.
	 * Combines customer, driver, and vehicle CSV representations.
	 *
	 * @return the CSV string representation
	 */
	@Override
	public String toCSV() {
		return customer.toCSV() + "," + driver.toCSV() + "," + vehicle.toCSV();
	}
}
