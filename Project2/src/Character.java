//Greg Paolo Violan, 011706641
/**
 * imported necessary api's
 */
import java.io.Serializable;
/**
 * Character abstract class implements Serializable
 * @author Greg
 *
 */
public abstract class Character implements Serializable{
	/**
	 * Character constructor
	 * @param n, name of character
	 * @param q, quip of character
	 * @param h, hp of character
	 * @param l, level of character
	 * @param g, gold of character
	 */
	public Character(String n, String q, int h, int l, int g){
		name = n;
		quip = q;
		hp = h;
		level = l;
		gold = g;
	}
	/**
	 * abstract method attack
	 * @param c, character
	 */
	public abstract void attack(Character c);
	/**
	 * returns the name of the character
	 * @return the name of character
	 */
	public String getName(){
		return name;
	}
	/**
	 * returns the quip of character
	 * @return the quip of character
	 */
	public String getQuip(){
		return quip;
	}
	/**
	 * returs the hp of the character
	 * @return the hp of the character
	 */
	public int getHp(){
		return hp;
	}
	/**
	 * returns the level of the character
	 * @return the level of the character
	 */
	public int getLevel(){
		return level;
	}
	/**
	 * returns the gold of the character
	 * @return the gold of the character
	 */
	public int getGold(){
		return gold;
	}
	/**
	 * increase the level of character
	 */
	public void increaseLevel(){
		++level;
		hp = (level * 10);
	}
	/**
	 * heal the character
	 * @param h
	 */
	public void heal(int h){
		hp += h;
	}
	/**
	 * character takes damage
	 * @param h, damage
	 */
	public void takeDamage(int h){
		hp -= h;
	}
	/**
	 * collects the gold
	 * @param g, collects gold
	 */
	public void collectGold(int g){
		gold += g;
	}
	/**
	 * display character's info
	 */
	public void display(){
		System.out.println("Name: " + name);
		System.out.println("Lv: " + level);
		System.out.println("Gold: " + gold);
		System.out.println("Hp: " + hp);
	}
	/**
	 * private String name of object
	 */
	private String name;
	/**
	 * private String quip of object
	 */
	private String quip;
	/**
	 * private int level of object
	 */
	private int level;
	/**
	 * private int hp of object
	 */
	private int hp;
	/**
	 * private int gold of object
	 */
	private int gold;
}
