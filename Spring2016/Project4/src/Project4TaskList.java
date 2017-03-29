/**
 * imported necessary libraries
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Project4TastList class is the main class for the heap and job classes. This class has a main and 8 methods. The main method uses
 * a heap with job objects, it also reads an existing file. Uses a task menu to which method to use. It also has display task
 * to display the whole jobs in the heap. It has display current task method that displays the current task. It has add to task
 * to add a task to the heap. It has a mark complete method that just removes the current task. Postpone next task method postpones
 * the current task to a different date and time. Make task method is for making a task for add task. CheckInt method just checks
 * the integer, use for prompting.
 * @author Greg Violan, 011706641
 */
public class Project4TaskList {
/**
 * main method that puts every methods together
 * @param args
 */
	public static void main(String[] args) {
		Heap<Job> job = new Heap<Job>();
		File f = new File("taskList.txt");
		
		try{
			Scanner scnr = new Scanner(f);
			do{
				String read = scnr.nextLine();
				String[] tokens = read.split(", ");
			
				Job j = new Job(tokens[0], tokens[1]);
				job.addNode(j);
			}while(scnr.hasNextLine());
		}catch(FileNotFoundException e){
			System.out.println("FNFE");
		}
		
		boolean s = false;
		do{
			int func = taskMenu(); 
			
			if(func == 1){
				displayTask(job);
			}
			else if(func == 2){
				displayCurrentTask(job);
			}
			else if(func == 3){
				addToTask(job);
			}
			else if(func == 4){
				markComplete(job);
			}
			else if(func == 5){
				postPoneNextTask(job);
			}
			else{
				System.out.println("Quiting.. Thank you");
				System.exit(0);
			}
		}while(!s);
		
	}
/**
 * Displays the menu for prompting the user
 * @return input, the prompt that the user chose
 */
	public static int taskMenu(){
		System.out.println("-- TASK MENU --");
		System.out.println("1. Display the list of tasks \n2. Display current task \n"
				+ "3. Add a new item to the task list \n4. Mark Complete \n5. Postpone next task"
				+ "\n6. Quit");
		int input = checkInt(1, 6);
		
		return input;
	}
/**
 * Displays the tasks in the heap
 * @param job, the heap that contains the task objects
 */
	public static void displayTask(Heap<Job> job){
		if(job.isEmpty()){
			System.out.println("Task list is empty\n");
		}
		else{
			System.out.println("-- TASK LIST --");
			job.printHeap();
		}
	}
/**
 * Displays the current task in the heap, which is the root
 * @param job, the heap that contains the task objects
 */
	public static void displayCurrentTask(Heap<Job> job){
		if(job.isEmpty()){
			System.out.println("Task list is empty, no current task\n");
		}
		else{
			System.out.println("Current task: \n" + job.getNodeAt(0).toString() + "\n");
		}
	}
/**
 * Adds a task to the heap
 * @param job, the heap that contains the task objects
 */
	public static void addToTask(Heap<Job> job){
		Scanner scnr = new Scanner(System.in);
		
		System.out.println("Enter the name of the task:");
		String jobName = scnr.nextLine();
		
		Job j = makeTask(jobName);
		System.out.println("Added: " + j.toString() + " to the task list\n");
		job.addNode(j);
		job.printHeap();
	}
/**
 * Removes the current task in the tasklist (heap)
 * @param job, the heap that contains the task objects
 */
	public static void markComplete(Heap<Job> job){
		if(job.isEmpty()){
			System.out.println("Invalid: Task list is empty,"
					+ " cannot mark anything complete\n");
		}
		else{
			System.out.println("Marking complete: \n" + job.removeMin().toString() + "\n");
		}
	}
/**
 * Postpones the current task to a different date
 * @param job, the heap that contains the task objects
 */
	public static void postPoneNextTask(Heap<Job> job){
		if(job.isEmpty()){
			System.out.println("Task list is empty, cannot postpone anything");
		}
		else{
			System.out.println("Postponing: " + job.getNodeAt(0).toString());		
			
			Job j = makeTask(job.getNodeAt(0).getName());
			
			job.removeMin();
			job.addNode(j);
			
			System.out.println(j.getName() + " was \npostponed to: " + j.getDate() + "\n");
		}
	}
/**
 * Creates a task(job) object to add into the task(job) heap. 
 * @param jobName, name of the task
 * @return job, the object job
 */
	public static Job makeTask(String jobName){
		//PROMPT MONTH
		System.out.println("Enter the month of the task (from 1-12):");
		int jobMonth = checkInt(1, 12);	
		String jobMonthStr = Integer.toString(jobMonth);
		
		//PROMPT DAY
		System.out.println("Enter the day of the task (from 1-31):");
		int jobDay = checkInt(1, 31);	
		String jobDayStr = Integer.toString(jobDay);
		
		//PROMPT YEAR
		System.out.println("Enter the year of the task (eg. >= 2016):");
		int jobYear = checkInt(2016, 2200);	
		String jobYearStr = Integer.toString(jobYear);
		
		//PROMPT HOUR
		System.out.println("Enter the hour of the task (0-24 [24 hr cycle]):");
		int jobHour = checkInt(0, 24);	
		
		String jobHourStr;
		if(jobHour < 10){
			jobHourStr = "0" + Integer.toString(jobHour);
		}else{
			jobHourStr = Integer.toString(jobHour);
		}
		
		//PROMPT MIN
		System.out.println("Enter the minute of the task (0-59):");
		int jobMin = checkInt(0, 59);
		
		String jobMinStr;
		if(jobMin < 10){
			jobMinStr = "0" + Integer.toString(jobMin);
		}else{
			jobMinStr = Integer.toString(jobMin);
		}
		
		String jobDate = jobMonthStr + "/" + jobDayStr + "/" + jobYearStr + " " + jobHourStr + ":"+ jobMinStr;
		
		Job j = new Job(jobName, jobDate);
		return j;
	}
/**
 * Checks the input integer, for prompting the user.
 * @param low, is the lower integer of the restricted prompt
 * @param high, is the higher integer of the restricted prompt
 * @return validNum, the validated prompted integer
 */
	public static int checkInt( int low, int high ) {
		Scanner in = new Scanner(System.in);
		boolean valid = false;
		int validNum = 0;
		
		while( !valid ) {
			if(in.hasNextInt()) {
				validNum = in.nextInt();
				if( validNum >= low && validNum <= high ){
					valid = true;
				} 
				else{
					System.out.println("**invalid input**");
				}
			}
			else{
				//clear buffer of junk input
				in.next();
				System.out.println("**invalid input**");
			}
		}
		return validNum;
	}
}
