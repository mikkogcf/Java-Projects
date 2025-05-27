/**
 * The Class Driver. Represents a driver with a license, extending the Person
 * class and implementing Printable.
 */
public class Driver extends Person implements Printable {

	/** The driver's license number or identifier. */
	private String license;

	/**
	 * Instantiates a new driver.
	 *
	 * @param name    the name of the driver
	 * @param email   the email address of the driver
	 * @param age     the age of the driver
	 * @param license the license of the driver
	 */
	public Driver(String name, String email, int age, String license) {
		super(name, email, age);
		this.license = license;
	}

	/**
	 * Gets the license of the driver.
	 *
	 * @return the license
	 */
	public String getLicense() {
		return license;
	}

	/**
	 * Sets the license of the driver.
	 *
	 * @param license the new license
	 */
	public void setLicense(String license) {
		this.license = license;
	}

	/**
	 * Returns a string representation of the driver. (Currently returns an empty
	 * string. Can be customized.)
	 *
	 * @return an empty string
	 */
	public String toString() {
		return "";
	}

	/**
	 * Converts the driver object to a CSV-formatted string. Includes name, age,
	 * email, and license in the format: "name,age,email,license"
	 *
	 * @return the CSV string representation
	 */
	@Override
	public String toCSV() {
		return super.getName() + "," + super.getAge() + "," + super.getEmail() + "," + license;
	}
}
