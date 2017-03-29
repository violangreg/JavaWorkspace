//Greg Paolo Violan, 011706641
/**
 * imported necessary api's
 */
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Scanner;
/**
 * Level class implements Serializable
 * @author Greg
 */
public class Level implements Serializable {
	/**
	 * Level constructor
	 */
	public Level(){
		level = new char[4][4];
	}
	/**
	 * generates a map of current level
	 * @param levelNum
	 */
	public void generateLevel(int levelNum){
		try{
			Scanner read = new Scanner(new File("Level"+levelNum+".txt"));
			//ADD ALL ROOMS FROM Level#.txt to level array
			do{
				for(int i = 0; i < 4; i++){
					String[] tokens = read.nextLine().split(" ");
					for(int j = 0; j < tokens.length; j++){
						level[i][j] = tokens[j].charAt(0);
					}
				}
			}while(read.hasNext());
			read.close();
			}catch(FileNotFoundException fnf){
				System.out.println("FNF");
			}
		
		//TP for displaying array
		//System.out.println(Arrays.deepToString(level));
		
	}
	/**
	 * returns the room of the current location
	 * @param p, point of the object
	 * @return the room of the point
	 */
	public char getRoom(Point p){
		return level[(int) p.getX()][(int) p.getY()];
	}
	/**
	 * display the map of the level
	 * @param p, point of the object
	 */
	public void displayMap(Point p){
		//TOP WALL
		System.out.print(" ");
		for(int i = 0; i < level.length; ++i){
			System.out.print(String.format("%-2s","_"));
		}
		//SIDE WALLS + ROOMS
		System.out.println();
		for(int i = 0; i < level.length; i++) {	
			System.out.print("|");
			for(int j = 0; j < level[i].length; j++){
				if(p.getX() == i && p.getY() == j) System.out.print("* ");
				else System.out.print(level[i][j] + " ");
			}
			System.out.println("|");
		}
		//BOTTOM WALL
		System.out.print(" ");
		for(int i = 0; i < level.length; ++i){
			System.out.print(String.format("%-2s","-"));
		}
		System.out.println();
		
	}
	/**
	 * sets the location at the starting point
	 * @return the starting location
	 */
	public Point findStartLocation(){
		Point point = new Point();
		
		for(int i = 0; i < level.length; ++i){
			for(int j = 0; j < level[i].length; ++j){
				if(level[i][j] == 's'){
					point.setLocation(i,j);
				}
			}
		}
		return point;
	}
	/**
	 * private char level
	 */
	private char[][] level;
}
