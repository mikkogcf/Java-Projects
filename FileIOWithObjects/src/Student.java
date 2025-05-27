import java.util.HashMap;

// TODO: Auto-generated Javadoc
/**
 * The Class Student.
 */
public class Student {

	/** The num students. */
	public static int numStudents = 0;
	
	/** The Constant MAX_CREDITS. */
	public static final int MAX_CREDITS = 12;
	
	/** The gpa. */
	private double gpa;
	
	/** The name. */
	private String name;
	
	/** The classes. */
	private HashMap<Course, Integer> classes;
	
	/**
	 * Instantiates a new student.
	 */
	public Student()
	{
		gpa = 0;
		name = "";
		classes = new HashMap<Course, Integer>();
	}
	
	/**
	 * Instantiates a new student.
	 *
	 * @param name the name
	 */
	public Student(String name)
	{
		gpa = 0;
		this.name = name;
		classes = new HashMap<Course, Integer>();
	}
	
	/**
	 * Adds a particular class to the student's list.
	 *
	 * @param c the c
	 */
	public void register(Course c)
	{
		classes.put(c, 4);
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Sets the grade.
	 *
	 * @param c the c
	 * @param grade the grade
	 */
	public void setGrade(Course c, int grade)
	{
		classes.replace(c, grade);
	}
	
	/**
	 * Gets the gpa.
	 *
	 * @return the gpa
	 */
	public double getGPA()
	{
		calculateGPA();
		return gpa;
	}
	
	/**
	 * Gets the classes.
	 *
	 * @return the classes
	 */
	public HashMap<Course, Integer> getClasses()
	{
		return classes;
	}
	
	/**
	 * Calculate GPA.
	 */
	private void calculateGPA()
	{
		//for every course registered,
			//add the grade to a sum
		
		int sum = 0;
		int num = 0;
		for(Course c : classes.keySet())
		{
			sum += classes.get(c);
			num++;
		}
		
		//take the average: sum / num
		gpa = sum / (double)num;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString()
	{
		return name + " " + getGPA();
	}
	
	/**
	 * Determine overload status.
	 *
	 * @param s the s
	 * @return true, if successful
	 */
	public static boolean determineOverloadStatus(Student s)
	{
		return false;
	}
	
	
	
	
	
	
	
}
