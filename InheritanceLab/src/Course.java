public class Course {
	// TODO: Declare private fields - courseNumber, courseTitle
	private String courseNumber;
	private String courseTitle;

	// TODO: Define mutator methods -
	// setCourseNumber(), setCourseTitle()
	public String getCourseNumber() {
		return courseNumber;
	}

	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}

	public String getCourseTitle() {
		return courseTitle;
	}

	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}

	public void printInfo() {
		System.out.println("Course Information: ");
		System.out.println("   Course Number: " + courseNumber);
		System.out.println("   Course Title: " + courseTitle);
	}

	// TODO: Define accessor methods -
	// getCourseNumber(), getCourseTitle()

	// TODO: Define printInfo()

}
