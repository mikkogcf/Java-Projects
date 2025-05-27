import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class LabProgram {
	public static void main(String[] args) throws IOException {
		Scanner scnr = new Scanner(System.in);

		/* TODO: Declare any necessary variables here. */
		File fileName = new File(scnr.next());
		Scanner inFile = new Scanner(fileName);
		scnr.close();
		PrintWriter out = new PrintWriter("report.txt");
		double mid1 = 0.0;
		double mid2 = 0.0;
		double finals = 0.0;
		int studentCount = 0;
		/* TODO: Read a file name from the user and read the tsv file here. */
		while (inFile.hasNextLine()) {
			double avg = 0.0;
			int avgCount = 0;
			studentCount++;
			String line = inFile.nextLine();
			String[] data = line.split("\t");
			for (int i = 2; i < data.length; i++) {
				avg += Double.valueOf(data[i]);
				avgCount++;
			}
			avg = avg / avgCount;
			if (avg >= 90) {
				out.println(line + "\tA");
			} else if (avg < 90 && avg >= 80) {
				out.println(line + "\tB");
			} else if (avg < 80 && avg >= 70) {
				out.println(line + "\tC");
			} else if (avg < 70 && avg >= 60) {
				out.println(line + "\tD");
			} else {
				out.println(line + "\tF");
			}
			mid1 += Double.valueOf(data[2]);
			mid2 += Double.valueOf(data[3]);
			finals += Double.valueOf(data[4]);
		}
		mid1 = mid1 / studentCount;
		mid2 = mid2 / studentCount;
		finals = finals / studentCount;
		out.printf("\nAverages: Midterm1 %.2f, Midterm2 %.2f, Final %.2f\n", mid1, mid2, finals);
		inFile.close();
		out.close();

		/*
		 * TODO: Compute student grades and exam averages, then output results to a text
		 * file here.
		 */

	}
}