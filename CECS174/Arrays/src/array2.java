//Greg Violan
//011706641
//Ver. 1
//4-27-15

import java.util.*;

public class array2 {
	
	/* VARIABLES */
	static Scanner scan = new Scanner(System.in);
	static int option = 0; //GLOBAL OPTION
	static int size = 0; //GLOBAL SIZE
	
	/* CLASS SIZE # */
	static int size1 = 0;
	static int size2 = 0;
	static int sizeConcat = 0;
	
	/* ARRAYS */
	static String[] name1 = new String[100]; // class 1
	static int[] grade1 = new int[100]; // class 1
	static String[] name2 = new String[100]; // class 2
	static int[] grade2 = new int[100]; // class 2
	static String[] nameGradeString1 = new String[100]; //class 1 both name
	static int[] nameGradeInt1 = new int[100]; //class 1 both grade
	static String[] nameGradeString2 = new String[100]; //class 2 both name
	static int[] nameGradeInt2 = new int[100]; //class 2 both grade
	static String[] concatenateName = new String[200];
	static int[] concatenateGrade = new int[200];
	
	/* MAIN */
	public static void main(String[] args) {
		array2.menu();
		
	}

	/* MENU */
	public static void menu(){
		int stop = 0;
		
		do{
		System.out.println("*Main Menu*");
		System.out.println("Choose an option:");
		System.out.println("1: Fill Array");
		System.out.println("2: Concatenate");
		System.out.println("3: Calculate Grade Average");
		System.out.println("4: Find Maximum Grade/Name");
		System.out.println("5: Find Minimum Grade/Name");
		System.out.println("6: Element Seperator");
		System.out.println("7: Find Element");
		System.out.println("8: Remove Element");
		System.out.println("9: Insert Element");
		System.out.println("10: Swap Elements");
		System.out.println("11: Copy arrays");
		System.out.println("12: Modify Grade");
		System.out.println("0: Exit");
		System.out.println("Please choose...");
		
		int option = scan.nextInt();
		
		if(option == 1){
			stop = 1;
			array2.subMenu();
		}
		else if(option == 2){
			stop = 1;
			array2.menuConcatenate();
		}
		else if(option == 3){
			stop = 1;
			array2.menuAverage();
		}
		else if(option == 4){
			stop = 1;
			array2.menuMaximum();
		}
		else if(option == 5){
			stop = 1;
			array2.menuMinimum();
		}
		else if(option == 6){
			stop = 1;
			array2.menuSeperator();
		}
		else if(option == 7){
			stop = 1;
			array2.menuFind();
		}
		else if(option == 8){
			stop = 1;
			array2.menuRemove();
		}
		else if(option == 9){
			stop = 1;
			array2.menuAdd();
		}
		else if(option == 10){
			stop = 1;
		}
		else if(option == 11){
			stop = 1;
		}
		else if(option == 12){
			stop = 1;
		}
		else if(option == 0){
			System.exit(0);
		}
		else{
			System.out.println("Please try again, input cannot be determined");
		}
		
		}while(stop != 1);
		
	}
	public static void subMenu(){
		int stop = 0;
		
		do{
			System.out.println("*Sub Menu*");
			System.out.println("Choose an option:");
			System.out.println("1: Fill Name");
			System.out.println("2: Fill Grade");
			System.out.println("3: Fill Both Grade & Name");
			System.out.println("4: Return to Main Menu");
			System.out.println("Please choose...");
			
			int option = scan.nextInt();
			
			if(option == 1){
				stop = 1;
				array2.classMenuName();
			}
			else if(option == 2){
				stop = 1;
				array2.classMenuGrade(size1, size2);
			}
			else if(option == 3){
				stop = 1;
				array2.classMenuBoth();
			}
			else if(option == 4){
				stop = 1;
				array2.menu();
			}
			else{
				System.out.println("Please try again, input cannot be determined");
			}
		}while(stop != 1);
	}
	public static void classMenuName(){
		int stop = 0;
		
		do{
			System.out.println("*Class Menu*");
			System.out.println("Choose an option:");
			System.out.println("1: Class 1");
			System.out.println("2: Class 2");
			System.out.println("3: Return to Sub Menu");
			System.out.println("4: Return to Main Menu");
			System.out.println("Please choose...");
			int option = scan.nextInt();
			
			if(option == 1){
				size1 = array2.arraySize();
				name1 = array2.class1ArrayName(size1);
				System.out.println("Filled...");
			}
			else if(option == 2){
				size2 = array2.arraySize();
				name2 = array2.class2ArrayName(size2);
				System.out.println("Filled...");
			}
			else if(option == 3){
				stop = 1;
				array2.subMenu();
			}
			else if(option == 4){
				stop = 1;
				array2.menu();
			}
			else{
				System.out.println("Please try again, input cannot be determined");
			}
		}while(stop != 1);
	}	
	public static void classMenuGrade(int size1, int size2){
		int stop = 0;
		
		do{
			System.out.println("*Class Menu*");
			System.out.println("Choose an option:");
			System.out.println("1: Class 1");
			System.out.println("2: Class 2");
			System.out.println("3: Return to Sub Menu");
			System.out.println("4: Return to Main Menu");
			System.out.println("Please choose...");
			int option = scan.nextInt();
			
			if(option == 1){
				if(size1 != 0){
					grade1 = array2.class1ArrayGrade(size1);
					System.out.println("Filled...");
				}
				else{
					System.out.println("Error, please fill name first");
					array2.subMenu();
				}
			}
			else if(option == 2){
				if(size1 != 2){
					grade2 = array2.class2ArrayGrade(size2);
					System.out.println("Filled...");
				}
				else{
					System.out.println("Error, please fill name first");
					array2.subMenu();
				}
			}
			else if(option == 3){
				stop = 1;
				array2.subMenu();
			}
			else if(option == 4){
				stop = 1;
				array2.menu();
			}
			else{
				System.out.println("Please try again, input cannot be determined");
			}
		}while(stop != 1);
		
	}
	public static void classMenuBoth(){
		int stop = 0;
		
		do{
			System.out.println("*Class Menu*");
			System.out.println("Choose an option:");
			System.out.println("1: Class 1");
			System.out.println("2: Class 2");
			System.out.println("3: Return to Sub Menu");
			System.out.println("4: Return to Main Menu");
			System.out.println("Please choose...");
			int option = scan.nextInt();
			
			if(option == 1){
				size1 = array2.arraySize();
				array2.class1ArrayBoth();
				System.out.println("Filled...");
			}
			else if(option == 2){
				size2 = array2.arraySize();
				array2.class2ArrayBoth();
				System.out.println("Filled...");
			}
			else if(option == 3){
				stop = 1;
				array2.subMenu();
			}
			else if(option == 4){
				stop = 1;
				array2.menu();
			}
			else{
				System.out.println("Please try again, input cannot be determined");
			}
		}while(stop != 1);
	}
	public static void menuAverage(){
		int stop = 0;
		
		do{
			System.out.println("*Average Menu*");
			System.out.println("Choose an option:");
			System.out.println("1: Average of Class 1");
			System.out.println("2: Average of Class 2");
			System.out.println("3: Average of Both Classes");
			System.out.println("4: Return to Sub Menu");
			System.out.println("5: Return to Main Menu");
			System.out.println("Please choose...");
			int option = scan.nextInt();
			
			if(option == 1){
				array2.average(grade1, size1);
			}
			else if(option == 2){
				array2.average(grade2, size2);
			}
			else if(option == 3){
				array2.averageBoth(grade1, size1, grade2, size2);
			}
			else if(option == 3){
				stop = 1;
				array2.subMenu();
			}
			else if(option == 4){
				stop = 1;
				array2.menu();
			}
			else{
				System.out.println("Please try again, input cannot be determined");
			}
		}while(stop != 1);
	}
	public static void menuConcatenate(){
		int stop = 0;
		
		do{
			System.out.println("*Concatenate Menu*");
			System.out.println("Choose an option:");
			System.out.println("1: Concatenate Name of Class 1 and 2");
			System.out.println("2: Concatenate Grade of Class 1 and 2");
			System.out.println("3: Return to Sub Menu");
			System.out.println("4: Return to Main Menu");
			System.out.println("Please choose...");
			int option = scan.nextInt();
			
			if(option == 1){
				array2.concatenateName(name1, name2);
			}
			else if(option == 2){
				array2.concatenateGrade(grade1, grade2);
			}
			else if(option == 3){
				stop = 1;
				array2.subMenu();
			}
			else if(option == 4){
				stop = 1;
				array2.menu();
			}
			else{
				System.out.println("Please try again, input cannot be determined");
			}
		}while(stop != 1);
		
	}
	public static void menuMaximum(){
		int stop = 0;
		
		do{
			System.out.println("*Maximum Grade Menu*");
			System.out.println("Choose an option:");
			System.out.println("1: Class 1 Maximum Grade");
			System.out.println("2: Class 2 Maximum Grade");
			System.out.println("3: Class 1 Longest String Name");
			System.out.println("4: Class 2 Longest String Name");
			System.out.println("5: Return to Main Menu");
			System.out.println("Please choose...");
			int option = scan.nextInt();
			
			if(option == 1){
				array2.maximumClass1Grade(grade1);
			}
			else if(option == 2){
				array2.maximumClass2Grade(grade2);
			}
			else if(option == 3){
				array2.maximumClassName(name1);
			}
			else if(option == 4){
				array2.maximumClassName(name2);
			}
			else if(option == 5){
				stop = 1;
				array2.menu();
			}
			else{
				System.out.println("Please try again, input cannot be determined");
			}
		}while(stop != 1);
	}
	public static void menuMinimum(){
		int stop = 0;
		
		do{
			System.out.println("*Minimum Grade/Name Menu*");
			System.out.println("Choose an option:");
			System.out.println("1: Class 1 Minimum Grade");
			System.out.println("2: Class 2 Minimum Grade");
			System.out.println("3: Class 1 Shortest String Name");
			System.out.println("4: Class 2 Shortest String Name");
			System.out.println("5: Return to Main Menu");
			System.out.println("Please choose...");
			int option = scan.nextInt();
			
			if(option == 1){
				array2.maximumClass1Grade(grade1);
			}
			else if(option == 2){
				array2.maximumClass2Grade(grade2);
			}
			else if(option == 3){
				array2.minimumClassName(name1);
			}
			else if(option == 4){
				array2.minimumClassName(name2);
			}
			else if(option == 5){
				stop = 1;
				array2.menu();
			}
			else{
				System.out.println("Please try again, input cannot be determined");
			}
		}while(stop != 1);
	}
	public static void menuSeperator(){
		int stop = 0;
		
		do{
			System.out.println("*Elemental Seperator Menu*");
			System.out.println("Choose an option:");
			System.out.println("1: Class 1 Seperate Class 1 Name with Class 1 Grade");
			System.out.println("2: Class 2 Seperate Class 2 Name with Class 2 Grade");
			System.out.println("3: Class Both Seperate Name with Grade");
			System.out.println("5: Return to Main Menu");
			System.out.println("Please choose...");
			int option = scan.nextInt();
			
			if(option == 1){
				array2.elementSeperator(name1, grade1, size1);
			}
			else if(option == 2){
				array2.elementSeperator(name2, grade2, size2);
			}
			else if(option == 3){
				if(sizeConcat != 0){
					array2.elementSeperator(concatenateName, concatenateGrade, sizeConcat);
				}
				else{
					System.out.println("Please concatenate both classes name and grades together..");
					array2.menuConcatenate();
				}
			}
			else if(option == 4){
				array2.minimumClassName(name2);
			}
			else if(option == 5){
				stop = 1;
				array2.menu();
			}
			else{
				System.out.println("Please try again, input cannot be determined");
			}
		}while(stop != 1);
	}
	public static void menuFind(){
		int stop = 0;
		
		do{
			System.out.println("*Elemental Find Menu*");
			System.out.println("Choose an option:");
			System.out.println("1: Class 1");
			System.out.println("2: Class 2");
			System.out.println("3: Class Both");
			System.out.println("4: Return to Main Menu");
			System.out.println("Please choose...");
			int option = scan.nextInt();
			
			if(option == 1){
				array2.elementFindName(name1, size1);
			}
			else if(option == 2){
				array2.elementFindName(name2, size2);
			}
			else if(option == 3){
				if(sizeConcat != 0){
					array2.elementFindName(concatenateName, sizeConcat);
				}
				else{
					System.out.println("Please concatenate both classes names first..");
					array2.menuConcatenate();
				}
			}
			else if(option == 4){
				stop = 1;
				array2.menu();
			}
			else{
				System.out.println("Please try again, input cannot be determined");
			}
		}while(stop != 1);
	}
	public static void menuRemove(){
		int stop = 0;
		
		do{
			System.out.println("*Elemental Remove Menu*");
			System.out.println("Choose an option:");
			System.out.println("1: Class 1");
			System.out.println("2: Class 2");
			System.out.println("3: Class Both");
			System.out.println("4: Return to Main Menu");
			System.out.println("Please choose...");
			int option = scan.nextInt();
			
			if(option == 1){
				array2.elementRemove(name1, grade1, size1);
			}
			else if(option == 2){
				array2.elementRemove(name2, grade2, size2);
			}
			else if(option == 3){
				if(sizeConcat != 0){
					array2.elementRemove(concatenateName, concatenateGrade, sizeConcat);
				}
				else{
					System.out.println("Please concatenate both classes names first..");
					array2.menuConcatenate();
				}
			}
			else if(option == 4){
				stop = 1;
				array2.menu();
			}
			else{
				System.out.println("Please try again, input cannot be determined");
			}
		}while(stop != 1);
	}
	public static void menuAdd(){
		int stop = 0;
		
		do{
			System.out.println("*Elemental Add Menu*");
			System.out.println("Choose an option:");
			System.out.println("1: Class 1");
			System.out.println("2: Class 2");
			System.out.println("3: Class Both");
			System.out.println("4: Return to Main Menu");
			System.out.println("Please choose...");
			int option = scan.nextInt();
			
			if(option == 1){
				array2.elementAdd(name1, grade1, size1);
			}
			else if(option == 2){
				array2.elementAdd(name2, grade2, size2);
			}
			else if(option == 3){
				if(sizeConcat != 0){
					array2.elementAdd(concatenateName, concatenateGrade, sizeConcat);
				}
				else{
					System.out.println("Please concatenate both classes names first..");
					array2.menuConcatenate();
				}
			}
			else if(option == 4){
				stop = 1;
				array2.menu();
			}
			else{
				System.out.println("Please try again, input cannot be determined");
			}
		}while(stop != 1);
	}
	
