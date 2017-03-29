//Greg Paolo Violan, 011706641
/**
 * imported necessary api's
 */
import java.awt.Point;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
/**
 * Enemy Generator class. This class generates a random Enemy object.
 * @author Greg
 */
public class EnemyGenerator {
	/**
	 * private ArrayList enemyList 
	 */
	private ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
	/**
	 * EnemyGenerator constructor, gets files from the EnemyList.txt file and put it in to the array
	 */
	public EnemyGenerator(){
		Item testItem = new Item("Test", 0);
		try{
		Scanner read = new Scanner(new File("EnemyList.txt"));
		//ADD ALL ENEMIES FROM EnemeyList.txt to enemyList Array
		do{
			read.useDelimiter(",");
			String line = read.nextLine();
			String[] tokens = line.split(",");
			                        //NAME     //QUIP      //HP                     //LV //G //ITEM
			Enemy enemy = new Enemy(tokens[0], tokens[1], Integer.parseInt(tokens[2]), 0, 0, testItem, new Point(0,0), tokens[0] + ".png");
			
			enemyList.add(enemy);
		}while(read.hasNext());
		read.close();
		}catch(FileNotFoundException fnf){
			System.out.println("FNF");
		}
	}
	/**
	 * Generate Enemy class, generates a random enemy depending on the level
	 * @param level, level of current map
	 * @return enemy
	 */
	public Enemy generateEnemy(int level){
		Enemy enemy = null;
		Item monsterItem = null;
		int randomMob = 0;
		int gold = 0;
		Random rand = new Random();
		
		//ITEM GENERATOR FOR THE MONSTER
		ItemGenerator itemGen = new ItemGenerator();
		
		//ITEM GENERATES IF ITS 1, ELSE it gives "NO_DROP"

		monsterItem = itemGen.generateItem();

		
		Point p = new Point(0,0);
		int randomPosition = rand.nextInt(8) + 1;
		if(randomPosition == 1){
			p.x = 586;
			p.y = 242;
		}
		else if(randomPosition == 2){
			p.x = 651;
			p.y = 387;
		}
		else if(randomPosition == 3){
			p.x = 651;
			p.y = 240;
		}
		else if(randomPosition == 4){
			p.x = 727;
			p.y = 239;
		}
		else if(randomPosition == 5){
			p.x = 723;
			p.y = 385;
		}
		else if(randomPosition == 6){
			p.x = 804;
			p.y = 241;
		}
		else if(randomPosition == 7){
			p.x = 802;
			p.y = 309;
		}
		else if(randomPosition == 8){
			p.x = 800;
			p.y = 385;
		}
		
		//LEVEL 1 MOBS : SNAKE, GIANT RAT, FROGLOK
		if(level == 1){
			rand = new Random();
			randomMob = rand.nextInt((3 - 1) + 1) + 1; //GENERATE RANDOM MOB
			gold = rand.nextInt((10 - 0) + 1) + 0; //GENERATE GOLD
			
			if(randomMob == 1) randomMob = 5; //5: SNAKE, 1 HP
			else if(randomMob == 2) randomMob = 2; //2: GIANT RAT, 2 HP
			else randomMob = 6; //6: FROGLOK, 3 HP

			//GENERATE A RANDOM LEVEL 3 MONSTER
			enemy = new Enemy(enemyList.get(randomMob).getName(), enemyList.get(randomMob).getQuip(), 
					enemyList.get(randomMob).getHp(), 1, gold, monsterItem, p, enemyList.get(randomMob).getImageString());
		}
		//LEVEL 2 MOBS : GNOLL, GOBLIN, ORC
		else if(level == 2){
			rand = new Random();
			randomMob = rand.nextInt((3 - 1) + 1) + 1; //GENERATE RANDOM MOB
			gold = rand.nextInt((20 - 0) + 1) + 0; //GENERATE GOLD
			
			if(randomMob == 1) randomMob = 4; //4: GNOLL, 4 HP
			else if(randomMob == 2) randomMob = 0; //5: GOBLIN, 5 HP
			else randomMob = 1; //1: ORC, 6 HP

			//GENERATE A RANDOM LEVEL 2 MONSTER
			enemy = new Enemy(enemyList.get(randomMob).getName(), enemyList.get(randomMob).getQuip(),
					enemyList.get(randomMob).getHp()*2, 2, gold, monsterItem, p, enemyList.get(randomMob).getImageString());
			
		}
		//LEVEL 3 MOBS : ORC, KOBOLD, TROLL
		else{
			rand = new Random();
			randomMob = rand.nextInt((3 - 1) + 1) + 1; //GENERATE RANDOM MOB
			gold = rand.nextInt((30 - 0) + 1) + 0; //GENERATE GOLD
			
			if(randomMob == 1) randomMob = 1; //6: ORC, 6 HP
			else if(randomMob == 2) randomMob = 3; //3: KOBOLD, 7 HP
			else randomMob = 7; //7: TROLL, 8 HP
			
			//GENERATE A RANDOM LEVEL 3 MONSTER
			enemy = new Enemy(enemyList.get(randomMob).getName(), enemyList.get(randomMob).getQuip(),
					enemyList.get(randomMob).getHp()*2, 3, gold, monsterItem, p, enemyList.get(randomMob).getImageString());
		}

		return enemy;
	}
}
