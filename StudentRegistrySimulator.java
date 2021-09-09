import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * @author Hasib Arian
 * @StudentNumber 500712788
 *
 */
public class StudentRegistrySimulator 

{

  public static void main(String[] args)
  
  {
	  
	 TreeMap<String, ActiveCourse> courses = new TreeMap<String ,ActiveCourse>();
	 
//	 try {
//		  Registry testRegistry = new Registry();
//		
//	  }
//	 
//	 catch(NullPointerException e) {
//		System.out.println();
//		return;
//	 }

	 
	 
	 
	 	Registry registry = new Registry(); //an object named registry in class Registry.
	 	
	 	if(registry.returnTest()) {
	 		return;
	 	}
	  
	  courses.put("CPS209", registry.returnActiveCourses("CPS209"));
	  courses.put("CPS511", registry.returnActiveCourses("CPS511"));
	  courses.put("CPS643", registry.returnActiveCourses("CPS643"));
	  courses.put("CPS616", registry.returnActiveCourses("CPS643"));
	  courses.put("CPS706", registry.returnActiveCourses("CPS643"));
	  Scheduler schedule = new Scheduler(courses);  //this is where i'm creating a new SCHEDULER object! when we update the scheduler
	  												//with its courses, we would most likely need to use a try and catch block when updating
	  												//its class times and such...
	  												
	  
	  Scanner scanner = new Scanner(System.in);
	  System.out.print(">");
	  
	  while (scanner.hasNextLine())
	  {
		  String inputLine = scanner.nextLine();
		  if (inputLine == null || inputLine.equals("")) continue;
		  
		  Scanner commandLine = new Scanner(inputLine);
		  String command = commandLine.next();
		  
		  if (command == null || command.equals("")) continue;
		  
		  else if (command.equalsIgnoreCase("L") || command.equalsIgnoreCase("LIST"))
		  {
			  registry.printAllStudents();
		  }
		  else if (command.equalsIgnoreCase("Q") || command.equalsIgnoreCase("QUIT"))
			  
			  return;
		  
		  else if (command.equalsIgnoreCase("REG"))  //-------DONE------------
		  {
			  
		
			  String name = commandLine.next();
			  String id =  commandLine.next();
			  
		if (!isStringOnlyAlphabet(name) && !isNumeric(id)) { 
				  System.out.println("Invalid Character(s) in Id "+ id);
				  System.out.print("Invalid Character(s) in Name "+ name);
			  }
			
		if (isStringOnlyAlphabet(name) && isNumeric(id)) {

					 registry.addNewStudent(name,id);
				  }
		if(!isNumeric(id) && isStringOnlyAlphabet(name)) {
					  System.out.println("Invalid Character(s) in Id "+ id);
				  }
			  
			 if(!isStringOnlyAlphabet(name) && isNumeric(id) ){
				  System.out.println("Invalid Character(s) in Name "+ name);
			  }
		  }
		  
		  
		  else if (command.equalsIgnoreCase("DEL"))		//-------DONE------------
		  {
			  // delete a student from registry
		
			  String id =  commandLine.next();
			 
				  if (isNumeric(id)) {
					 registry.removeStudent(id);
				  }
				  else {
					  System.out.println("Invalid Character(s) in Id "+ id);
				  }
			  
		  }
		  
		  else if (command.equalsIgnoreCase("ADDC"))
		  {
		
			  String id = commandLine.next();
			  String course = commandLine.next();
			 
			  if (isNumeric(id)) {
				  registry.addCourse(id, course);
				  }
			  
			  
			  
		  }
		  else if (command.equalsIgnoreCase("DROPC"))
		  {
			  
			  String id = commandLine.next();
			  String course = commandLine.next();
			  if (isNumeric(id)) {
				  registry.dropCourse(id, course);
				  }
			  
		  }
		  else if (command.equalsIgnoreCase("PAC"))			//-------DONE------------
		  {
		
			  registry.printActiveCourses();
			  
		  }		  
		  else if (command.equalsIgnoreCase("PCL"))					//-------DONE------------
		  {
			
			  String courseCode = commandLine.next();
			 
			  
			  registry.printClassList(courseCode);
			  
			  
		  }
		  else if (command.equalsIgnoreCase("PGR"))
		  {
			 
			  String courseCode = commandLine.next();
			  registry.printGrades(courseCode);
		
		  }
		  else if (command.equalsIgnoreCase("PSC"))
		  {
			
			  String StudentId = commandLine.next();
			  if (isNumeric(StudentId)) {
			  registry.printStudentCourses(StudentId);
			  }
		  }
		  else if (command.equalsIgnoreCase("PST"))
		  {
			
			  String studentId = commandLine.next();
			  
			  if (isNumeric(studentId)) {
			  registry.printStudentTranscript(studentId);
			  }
		  }
		  else if (command.equalsIgnoreCase("SFG"))
		  {
		
			  String courseCode = commandLine.next();
			  String studentId = commandLine.next();
			  String grade = commandLine.next();
			  double dGrade = Double.parseDouble(grade);
			  
			  if (isNumeric(studentId)) {
			  registry.setFinalGrade(courseCode, studentId, dGrade);
			  }
		  }
		  		  
//----------------------------------------------------------------------------------------------------------------//		  
		  

		  else if(command.equalsIgnoreCase("SCH")) {
			 
			  String courseCode = commandLine.next();
			  String day = commandLine.next();
			  
			  if(isNumeric(day)) {
				  		
				  int day1 = Integer.parseInt(day);
				  
				  try { 
					  schedule.setAutomaticDayAndTime(courseCode, day1);
					 }
					 catch(InvalidDurationException x) {
						 System.out.print("Invalid Lecture Duration");
					 }
					 catch(UnknownCourseException p) {
						 System.out.print("Unknown Course: " + courseCode);
					 }
					  
				  }
			  else if (!isNumeric(day)) {
				  String startTime = commandLine.next();
				  String LectureDur = commandLine.next();
				  
			
					 try { 
						 int LectureDurInt= Integer.parseInt(LectureDur);
						 int startTimeint = Integer.parseInt(startTime);
						 schedule.setDayAndTime(courseCode, day, startTimeint,LectureDurInt);
					 }
					 catch (UnknownCourseException unknowncourse) {
						 System.out.println(unknowncourse.getMessage());
					 }
					 catch (InvalidDayException invalidday ) {
						 System.out.println(invalidday.getMessage());
					 }
					 catch(InvalidTimeException invalidStart) {
						 System.out.println(invalidStart.getMessage());
					 }
					 catch(InvalidDurationException invalidDur) {
						 System.out.println(invalidDur.getMessage());
					 }
					 catch (LectureTimeCollisionException coll) {
						 System.out.println(coll.getMessage());
					 }
					 
					 
			  }
				  
				  
				  
			  }

	  
    
		  
		  else if(command.equalsIgnoreCase("PSCH")) {
			  
			  schedule.printSchedule();
		  }
		  
		  
		  else if(command.equalsIgnoreCase("CSCH")) {
			  
			  String courseCode = commandLine.next();
			  
			  schedule.clearSchedule(courseCode);
			  
		  }
		  
		  else if(command.equalsIgnoreCase("ASCH")) {
			  
			  String courseCode = commandLine.next();
			  String duration = commandLine.next();

			  int durations= Integer.parseInt(duration);
			 try { 
			  schedule.setAutomaticDayAndTime(courseCode, durations);
			 }
			 catch(InvalidDurationException x) {
				 System.out.print("Invalid Lecture Duration");
			 }
			 catch(UnknownCourseException p) {
				 System.out.print("Unknown Course: " + courseCode);
			 }
		  }
		  

		  
//----------------------------------------------------------------------------------------------------------------//			  
		  
		  
		  else if (command.equalsIgnoreCase("SCN"))
		  {
	
		
			 String courseCode = commandLine.next();
			  
			 registry.sortCourseByName(courseCode);
			  
			  
		  }
		  else if (command.equalsIgnoreCase("SCI"))
		  {
			
			  String courseCode = commandLine.next();
			  registry.sortCourseById(courseCode);
		  }
		  System.out.print("\n>");
	  }
  }
  
  
  
  /** method that checks if theres only alphabets in a string.
 * @param str
 * @return
 */
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
  
  /** uses catch to set a boolean to true or false
 * @param str
 * @return
 */
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
}