	/* ARRAY SIZE */
	public static int arraySize(){
		int size = 0;
		
		do{
			System.out.println("Please enter student size (0-100)");
			size = scan.nextInt();
			
			if(size > 100){
				System.out.println("Please try again, input cannot be determined");
			}
			else if(size < 0){
				System.out.println("Please try again, input cannot be determined");
			}
			else if(size == 0){
				System.out.println("Canceled...");
			}
		}while(size < 0 && size > 100);
		
		return size;
	}

	/* ARRAY CLASS 1 METHODS */
	public static String[] class1ArrayName(int size1){
	
		System.out.println("Enter names (in order)");
		for(int i = 0; i <= size1; i++){
			name1[i] = scan.nextLine(); 
		}
		
		return name1;
	}	
	public static int[] class1ArrayGrade(int size1){
		
		System.out.println("Enter grades (in order)");
		for(int i = 0; i < size1; i++){
			grade1[i] = scan.nextInt(); 
		}
		
		return grade1;
	}
	
	/* ARRAY CLASS 2 METHODS */
	public static String[] class2ArrayName(int size2){
		
		System.out.println("Enter names (in order)");
		for(int i = 0; i <= size2; i++){
			name2[i] = scan.nextLine(); 
		}
		
		return name2;
	}	
	public static int[] class2ArrayGrade(int size2){
		
		System.out.println("Enter grades (in order)");
		for(int i = 0; i < size2; i++){
			grade2[i] = scan.nextInt(); 
		}
		
		return grade2;
	}
	
