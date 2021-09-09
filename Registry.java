import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author Hasib Arian
 * @StudentNumber 500712788
 *
 */
public class Registry
{
  // private ArrayList<Student>    students = new ArrayList<Student>();			//an array filled with stduents
  // private ArrayList<ActiveCourse> courses  = new ArrayList<ActiveCourse>();		//an array filled with active courses
   private Map<String, Student> students = new TreeMap<String ,Student>();
   private Map<String, ActiveCourse> courses = new TreeMap<String ,ActiveCourse>();
   private boolean test;
   
   /**
 * constructor method which intializes all the students
 * and active courses
 */
public Registry()
   {
		test = false;
	try {
			
		readFile();
	}
	
	catch(IOException e) {
		test = true;
		System.out.println("students.txt File Not Found ");
	}
	catch(BadFileFormatException r) {
		test = true;
		System.out.println("Bad File Format for students.txt");
	}
	
		//now to add some students in some courses!
		
//	  // in A2 we will read from a file
//	   Student s1 = new Student("JohnOliver", "34562");			//this is creating a [S]tudent object called s1
//	   Student s2 = new Student("HarryWindsor", "38467");		
//	   Student s3 = new Student("SophieBrown", "98345");
//	   Student s4 = new Student("FaisalQuereshi", "57643");
//	   Student s5 = new Student("GenghisKhan", "25347");
//	   Student s6 = new Student("SherryTu", "46532");
  
		ArrayList<Student> list = new ArrayList<Student>();
//			
	   // Add some active courses with students
	   String courseName = "Computer Science II";
	   String courseCode = "CPS209";
	   String descr = "Learn how to write complex programs!";
	   String format = "3Lec 2Lab";
	   //putting courses in treemap, key = courseName, and object ActiveCourse
	   courses.put(courseCode,new ActiveCourse(courseName,courseCode,descr,format,"W2020",list)); //in active course he add a new active course
	   // Add course to student list of courses

	   // CPS511
	   list.clear();
	   courseName = "Computer Graphics";
	   courseCode = "CPS511";
	   descr = "Learn how to write cool graphics programs";
	   format = "3Lec";
	   //s1, s5 ,s6
	   courses.put(courseCode,new ActiveCourse(courseName,courseCode,descr,format,"F2020",list));

	   
//	   // CPS643
	   list.clear();
	   courseName = "Virtual Reality";
	   courseCode = "CPS643";
	   descr = "Learn how to write extremely cool virtual reality programs";
	   format = "3Lec 2Lab";
	   courses.put(courseCode,new ActiveCourse(courseName,courseCode,descr,format,"W2020",list));
	  
	   
	   // CPS706
	   	list.clear();
	     courseName = "Computer Networks";
	     courseCode = "CPS706";
	     descr = "Learn about Computer Networking";
	     format = "3Lec 1Lab";
	     courses.put(courseCode,new ActiveCourse(courseName,courseCode,descr,format,"W2020",list));
	     
	     // CPS616]
	     list.clear();
	     courseName = "Algorithms";
	     courseCode = "CPS616";
	     descr = "Learn about Algorithms";
	     format = "3Lec 1Lab";
	     courses.put(courseCode,new ActiveCourse(courseName,courseCode,descr,format,"W2020",list));
  }
   
public ActiveCourse returnActiveCourses(String courseCode) {
	
	return courses.get(courseCode);
}

public boolean readFile()throws IOException, BadFileFormatException  {  //will read the text file one line at a time and grab both name and id
//then it sends the name and ID to registry to then be implemented as a student object.	
	
	if(!new File("students.txt").exists()) {
		
		throw new IOException("students.txt File Not Found");
	}
	
	else {
		
		FileReader read = new FileReader("students.txt");
			Scanner scans = new Scanner(read);
			
			while(scans.hasNextLine()) {
				
				if(!scans.hasNext()) {
					throw new BadFileFormatException("");
				}
				
				String Name = scans.next();
				
				if(!scans.hasNext()) {
					throw new BadFileFormatException("");
				}
				String Id = scans.next();
				Student s1 = new Student(Name, Id);
				
				if(!isStringOnlyAlphabet(Name)) {
					
					throw new BadFileFormatException("");
				}
				
				if(!isNumeric(Id)) {
					
					throw new BadFileFormatException("");
				}
				students.put(Id,s1);
			}
			scans.close();
			return true;
	}


}



/**Adds new student to the registry class students Array List
 * @param name, string of student Name
 * @param id, string of student Id 
 * @return boolean
 */
public boolean addNewStudent(String name, String id) //this method calls onto student class to initialize a new Student
   {
			//check if student is already in registry
			//if not, add them to registry
			//TreeSet, if u want to update a key, u must use the keyword Put
	
	if(!students.containsKey(id)) {
		//FIRST CREATE STUDENT OBJECT, THEN PUT IT IN THE MAP
		Student studentObj = new Student(name,id);
		students.put(id, studentObj); //students <string, Student>
		return true;
	}
	else{
		System.out.print("Student "+ name + " is already registered");
		return false;
	}
   }

   
   /**
    * Removes student from registry's students() arraylist
 * @param name, string of student Name
 * @param id, string of student Id
 * @return boolean
 */
public boolean removeStudent(String id)
   {
	
	if(students.containsKey(id)) {
		students.remove(id);
		return true;
	}
	else {
		System.out.print("Student with an id of "+id+" is not in the registry.");
		return false;
	}

   }
/**
* prints all students ID & NAME in the registry!
*/
public void printAllStudents() // --------DONE-------------- ("L")
   {
	  //get the keyset of all students and store it in keySetHolder
	
	Set<String> keySetHolder = students.keySet();
	for(String key : keySetHolder) {
		Student value = students.get(key);
		System.out.println(value);
	}
	
   }
   
 
 /**Given a studentId and a course code, add student to the active course. This method loops through Registry's student
  * and course arraylist to check if courses and student exist. it then checks if student has taken course before and then
  * finally adds the student in registrys active course specified, and adds the course to students creditcourses.
  * it initializes a grade of zero for the student added.
 * @param studentId
 * @param courseCode
 */
public void addCourse(String studentId, String courseCode) //------------------DONE----------------("ADDC")
   {
	courseCode=courseCode.toUpperCase();
	if(students.containsKey(studentId)) { //check if student in reg
		 if(courses.containsKey(courseCode)) { //check if course is active
			 String studentname=students.get(studentId).getName();
			 courses.get(courseCode).addStudentToActiveCourse(studentId,studentname);					//now to add the student into the active course
			 String courseName= courses.get(courseCode).getName();
			 String descr= courses.get(courseCode).getShortDescription();
			 String format = courses.get(courseCode).getFormat();
			 String sem = courses.get(courseCode).getSemester();
			 double grade = 0.0;
			 students.get(studentId).addCourse(courseName,courseCode, descr, format, sem, grade);
		 }
	 }
	   
   }	   
  
  
   /**
    * Given a studentId and a course code, drop student from the active course by:
    * looping through students array list and finding and saving students index in the list in variable studentIndex
    * then looping through the courses array list and checking if the course is active
    * and then invoking method from active course class to drop the course
    * and effectively removing the course from the students creditclass list!
 * @param studentId
 * @param courseCode
 */
public void dropCourse(String studentId, String courseCode)//---------DONE----------------("DROPC")
   {
	   // Find the active course
	   // Find the student in the list of students for this course
	   // If student found:
	   //   remove the student from the active course
	   //   remove the credit course from the student's list of credit courses
	  courseCode=courseCode.toUpperCase();
	if(courses.containsKey(courseCode)) {
		if(courses.get(courseCode).getStudentById(studentId).equals(studentId)) {
			students.get(studentId).removeActiveCourse(courseCode);					//remove the course from students list of active courses
			courses.get(courseCode).removeFromActiveCourse(studentId, courseCode); //remove the student from the active course
		}
	}
   }
   
/**
* Print all active courses in the session currently
*/
public void printActiveCourses()//----------DONE------("PAC")
   {
	Set<String> keySet = courses.keySet();
	
	for(String key: keySet) {
		ActiveCourse value = courses.get(key);
		String description = value.getDescription();
		System.out.println(description);
	}
   }

