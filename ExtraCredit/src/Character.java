//Greg Paolo Violan, 011706641
/**
 * imported necessary api's
 */
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.Serializable;
/**
 * Character abstract class implements Serializable. Character class is abstract
 * but it derives most of the Hero and Enemy object's data variables. It has a name,
 * quip, level, hp, gold, and location of the object
 * @author Greg
 */
@SuppressWarnings("serial")
public abstract class Character implements Serializable{
	/**
	 * private String name of object
	 */
	private String name, quip;
	/**
	 * private int level of object
	 */
	private int level, hp, gold;
	/**
	 * private Rectangle of object
	 */
	private Rectangle location;
	/**
	 * Character constructor
	 * @param n, name of character
	 * @param q, quip of character
	 * @param h, hp of character
	 * @param l, level of character
	 * @param g, gold of character
	 */
	public Character(String n, String q, int h, int l, int g, Point p, int w, int ht){
		name = n;
		quip = q;
		hp = h;
		level = l;
		gold = g;
		location = new Rectangle((int)p.getX(), (int)p.getY(), w, ht);
	}
	/**
	 * abstract method attack
	 * @param c, character
	 */
	public abstract int attack(Character c);
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
	}
	/**
	 * heal the character
	 * @param h, amount to heal
	 */
	public void heal(int h){
		hp += h;
	}
	/**
	 * character takes damage
	 * @param h, amount to damage
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
	 * gets the location of the object
	 * @return location of the object
	 */
	public Point getLocation(){
		return location.getLocation();
	}
	/**
	 * getBounds gets the Rectangle of the object (used for collision)
	 * @return Rectangle of object
	 */
	public Rectangle getBounds(){
		return location;
	}
	/**
	 * getWidth gets the width of the object
	 * @return width of the object
	 */
	public int getWidth(){
		return (int) location.getWidth();
	}
	/**
	 * getHeight gets the height of the object
	 * @return the height of the object
	 */
	public int getHeight(){
		return (int) location.getHeight();
	}
	/**
	 * isDead returns true if the object has equal or less than 0 HP, otherwise false
	 * @return true if the object has equal or less than 0 HP, otherwise false
	 */
	public boolean isDead(){
		boolean dead = false;
		if(hp <= 0) dead = true;
		return dead;
	}
	/**
	 * setWidth sets the width of the object
	 * @param w, sets the width
	 */
	public void setWidth(int w){
		location.width = w;
	}
	/**
	 * setHeight sets the height of the object
	 * @param h, sets the height
	 */
	public void setHeight(int h){
		location.height = h;
	}
	/**
	 * sets the location of the object (for moving the hero)
	 * @param x, x position
	 * @param y, y position
	 */
	public void setLocation(int x, int y){
		Point p = new Point((int)this.getLocation().getX() + x,(int)this.getLocation().getY() + y);
		location.setLocation(p);
	}
	/**
	 * sets the position of the object
	 * @param p, Point to set
	 */
	public void setPosition(Point p){
		location.setLocation(p);
	}
	/**
	 * update draws/renders the object
	 * @param g, the Graphics object from the library which draws the objects
	 */
	public void update(Graphics g){
		// renders the object
		draw(g, getLocation(), getWidth(), getHeight());
	}
	/**
	 * abstract method for drawing/rendering the object
	 * @param g, the Graphics
	 * @param p, the Point
	 * @param w, the width
	 * @param h, the height
	 */
	public abstract void draw(Graphics g, Point p, int w, int h);
}
