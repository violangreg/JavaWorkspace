//Greg Paolo Violan, 011706641
/**
 * imported necessary api's
 */
import java.util.Random;
/**
 * Enemy class extending Character class
 * @author Greg
 *
 */
public class Enemy extends Character {
	/**
	 * Enemy constructor,
	 * @param n, name of enemy
	 * @param q, quip of enemy
	 * @param h, hp of enemy
	 * @param l, level of enemy
	 * @param g, gold of enemy
	 * @param i, item of enemy
	 */
	public Enemy(String n, String q, int h, int l, int g, Item i){
		super(n, q, h, l, g);
		item = i;
	}
	/**
	 * Abstract method attack overriding, attacks with random damage
	 */
	@Override
	public void attack(Character c) {
		Random rand = new Random();
		int randomNum;
		
		randomNum = rand.nextInt((2*getLevel() - 1+getLevel()) + 1) + 1;
		c.takeDamage(randomNum);
		System.out.println("A " + getName() + " hits " + c.getName() + " for " + randomNum + " damage");
	}
	/**
	 * returns the item of this object
	 * @return retusn the item
	 */
	public Item getItem(){
		return item;
	}
	/**
	 * private Item item
	 */
	private Item item;
}
