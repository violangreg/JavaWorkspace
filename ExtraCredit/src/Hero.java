//Greg Paolo Violan, 011706641
/**
 * imported necessary api's
 */
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;

import javax.imageio.ImageIO;
/**
 * Hero class extending Character and implementing Serializable. This class handles Hero object such as 
 * its name, quip, point, and direction
 * @author Greg
 */
@SuppressWarnings("serial")
public class Hero extends Character implements Serializable {
	/**
	 * private items array
	 */
	private ArrayList<Item> items = new ArrayList<Item>();
	/**
	 * private int direction for the direction movement of object
	 */
	private int direction;
	/**
	 * Hero class constructor
	 * @param n, name of hero
	 * @param q, quip of hero
	 * @param start, starting location of hero
	 */
	public Hero(String n, String q, Point p, int dir){
		super(n, q, 162, 1, 0, p, 40, 40);
		direction = dir;
	}
	/**
	 * getDirections gets direction of the object
	 * @return direction, the direction of the object
	 */
	public int getDirection(){
		return direction;
	}
	/**
	 * setDirection sets the direction of the object from (1-8 cardinal points, 1 being north -> 2 north-east -> ... -> 8 being north-west)
	 * @param d, sets the direction depending on the parameter (1-8 cardinal points)
	 */
	public void setDirection(int d){
		direction = d;
	}
	/**
	 * returns Array of items
	 * @return items array
	 */
	public ArrayList<Item> getItems(){
		return items;
	}
	/**
	 * picks up items if array item size is more than 5, true and dont pick up
	 * @param i, passes in item object
	 * @return, return true or false
	 */
	public boolean pickUpItem(Item i){
		if(items.size() > 5){
			return true;
		}else{
			items.add(i);
			return false;
		}
	}
	/**
	 * remove item in items array
	 * @param i, passes in item object
	 */
	public void removeItem(Item i){
		items.remove(i);
	}
	/**
	 * remove items in items array in a particular index
	 * @param index, index of item
	 */
	public void removeItem(int index){ //REMOVES ITEM FROM THE ITEM ARRAYLIST
		items.remove(index);
	}
	@Override
	/**
	 * abstract method from character, overriding it with attack c
	 * @param c, the character object that is being attack
	 */
	public int attack(Character c) {
		Random rand = new Random();
		int randomNum;
		
		randomNum = rand.nextInt((3*getLevel() - (1+getLevel())) + 1) + (1+getLevel());
		c.takeDamage(randomNum);
		return randomNum;
	}
	/**
	 * move method moves the hero up, down, left, or right in 70 pixels
	 */
	public void move(){
		switch(direction){
			case 1: 
				this.setLocation(0, -70);
				break;
			case 2:
				this.setLocation(70, 0);
				break;
			case 3:
				this.setLocation(0, 70);
				break;
			case 4:
				this.setLocation(-70, 0);
				break;
		}
	}
	@Override
	/**
	 * draw method draw/renders the hero's graphics
	 * @param g, the graphics
	 * @param p, the point of the object
	 * @param w, the width of the object
	 * @param h, the height of the object
	 */
	public void draw(Graphics g, Point p, int w, int h) {
		// hero's hp
		BufferedImage heroRight = null;
		BufferedImage heroLeft = null;
		BufferedImage heroBack = null;
		
		g.setColor(Color.RED);
		g.fillRect(980/2 - 340, 605/2 - 138, this.getHp(), 15);
		
		try {
			heroRight = ImageIO.read(new File("shannonRight.png"));
			heroLeft = ImageIO.read(new File("shannonLeft.png"));
			heroBack = ImageIO.read(new File("shannonBack.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		switch(direction){
		case 1:
			// north
			g.drawImage(heroBack,(int) p.getX(), (int) p.getY(), null);
			break;
		case 2:
			// east
			g.drawImage(heroRight, (int) p.getX(), (int) p.getY(), null);
			break;
		case 3:
			// south
			g.drawImage(heroRight, (int) p.getX(), (int) p.getY(), null);
			break;
		case 4:
			// west
			g.drawImage(heroLeft,(int) p.getX(), (int) p.getY(), null);
			break;
		}
	}
}
