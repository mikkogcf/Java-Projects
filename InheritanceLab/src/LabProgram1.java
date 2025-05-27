import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class LabProgram1 {

	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		int j;
		String line = "";
		Deque<Character> Allletters = new LinkedList<Character>();
		line = scnr.nextLine();
		scnr.close();
		char[] letters = line.toCharArray();

		for (char element : letters) {
			if (Character.isWhitespace(element) || !Character.isLetterOrDigit(element)) {
				continue;
			}
			Allletters.addLast(element);
		}

		boolean isPalindrome = true;
		int count = Allletters.size() / 2;
		for (j = 0; j < count; j++) {
			if (!Allletters.peekFirst().equals(Allletters.peekLast())) {
				isPalindrome = false;
			}
			Allletters.removeFirst();
			Allletters.removeLast();
		}

		if (isPalindrome) {
			System.out.println("Yes, \"" + line + "\" is a palindrome.");
		} else {
			System.out.println("No, \"" + line + "\" is not a palindrome.");
		}

	}
}
