//Greg Paolo D. Violan
//011706641
/**
 * imports the needed for implementation
 */
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * Project 1 for CECS277, Star Wars Interactive Scene. This assignment make use of inheritance, extension, interface
 * and try, catch exception.
 * @author Greg Violan
 */

public class Project1 {		
	/**
	 * main method for project
	 * @param args
	 */
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner scnr = new Scanner(System.in);
		
		Entity[] entity = new Entity[16]; 
		
		Entity en = new Jedi("hi","h", "h");
		
		
		ArrayList<Entity> allies = new ArrayList<Entity>();
		entity[1] = new Rebel("Zeila", "Pewpew pew pew!!");
		entity[2] = new Rebel("Philander", "Stormtrooper SCUM *cough*");
		entity[3] = new Rebel("Kevin", "....");
		entity[4] = new Rebel("Paulo", "It's smoking hot in here!");
		entity[5] = new Rebel("Peter", "*Spits* have a piece of me noob!");
		entity[6] = new Medical("Medik-87", 5);
		entity[7] = new Astromech("HL-U", 5);
	    
		ArrayList<Entity> enemies = new ArrayList<Entity>();
		entity[8] = new SithLord("Stevestein", "This is your end! MWAHAHA", "red"); //enemies[0]
		entity[9] = new Stormtrooper("Vincent", "Can you see that?? Yup, thats your new grave ahah"); //enemies[1]
		entity[10] = new Stormtrooper("Char", "BOOM HEADSHOT!!"); //enemies[2]
		entity[11] = new Stormtrooper("Matthew", "What is this stench I smell? Oh just a rebel."); //enemies[3]
		entity[12] = new Stormtrooper("Josh", "Give me some of that rebel meat!!"); //enemies[4]
		entity[13] = new Stormtrooper("Andrew", "Here we go again~~"); //enemies[5]
		entity[14] = new Door("Plasma Door"); //enemies[6]
		entity[15] = new Computer("FF-75"); //enemies[7]
		for(int ii = 8; ii <= 15; ++ii){
			enemies.add(entity[ii]);
		}
		
		String name;
		String color;
		
		System.out.println("Welcome to the Star Wars Galaxy");
		System.out.println("Please choose a name for your Jedi:");
		name = scnr.nextLine();
		
		System.out.println("Please choose a color for your Lightsaber:");
		color = scnr.nextLine();
		
		entity[0] = new Jedi(name, "May the force be with you!!", color);
		
		for(int ii = 0; ii <= 7; ++ii ){
			allies.add(entity[ii]);
		}
		
		int mission = 0;
		boolean s = false;
		while(!s)
		{
			try{
				System.out.println("Choose a mission:");
				System.out.println("1. Hunt the Sith Lord");
				System.out.println("2. Capture the Imperial Base");
				mission = scnr.nextInt();
				s = true;
			}catch(InputMismatchException e){
				System.out.println("Error, please try again");
				scnr.next();
			}
			System.out.println("");
		}	
		
