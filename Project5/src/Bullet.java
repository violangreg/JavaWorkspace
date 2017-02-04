/**
 * Declare necessary libraries and apis
 */
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Bullet class extends Entity, Bullet is an object for the Hunter's class bullet, it has collision
 * @author Greg
 */
public class Bullet extends Entity{
	/**
	 * Declaring BufferedImage of the Bullet
	 */
	private BufferedImage image;
	/**
	 * Bullet's constructor:
	 * @param p, the point of the bullet's location
	 * @param w, the width of the bullet
	 * @param h, the height of the bullet
	 * @param hp, the hit points of the bullet
	 * @param sp, the speed of the bullet
	 * @param dir, the direction of the bullet
	 */
	public Bullet(Point p, int w, int h, int hp, int sp, int dir){
		super(p, w, h, hp, sp, dir);
		try {
			image = ImageIO.read(new File("bullet.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * testCollision gives the Bullets be able to detect collision with other objects
	 * @param e, the object that is collided
	 * @return true if the Bullet collides with object e
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
	 * draw draws/renders the Bullet
	 */
	public void draw(Graphics g, Point p, int w, int h, int dir) {
		g.drawImage(image, (int)p.getX() , (int)p.getY(), null);
	}
}