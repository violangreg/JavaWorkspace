import java.util.Random;
/**
 * Jedi class extending Person class and implementing HasForce and Healable interface
 * @author Greg
 */
public class Jedi extends Person implements HasForce, Healable
{
	/**
	 * Jedi constructor
	 * @param n, name of object
	 * @param q, quip of object
	 * @param c, weapon color of object
	 */
	public Jedi(String n, String q, String c)
	{
		super(n, 100, "lightsaber", q);
		saberColor = c;
	}
	/**
	 * attacks the targeted entity, randomized damage
	 * param e, passes in the entity, which is the target
	 */
	@Override
	public void attack(Entity e)
	{
		Random rand = new Random();
		int randomNum;
		int damaged;
		
		randomNum = rand.nextInt((17 - 8) + 1) + 8;
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
			if(randomNum == 1) System.out.println(getName() + " slashes " + e.getName() + " with his/her " + saberColor + " " + getWeapon() + " but " + e.getName() + " dodges it" );
			else 
			{
				System.out.print(getName() + " slashes " + e.getName() + " with his/her " + saberColor + " " + getWeapon() + " for ");
				attack(e);
			}
		}
		else if(getTask().equals("f"))
		{
			System.out.print(getName() + " uses the force on " + e.getName() + " for ");
			useForce(e);
			sayCatchPhrase();
		}
		else if(getTask().equals("h")) heal(getHp());
		else return;
	}
	/**
	 * uses force on the object
	 * param e, passes in the entity, which is the target
	 */
	@Override
	public void useForce(Entity e) 
	{
		Random rand = new Random();
		int randomNum;
		int damaged;
		
		randomNum = rand.nextInt((15 - 7) + 1) + 7;
		damaged = e.getHp() - randomNum;
		e.modifyHp(damaged);
		System.out.println(randomNum + " hit points");
		
	}
	/**
	 * modifies the hp of this object
	 * param hp, is the current hp of this object
	 */
	@Override
	public void heal(int hp) {
		Random rand = new Random();
		int randomNum;
		int heal;
		hp = getHp();
		
		randomNum = rand.nextInt((35 - 20) + 1) + 20;
		heal = hp + randomNum;
		modifyHp(heal);
		System.out.println(getName() + " is healed for " +randomNum + " hit points");
	}
	/**
	 * private string weapon color of object
	 */
	private String saberColor;
}
