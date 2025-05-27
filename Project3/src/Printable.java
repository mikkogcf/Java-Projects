/**
 * The Interface Printable.
 * 
 * This interface enforces a rule for converting an implementing class into a
 * CSV (Comma-Separated Values) string representation.
 */
public interface Printable {

	/**
	 * Converts the implementing object to a CSV-formatted string.
	 * 
	 * Implementing classes should override this method to define how their data is
	 * formatted for CSV output.
	 *
	 * @return the CSV string representation of the object
	 */
	public abstract String toCSV();

}
