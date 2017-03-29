import java.util.Random;
/**
 * Rebel class extending Person class and implementing Healable interface
 * @author Greg
 *
 */
public class Rebel extends Person implements Healable
{
	/**
	 * Rebel constructor
	 * @param n, name of object
	 * @param q, quip of object
	 */
	public Rebel(String n, String q)
	{
		super(n,50, "A280 blaster rifle", q);
	}
	/**
	 * attacks the object with a randomized output
	 * param e, passes in the entity, which is the target
	 */
	@Override
	public void attack(Entity e) 
	{
		Random rand = new Random();
		int randomNum;
		int damaged;
		
		randomNum = rand.nextInt((7 - 3) + 1) + 3;
		damaged = e.getHp() - randomNum;
		e.modifyHp(damaged);
		System.out.println(randomNum + " hit points");
	}
	/**
	 * doTask method depends on the setTask of object
	 * param e, passes in the entity, which is the target
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
		}
		else if(getTask().equals("h")) heal(getHp());
		else if(getTask().equals("d")) {
			modifyHp(0);
		}
		else return;
	}
	/**
	 * modifies the hp of this object
	 * param hp, is the current hp of this object
	 */
	@Override
	public void heal(int hp) 
	{
		Random rand = new Random();
		int randomNum;
		int heal;
		hp = getHp();
		
		randomNum = rand.nextInt((35 - 20) + 1) + 20;
		heal = hp + randomNum;
		modifyHp(heal);
		System.out.println(getName() + " is healed for " + randomNum + " hit points");
	}
}
