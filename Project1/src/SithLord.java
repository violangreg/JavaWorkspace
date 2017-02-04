import java.util.Random;
/**
 * Sith Lord class extending Person class and implements HasForce interface
 * @author Greg
 */
public class SithLord extends Person implements HasForce {
	/**
	 * Sith Lord constructor
	 * @param n, name of object
	 * @param q, quip of object
	 * @param c, color weapon of object
	 */
	public SithLord(String n, String q, String c)
	{
		super(n, 100, "lightsaber", q);
		saberColor = c;
	}
	/**
	 * attack method of object, randomized 
	 */
	@Override
	public void attack(Entity e) {
		Random rand = new Random();
		int randomNum;
		int damaged;
		
		randomNum = rand.nextInt((15 - 7) + 1) + 5;
		damaged = e.getHp() - randomNum;
		e.modifyHp(damaged);
		System.out.println(randomNum + " hit points");
	}
	/**
	 * doTask method, depending on which setTask
	 */
	@Override
	public void doTask(Entity e) {
		Random rand = new Random();
		int randomNum;
		randomNum = rand.nextInt((3 - 1) + 1) + 1;
		
		if(getTask().equals("a"))
		{
			if(randomNum == 1) System.out.println(getName() + " slashes " + e.getName() + " with his " + saberColor + " " + getWeapon() + " but " + e.getName() + " dodges it" );
			else 
			{
				System.out.print(getName() + " slashes " + e.getName() + " with his " + saberColor + " " + getWeapon() + " for ");
				attack(e);
			}
		}
		else if(getTask().equals("f"))
		{
			if(randomNum == 1) System.out.println(getName() + " uses the force on " + e.getName() + " but " + e.getName() + " dodges it causing no damage");
			else 
			{
				System.out.print(getName() + " uses the force on " + e.getName() + " for ");
				useForce(e);
				sayCatchPhrase();
			}
		}
		else return;
		
		if(getHp() <= 0) setTask("d");
	
	}
	/**
	 * uses the force of object on the entity object
	 */
	@Override
	public void useForce(Entity e) {
		Random rand = new Random();
		int randomNum;
		int damaged;
		
		randomNum = rand.nextInt((30 - 15) + 1) + 15;
		damaged = e.getHp() - randomNum;
		e.modifyHp(damaged);
		System.out.println(randomNum + " hit points");
	}
	/**
	 * private string saber color
	 */
	private String saberColor;
}
