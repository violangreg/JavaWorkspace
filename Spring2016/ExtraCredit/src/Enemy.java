//Greg Paolo Violan, 011706641
/**
 * imported necessary api's
 */
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;
/**
 * Enemy class extending Character class, Enemy class constructs Enemy objects that is used
 * in the game. It has a name, quip, hp, level, gold, and item
 * @author Greg
 */
@SuppressWarnings("serial")
public class Enemy extends Character {
	/**
	 * private Image of the object for rendering object's graphics
	 */
	private Image img;
	/**
	 * private String for the String file name
	 */
	private String imS;
	/**
	 * Enemy constructor,
	 * @param n, name of enemy
	 * @param q, quip of enemy
	 * @param h, hp of enemy
	 * @param l, level of enemy
	 * @param g, gold of enemy
	 * @param i, item of enemy
	 */
	public Enemy(String n, String q, int h, int l, int g, Item i, Point p, String im){
		super(n, q, h*2, l, g, p, 50, 50);
		item = i;
		imS = im;
		img = new Image((int)p.getX(), (int)p.getY(), im, null);
	}
	@Override
	/**
	 * Abstract method attack overriding, attacks with random damage
	 * @param c, character that is being attack
	 */
	public int attack(Character c) {
		Random rand = new Random();
		int randomNum;
		
		randomNum = rand.nextInt((2*getLevel() - 1+getLevel()) + 1) + 1;
		c.takeDamage(randomNum);
		return randomNum;
	}
	/**
	 * returns the item of this object
	 * @return returns the item
	 */
	public Item getItem(){
		return item;
	}
	/**
	 * returns the string name of the img file
	 * @return imS, image name of the file
	 */
	public String getImageString(){
		return imS;
	}
	/**
	 * 
	 * @return the Image of the object
	 */
	public Image getImage(){
		return img;
	}
	/**
	 * private Item for the item in this object
	 */
	private Item item;
	@Override
	/**
	 * draw renders graphics of the object in the game
	 * @param g, Graphics object
	 * @param p, Point of object
	 * @param w, width of object
	 * @param h, height of object
	 */
	public void draw(Graphics g, Point p, int w, int h) {
		img.paintComponent(g);
	}
}
