/**
 * The Class VIPCustomer. Represents a customer with VIP status, who earns
 * reward points.
 */
public class VIPCustomer extends Customer {

	/** The points accumulated by the VIP customer. */
	private int points;

	/**
	 * Instantiates a new VIP customer.
	 *
	 * @param name      the name of the customer
	 * @param email     the email address of the customer
	 * @param age       the age of the customer
	 * @param partySize the size of the customer's party
	 * @param points    the number of loyalty points earned by the customer
	 */
	public VIPCustomer(String name, String email, int age, int partySize, int points) {
		super(name, email, age, partySize);
		this.setPoints(points);
	}

	/**
	 * Gets the points earned by the VIP customer.
	 *
	 * @return the points
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * Sets the points earned by the VIP customer.
	 *
	 * @param points the new points value
	 */
	public void setPoints(int points) {
		this.points = points;
	}

	/**
	 * Returns a string representation of the VIP customer. (Currently returns an
	 * empty string. Can be customized.)
	 *
	 * @return an empty string
	 */
	public String toString() {
		return "";
	}

	/**
	 * Converts the VIP customer object to a CSV-formatted string. Format:
	 * "name,age,email,partySize,points"
	 *
	 * @return the CSV string representation
	 */
	@Override
	public String toCSV() {
		return super.getName() + "," + super.getAge() + "," + super.getEmail() + "," + super.getPartySize() + ","
				+ points;
	}
}
