/**
 * Medical class extending Droid class
 * @author Greg Violan
 */
public class Medical extends Droid
{
	/**
	 * Medical constructor
	 * @param n, name of object
	 * @param t, how much tasks object can have
	 */
	public Medical(String n, int t)
	{
		super(n, 20, t);
	}
	/**
	 * doTask method depends on the setTask of object
	 * param e, passes in the entity, which is the target
	 */
	@Override
	public void doTask(Entity e) 
	{
		if(getNumTasks() <= 0){
			System.out.println(getName() + " cannot do anymore tasks");
			setTask("r");
		}
		else {
			if(e instanceof Healable){
				Healable h = (Healable) e;
				h.heal(e.getHp());
				useTask();
				setTask("h");
				System.out.println(getName() + " has " + getNumTasks() + " tasks left");
				System.out.println(getName() + " heals " + e.getName());
			}
		}
		
	}
}
