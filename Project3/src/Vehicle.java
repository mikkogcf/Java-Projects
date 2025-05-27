/**
 * The Class Vehicle. Represents a vehicle with a seat count, luxury status, and
 * a description. Implements the Printable interface for CSV output.
 */
public class Vehicle implements Printable {

	/** The number of seats in the vehicle. */
	private int seats;

	/** Indicates whether the vehicle is a luxury vehicle. */
	private boolean isLuxury;

	/** The description of the vehicle. */
	private String description;

	/**
	 * Instantiates a new vehicle with the given seat count, luxury status, and
	 * description.
	 *
	 * @param seats       the number of seats
	 * @param isLuxury    whether the vehicle is luxury
	 * @param description the description of the vehicle
	 */
	Vehicle(int seats, boolean isLuxury, String description) {
		this.setSeats(seats);
		this.setLuxury(isLuxury);
		this.setDescription(description);
	}

	/**
	 * Gets the description of the vehicle.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of the vehicle.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the number of seats in the vehicle.
	 *
	 * @return the seats
	 */
	public int getSeats() {
		return seats;
	}

	/**
	 * Sets the number of seats in the vehicle.
	 *
	 * @param seats the new seats
	 */
	public void setSeats(int seats) {
		this.seats = seats;
	}

	/**
	 * Checks if the vehicle is a luxury vehicle.
	 *
	 * @return true, if the vehicle is luxury
	 */
	public boolean isLuxury() {
		return isLuxury;
	}

	/**
	 * Sets the luxury status of the vehicle.
	 *
	 * @param isLuxury the new luxury status
	 */
	public void setLuxury(boolean isLuxury) {
		this.isLuxury = isLuxury;
	}

	/**
	 * Returns a string representation of the vehicle. (Currently returns an empty
	 * string. Can be customized.)
	 *
	 * @return an empty string
	 */
	public String toString() {
		return "";
	}

	/**
	 * Converts the vehicle object to a CSV-formatted string.
	 *
	 * @return the CSV string in the format "description,seats,ISLUXURY"
	 */
	@Override
	public String toCSV() {
		return description + "," + seats + "," + String.valueOf(isLuxury).toUpperCase();
	}
}
