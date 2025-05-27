public class OfferedCourse extends Course {
	// TODO: Declare private fields - instructorName, term, classTime
	private String instructorName;
	private String term;
	private String classTime;

	// TODO: Define mutator methods -
	// setInstructorName(), setTerm(), setClassTime()
	public String getInstructorName() {
		return instructorName;
	}

	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getClassTime() {
		return classTime;
	}

	public void setClassTime(String classTime) {
		this.classTime = classTime;
	}

//	public void printInfo() {
//		System.out.println("Course Information: ");
//		System.out.println("   Course Number: " + courseNumber);
//		System.out.println("   Course Title: " + courseTitle);
//	}

	// TODO: Define accessor methods -
	// getInstructorName(), getTerm(), getClassTime()

}