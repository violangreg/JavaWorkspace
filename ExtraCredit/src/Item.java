//Greg Paolo Violan, 011706641
/**
 * imported necessary api's
 */
import java.awt.*;
import java.io.*;
import java.util.*;
/**
 * Item class implements Serializable. Item class constructs Item objects that has name and gold value
 * @author Greg
 */
@SuppressWarnings("serial")
public class Item implements Serializable{
	/**
	 * private String name of object
	 */
	private String name;
	/**
	 * private int goldValue of object
	 */
	private int goldValue;
	/**
	 * Item constructor
	 * @param n, name of item
	 * @param v, value of item
	 */
	public Item(String n, int v){
		name = n;
		goldValue = v;
	}
	/**
	 * returns the name of the item
	 * @return name of item
	 */
	public String getName(){
		return name;
	}
	/**
	 * returns the value of the item
	 * @return value of item
	 */
	public int getValue(){
		return goldValue;
	}
	/**
	 * creates a Point for the object
	 * @return a Point
	 */
	public Point getPoint(){
		int x, y;
		x = y = 0;
		Random r = new Random();
		int randomPosition = r.nextInt(3) + 1;
		if(randomPosition == 1){
			x = 652;
			y = 167;
		}
		else if(randomPosition == 2){ 
			x = 723;
			y = 313;
		}
		else if(randomPosition == 3){
			x = 586;
			y = 171;
		}
		return new Point(x,y);
	}
	/**
	 * gets the Image of the object & put a point
	 * @return Image of object
	 */
	public Image getImage(){
		Point p = this.getPoint();
		Image img = null;
		
		if(this.getName().equals("Health Potion")){
			img = new Image((int)p.getX(), (int)p.getY(), "potion.png", null);
		}
		else{
			img = new Image((int)p.getX(), (int)p.getY(), "Items.png", null);
		}
		return img;
	}
}
