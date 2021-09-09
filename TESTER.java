import java.util.Scanner;

import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.io.FileReader;
import java.io.IOException;


public class TESTER{
	public static void main(String[] args) {
	
		String[][] timeTable = {{"     ", "   Mon   ","   Tues   ","   Wed   ","   Thu   ","   Fri   "},
									{"0800","          ","          ","          ","          ","          "}, 
									{"0900","          ","          ","          ","          ","          "}, //9 spaces
									{"1000","          ","          ","          ","          ","          "},
									{"1100","          ","          ","          ","          ","          "}, 
									{"1200","          ","          ","          ","          ","          "}, 
									{"1300","          ","          ","          ","          ","          "},
									{"1400","          ","          ","          ","          ","          "}, 
									{"1500","          ","          ","          ","          ","          "},
									{"1600","          ","          ","          ","          ","          "}};
		
		for(String[] row: timeTable) {
			for(String day: row) {
				System.out.print(day);
			}
			
			timeTable[1][1]= "  CPS209  ";
			timeTable[2][1]= "  CPS209  ";
			timeTable[3][1]= "  CPS209  ";
			System.out.println();
		}
		
		String test = "    ";
		System.out.print(test.length());
		System.out.print(test.isEmpty());
		System.out.print("Mon".substring(0,1) + "Mon".substring(1,3));
		
		}
	
}
