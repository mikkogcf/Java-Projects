import java.util.ArrayList;
import java.util.Scanner;

public class PlantArrayListExample {

	// TODO: Define a printArrayList method that prints an ArrayList of plant (or
	// flower) objects

	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		String input;
		ArrayList<Plant> myGarden = new ArrayList<Plant>();
		// TODO: Declare an ArrayList called myGarden that can hold object of type plant
		String plantName;
		String plantCost;
		String colorOfFlowers;
		boolean isAnnual;
		// TODO: Declare variables - plantName, plantCost, colorOfFlowers, isAnnual

		input = scnr.next();
		while (!input.equals("-1")) {

			if (input.equalsIgnoreCase("plant")) {
				plantName = scnr.next();
				plantCost = scnr.next();
				Plant p = new Plant();
				p.setPlantName(plantName);
				p.setPlantCost(plantCost);
				myGarden.add(p);
			} else if (input.equalsIgnoreCase("flower")) {
				Flower f = new Flower();
				plantName = scnr.next();
				plantCost = scnr.next();
				isAnnual = scnr.nextBoolean();
				colorOfFlowers = scnr.next();
				f.setPlantName(plantName);
				f.setPlantCost(plantCost);
				f.setPlantType(isAnnual);
				f.setColorOfFlowers(colorOfFlowers);
				myGarden.add(f);
			}

			// TODO: Check if input is a plant or flower
			// Store as a plant object or flower object
			// Add to the ArrayList myGarden

			input = scnr.next();
		}

		for (Plant temp : myGarden) {
			temp.printInfo();
			System.out.println();
		}

	}
}