	/* ARRAY BOTH METHODS */
	// CLASS 1 //
	public static void class1ArrayBoth(){
		
		System.out.println("Enter Name then Grade (in order)");
		array2.class1ArrayBothName(size1);
		array2.class1ArrayBothGrade(size1);
		
	}
	public static String[] class1ArrayBothName(int size1){
		
		for(int i = 0; i < size1; i++){
			nameGradeString1[i] = scan.nextLine();
		}
		
		return nameGradeString1;
	}
	public static int[] class1ArrayBothGrade(int size1){
		
		for(int i = 0; i < size1; i++){
			nameGradeInt1[i] = scan.nextInt();
		}
		
		return nameGradeInt1;
	}

	// CLASS 2 //
	public static void class2ArrayBoth(){
		
		System.out.println("Enter Name then Grade (in order)");
		array2.class2ArrayBothName(size2);
		array2.class2ArrayBothInt(size2);

	}	
	public static String[] class2ArrayBothName(int size2){
		
		for(int i = 0; i < size2; i++){
			nameGradeString2[i] = scan.nextLine();
		}
		
		return nameGradeString2;
	}	
	public static int[] class2ArrayBothInt(int size2){
		
		for(int i = 0; i < size2; i++){
			nameGradeInt2[i] = scan.nextInt();
		}
		
		return nameGradeInt2;
	}
	
