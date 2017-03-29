//Greg Paolo Violan, 011706641
/**
 * imported necessary api's
 */
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
/**
 * Hero class extending Character and implementing Serializable 
 * @author Greg
 *
 */
public class Hero extends Character implements Serializable {
	/**
	 * Hero class constructor
	 * @param n, name of hero
	 * @param q, quip of hero
	 * @param start, starting location of hero
	 */
	public Hero(String n, String q, Point start){
		super(n, q, 10, 1, 0);
		location = start;
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
	/**
	 * gets the location of this object
	 * @return location
	 */
	public Point getLocation(){
		return location;
	}
	/**
	 * set location of object
	 * @param p, set location
	 */
	public void setLocation(Point p){
		location = p;
	}
	/**
	 * go north
	 * @param l, level of map
	 * @return new room of north location
	 */
	public char goNorth(Level l){
		Point move = location;
		move.setLocation(move.getX() - 1, move.getY());
		return l.getRoom(move);
	}
	/**
	 * go south
	 * @param l, level of map
	 * @return new room of south location
	 */
	public char goSouth(Level l){
		Point move = location;
		move.setLocation(move.getX() + 1, move.getY());
		return l.getRoom(move);
	}
	/**
	 * go east
	 * @param l, level of map
	 * @return new room of east location
	 */
	public char goEast(Level l){
		Point move = location;
		move.setLocation(move.getX(), move.getY() + 1);
		return l.getRoom(move);
	}
	/**
	 * go west
	 * @param l, level of map
	 * @return new room of west location
	 */
	public char goWest(Level l){
		Point move = location;
		move.setLocation(move.getX(), move.getY() - 1);
		return l.getRoom(move);
	}
	/**
	 * abstract method from character, overriding it with attack c
	 */
	@Override
	public void attack(Character c) {
		Random rand = new Random();
		int randomNum;
		
		randomNum = rand.nextInt((3*getLevel() - (1+getLevel())) + 1) + (1+getLevel());
		c.takeDamage(randomNum);
		System.out.println(getName() + " hits a " + c.getName() + " for " + randomNum + " damage");
	}
	/**
	 * private point location
	 */
	private Point location;
	/**
	 * private items array
	 */
	private ArrayList<Item> items = new ArrayList<Item>();
}
