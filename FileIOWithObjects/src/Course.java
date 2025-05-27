
public class Course {
	
	//variables
	private String code;
	private String name;
	private int credits;
	
	//methods
	
	//CONSTRUCTORS
	public Course()
	{
		code = "";
		// dont have this! : code = null;
		name = "";
		credits = 0;
	}
	
	public Course(String code, String name, int credits)
	{
		this.code = code;
		this.name = name;
		this.credits = credits;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}
	
	
	public String toString()
	{
		return "Code: " + code + "; Name: " 
				+ name + "; Credits: " + credits;
	}
	
	
	
	
	
	
	

}
