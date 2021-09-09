import java.util.ArrayList;

// Make class Student implement the Comparable interface
// Two student objects should be compared by their name
/**
 * @author Hasib Arian
 * @StudentNumber 500712788
 *
 */
public class Student
{
  private String name;
  private String id;
  private double initalGrade;
  public  ArrayList<CreditCourse> courses;		//students list of credit courses
  
  
  /**Constructor Class which sets inital grade to zero and creates a courses arraylist
 * @param name
 * @param id
 */
public Student(String name, String id)
  {
	 this.name = name;
	 this.id   = id;
	 initalGrade = 0.0;
	 courses   = new ArrayList<CreditCourse>();

  }
  
  /**returns id of student as a string
 * @return id
 */
public String getId()
  {
	  return id;
  }
  
  /** returns name of student as a string
 * @return name
 */
public String getName()
  {
	  return name;
  }
  

  /**adds a credit course to the list of courses for the student
 * @param courseName
 * @param courseCode
 * @param descr
 * @param format
 * @param sem
 * @param grade
 */
public void addCourse(String courseName, String courseCode, String descr, String format,String sem, double grade)
  {
	
	  CreditCourse obj = new CreditCourse(courseName, courseCode, descr, format, sem, grade);
	  obj.setActive();
	  courses.add(obj);
  }
  

  /**
 * prints a students transcript of all courses they've taken and completed. Active courses are not printed.
 * printed in format: course code + course name + semester + grade
 */
public void printTranscript()
  {
	  for (int i = 0; i < courses.size(); i++) {
		  if(courses.get(i).getActive() == false) {
		  	System.out.println(courses.get(i).displayCourseInfo() + "Grade " + courses.get(i).returnGrade());
	  	}
	  }
  }
  
  /**
 * checks each students credit course list to see if the students has ever taken 
 * or is currently in class of courseCode
 * @param courseCode
 * @return boolean
 */
public boolean Coursetaken(String courseCode) {
	  
	  for(int i = 0; i < courses.size(); i++) { //looping through each CC object
		  
		  if(courses.get(i).getCode().equals(courseCode)) { //check each CC objects course code
				  return true;
			  }
			  
		  }
  
	  return false;
  }
		 
    
  public double getGrade()
  {
	  return initalGrade;
  }
  public void setInitalGrade(double grade)
  {
	  initalGrade = grade;
  }
  
  public void setInactive(String courseCode)
  {
	  for(int i = 0; i < courses.size(); i++) {
		  if(courses.get(i).getCode().equalsIgnoreCase(courseCode)) {
			  courses.get(i).setInactive();
		  }
	  }
  }
  
  
  /**
   * updates grade of student in the active course they're in
 * @param grade
 * @param courseCode
 */
public void updateGrade(double grade, String courseCode)
  {
	  for(int i = 0; i < courses.size(); i ++) {
		  if(courses.get(i).getCode().equalsIgnoreCase(courseCode)) {
			  courses.get(i).setGrade(grade);								//setting the final grade of the student
		  }
	  }
  }

  /**
 *  Prints all active courses this student is enrolled in see variable active in class CreditCourse
 */
public void printActiveCourses()
  {
	for (int i = 0; i < courses.size(); i ++) {
		if(courses.get(i).getActive() == true) {
		  	System.out.println(courses.get(i).displayCourseInfo());
		}
		
  }
  
  }
  /**Drop a course (given by courseCode) 
   * Finds the credit course in courses arraylist above and 
   * removes it only remove it if it is an active course
 * @param courseCode
 */
public void removeActiveCourse(String courseCode)
  {
	  for(int i = 0; i < courses.size(); i++) {
		  
		  if(courses.get(i).getCode().equals(courseCode)) {  //check course code if its equal
			  courses.remove(i);								//removes that course
		  }
	  } 
  }
  
  /**
 *over-rides the toString method to return the ID and Name of student
 */
public String toString()
  {
	  return "Student ID: " + id + " Name: " + name;
  }
}

 