   /**
    * Print the list of students in an active course when a course code is provided.
    * Accomplishes this by keeping track of the index the course code exists in the array list of courses
    * creates an object and calls the method printClassList of class ActiveCourses by using the object.
 * @param courseCode, a string representing the coursecode, eg CPS209
 */
public void printClassList(String courseCode) 	//--------DONE----("PCL")
   {
	
	courseCode = courseCode.toUpperCase();
	courses.get(courseCode).printClassList();
	
   }
 /**Given a course code, find course and sort class list by student name
 * @param courseCode
 */
public void sortCourseByName(String courseCode)
   {
	
	courseCode=courseCode.toUpperCase();
	
	if(courses.containsKey(courseCode)) {
		courses.get(courseCode).sortByName();
	}
   }
   
 
   /** Given a course code, find course and sort class list by student name
 * @param courseCode: a String.
 */
public void sortCourseById(String courseCode)
   {
	courseCode=courseCode.toUpperCase();
	
	if(courses.containsKey(courseCode)) {
		courses.get(courseCode).sortById();
	}
   }
   
   /**
    *  Given a course code, finds the active course and print student names and grades.
    *  Accomplishes by keeping an index of the course and then creating an active course object.
    *  PrintGrades() method is used to then print the grades of all students in that class.
 * @param courseCode
 */
public void printGrades(String courseCode) //	("--PGR CourseCode--")
{
//we need to check if the course exists tbh
	
	courseCode = courseCode.toUpperCase();
	if(courses.containsKey(courseCode)) {
		
		courses.get(courseCode).printGrades();  //we got the active course object, now we need to print all it's students
			
		}
	else{
			System.out.print(courseCode+ " is not an active class at this time");
	}

   }
   
