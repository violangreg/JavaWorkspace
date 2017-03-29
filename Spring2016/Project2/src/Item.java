//Greg Paolo Violan, 011706641
/**
 * imported necessary api's
 */
import java.io.Serializable;
/**
 * Item class implements Serializable
 * @author Greg
 *
 */
public class Item implements Serializable{
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
	 * private String name of object
	 */
	private String name;
	/**
	 * private int goldValue of object
	 */
	private int goldValue;
}
