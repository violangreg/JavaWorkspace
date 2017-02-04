/**
 * Abstract class Person extending Entity
 * @author Greg
 */
public abstract class Person extends Entity 
{
	/**
	 * Person constructor
	 * @param n, name of object
	 * @param h, hp of object
	 * @param w, weapon of object
	 * @param q, quip of object
	 */
	public Person(String n, int h, String w, String q)
	{
		super(n, h);
		weapon = w;
		quip = q;
	}
	/**
	 * prints out the quip of object
	 */
	public void sayCatchPhrase()
	{
		System.out.println(quip);
	}
	/**
	 * abstract method for overriding to other classes
	 * @param e, passes in the entity, which is the target
	 */
	public abstract void attack(Entity e);
	/**
	 * get weapon method to get the object's weapon
	 * @return weapon, the weapon of object
	 */
	public String getWeapon()
	{
		return weapon;
	}
	/**
	 * private string weapon of object
	 */
	private String weapon;
	/**
	 * private string quip of object
	 */
	private String quip;
}