   /**
    * Given a studentId, print all active courses of student
    * Accomplished by looping through courses and 
 * @param studentId
 */
public void printStudentCourses(String studentId) //--------DONE------------("PSC")
{
	
	if(students.containsKey(studentId)) {  //if student exists in the treemap
		students.get(studentId).printActiveCourses();		//get the student object and print all it's active courses.
	}
	
	  }
   
public boolean returnTest()
{
	return test;
}


   /**Given a studentId, print all completed courses and grades of student
 * @param studentId
 */
public void printStudentTranscript(String studentId) //-------DONE--------- ("PST")
   {
	if(students.containsKey(studentId)) {  //if student exists in the treemap
		students.get(studentId).printTranscript();		//get the student object and print all it's active courses.
	}
	
	  }
   
 
   /**Given a course code, student id and numeric grade, it set the final grade of the student
    * and also sets the the course as inactive in the students credit courses.
 * @param courseCode
 * @param studentId
 * @param grade
 */
public void setFinalGrade(String courseCode, String studentId, double grade)//--DONE--("-SFG courseCode, studentId, dubGrade-")
   {
	   // find the active course
	   // If found, find the student in class list
	   // then search student credit course list in student object and find course
	   // set the grade in credit course and set credit course inactive
	   
	   courseCode=courseCode.toUpperCase();
	if(courses.containsKey(courseCode)) {
		if(students.containsKey(studentId)) {
			courses.get(courseCode).setGradeAC(studentId, grade); 	
			students.get(studentId).updateGrade(grade, courseCode);
			students.get(studentId).setInactive(courseCode);
			
		}
	}
	
   }
public static boolean isNumeric(String str)
{
	 boolean numeric = true;

   try {
       Double num = Double.parseDouble(str);
   } catch (NumberFormatException e) {
       numeric = false;
   }
   
   if (numeric) {
  	 return true;
   }
   else {
  	 return false;
   }
	
}
private static boolean isStringOnlyAlphabet(String str) 
{ 
	
	  if (str.contains("0")) {
		  return false;
	  }
	  if (str.contains("1")) {
		  return false;
	  }
	  if (str.contains("2")) {
		  return false;
	  }
	  if (str.contains("3")) {
		  return false;
	  }
	  if (str.contains("4")) {
		  return false;
	  }
	  if (str.contains("5")) {
		  return false;
	  }
	  if (str.contains("6")) {
		  return false;
	  }
	  if (str.contains("7")) {
		  return false;
	  }
	  if (str.contains("8")) {
		  return false;
	  }
	  if (str.contains("9")) {
		  return false;
	  }
	 return true;
} 
}