	/* AVERAGE */
	public static void average(int[] array, int size){
		double counter = 0;
		double average = 0;
		double total = 0;
		
		for(int i = 0; i<size; i++){
			total += array[i];
			counter++;
		}
		average = (total/counter);
		System.out.println("Class average: " + average);
	}
	public static void averageBoth(int[] array, int size, int[] array2, int size2){
		double counter = 0;
		double average = 0;
		double total = 0;
		
		for(int i = 0; i<size; i++){
			total += array[i];
			counter++;
		}
		for(int i = 0; i<size2; i++){
			total += array2[i];
			counter++;
		}
		average = (total/counter);
		System.out.println("Class average: " + average);
	}
	
	/* CONCATENATE */
	public static void concatenateName(String[] name1, String[] name2){
		if(size1 != 0 && size2 != 0){
			sizeConcat = size1 + size2;
			System.arraycopy(name1, 1, concatenateName, 0, size1);
			System.arraycopy(name2, 1, concatenateName, size1, size2);
			System.out.println("Concatenating Name...");
			for(int i = 0; i < sizeConcat; i++){
				System.out.println(concatenateName[i]);
			}
			System.out.println("Name Concatinated");
		}
		else{
			System.out.println("Error, please fill array first");
			array2.subMenu();
		}
	}
	public static void concatenateGrade(int[] grade1, int[] grade2){
		if(size1 != 0 && size2 != 0){
			sizeConcat = size1 + size2;
			System.out.println("Concatenating Grade...");
			System.arraycopy(grade1, 0, concatenateGrade, 0, size1);
			System.arraycopy(grade2, 0, concatenateGrade, size1, size2);
			for(int i = 0; i < sizeConcat; i++){
				System.out.println(concatenateGrade[i]);
			}
			System.out.println("Grade Concatenated");
		}
		else{
			System.out.println("Error, please fill array first");
			array2.subMenu();
		}
	}

