import java.util.InputMismatchException;
import java.util.Scanner;

public class LabProgram7 {
	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		try {
			int userNum = scnr.nextInt();
			int divNum = scnr.nextInt();
			int quotient = userNum / divNum;
			System.out.println(quotient);
		} catch (ArithmeticException e) {
			System.out.println("Arithmetic Exception: " + e.getMessage());
		} catch (InputMismatchException e) {
			System.out.println("Input Mismatch Exception: " + e.toString());
		}
		scnr.close();
	}
}
