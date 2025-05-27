import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class LabProgram2 {
	public static void main(String[] args) throws IOException {
		Scanner scnr = new Scanner(System.in);

		String inputFileName = scnr.next();
		File f = new File(inputFileName);

		Scanner inFile = new Scanner(f);
		String contents = "";
		while (inFile.hasNextLine()) {
			String fileName = inFile.nextLine();

			if (fileName.contains("_photo.jpg")) {
				int index = fileName.indexOf("_photo.jpg");
				fileName = fileName.substring(0, index) + "_info.txt";
				contents = contents + fileName + "\n";
			}
		}
		System.out.println(contents);
		scnr.close();
		inFile.close();
	}
}
