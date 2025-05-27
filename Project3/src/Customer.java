/**
 * The Class Customer. Represents a customer who is a person with an associated
 * party size.
 */
public class Customer extends Person {

	/** The number of people in the customer's party. */
	private int partySize;

	/**
	 * Instantiates a new customer.
	 *
	 * @param name      the name of the customer
	 * @param email     the email address of the customer
	 * @param age       the age of the customer
	 * @param partySize the size of the customer's party
	 */
	public Customer(String name, String email, int age, int partySize) {
		super(name, email, age);
		this.partySize = partySize;
	}

	/**
	 * Gets the size of the customer's party.
	 *
	 * @return the party size
	 */
	public int getPartySize() {
		return partySize;
	}

	/**
	 * Sets the size of the customer's party.
	 *
	 * @param partySize the new party size
	 */
	public void setPartySize(int partySize) {
		this.partySize = partySize;
	}

	/**
	 * Returns a string representation of the customer. (Currently returns an empty
	 * string. Can be customized.)
	 *
	 * @return an empty string
	 */
	@Override
	public String toString() {
		return "";
	}

	/**
	 * Converts the customer object to a CSV-formatted string. Includes name, email,
	 * age, and party size in the format: "name,email,age,partySize"
	 *
	 * @return the CSV string representation
	 */
	@Override
	public String toCSV() {
		return super.getName() + "," + super.getAge() + "," + super.getEmail() + "," + partySize + ",-1";
	}
}
