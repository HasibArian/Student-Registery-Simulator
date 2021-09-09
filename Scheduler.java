import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;

public class Scheduler 
{
    // In main() after you create a Registry object, create a Scheduler object and pass in the students ArrayList/TreeMap
	// If you do not want to try using a Map then uncomment
	// the line below and comment out the TreeMap line
	
	TreeMap<String,Integer> DaysOfWeek = new TreeMap<String, Integer>();
	TreeMap<Integer,Integer> StartTimes = new TreeMap<Integer, Integer>();
	TreeMap<Integer,Integer> LectureHours = new TreeMap<Integer, Integer>();
	TreeMap<String,ActiveCourse> schedule;
	
	private TreeMap<String,Integer> totalCourseLength = new TreeMap<String, Integer>();
	
	private String[][] timeTable;
		
	/** A constructor method that takes a reference to a TreeMap of active courses.
	 *  In the main method of class StudentRegistrySimulator, 
	 *  after you have created a Registry object, you should create a Scheduler object and pass in the courses 
	 *  (formerly an ArrayList of ActiveCourse) treemap from the registry.
	 *   Initialize the schedule instance variable. See the skeleton code. 
	 *   This constructor method has been written for you.
	 * @param courses
	 */
	public Scheduler(TreeMap<String,ActiveCourse> courses)
	{
	  schedule = courses;
	  DaysOfWeek.put("Mon",1); DaysOfWeek.put("Tue",2);
	  DaysOfWeek.put("Wed",3); DaysOfWeek.put("Thu",4); DaysOfWeek.put("Fri",5);
	  StartTimes.put(800,1); StartTimes.put(900,2); StartTimes.put(1000,3);
	  StartTimes.put(1100,4); StartTimes.put(1200,5); StartTimes.put(1300,6);
	  StartTimes.put(1400,7); StartTimes.put(1500,8); StartTimes.put(1600,9);
	  LectureHours.put(1,1);  LectureHours.put(2,2);  LectureHours.put(3,3);
	  
	  totalCourseLength.put("CPS209", 0);  totalCourseLength.put("CPS511", 0);   totalCourseLength.put("CPS616", 0);
	  totalCourseLength.put("CPS643", 0);  totalCourseLength.put("CPS706", 0);
	  
	  timeTable =new String[][] {{"     ","   Mon   ","   Tues   ","   Wed   ","   Thu   ","   Fri   "},
		  						 {"0800","          ","          ","          ","          ","          "}, 
		  						 {"0900","          ","          ","          ","          ","          "}, //9 spaces
		  						 {"1000","          ","          ","          ","          ","          "},
		  						 {"1100","          ","          ","          ","          ","          "}, 
		  						 {"1200","          ","          ","          ","          ","          "}, 
		  						 {"1300","          ","          ","          ","          ","          "},
		  						 {"1400","          ","          ","          ","          ","          "}, 
		  						 {"1500","          ","          ","          ","          ","          "},
		  						 {"1600","          ","          ","          ","          ","          "}};
	  
	}
	
	public void setDayAndTime(String courseCode, String day, int startTime, int duration) throws UnknownCourseException, InvalidDayException,InvalidTimeException,InvalidDurationException,LectureTimeCollisionException
	{
		//a lot of error checking to be done with this one!!!
		
		courseCode=courseCode.toUpperCase();
		String firstLetter = day.substring(0,1).toUpperCase();
		String restOfLetters= day.substring(1,3).toLowerCase();
		day = firstLetter + restOfLetters;
		
		boolean test = true;
		
		if(!schedule.containsKey(courseCode)) {
			test = false;
			throw new UnknownCourseException("Unknown Course: " + courseCode);
		}
		
		if(!DaysOfWeek.containsKey(day)) {
			test = false;
			throw new InvalidDayException("Invalid Lecture Day");
		}
		
		
		if(startTime > 1600) {
			test = false;
			throw new InvalidTimeException("Invalid Lecture Start Time");
		}
		
		if (startTime < 800) {
			test = false;
			throw new InvalidTimeException("Invalid Lecture Start Time");
		}
		
		if (startTime == 1500 && duration == 3) {
			test = false;
			throw new InvalidTimeException("Invalid Lecture duration for that start time");
		}
		
		if (startTime == 1600 && duration == 2) {
			test = false;
			throw new InvalidTimeException("Invalid Lecture duration for that start time");
		}
		
		if(!LectureHours.containsKey(duration)) {
			test = false;
			throw new InvalidDurationException("Invalid Lecture Duration");
		}
		
		if(checkCollison(courseCode, day, startTime, duration)) {
			test = false;
			throw new LectureTimeCollisionException("Lecture Time Collision");
		}
		if(returnSlots(courseCode) == 3) {
			test = false;
			System.out.println(courseCode + " already has been scheduled");
		}
		
		if((returnSlots(courseCode) == 2 && duration > 1)|| (returnSlots(courseCode) == 1 && duration > 2) ) {
			test = false;
			System.out.println(courseCode + " already has been scheduled");
		}
		
		
		
		
		if (test) {
			schedule.get(courseCode).setLectureStart(startTime);
			schedule.get(courseCode).setLectureDuration(duration);
			schedule.get(courseCode).setLectureDay(day);
			
	
			for (int i = 0; i < duration; i++) {
				
				if(i==0) {
				timeTable[StartTimes.get(startTime)][DaysOfWeek.get(day)] = "  "+ courseCode +" ";
				}
			   else if (i>0) {
					
					if(startTime == 1500 && duration > 2) {
						System.out.print("Invalid Lecture Time");
						i++;
					}
					
					else if(startTime < 800) {
						System.out.print("Invalid Lecture Time");
						i++;
					}
					
					else {
						timeTable[StartTimes.get(startTime)+ i][DaysOfWeek.get(day)] = "  "+ courseCode +" ";
						
					}
				}
			}
			
	
		}
		
	}
	
