/**
 * import necessary libraries and APIs
 */
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
/**
 * Quarry Generator class reads a file from QuarryList.txt and generates a random quarry
 * @author Greg
 */
public class QuarryGenerator {
	/**
	 * Declaring ArrayList of the quarries
	 */
	private ArrayList<Quarry> quarry;
	/**
	 * Declaring & instantiating object lock for synchronizing
	 */
	private Object lock = new Object();
	/**
	 * QuarryGenerator constructor, creates an ArrayList of Quarry
	 */
	public QuarryGenerator(){
		quarry = new ArrayList<Quarry>();
	}
	/**
	 * generateQuarry generates a random Quarry from the QuarryList.
	 * @return quarry, a random quarry from the list
	 */
	public Quarry generateQuarry(){
		synchronized(lock){
		Random rand = new Random();
		Quarry qTemp = null;
		
		try{
			File f = new File("QuarryList.txt");
			Scanner scnr = new Scanner(f);
			do{
				String read = scnr.nextLine();
				String[] tokens = read.split(",");
				
				// PULLING OUT DATA
				String name = tokens[0];
				int weight = Integer.parseInt(tokens[1]);
				int hp = Integer.parseInt(tokens[2]);
				int speed = Integer.parseInt(tokens[3]);
				
				//----------- SIZE --------------
				// width and height of quarry, how big it'll be
				int w, h;
				w = h = 0;
				
				// change size of quarry depending on the quarries' weight
				if (weight < 25) {
					w = 30;
					h = 30;
				} else if (weight < 100) {
					w = 35;
					h = 35;
				} else if (weight < 250) {
					w = 40;
					h = 40;
				}

				//--------- POSITION -------------
				//Gets the screen resolution for quarry position
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				int width = (int) screenSize.getWidth();
				int height = (int) screenSize.getHeight();
				
				// random starting position of quarry (should be at the edge of screen)
				int x = rand.nextInt(width/2);
				int y = rand.nextInt(height/2);
				int r = rand.nextInt(4) + 1;
				
				// choose a random side
				if(r == 1){
					x = 0;
				}
				else if(r == 2){
					y = 0;
				}					//width - height/2, height - width/10
				else if(r == 3){ // hard coded number, looking for another way to do this
					x = width - height/2 - w ; // want the maximum width of window, and the width offset of object
					if(x >= width){
						int xOffSet = x - width;
						x -= xOffSet - w;
					}
				}
				else if(r == 4){
					y = height - width/10 - h; // want the maximum height of window, and the height offset of object
					if(y >= height){
						int yOffSet = y - height;
						y -= yOffSet - h;
					}
				}		
				
				// Random initial position of a quarry
				Point point = new Point(x,y);
				
				//create the quarry
				qTemp = new Quarry(point, w, h, hp, speed, name, weight);
				
				// Random initial direction of aquarry
				int rDir = rand.nextInt(3) + 1;
				if(r == 1) {
					if(rDir == 1) qTemp.setDirection(2);
					else if(rDir == 2) qTemp.setDirection(3);
					else if(rDir == 3) qTemp.setDirection(4);
				}
				else if(r == 2) {
					if(rDir == 1) qTemp.setDirection(4);
					else if(rDir == 2) qTemp.setDirection(5);
					else if(rDir == 3) qTemp.setDirection(6);
				}
				else if(r == 3) {
					if(rDir == 1) qTemp.setDirection(6);
					else if(rDir == 2) qTemp.setDirection(7);
					else if(rDir == 3) qTemp.setDirection(8);
				}
				else if(r == 4){
					if(rDir == 1) qTemp.setDirection(8);
					else if(rDir == 2) qTemp.setDirection(1);
					else if(rDir == 3) qTemp.setDirection(2);
				}
				quarry.add(qTemp);		
				
			}while(scnr.hasNextLine());
			scnr.close();
		}catch(FileNotFoundException e){
			System.out.println("FNFE");
		}
		
		Quarry q = quarry.get(rand.nextInt(quarry.size()));
		return q;
		}
	}
}