	/* MAXIMUM */
	public static void maximumClass1Grade(int[] grade){
		int maximum = 0;
		for(int i = 0; i < size1; i++){
			if(grade[i] > maximum){
			maximum = grade[i];
			}
		}
		System.out.println("Maximum: " + maximum);
	}
	public static void maximumClass2Grade(int[] grade){
		int maximum = 0;
		for(int i = 0; i < size2; i++){
			if(grade[i] > maximum){
			maximum = grade[i];
			}
		}
		System.out.println("Maximum: " + maximum);
	}
	public static void maximumClassName(String[] name){

		int maxLength = 0;
		String longestString = null;
		for(String s : name){
			if(s.length() > maxLength){
				maxLength = s.length();
				longestString = s;
			}
		}
		System.out.println("Longest String: " + longestString);
	}
	
	/* MINIMUM */
	public static void minimumClass1Grade(int[] grade){
		int minimum = 0;
		for(int i = 0; i < size1; i++){
			if(grade[i] < minimum){
			minimum = grade[i];
			}
		}
		System.out.println("Minimum: " + minimum);
	}
	public static void minimumClass2Grade(int[] grade){
		int minimum = 0;
		for(int i = 0; i < size2; i++){
			if(grade[i] < minimum){
			minimum = grade[i];
			}
		}
		System.out.println("Minimum: " + minimum);
	}
	public static void minimumClassName(String[] name){
		int minLength = 0;
		String shortestString = null;
		for(String s : name){
			if(s.length() < minLength){
				minLength = s.length();
				shortestString = s;
			}
		}
		System.out.println("Shortest String: " + shortestString);
	}
	
