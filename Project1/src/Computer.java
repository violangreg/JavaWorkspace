import java.util.Scanner;
/**
 * Computer class extending Entity class
 * @author Greg
 *
 */
public class Computer extends Entity
{
	/**
	 * Computer constructor
	 * @param n, name of object
	 */
	public Computer(String n)
	{
		super(n, 50);
	}
	/**
	 * doTask method, this will depend on setTask
	 * param e, passes in the entity, which is the target
	 */
	@Override
	public void doTask(Entity e) 
	{
		Scanner scnr = new Scanner(System.in);
		String password = "";
		String code = "VCMJA";
		while(!password.equalsIgnoreCase("VCMJA"))
		{
			
			System.out.println("Please type in the correct passcode...(type in b to return)");
			password = scnr.nextLine();	
			if(password.equalsIgnoreCase(code)) {
				setTask("w");
				modifyHp(0);
			}
			else if(password.equals("b")) return;
		}
		
	}
	
}
