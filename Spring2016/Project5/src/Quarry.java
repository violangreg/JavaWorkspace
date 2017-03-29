/**
 * import necessary libraries and apis
 */
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
/**
 * Quarry extends Entity, Quarry are the objects that the Hunter will hunt.
 * @author Greg
 */
public class Quarry extends Entity {
	/**
	 * Declaring the weight of the quarry
	 */
	private int weight;
	/**
	 * Declaring the name of the quarry
	 */
	private String name;
	/**
	 * Declaring the BufferedImage of the quarry
	 */
	private BufferedImage img;
	/**
	 * Quarry constructor:
	 * @param p, the point location of the quarry
	 * @param w, the width of the quarry
	 * @param h, the height of the quarry
	 * @param hp, the hit points of the quarry
	 * @param sp, the speed of the quarry
	 * @param n, the name of the quarry
	 * @param wt, the weight of the quarry
	 */
	public Quarry(Point p, int w, int h, int hp, int sp, String n, int wt){
		super(p, w, h, hp, sp, 1);
		name = n;
		weight = wt;
		
		// Random quarry image
		Random r = new Random();
		try {
			img = ImageIO.read(new File("hunter" + r.nextInt(7) + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * testCollision detects collisions that collides with the quarry
	 * @param e, is the parameter of the entity object
	 * @return true if the object collides with the quarry, otherwise false
	 */
	public boolean testCollision(Entity e){
		Rectangle r = new Rectangle((int) getLocation().getX(), 
				(int) getLocation().getY(), getWidth(), getHeight());
		// TOP LEFT
		Point p1 = new Point((int) e.getLocation().getX(),
				(int) e.getLocation().getY());
		// TOP RIGHT
		Point p2 = new Point((int) e.getLocation().getX() + e.getWidth(),
				(int) e.getLocation().getY());
		// BOTTOM RIGHT
		Point p3 = new Point((int) e.getLocation().getX() + e.getWidth(),
				(int) e.getLocation().getY() + e.getHeight());
		// BOTTOM LEFT
		Point p4 = new Point((int) e.getLocation().getX(), 
				(int) e.getLocation().getY() + e.getHeight());
		boolean collide = false;
		if(r.contains(p1)){
			collide = true;
		}
		if(r.contains(p2)){
			collide = true;
			
		}
		if(r.contains(p3)){
			collide = true;
		}
		if(r.contains(p4)){
			collide = true;
		}
		return collide;
	}
	/**
	 * getName gets the name of the quarry
	 * @return name, the name of the quarry
	 */
	public String getName(){
		return name;
	}
	/**
	 * getWeight gets the weight of the quarry
	 * @return weight, the weight of the quarry
	 */
	public int getWeight(){
		return weight;
	}
	@Override
	/**
	 * draw draw/renders the quarry object
	 */
	public void draw(Graphics g, Point p, int w, int h, int dir) {
		g.drawImage(img,(int) p.getX(), (int) p.getY(), null);
	}
}