	public void setAutomaticDayAndTime(String courseCode, int duration) throws InvalidDurationException,UnknownCourseException
	{
		courseCode=courseCode.toUpperCase();
boolean test = true;
		if(!LectureHours.containsKey(duration)) {
			test = false;
			throw new InvalidDurationException("Invalid Lecture Duration");
			
		}
		if(!schedule.containsKey(courseCode)) {
			test = false;
			throw new UnknownCourseException("Unknown Course: " + courseCode);
		}
		if(returnSlots(courseCode) == 3) {
			test = false;
			System.out.println(courseCode + " Can have a Max of 3 slots");
		}
		
		if((returnSlots(courseCode) == 2 && duration > 1) || (returnSlots(courseCode) == 1 && duration > 2) ) {
			test = false;
			System.out.println(courseCode + " Can have a Max of 3 slots");
		}
		
		
		if(test) {
		for (int i = 1; i < 6; i++) {    // loop through the array
			// find each open space, and then check if 2 ahead of it is open, then add it.
			for(int j = 1; j < 10; j++) {
				
				if(timeTable[j][i].trim().equals("")) {
						if (duration == 1 && j+1 <10) {
							timeTable[j][i]= "  "+ courseCode + "  ";
							totalCourseLength.put(courseCode, duration);
							return;
						}
						if(duration == 2 && j+1 < 9) {
							if(timeTable[j][i].trim().equals("")) {
							if(timeTable[j+1][i].trim().equals("")) {
								timeTable[j][i]= "  "+ courseCode+ "  ";
								timeTable[j+1][i]= "  "+ courseCode+ "  ";
								totalCourseLength.put(courseCode, duration);
								return;
							}
								
							}
						}
						if (duration ==3 && j+1 < 8) {
							if(timeTable[j][i].trim().equals("")) {
							if(timeTable[j+1][i].trim().equals("")) {
								if(timeTable[j+2][i].trim().equals("")) {
									timeTable[j][i]= "  "+ courseCode+ "  ";
									timeTable[j+1][i]= "  "+ courseCode+ "  ";
									timeTable[j+2][i]= "  "+ courseCode+ "  ";
									totalCourseLength.put(courseCode, duration);
									return;
								}
							}
							}
						}
			}
			}
			
		}
		}
		
	}
	
	public void clearSchedule(String courseCode)
	{
		
		courseCode = courseCode.toUpperCase();
		if(schedule.containsKey(courseCode)) {
			schedule.get(courseCode).setLectureStart(0);
			schedule.get(courseCode).setLectureDuration(0);
			schedule.get(courseCode).setLectureDay("");
			
			totalCourseLength.put(courseCode, 0);
			
		}
		
		for(int i = 0 ; i < DaysOfWeek.size()+1; i++) {
			for(int j = 0; j < StartTimes.size()+1;j++) {
				
				if(timeTable[j][i].contains(courseCode)) {
					timeTable[j][i]= "       ";
				}
			}
		}
	}
	
public int returnSlots(String courseCode) { //returns the number of slots with that course
	
	int times =0;
	for(int i = 1; i < 6; i++) {
		for (int j=1; j < 10; j++) {
			if(timeTable[j][i].trim().contains(courseCode)) {
				times++;
			}
		}
	}
	return times;
}
	
public boolean checkCollison(String courseCode,String day, int startTime, int duration) {
			
	String firstLetter = day.substring(0,1).toUpperCase();
	String restOfLetters= day.substring(1,3).toLowerCase();
	day = firstLetter + restOfLetters;
	
	
		boolean test = true;
		boolean test2 = true;
		boolean test3 = true;	
		if(duration == 1) {
			
			if(timeTable[StartTimes.get(startTime)][DaysOfWeek.get(day)].trim().length() == 0) {
				test = false;
			}
			
			if(!test) {
				return false;
			}
			
		}
		
		else if(duration == 2) {
			
			if(timeTable[StartTimes.get(startTime)][DaysOfWeek.get(day)].trim().length() == 0) { //is slot one empty? Yes = False
				test = false;
			}
			if(timeTable[StartTimes.get(startTime)+ 1][DaysOfWeek.get(day)].trim().length() == 0) {	
				test2 = false;
			}
			
			if(!test && !test2) {				//if slot one AND 2 empty, then return false because NO lecture collison
				return false;
			}
			
		}
		
		else if(duration == 3) {
			
			if(timeTable[StartTimes.get(startTime)][DaysOfWeek.get(day)].trim().length() == 0) {
				test = false;
			}
			if(timeTable[StartTimes.get(startTime)+ 1][DaysOfWeek.get(day)].trim().length() == 0) {
				test2 = false;
			}
			if(timeTable[StartTimes.get(startTime)+ 2][DaysOfWeek.get(day)].trim().length() == 0) {
				test3 = false;
			}
			
			if(!test && !test2 && !test3) {
				return false;
			}
			
		}
		return true;
	
}
		
	public void printSchedule()
	{
				for(String[] row: timeTable) {
						for(String day: row) {
								System.out.print(day);
										}		
							System.out.println();
								}
					}
			}
	


