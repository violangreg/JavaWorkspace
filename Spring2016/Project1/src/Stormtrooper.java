import java.util.Random;
/**
 * Stormtrooper class extending Person class
 * @author Greg
 */
public class Stormtrooper extends Person
{
	/**
	 * Stormtrooper constructor
	 * @param n, name of object
	 * @param q, the quip of object 
	 */
	public Stormtrooper(String n, String q)
	{
		super(n, 50, "E-11 blaster rifle", q);
	}
	/**
	 * attack method of the object, randomizes the dmg output
	 */
	@Override
	public void attack(Entity e) {
		Random rand = new Random();
		int randomNum;
		int damaged;
		
		randomNum = rand.nextInt((7 - 3) + 1) + 3;
		damaged = e.getHp() - randomNum;
		e.modifyHp(damaged);
		System.out.println(randomNum + " hit points");
	}
	/**
	 * doTasks of class, depending on which setTask
	 */
	@Override
	public void doTask(Entity e) 
	{
		Random rand = new Random();
		int randomNum;
		randomNum = rand.nextInt((3 - 1) + 1) + 1;
		
		if(getTask().equals("a"))
		{
			if(randomNum == 1) System.out.println(getName() + " shoots " + e.getName() + " with his/her " + getWeapon() + " but " + getName() + " misses " + e.getName() );
			else 
			{
				System.out.print(getName() + " shoots " + e.getName() + " with his/her " + getWeapon() + " for ");
				attack(e);
				sayCatchPhrase();
			}
		}else if(getTask().equals("k")){
			System.out.println(getName() +  " the passcode for the computer is " + getName().charAt(0));
			modifyHp(0);
		}
		else if(getTask().equals("d")) modifyHp(0);
		else return;
	}
}
