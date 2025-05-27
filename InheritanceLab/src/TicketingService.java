import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class TicketingService {

	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		String personName = "";
		int counter = 0;
		int youPosition = 0;

		Queue<String> peopleInQueue = new LinkedList<String>();

		personName = scnr.nextLine();
		while (!personName.equals("-1")) {
			peopleInQueue.add(personName);
			personName = scnr.nextLine();
		}
		for (String person : peopleInQueue) {

			if (person.equals("You")) {
				youPosition++;
				break;
			}
			youPosition++;
		}
		System.out.println("Welcome to the ticketing service... ");
		System.out.println("You are number " + youPosition + " in the queue.");
		while (!peopleInQueue.isEmpty()) {

			if (peopleInQueue.peek().equals("You")) {
				System.out.println(peopleInQueue.peek() + " can now purchase your ticket!");
				peopleInQueue.remove();
				break;
			} else {

				System.out.println(peopleInQueue.peek() + " has purchased a ticket.");
				peopleInQueue.remove();
				youPosition--;
			}
			System.out.println("You are now number " + youPosition);

		}

		// TODO: In a loop, remove head person from peopleInQueue,
		// output their name and that they have purchased a ticket,
		// then output your position in the queue. When you are at
		// the head, output that you can purchase your ticket.

	}
}
