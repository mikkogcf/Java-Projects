/*
 * Student Name: John Micah Mercado
 * Student ID Number: N01611717
 * Submission Date: 11 March 2025
 */

/**
 * The Class Catch represents a fish catch record with information about the
 * angler, the fish caught, and the date it was caught.
 */
public class Catch {

	/** The license number of the angler. */
	private String license;

	/** The name of the fish caught. */
	private String name;

	/** The weight of the fish in pounds. */
	private float weight;

	/** The length of the fish in inches. */
	private int length;

	/** The day the fish was caught. */
	private int catchDay;

	/** The month the fish was caught. */
	private int catchMonth;

	/** The year the fish was caught. */
	private int catchYear;

	/**
	 * Instantiates a new Catch object with default values. This constructor
	 * initializes all fields to default values (empty string or 0).
	 */
	public Catch() {
		this.license = "";
		this.name = "";
		this.weight = 0;
		this.length = 0;
		this.catchDay = 0;
		this.catchMonth = 0;
		this.catchYear = 0;
	}

	/**
	 * Instantiates a new Catch object with specified values.
	 *
	 * @param license    the license number of the angler
	 * @param name       the name of the fish caught
	 * @param weight     the weight of the fish
	 * @param length     the length of the fish
	 * @param catchDay   the day the fish was caught
	 * @param catchMonth the month the fish was caught
	 * @param catchYear  the year the fish was caught
	 */
	public Catch(String license, String name, float weight, int length, int catchDay, int catchMonth, int catchYear) {
		this.license = license;
		this.name = name;
		this.weight = weight;
		this.length = length;
		this.catchDay = catchDay;
		this.catchMonth = catchMonth;
		this.catchYear = catchYear;
	}

	/**
	 * Gets the license number of the angler.
	 *
	 * @return the license number
	 */
	public String getLicense() {
		return license;
	}

	/**
	 * Gets the name of the fish caught.
	 *
	 * @return the name of the fish
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the weight of the fish.
	 *
	 * @return the weight of the fish
	 */
	public float getWeight() {
		return weight;
	}

	/**
	 * Gets the length of the fish.
	 *
	 * @return the length of the fish
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Gets the day the fish was caught.
	 *
	 * @return the catch day
	 */
	public int getCatchDay() {
		return catchDay;
	}

	/**
	 * Gets the month the fish was caught.
	 *
	 * @return the catch month
	 */
	public int getCatchMonth() {
		return catchMonth;
	}

	/**
	 * Gets the year the fish was caught.
	 *
	 * @return the catch year
	 */
	public int getCatchYear() {
		return catchYear;
	}

	/**
	 * Sets the license number of the angler.
	 *
	 * @param license the new license number
	 */
	public void setLicense(String license) {
		this.license = license;
	}

	/**
	 * Sets the name of the fish caught.
	 *
	 * @param name the new name of the fish
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the weight of the fish.
	 *
	 * @param weight the new weight of the fish
	 */
	public void setWeight(float weight) {
		this.weight = weight;
	}

	/**
	 * Sets the length of the fish.
	 *
	 * @param length the new length of the fish
	 */
	public void setLength(int length) {
		this.length = length;
	}

	/**
	 * Sets the day the fish was caught.
	 *
	 * @param catchDay the new catch day
	 */
	public void setCatchDay(int catchDay) {
		this.catchDay = catchDay;
	}

	/**
	 * Sets the month the fish was caught.
	 *
	 * @param catchMonth the new catch month
	 */
	public void setCatchMonth(int catchMonth) {
		this.catchMonth = catchMonth;
	}

	/**
	 * Sets the year the fish was caught.
	 *
	 * @param catchYear the new catch year
	 */
	public void setCatchYear(int catchYear) {
		this.catchYear = catchYear;
	}

	/**
	 * Provides a string representation of the Catch object, summarizing the catch
	 * details.
	 *
	 * @return a string describing the catch
	 */
	public String toString() {
		// Capitalize the first letter of the fish name
		return "Angler " + license + " caught a " + name.substring(0, 1).toUpperCase() + name.substring(1) + ": "
				+ weight + "lbs, " + length + "inches, on " + catchYear + "/" + catchMonth + "/" + catchDay;
	}
}
