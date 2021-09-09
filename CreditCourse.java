/**
 * @author Hasib Arian
 * @StudentNumber 500712788
 *
 */
public class CreditCourse extends Course
{

	private String semester;
	private double grade;
	private String tcode;
	private boolean active;
	
	/**Constructor method for the class CreditCourse
	 * @param name
	 * @param code
	 * @param descr
	 * @param fmt
	 * @param semester
	 * @param grade
	 */
	public CreditCourse(String name, String code, String descr, String fmt, String semester, double grade)
	{
	
	super(name, code,  descr, fmt); 
	this.semester = semester;
	this.grade = grade;
	tcode = code;
	active =true ;
	}
	
	/**Checking if the credit course is active (currently in session)
	 * @return boolean
	 */
	public boolean getActive() 
	{
		
		return active;
	}
	
	/**Method which sets the grade of the credit course to a specified Grade
	 * @param Grade
	 */
	public void setGrade(double Grade)
	{
	
		this.grade = Grade;
		
	}
	
	/**returns letter grade which represents the numeric grade of the student in the course.
	 * @return number: string
	 */
	public String returnGrade() 
	
	{
		
		String number = convertNumericGrade(this.grade);
		
		return number;
		
	}
	
	/**returns the code of the credit course as tcode.
	 * @return tcode: string
	 */
	public String getCourseCode()		
	{
		 return tcode;
	}
	
	/**
	 * sets the credit course as active
	 */
	public void setActive()		
	{
		 active = true;
	}
	
	/**
	 * sets credit course as inactive
	 */
	public void setInactive()	
	{
		 active = false;
	}
	
	/**returns information of a course example: CPS209 ComputerScience java + W2020
	 * @return 
	 */
	public String displayCourseInfo()	
	{
		//returns example: CPS209 ComputerScience java + W2020 + 45
		return super.getCode() + " " + super.getName() + " " + semester + " " ;
	}
	
}