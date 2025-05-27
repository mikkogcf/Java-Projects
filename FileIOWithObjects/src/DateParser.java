import java.util.Scanner;

public class DateParser {
	public static int getMonthAsInt(String monthString) {
		int monthInt;

		// Java switch/case statement
		switch (monthString) {
		case "January":
			monthInt = 1;
			break;
		case "February":
			monthInt = 2;
			break;
		case "March":
			monthInt = 3;
			break;
		case "April":
			monthInt = 4;
			break;
		case "May":
			monthInt = 5;
			break;
		case "June":
			monthInt = 6;
			break;
		case "July":
			monthInt = 7;
			break;
		case "August":
			monthInt = 8;
			break;
		case "September":
			monthInt = 9;
			break;
		case "October":
			monthInt = 10;
			break;
		case "November":
			monthInt = 11;
			break;
		case "December":
			monthInt = 12;
			break;
		default:
			monthInt = 0;
		}

		return monthInt;
	}

	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		int day = 0;
		int month = 0;
		int year = 0;
		int index = 0;
		while (scnr.hasNextLine()) {

			String input = scnr.nextLine();

			index = input.indexOf(" ");
			if (index == -1) {
				continue;
			} else {
				String extractedStr = input.substring(0, index);
				String newString = input.substring(index + 1, input.length());
				if (getMonthAsInt(extractedStr) == 0) {
					continue;
				} else {
					month = getMonthAsInt(extractedStr);
					index = newString.indexOf(" ");
					extractedStr = newString.substring(0, index);
					// System.out.println(extractedStr);
					newString = newString.substring(index + 1, newString.length());
					// System.out.println(newString);
					if (!extractedStr.contains(",")) {
						continue;
					} else {
						extractedStr = extractedStr.substring(0, extractedStr.length() - 1);
						day = Integer.valueOf(extractedStr);
						year = Integer.valueOf(newString);
					}
				}
			}
			System.out.println(month + "/" + day + "/" + year);
		}
		scnr.close();
		// TODO: Read dates from input, parse the dates to find the ones
		// in the correct format, and output in m/d/yyyy format

	}
}
