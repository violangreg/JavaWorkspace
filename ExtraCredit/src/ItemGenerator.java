//Greg Paolo Violan, 011706641
/**
 * imported necessary api's
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
/**
 * Item Generator class is for generating random items
 * @author Greg
 */
public class ItemGenerator {
	/**
	 * private array for items
	 */
	private ArrayList<Item> itemList = new ArrayList<Item>();
	/**
	 * Item Generator constructor, takes the ItemList file and puts it in an array
	 */
	public ItemGenerator(){
		try{
			Scanner read = new Scanner(new File("ItemList.txt"));
			//ADD ALL ITEMS FROM ItemList.txt to itemList Array
			do{
				read.useDelimiter(",");
				String line = read.nextLine();
				String[] tokens = line.split(",");
				
				                      //NAME     //GOLD
				Item item = new Item(tokens[0], Integer.parseInt(tokens[1]));
				
				itemList.add(item);
				
			}while(read.hasNext());
			read.close();
			}catch(FileNotFoundException fnf){
				System.out.println("FNF");
			}
	}
	/**
	 * Generate Item class, genetrates random items 
	 * @return the item
	 */
	public Item generateItem(){
		
		Item item = null;
		Random rand = new Random();
		int randomItem = rand.nextInt((8 - 0) + 1) + 0;
		
		//GENERATES A RANDOM ITEM from the array index 0-8
		item = new Item(itemList.get(randomItem).getName(), itemList.get(randomItem).getValue());
		
		return item;
	}
}
