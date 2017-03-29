/**
 * Abstract Entity class
 * @author Greg
 *
 */
public abstract class Entity
{
	/**
	 * Entity constructor
	 * @param n, name of object
	 * @param h, hp of object
	 */
	public Entity(String n, int h)
	{
		name = n;
		hp = h;
	}
	/**
	 * abstract doTask for Overriding doTasks for other classes
	 * @param e, targeted entity
	 */
	public abstract void doTask(Entity e);
	/**
	 * gets the name of the object
	 * @return name of object
	 */
	public String getName()
	{
		return name;
	}
	/**
	 * gets the hp of the object
	 * @return hp of object
	 */
	public int getHp()
	{
		return hp;
	}
	/**
	 * Checks the active status of an object
	 * @return active, true or false
	 */
	public boolean getActive()
	{
		return active;
	}
	/**
	 * modifies the hp of the object, if its 0, changed active to true which means dead 
	 * @param h, hp of the object
	 */
	public void modifyHp(int h)
	{
		hp = h;
		if(getHp() <= 0) active = true;
	}
	/**
	 * gets the task of the object
	 * @return task, depending on the setTask of an object
	 */
	public String getTask()
	{
		return task;
	}
	/**
	 * set task of the object
	 * @param t, a string of whatever you want to set the task of the object
	 */
	public void setTask(String t)
	{
		task = t;
	}
	/**
	 * name of the object
	 */
	private String name;
	/**
	 * hp of the object
	 */
	private int hp;
	/**
	 * active status of object
	 */
	private boolean active;
	/**
	 * task of the object
	 */
	private String task;
	
}
