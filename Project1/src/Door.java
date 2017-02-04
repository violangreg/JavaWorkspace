import java.util.Scanner;
/**
 * Door class which extends Entity
 * @author Greg Violan
 */
public class Door extends Entity
{
	/**
	 * door constructor
	 * @param n, name of the door
	 */
	public Door(String n)
	{
		super(n, 100);
	}
	/**
	 * doTask class
	 * @param entity, passes in the object on who to do action onto
	 */
	@Override
	public void doTask(Entity e) 
	{
		System.out.println(getName() + ": Errrggg... ghhhh the.. passcoodeee.. iss the backwards order... of... the Bad... guuuu---.. *clink*" );
		System.out.println("You have destroyed the " + getName() + " and you found a computer inside with a passcode");
	}
}
