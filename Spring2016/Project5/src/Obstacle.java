/**
 * import necessary libraries and API's
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
 * Obstacle class extends Entity, obstacles are objects that other objects can collide with
 * @author Greg
 */
public class Obstacle extends Entity{
	/**
	 * Declaring the type of the obstacle
	 */
	private int type;
	/**
	 * Declaring the BufferedImage of the obstacle
	 */
	private BufferedImage image;
	/**
	 * Obstacle constructor:
	 * @param p, the point location of the obstacle
	 * @param t, the type of the obstacle
	 */
	public Obstacle(Point p, int t){
		super(p, 50, 50, 1, 1, 1);
		type = t;
		try{
			Random r = new Random();
			int i = 0;
			int randObs = r.nextInt(5);
			
			if(type == 1) i = randObs;
			else if(type == 2){
				if(randObs == 0 || randObs == 1) i = 6;
				else if(randObs == 2 || randObs == 3) i = 7;
				setHeight(70);
				setWidth(35);
			}
			else if(type == 3){
				i = 1; // BLOOD 1
				setHeight(30);
				setWidth(30);
			}
			else if(type == 4) {
				i = 2; // BLOOD 2
				setHeight(35);
				setWidth(35);
			}
			else if(type == 5){
				i = 3; // BLOOD 3
				setHeight(40);
				setWidth(40);
			}
			
			if(type <= 2){
				image = ImageIO.read(new File("obstacle" + i + ".png"));
			}
			else{
				image = ImageIO.read(new File("blood" + i + ".png"));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * testCollision is used to detect collision with other objects
	 * @param e, the entity that can collide with the obstacle
	 * @return true if an entity collides with the obstacle
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
	@Override
	/**
	 * draw draws/renders the obstacle object
	 */
	public void draw(Graphics g, Point p, int w, int h, int dir){
		g.drawImage(image, (int)p.getX(), (int)p.getY(), null);
	}
}