	/* Elemental Separator */
	public static void elementSeperator(String[] name, int[] grade, int size){
		System.out.println("Seperating...");
		for(int i = 0; i<size; i++){
			System.out.printf("%6s | ", name[i]);
		}
		System.out.println();
		for(int i = 0; i<size; i++){
			System.out.printf("%6d | ", grade[i]);
		}
		System.out.println();
		System.out.println("Seperated");
	}
	
	/* Find an Element */
	public static void elementFindName(String[] name, int size){
		int index = 0;
		int valid = 0;
		int found = 0;
		
		System.out.println("Please input a student name");
		String find = scan.next();
		
		for(int i = 0; i<=size; i++){
			if(name[i].equals(find)){
				found = index;
				valid++;
			}
			index++;
		}
		if(valid != 0)
			System.out.println("Element is found in: " + found + " index");
		else
			System.out.println("Element is not found, please try again");
	}
	
	/* Remove Element */	
	public static void elementRemove(String[] name, int[] grade, int size){
		int index = 0;
		int found = 0;
		
		System.out.println("Please input a student name");
		String find = scan.next();
		
		for(int i = 0; i < size; i++){
			if(name[i].equals(find)){
				found = i;
			}
			index++;
		}
		
		for(int i = found; i < size; i++){
			name[i] = name[i+1];
		}
		
		for(int i = found; i < size; i++){
			grade[i] = grade[i+1];
		}
		
		System.out.println("Updating array..");
		for(int i = 0; i<size; i++){
			System.out.printf("%6s | ", name[i]);
		}
		System.out.println();
		for(int i = 0; i<size; i++){
			System.out.printf("%6d | ", grade[i]);
		}
		System.out.println();	
	}
	
	/* Insert an Element*/
	public static void elementAdd(String[] name, int[] grade, int size){
		size++;
		System.out.println("Please input student name");
		String s = scan.nextLine();
		name[size] = s;
		
		System.out.println("Please input student grade");
		int g = scan.nextInt();
		grade[size] = g;
	}
	
	/* Swapping Element */
	
	
	/* Copying Arrays into another one */
	/* Modify Grade */
	
	
	
}
