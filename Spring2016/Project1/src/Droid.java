/**
 * abstract Droid class extending Entity class
 * @author Greg
 *
 */
public abstract class Droid extends Entity
{
	/**
	 * Droid class 
	 * @param n, name of object
	 * @param h, hp of object
	 * @param t, how many task an object can have
	 */
	public Droid(String n, int h, int t)
	{
		super(n, 20);
		numTasks = t;
	}
	/**
	 * gets the number of task a droid has
	 * @return numTasks, return how much tasks object has
	 */
	public int getNumTasks()
	{
		return numTasks;
	}
	/**
	 * when a task is used, take out one from the number of tasks
	 */
	public void useTask()
	{
		numTasks = getNumTasks() - 1;
	}
	/**
	 * private int variable for number of tasks
	 */
	private int numTasks;
}
