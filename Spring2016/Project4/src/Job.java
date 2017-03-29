/**
 * Job class is for constructing a Job object to be used in the heap. It has a task name and due date, they both have a 
 * get method. This implements Comparable to override compareTo for comparing in the heap. It also overrides toString for
 * displaying.
 * @author Greg Violan, 011706641
 *
 */
public class Job implements Comparable<Job>{
	/**
	 * Job constructor
	 * @param n, name of job
	 * @param d, date of job
	 */
	public Job(String n, String d){
		taskName = n;
		dueDate = d;
	}
	/**
	 * Gets the name of the job
	 * @return taskName, name of job
	 */
	public String getName(){
		return taskName;
	}
	/**
	 * Gets the date of the job
	 * @return dueDate, date of job
	 */
	public String getDate(){
		return dueDate;
	}
	/**
	 * Overrides compareTo, compares the date and time of the job
	 * @param j, the job to compare to
	 * @return compare, an integer used by comparing in the heap
	 */
	@Override
	public int compareTo(Job j) {
		int compare = 0;
	
		String[] thisTokens = dueDate.split("/"); // tokens of task date
		String[] thisTokens2 = thisTokens[2].split(" "); // tokens of task year
		String[] thisTokens3 = thisTokens2[1].split(":"); // tokens of task time
		
		int thisYear = Integer.parseInt(thisTokens2[0]);
		int thisMonth = Integer.parseInt(thisTokens[0]);
		int thisDay = Integer.parseInt(thisTokens[1]);
		int thisHour = Integer.parseInt(thisTokens3[0]);
		int thisMin = Integer.parseInt(thisTokens3[1]);
		
		String[] jTokens = j.dueDate.split("/"); // tokens of task date
		String[] jTokens2 = jTokens[2].split(" "); // tokens of task year
		String[] jTokens3 = jTokens2[1].split(":"); // tokens of task time

		int jYear = Integer.parseInt(jTokens2[0]);
		int jMonth = Integer.parseInt(jTokens[0]);
		int jDay = Integer.parseInt(jTokens[1]);
		int jHour = Integer.parseInt(jTokens3[0]);
		int jMin = Integer.parseInt(jTokens3[1]);
		
		if(thisYear == jYear){
			if(thisMonth == jMonth){
				if(thisDay == jDay){
					if(thisHour == jHour){
						if(thisMin == jMin){
							compare = taskName.toLowerCase().compareTo(j.taskName.toLowerCase()); //Disregards capitals
						}
						else if(thisMin > jMin){
							compare = 1;
						}
						else if(thisMin < jMin){
							compare = -1;
						}
					}
					else if(thisHour > jHour){
						compare = 1;
					}
					else if(thisHour < jHour){
						compare = -1;
					}
				}
				else if(thisDay > jDay){
					compare = 1;
				}
				else if(thisDay < jDay){
					compare = -1;
				}
			}
			else if(thisMonth > jMonth){
				compare = 1;
			}
			else if(thisMonth < jMonth){
				compare = -1;
			}
		}
		else if(thisYear > jYear){
			compare = 1;
		}
		else if(thisYear < jYear){
			compare = -1;
		}
		
		return compare; // 1 if argument is before, -1 if argument is after
	}
	/**
	 * Overrides toString for easier display
	 * @return taskName, name of job
	 * @return dueDate, date of job
	 */
	@Override
	public String toString(){
		return taskName + " " + dueDate; 
	}
	/**
	 * Name of the job
	 */
	private String taskName;
	/**
	 * Date of the job
	 */
	private String dueDate;
}
