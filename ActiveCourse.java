import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author Hasib Arian
 * @StudentNumber 500712788
 *
 */
public class ActiveCourse extends Course
{
	private ArrayList<Student> 	students = new ArrayList<Student>(); 
	private String             	semester;
	private String				Studentid;
	private int					lectureStart;		//scheduler is gonna change these variables
	private int					lectureDuration;	//we neeed to have methods for these variables to be changed.
	private String 				lectureDay;
	
	
	/**
	 * constructor which sets semester as an empty string
	 * and super calls the inherited variables from the parent class (Course)
	 */
	public ActiveCourse() {
		
		super();
		semester = "";
	}
	
	
   /** constructor method which creates an active course object with parameters to initialize.
 * @param name
 * @param code
 * @param descr
 * @param fmt
 * @param semester
 * @param students
 */
public ActiveCourse(String name, String code, String descr, String fmt,String semester, ArrayList<Student> students)
   {
	   super(name, code,  descr , fmt); 
	   this.semester = semester;
	 
	   for (int i = 0; i < students.size(); i++) {
		   this.students.add(students.get(i)); 		
	
	   }
   }
public void setLectureStart(int time) {
	
	lectureStart = time;
}
public void setLectureDuration(int Duration) {
	
	lectureDuration = Duration;
}

public void setLectureDay(String day) {
	
	lectureDay = day;
}

   
   /** adds a student to an active course
 * @param id
 * @param name
 * @param courseCode
 */
public void addStudentToActiveCourse(String id, String name)
   {
			Student s = new Student(name, id);
			students.add(s);
				   	
   }
   
   /**removes student from active course
 * @param id
 * @param name
 * @param courseCode
 */
public void removeFromActiveCourse(String id, String courseCode)
   {
	   		for (int i = 0; i < students.size(); i ++) {
	   			if(students.get(i).getId().equals(id)) {
	   				students.remove(i);
	   	
	   			}
	   		}					   	
   }
   /**returns the semester of the active course
 * @return semester
 */
public String getSemester()
   {
	   return semester;
   }
   
  
   /**
 * prints the all the students in the active course
 */
public void printClassList()
   {
	   for (int i = 0; i < students.size(); i++) {			//prints all student objects
		   System.out.println(students.get(i));
	   }
	   
   }


   /**
 *  Prints the grade of each student in this course (along with name and student id)
 */
public void printGrades() //-------DONE--------
   {
	   
	  for (int i = 0; i < students.size(); i++) {
		  System.out.println(students.get(i).getId() + " "+ students.get(i).getName() +" " + students.get(i).getGrade());
	  }
   }
   
   /** Returns the student Id of the student in the active course as a STRING (not a number)
 * @param studentId
 * @return studentId: a string version of studentID
 */
public String getStudentById(String studentId)
   {
	   String Studentid = "";
	   for(int i = 0; i < students.size(); i++) {
		   if(students.get(i).getId().equals(studentId)){
			   Studentid = Studentid + students.get(i).getId();
		   }
	   }
	   return Studentid;
	   
   }

   /**
 * Returns the grade of the student as a double number along with the students Id and name!
 */
public void getGrades() 	
   {
	  // Search the student's list of credit courses to find the course code that matches this active course
	   for(int i = 0; i <students.size(); i++) {
		  
			 System.out.println(students.get(i).getId() + " "+ students.get(i).getName() + " "+ students.get(i).getGrade());
	   }

   }

public void setGradeAC(String studentId, double Grade) 	
{
	  // Search the student's list of credit courses to find the course code that matches this active course
	
	   
		
	for(int i = 0; i < students.size(); i++) {
		   if(students.get(i).getId().equals(studentId)){
			   students.get(i).setInitalGrade(Grade);
		   }
	   }

	   }


   
   /**
 * Returns a String containing the course information as well as the semester and the number of 
 * students enrolled in the course must override method in the superclass Course and 
 * use super class method getDescription()
 */
public String getDescription()
   {
	   return super.getDescription() +" " +semester + " Enrollment: "+ students.size() +"\n";
   }
    
   public String getShortDescription()
   {
	   return super.getDescription() +"\n";
   }
   

   /** Sort the students in the course by name using the Collections.sort()
    *  method with appropriate arguments
 */
public void sortByName()
   {
	   
	   Collections.sort(students, new NameComparator());
	   
   }
   
   
   /**
 * @author Hasib A
 *Class that implements the comparator interface allowing for student object comparison based on their names
 */
private class NameComparator implements Comparator<Student> //just  a class!
   {
	/**
	 * overriding the compare method from the comparator class!
	 * compares two objects names
	 * @param o1
	 * @param o2
	 *@return int
	 */
	@Override
	public int compare(Student o1, Student o2) {
		
		String a = o1.getName();
		String b = o2.getName();
		return a.compareTo(b);
		
		}
	}
   

   
  
   /**
 * Sort the students in the course by student id using the Collections.sort() method with appropriate arguments
 */
public void sortById()
   {
	   
	   Collections.sort(students, new IdComparator());
   }
   
   // Fill in the class so that this class implement the Comparator interface
   // This class is used to compare two Student objects based on student id
   /**
 * @author Hasib A
 *IdComparator class which uses the comparator to compare students student ID's
 */
private class IdComparator implements Comparator<Student>
   {
	   /**
	    * @param stuu1: student 1
	    * @param stuu2: student 2
	 *@return int value which tells us which objects student number is bigger/smaller/same
	 */
	public int compare(Student stuu1, Student stuu2) {
			
		   int id1 = Integer.parseInt(stuu1.getId());
		  int id2=Integer.parseInt(stuu2.getId());
		   if(id1 > id2){
				return 1;
			}
			if(id1< id2){
				return -1;
				
			}
			return 0; 
		}
   }
}
