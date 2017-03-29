/**
 * Greg Paolo Violan, 011706641
 * Date: 2/22/16
 * Program: Assignment 2
 * Description: Project2 CECS277, Dungeon Interactive Game with 2d map. Random generated items and monsters
 * by using IO files. Object IO stream for saving game point.
 */

/**
 * imported necessary api's
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;
import java.util.Scanner;
/**
 * Project2 CECS277, Dungeon Interactive Game with 2d map. Random generated items and monsters
 * by using IO files. Object IO stream for saving game point.
 * @author Greg
 *
 */
public class Project2 implements Serializable {
/**
 * Main method to begin game, it instantiates necessary objects and make uses of ObjectInputStream
 * to load hero object file. 
 * @param args
 */
	public static void main(String[] args)
	{
		Hero hero = null;
		Level level = null;
		char room = 0;
		File f = new File("hero.dat");
		
		if(f.exists()){
			try{
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
				hero = (Hero) in.readObject();
				in.close();
				level = new Level();
				
				System.out.println(hero.getName() + " is at level " + hero.getLevel());
				
				level.generateLevel(hero.getLevel());
				
			}catch(IOException e){
				System.out.println("Error processing file");
			}catch(ClassNotFoundException e){
				System.out.println("Cou1ld not find class.");
			}
		}
		else{
			level = new Level();
			level.generateLevel(1);		
			String name = menu();
			hero = new Hero(name, "Hell ya!", level.findStartLocation());
			
		}
		
		while(hero.getHp() > 0){
			
			room = navigation(hero, level);
			level.displayMap(hero.getLocation());
			
			if(room == 's'){
				heroMenu(hero);
			}
			else if(room == 'm'){
				combatInterface(hero, level, room, f);
			}
			else if(room == 'i'){
				itemInteface(hero);
			}
			else if(room == 'f'){
				exitLevel(hero, level, f);
			}
			
		}
		
		System.out.println("You've died!\nGame Over!");
		System.exit(0);
		
		
	}
	/**
	 * Menu method for the beginning of the game, prompts user's name
	 * @return
	 */
	public static String menu(){
		Scanner scnr = new Scanner(System.in);
		String name;
		System.out.print("What is your name, traveler? ");
		name = scnr.nextLine();
		System.out.println(name + " enters the Mushin Tower!");
		return name;
	}
	/**
	 * Navigation method for navigating throughout the map of the game
	 * @param hero, user character's object
	 * @param level, current user's map object
	 * @return the room of the map the user currently is in
	 */
	public static char navigation(Hero hero, Level level){
		Scanner scnr = new Scanner(System.in);
		String path;
		char room = 0;
		boolean k = false;
		
		while(!k){
			hero.display();
			level.displayMap(hero.getLocation());
			
			System.out.println("Choose a path");
			System.out.println(String.format("%-5s", "1. North"));
			System.out.println(String.format("%-5s", "2. South"));
			System.out.println(String.format("%-5s", "3. East"));
			System.out.println(String.format("%-5s", "4. West"));
			path = scnr.nextLine();
			
			if(path.equals("1") || path.equals("2") || path.equals("3") || path.equals("4")){
				if(path.equals("1")){
					if(hero.getLocation().getX() - 1 == -1 || hero.getLocation().getX() - 1 == 4) System.out.println("You cannot go that path");
					else{
						room = hero.goNorth(level);
						k = true;
					}
				}
				else if(path.equals("2")){
					if(hero.getLocation().getX() + 1 == -1 || hero.getLocation().getX() + 1 == 4) System.out.println("You cannot go that path");
					else{
						room = hero.goSouth(level);
						k = true;
					}
				}
				else if(path.equals("3")){
					if(hero.getLocation().getY() + 1 == -1 || hero.getLocation().getY() + 1 == 4) System.out.println("You cannot go that path");
					else{
						room = hero.goEast(level);
						k = true;
					}
				}
				else if(path.equals("4")){
					if(hero.getLocation().getY() - 1 == -1 || hero.getLocation().getY() - 1 == 4) System.out.println("You cannot go that path");
					else{
						room = hero.goWest(level);
						k = true;
					}
				}else System.out.println("bug");
			}
			else{
				System.out.println("Invalid input");
			}
		}
		return room;
		}
	/**
	 * Combat Interface method, when room is 'm', this is called, it begins a battle with a randomly generated monster
	 * @param hero, user's character object
	 * @param level, user's current map level
	 * @param room, user's current room location
	 * @param f, file for saving the game, ObjectOutputStream
	 */
	public static void combatInterface(Hero hero, Level level, char room, File f){
		Scanner scnr = new Scanner(System.in);
		EnemyGenerator enemyGen = new EnemyGenerator();
		Enemy enemy = enemyGen.generateEnemy(hero.getLevel());
		String input;
		
		System.out.println(hero.getName() + " has encounters a " + enemy.getName());
		
		boolean s = false;
		while(!s){
			boolean potion = false;
			for(int i = 0; i < hero.getItems().size(); ++i){
				if(hero.getItems().get(i).getName().equals("Health Potion")){
					potion = true;
				}
				else potion = false;
			}
			
			System.out.println(hero.getName() + " has " + hero.getHp() + " health");
			System.out.println(enemy.getName() + " has " + enemy.getHp() + " health");
			System.out.println("What do you want to do?");
			System.out.println("1. Run away");
			System.out.println("2. Attack");
			if(potion == true){
				System.out.println("3. Use Potion");
			}
			
			input = scnr.nextLine();
			
			if(input.equals("1")){
				randomDirection(hero, level, room, f);
				s = true;
				//s = flee(hero, level, enemy);
			}
			else if(input.equals("2")){
				heroCombat(hero, level, enemy);
				if(enemy.getHp() <= 0){
					s = true;
				}
			}
			else if(potion){
				if(input.equals("3")){
					if(hero.getHp() < (hero.getLevel()*10)){
						potion(hero);
					}
					else {
						System.out.println("Your hp is full!");
						potion = false;
					}
					
				}
			}
			else System.out.println("Invalid Input");
		}
	}
	/**
	 * Flee method, just another flee method I came up with. If failed to flee, you get hit, if you do flee you can choose path
	 * @param hero, user's character object 
	 * @param level, user's current map level
	 * @param e, randomly generated enemy
	 * @return boolean flee
	 */
	public static boolean flee(Hero hero, Level level, Enemy e){
		boolean fled = false;
		Random rand = new Random();
		int flee;
		flee = rand.nextInt((4 - 1) + 1) + 1;
		if(flee == 1){
			System.out.println("You failed to flee");
			e.attack(hero);
			System.out.println(hero.getName() + " has " + hero.getHp() + " health.");
			return false;
		}
		else{
			System.out.println("You successfully ran away!");
			fled = true;
		}
		return fled;
	}
	/**
	 * Chooses a random direction for running away
	 * @param hero, user's character object
	 * @param level, user's current map level
	 * @param room, user's current room location
	 * @param f, file for object IO stream
	 */
	public static void randomDirection(Hero hero, Level level, char room, File f){
		
		Random rand = new Random();
		int randomDirection = rand.nextInt((4 - 1) + 1) +1; 
		
		boolean s = false;
		while(!s){
			
			if(randomDirection == 1){
				if(hero.getLocation().getX() - 1 == -1 || hero.getLocation().getX() - 1 == 4){
					randomDirection = rand.nextInt((4 - 1) + 1) +1; 
				}
				else{
					
					System.out.println("You ran away north");
					room = hero.goNorth(level);
					
					level.displayMap(hero.getLocation());
					
					if(room == 's'){
						heroMenu(hero);
					}
					else if(room == 'm'){
						combatInterface(hero, level, room, f);
					}
					else if(room == 'i'){
						itemInteface(hero);
					}
					else if(room == 'f'){
						exitLevel(hero, level, f);
					}
					
					s = true;
				}
			}
			else if(randomDirection == 2){
				if (hero.getLocation().getX() + 1 == -1 || hero.getLocation().getX() + 1 == 4){
					randomDirection = rand.nextInt((4 - 1) + 1) +1; 
				}
				else {
					
					System.out.println("You ran away south");
					room = hero.goSouth(level);
					
					level.displayMap(hero.getLocation());
					
					if(room == 's'){
						heroMenu(hero);
					}
					else if(room == 'm'){
						combatInterface(hero, level, room, f);
					}
					else if(room == 'i'){
						itemInteface(hero);
					}
					else if(room == 'f'){
						exitLevel(hero, level, f);
					}
					s = true;
				}
			}
			else if(randomDirection == 3)
				if(hero.getLocation().getY() + 1 == -1 || hero.getLocation().getY() + 1 == 4){
					randomDirection = rand.nextInt((4 - 1) + 1) +1; 
				}
				else{
					
					System.out.println("You ran away east");
					room = hero.goEast(level);
					
					level.displayMap(hero.getLocation());
					if(room == 's'){
						heroMenu(hero);
					}
					else if(room == 'm'){
						combatInterface(hero, level, room, f);
					}
					else if(room == 'i'){
						itemInteface(hero);
					}
					else if(room == 'f'){
						exitLevel(hero, level, f);
					}
					s = true;
				}
			else if(randomDirection == 4) {
				if(hero.getLocation().getY() - 1 == -1 || hero.getLocation().getY() - 1 == 4){
					randomDirection = rand.nextInt((4 - 1) + 1) +1; 
				}
				else {
					
					System.out.println("You ran away west");
					room = hero.goWest(level);
					
					level.displayMap(hero.getLocation());
					
					if(room == 's'){
						heroMenu(hero);
					}
					else if(room == 'm'){
						combatInterface(hero, level, room, f);
					}
					else if(room == 'i'){
						itemInteface(hero);
					}
					else if(room == 'f'){
						exitLevel(hero, level, f);
					}
					s = true;
				}
			}
		}
		
	return;
		
	}
	/**
	 * Hero Combat method, fighting starts and drops occurs
	 * @param hero, user's character object
	 * @param level, user's current map level
	 * @param enemy, randomly generated enemy
	 */
	public static void heroCombat(Hero hero, Level level, Enemy enemy){
		hero.attack(enemy);
		enemy.attack(hero);
		
		if(hero.getHp() <= 0){
			System.out.println("You've died!\nGame Over!");
			System.exit(0);
		}
		
		
		if(enemy.getHp() <= 0){
			System.out.println(enemy.getQuip());
			System.out.println(hero.getName() + " says " + "'" + hero.getQuip() + "'");
			if(enemy.getGold() != 0){
				System.out.println(hero.getName() + " receives " + enemy.getGold() + " gold.");
				hero.collectGold(enemy.getGold());
			}
			if(!enemy.getItem().getName().equals(("NO_DROP"))){
				if(hero.getItems().size() < 5){
					System.out.println(hero.getName() + " receives " + enemy.getItem().getName() + ".");
					hero.pickUpItem(enemy.getItem());
				}
				else{
					System.out.println("Your bag is full.");
					System.out.println(enemy.getItem().getName() + " is sold for " + enemy.getItem().getValue() + " gold.");
					hero.collectGold(enemy.getItem().getValue());
				}
			}
		}
	}
	/**
	 * Potion method to heal user's character
	 * @param hero, user's character object
	 */
	public static void potion(Hero hero){
		int heal;
		heal = (hero.getLevel() * 10) - hero.getHp();
		hero.heal(heal);
		
		for(int i = 0; i < hero.getItems().size(); ++i){
			if(hero.getItems().get(i).getName().equals("Health Potion")){
				hero.getItems().remove(i);
			}
		}
		System.out.println("You healed for " + heal + " health.");
	}
	/**
	 * Item Interface method, generates a random item and prompt's user to keep or sell
	 * @param hero, user's character object
	 */
	public static void itemInteface(Hero hero){
		Scanner scnr = new Scanner(System.in);
		ItemGenerator itemGen = new ItemGenerator();
		Item item = itemGen.generateItem();
		
		String input;
		
		boolean k = false;
		System.out.println(hero.getName() + " found a " + item.getName());
		while(!k){
			System.out.println("What do you want to do?");
			System.out.println("1. Keep it");
			System.out.println("2. Sell it");
			input = scnr.nextLine();
			if(input.equals("1")){
				if(hero.getItems().size() < 5){
					hero.getItems().add(item);
					k = true;
				}
				else{
					System.out.println("Your bag is full.");
					hero.collectGold(item.getValue());
					System.out.println(item.getName() + " was sold for " + item.getValue() + " gold.");
					k = true;
				}
			}
			else if(input.equals("2")){
				hero.collectGold(item.getValue());
				System.out.println(item.getName() + " was sold for " + item.getValue() + " gold.");
				k = true;
			}else System.out.println("Invalid input");
		}
			
		
	}
	/**
	 * Hero Menu
	 * @param hero, user's character object
	 */
	public static void heroMenu(Hero hero){
		Scanner scnr = new Scanner(System.in);
		String input;
		
		hero.display();
		boolean s = false;
		while(!s){
			for(int i = 1; i <= hero.getItems().size() ; ++i){
				System.out.println(i + ". " + hero.getItems().get(i-1).getName());
			}
			System.out.println("c: cancel");
			System.out.println("Do you want to sell something?");
			input = scnr.nextLine();
			
			int p = 0;
			
			for(p = 1; p <= hero.getItems().size(); ++p){
				if(input.equals(Integer.toString(p))){
					hero.collectGold(hero.getItems().get(p-1).getValue());
					System.out.println(hero.getItems().get(p-1).getName() + " was sold for " + hero.getItems().get(p-1).getValue() + " gold.");
					hero.removeItem(hero.getItems().get(p-1));
					if(hero.getItems().isEmpty()) s = true;
					hero.display();
				}
			}
			if(input.equalsIgnoreCase("c")) s = true;
		}
	}
	/**
	 * Exit Level method, increases user's character level and change current map level and save hero object output stream 
	 * @param hero, user's character object
	 * @param level, user's current map level
	 * @param f, file for object IO stream
	 */
	public static void exitLevel(Hero hero, Level level, File f){
		hero.increaseLevel();
		
		if(hero.getLevel() == 4){
			System.out.println("You conquered Mushin Tower!");
			System.out.println("Thank you for playing!");
			System.exit(0);
		}
		
		level.generateLevel(hero.getLevel());
		hero.setLocation(level.findStartLocation());
		System.out.println("You've entered level " + hero.getLevel());
		
		try{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
			out.writeObject(hero);
			out.close();
		}catch(IOException e){
			System.out.println("Error processing file");
		}
	}
}
