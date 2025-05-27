import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class LabProgram3 {
	public static void main(String[] args) throws IOException {
		Scanner scnr = new Scanner(System.in);
		String fileName = scnr.next();
		Scanner inFile = new Scanner(new File(fileName));
		scnr.close();
		String temp = "";
		while (inFile.hasNextLine()) {
			String[] line = inFile.nextLine().split(",");

			if (line[1].length() > 44) {
				line[1] = line[1].substring(0, 44);
			}
			if (temp == "") {
				System.out.printf("%-44s | %5s | %s", line[1], line[2], line[0]);
				temp = line[1];
			} else if (temp.equals(line[1])) {
				System.out.printf(" %s", line[0]);
			} else {
				System.out.printf("\n%-44s | %5s | %s", line[1], line[2], line[0]);
				temp = line[1];
			}
		}
		System.out.println();
		inFile.close();
	}
}
