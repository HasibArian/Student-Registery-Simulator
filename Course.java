/**
 * @author Hasib Arian
 * @StudentNumber 500712788
 *
 */
public class Course 
{
	private String code;
	private String name;
	private String description;
	private String format;
	   
	/**
	 * empty constructor which sets code, name , description, and format as an empty string
	 */
	public Course()
	{
	  this.code        = "";
	  this.name        = "";
	  this.description = "";
	  this.format      = "";
	}
	   
	/** another constructor method which takes in parameters and initializes code, name , description, and format as
	 * per parameters
	 * @param name
	 * @param code
	 * @param descr
	 * @param fmt
	 */
	public Course(String name, String code, String descr, String fmt)
	{
	  this.code        = code;
	  this.name        = name;
	  this.description = descr;
	  this.format      = fmt;
	}
	   
	/** returns a string of the course code
	 * @return code: the code of the the course as a string
	 */
	public String getCode()
	{
	   return code;
	}
	   
	/**returns a string of the course name
	 * @return name: the name of the course
	 */
	public String getName()
	{
	  return name;
	}
	   
	/**returns a string of the course format
	 * @return format
	 */
	public String getFormat()
	{
	  return format;
	}
	   
	/**returns description of the course which includes the code, name, description and course format
	 * @return code + name + description + format
	 */
	public String getDescription()
	{
	  return code +" - " + name + "\n" + description + "\n" + format;
	}
	
	 /** returns information of course in the form of code and name
	 * @return code + name
	 */
	public String getInfo()
	 {
	   return code +" - " + name;
	 }
	 
	
	 /**static method to convert numeric score to letter grade string e.g. 91 --> "A+"
	 * @param score
	 * @return String 
	 */
	public static String convertNumericGrade(double score)
	 {
		 if (score >= 90) {
	    		return "A+" ;
	    	}
	    		else if (score >= 85) {
	    		return "A" ;
	    		}
	    		else if (score >= 80) {
	    		return "A-" ;
	    		}
	    		else if (score >= 77) {
	    		return "B+";
	    		}
	    		else if (score >= 73) {
	    		return "B" ;
	    		}
	    		else if (score >= 70) {
	    		return "B-" ;
	    		}
	    		else if (score >= 67) {
	    		return "C+";
	    		}
	    		else if (score >= 63) {
	    		return "C" ;
	    		}
	    		else if (score >= 60) {
	    		return "C-" ;
	    		}
	    		else if (score>= 57) {
	        	return "D+";
	    		}
	        	else if (score >= 53) {
	        	return "D" ;
	        	}
	        	else if (score >= 50) {
	        	return "D-" ;
	        	}
	        	else if (score < 50) {
	            	return "F" ;
	        	}
		 
		 
		 
	        	else {
	        		return "F";
	        	}
	 }
	 
}