		if(mission == 1) missionOne(entity, allies, enemies, mission);
		else missionTwo(entity, allies, enemies, mission);
	}
	/**
	 * 	Mission One method for killing the Sith Lord
	 * @param entity array, passing in the interactive objects
	 * @param mission option (1 or 2) between killing the Sith Lord or Capturing the base
	 */
	public static void missionOne(Entity[] entity, ArrayList<Entity> allies, ArrayList<Entity> enemies, int mission)
	{
		System.out.println("You run across the Sith Lord, he has troops with him.  Attack!");
		
		while(allies.get(0).getHp() >= 0 || enemies.get(0).getHp() >= 0)
		{
			hpInterface(allies, enemies, mission);
			actionInterface(allies, enemies, mission);
			AI(entity, allies, enemies, mission);
			if(allies.get(0).getHp() <= 0 || allies.get(0).getHp() <= 0) gameOver(allies, enemies);
		}
	}
	/**
	 * Begins Mission Two, capture the Imperial Base!
	 * @param entity array, passing in the interactive objects
	 * @param mission option (1 or 2) between killing the Sith Lord or Capturing the base
	 */
	public static void missionTwo(Entity[] entity, ArrayList<Entity> allies, ArrayList<Entity> enemies, int mission)
	{
		System.out.println("Capture the Imperial base!! Stormtroopers are blocking the " + enemies.get(6).getName());
		
		while(allies.get(0).getHp() >= 0 || enemies.get(7).getHp() >= 0)
		{
			hpInterface(allies, enemies, mission);
			actionInterface(allies, enemies, mission);
			AI(entity, allies, enemies, mission);
			if(enemies.get(6).getHp() <= 0 || allies.get(0).getHp() <= 0)
			{
				if(allies.get(0).getHp() <= 0) gameOver(allies, enemies);
				enemies.get(6).doTask(enemies.get(7));
				if(enemies.get(6).getTask().equals("w"))
				{
					gameOver(allies, enemies);
				}
			}
		}
	}
	/**
	 * Hp interface for the objects
	 * @param entity array, passing in the interactive objects
	 * @param mission option (1 or 2) between killing the Sith Lord or Capturing the base
	 */
	public static void hpInterface(ArrayList<Entity> allies, ArrayList<Entity> enemies, int mission)
	{
		System.out.println();
		System.out.println("Good guys");
		System.out.println("---------");
		for(int ii = 0; ii <= 7; ++ii) System.out.println(String.format("%-20s %s" , allies.get(ii).getName(), allies.get(ii).getHp() ));
		
		System.out.println("");
		
		System.out.println("Bad guys");
		System.out.println("---------");
		if(mission == 1) for(int ii = 0; ii <= 5; ++ii) System.out.println(String.format("%-20s %s" , enemies.get(ii).getName(), enemies.get(ii).getHp() ));
		else for(int ii = 6; ii >= 1; --ii) System.out.println(String.format("%-20s %s" , enemies.get(ii).getName(), enemies.get(ii).getHp() ));
	}
	/**
	 * action interface to choose between which action to do and to who
	 * @param entity array, passing in the interactive objects
	 * @param mission option (1 or 2) between killing the Sith Lord or Capturing the base
	 */
	public static void actionInterface(ArrayList<Entity> allies, ArrayList<Entity> enemies, int mission)
	{
		@SuppressWarnings("resource")
		Scanner scnr = new Scanner(System.in);
		int action = 0;
		
		boolean s = false;
		while(!s)
		{
			try{
				System.out.println("");
				System.out.println("What do you want to do?");
				System.out.println("1. Attack with lightsaber");
				System.out.println("2. Attack using the force");
				System.out.println("3. Have the droid to heal someone");
				if(mission == 2) System.out.println("4. Try to hack the " + enemies.get(6).getName());
				action = scnr.nextInt();
				if(action == 1 || action == 2 || action == 3) s = true;
				else if(mission == 2 && action == 1 || action == 2 || action == 3 || action == 4) s = true;
			}catch(InputMismatchException e){
				System.out.println("Error, please try again");
				scnr.next();
			}
		}
		if(action == 1 || action == 2)
		{	
			int target = 0;
			
			boolean k = false;
			
			while(!k)
			{
				if(action == 1)
				{	
					allies.get(0).setTask("a");
					
					target = target(allies, enemies, mission);
					
					doTarget(allies, enemies, mission, target);
					
					k = true;
				}
				else if(action == 2)
				{
					allies.get(0).setTask("f");
					
					target = target(allies, enemies, mission);
					
					doTarget(allies, enemies, mission, target);
					
					k = true;
				}
			}
		}
		else if(action == 3)
		{
			int targetOption = 0;
			
			boolean k = false;
			while(!k)
			{
				try{
				System.out.println("Choose someone to heal:");
				System.out.println("1. " + allies.get(0).getName());
				System.out.println("2. " + allies.get(1).getName());
				System.out.println("3. " + allies.get(2).getName());
				System.out.println("4. " + allies.get(3).getName());
				System.out.println("5. " + allies.get(4).getName());
				System.out.println("6. " + allies.get(5).getName());
				targetOption = scnr.nextInt();
				if(targetOption == 1 || targetOption == 2 || targetOption == 3 || targetOption == 4 || targetOption == 5 || targetOption == 6) k = true;
				}catch(InputMismatchException e)
				{
					System.out.println("Error, please try again");
					System.out.println("");
					scnr.next();
				}
				
			}	
				switch(targetOption)
				{
					case 1: allies.get(6).doTask(allies.get(0));
							if(allies.get(6).getTask().equals("h")) return;
							else {
								if(enemies.get(0).getTask().equals("d")) gameOver(allies, enemies);
								actionInterface(allies, enemies, mission);
							}
							break;
					case 2: allies.get(6).doTask(allies.get(1));
							if(allies.get(6).getTask().equals("h")) return;
							else {
								if(enemies.get(0).getTask().equals("d")) gameOver(allies, enemies);
								actionInterface(allies, enemies, mission);
							}
							break;
					case 3: allies.get(6).doTask(allies.get(2));
							if(allies.get(6).getTask().equals("h")) return;
							else {
								if(enemies.get(0).getTask().equals("d")) gameOver(allies, enemies);
								actionInterface(allies, enemies, mission);
							}
							break;
					case 4: allies.get(6).doTask(allies.get(3));
							if(allies.get(6).getTask().equals("h")) return;
							else {
								if(enemies.get(0).getTask().equals("d")) gameOver(allies, enemies);
								actionInterface(allies, enemies, mission);
							}
							break;
					case 5: allies.get(6).doTask(allies.get(4));
							if(allies.get(6).getTask().equals("h")) return;
							else {
								if(enemies.get(0).getTask().equals("d")) gameOver(allies, enemies);
								actionInterface(allies, enemies, mission);
							}
							break;
					case 6: allies.get(6).doTask(allies.get(5));
							if(allies.get(6).getTask().equals("h")) return;
							else {
								if(enemies.get(0).getTask().equals("d")) gameOver(allies, enemies);
								actionInterface(allies, enemies, mission);
							}
							break;
					default: System.out.println("Error, please try again");
							 System.out.println("");
							break;
				}
		}else if(action == 4){
			allies.get(7).doTask(enemies.get(7));
			if(allies.get(7).getTask().equals("s")){
				enemies.get(7).doTask(enemies.get(7));
				if(enemies.get(7).getTask().equals("w")) gameOver(allies, enemies);
			}
			else if(allies.get(7).getTask().equals("r")) actionInterface(allies, enemies, mission);
		}
	}
	/**
	 * AI interface to automatically do actions for the AI allies and enemies
	 * @param entity array, passing in the interactive objects
	 * @param mission option (1 or 2) between killing the Sith Lord or Capturing the base
	 */
	public static void AI(Entity[] entity, ArrayList<Entity> allies, ArrayList<Entity> enemies, int mission)
	{
		Random rand = new Random();
		int randomEnemy;
		int randomAlly;
		int randomaction;
	
		for(int ii = 0; ii <= 15; ++ii)
		{	
			if(entity[ii].getHp() <= 0)
			{
				entity[ii].setTask("d");
				
				if(mission == 2)
				{
					if(ii >= 9 || ii <= 13)
					{
						entity[ii].setTask("k");
					}
					if(entity[14].getHp() <= 0)
					{
						entity[15].doTask(entity[15]);
						if(entity[15].getTask().equals("w")) gameOver(allies, enemies);
					}
				}
			}
			else entity[ii].setTask("a");
		}
		
		//sithlord attack
		if(mission == 1){
			randomaction = rand.nextInt((3 - 1) + 1) + 1;
			if(randomaction == 1) enemies.get(0).setTask("f");
			randomAlly = rand.nextInt((5 - 0) + 1) + 0;
			while(entity[randomAlly].getActive() == true){
			randomAlly = rand.nextInt((5 - 0) + 1) + 0;
			}
			enemies.get(0).doTask(entity[randomAlly]);
			if(enemies.get(0).getTask().equals("d")) gameOver(allies, enemies);
		}
	
		//rebels attack
		if(mission == 1)
		{
			for(int ii = 1; ii <= 5; ++ii) 
			{
				randomEnemy = rand.nextInt((13 - 8) + 1) + 8;
				
				while(entity[randomEnemy].getActive() == true)
				{
					randomEnemy = rand.nextInt((13 - 8) + 1) + 8;
				}
				entity[ii].doTask(entity[randomEnemy]);
				if(enemies.get(0).getTask().equals("d")) gameOver(allies, enemies);
				
			}
		}
		else
		{
			for(int ii = 1; ii <= 5; ++ii) 
			{
				randomEnemy = rand.nextInt((14 - 9) + 1) + 9;
				while(entity[randomEnemy].getActive())
				{
					randomEnemy = rand.nextInt((14 - 9) + 1) + 9;
				}
				entity[ii].doTask(entity[randomEnemy]);
				if(entity[14].getHp() <= 0)
				{
					enemies.get(7).doTask(enemies.get(7));
					if(enemies.get(0).getTask().equals("d")) gameOver(allies, enemies);
				}
				
			}
		}
		
		//stormtroopers attack
		for(int ii = 9; ii <= 13; ++ii)
		{
			randomAlly = rand.nextInt((5 - 0) + 1) + 0;
			while(entity[randomAlly].getActive())
			{
				randomAlly = rand.nextInt((5 - 0) + 1) + 0;
			}
			entity[ii].doTask(entity[randomAlly]);
		}
	
	}
	/**
	 * Game over method to terminate the game when mission is complete
	 * @param entity array, passing in the interactive objects
	 */
	public static void gameOver(ArrayList<Entity> allies, ArrayList<Entity> enemies)
	{
		System.out.println("");
		System.out.println("Game over!");
		
		if(enemies.get(0).getHp() <= 0)
		{
			System.out.println("You have slained the Sith Lord!  May the galaxies be indebted by your hands young one!!");
		}
		else if(enemies.get(7).getHp() <= 0) {
			System.out.println("You hacked into the computer!! You crippled their data and have stopped the Imperials for invading any further!");
			System.out.println("Nice work " + allies.get(0).getName() + "! May the galaxies be indebted by your hands young one!!");
		}
		else if(allies.get(0).getHp() <= 0)
		{
			System.out.println(allies.get(0).getName() + " is dead... The Sith Lord surrounded your remaining troops and conquered the galaxies!!!");
		}
		
		System.out.println("Thank you for playing --- MADE BY GREG VIOLAN ^_^");
		System.exit(0);
	}
	/**
	 * Target method choosing who the user target
	 * @param entity array, passing in the interactie objects
	 * @param mission option (1 or 2) between killing the Sith Lord or Capturing the base
	 * @return target, the object that the user will be targeting
	 */
	public static int target(ArrayList<Entity> allies, ArrayList<Entity> enemies, int mission)
	{
		int target = 0;
		@SuppressWarnings("resource")
		Scanner scnr = new Scanner(System.in);
		
		boolean k = false;
		while(!k)
		{	
			try{
				System.out.println("Choose someone to attack:");
				if(mission == 1) 
				{
					System.out.println("1. " + enemies.get(0).getName());
					System.out.println("2. " + enemies.get(1).getName());
					System.out.println("3. " + enemies.get(2).getName());
					System.out.println("4. " + enemies.get(3).getName());
					System.out.println("5. " + enemies.get(4).getName());
					System.out.println("6. " + enemies.get(5).getName());
				}
				else 
				{
					System.out.println("1. " + enemies.get(6).getName());
					System.out.println("2. " + enemies.get(5).getName());
					System.out.println("3. " + enemies.get(4).getName());
					System.out.println("4. " + enemies.get(3).getName());
					System.out.println("5. " + enemies.get(2).getName());
					System.out.println("6. " + enemies.get(1).getName());
				}
				
				target = scnr.nextInt();
				
				if(mission == 1){
					if(target == 1 || target == 2 || target == 3 || target == 4 || target == 5 || target == 6 && mission == 1) 
					{
						if(target == 1 && enemies.get(0).getActive()) System.out.println(enemies.get(0).getName() + " is dead, choose a different one. ");
						else if(target == 2 && enemies.get(1).getActive()) System.out.println(enemies.get(1).getName() + " is dead, choose a different one. ");
						else if(target == 3 && enemies.get(2).getActive()) System.out.println(enemies.get(2).getName() + " is dead, choose a different one. ");
						else if(target == 4 && enemies.get(3).getActive()) System.out.println(enemies.get(3).getName() + " is dead, choose a different one. ");
						else if(target == 5 && enemies.get(4).getActive()) System.out.println(enemies.get(4).getName() + " is dead, choose a different one. ");
						else if(target == 6 && enemies.get(5).getActive()) System.out.println(enemies.get(5).getName() + " is dead, choose a different one. ");
						else k = true;
					}
				}
				else
				{
					if(target == 1 || target == 2 || target == 3 || target == 4 || target == 5 || target == 6 && mission == 2)
					{
						if(target == 1 && enemies.get(6).getActive()) System.out.println(enemies.get(6).getName() + " is dead, choose a different one. ");
						else if(target == 2 && enemies.get(5).getActive()) System.out.println(enemies.get(5).getName() + " is dead, choose a different one. ");
						else if(target == 3 && enemies.get(4).getActive()) System.out.println(enemies.get(4).getName() + " is dead, choose a different one. ");
						else if(target == 4 && enemies.get(3).getActive()) System.out.println(enemies.get(3).getName() + " is dead, choose a different one. ");
						else if(target == 5 && enemies.get(2).getActive()) System.out.println(enemies.get(2).getName() + " is dead, choose a different one. ");
						else if(target == 6 && enemies.get(1).getActive()) System.out.println(enemies.get(1).getName() + " is dead, choose a different one. ");
						else k = true;
					}
				}
				System.out.println("");
			}catch(InputMismatchException e){
				System.out.println("Error, please try again");
				System.out.println("");
				scnr.next();
			}
		}
		return target;
	}
	/**
	 * Does the action on the targeted object
	 * @param entity array, passing in the interactie objects
	 * @param mission option (1 or 2) between killing the Sith Lord or Capturing the base
	 * @param target, the object that the user targeted
	 */
	public static void doTarget(ArrayList<Entity> allies, ArrayList<Entity> enemies, int mission, int target)
	{
		switch(target)
		{
			case 1: if(mission == 1) allies.get(0).doTask(enemies.get(0));
					else allies.get(0).doTask(enemies.get(6));
					break;
			case 2: if(mission == 1) allies.get(0).doTask(enemies.get(1));
					else allies.get(0).doTask(enemies.get(5));
					break;
			case 3: if(mission == 1) allies.get(0).doTask(enemies.get(2));
					else allies.get(0).doTask(enemies.get(4));
					break;
			case 4: if(mission == 1) allies.get(0).doTask(enemies.get(3));
					else allies.get(0).doTask(enemies.get(3));
					break;
			case 5: if(mission == 1) allies.get(0).doTask(enemies.get(4));
					else allies.get(0).doTask(enemies.get(2));
					break;
			case 6: if(mission == 1) allies.get(0).doTask(enemies.get(5));
					else allies.get(0).doTask(enemies.get(1));
					break;
			default: return;
		}
	}
	
}
