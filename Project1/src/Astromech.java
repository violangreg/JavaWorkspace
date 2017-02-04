import java.util.Random;
/**
 * Astromech class extending Droid class
 * @author Greg
 *
 */
public class Astromech extends Droid
{
	/**
	 * Astromech constructor
	 * @param n, name of object
	 * @param t, how much tasks an object have
	 */
	public Astromech(String n, int t)
	{
		super(n, 20, t);
	}
	/**
	 * doTask of Astromech continues various tasks for mission 2
	 * param e, passes in the entity, which is the target
	 */
	@Override
	public void doTask(Entity e) 
	{
		Random rand = new Random();
		int randomNum;
		randomNum = rand.nextInt((5 - 1) + 1) + 1;
		setTask("");
		
		
		if(getNumTasks() <= 0){
			System.out.println(getName() + " cannot do anymore tasks");
			setTask("r");
		}
		else {
			useTask();
			System.out.println(getName() + " has " + getNumTasks() + " tasks left");
			
			if(randomNum == 1){
				System.out.println(getName() + " succesfully hacks the " + e.getName());
				setTask("s");
				e.modifyHp(0);
				
			}
			else {
				System.out.println(getName() + " failed to hack the " + e.getName());
			}
		}
	}
}
