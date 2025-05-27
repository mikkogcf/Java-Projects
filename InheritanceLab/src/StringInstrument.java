// TODO: Define a class: StringInstrument that is derived from the Instrument class
public class StringInstrument extends Instrument {
	// TODO: Declare private fields: numStrings, numFrets
	private int numStrings;
	private int numFrets;

	public int getNumOfStrings() {
		return numStrings;
	}

	public StringInstrument() {
		this.numStrings = 0;
		this.numFrets = 0;
	}

	public void setNumOfStrings(int numStrings) {
		this.numStrings = numStrings;
	}

	public int getNumOfFrets() {
		return numFrets;
	}

	public void setNumOfFrets(int numFrets) {
		this.numFrets = numFrets;
	}

	// TODO: Define mutator methods -
	// setNumOfStrings(), setNumOfFrets()

	// TODO: Define accessor methods -
	// getNumOfStrings(), getNumOfFrets()

}
