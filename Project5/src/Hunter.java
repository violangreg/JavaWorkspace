/**
 * import necessary libs and apis
 */
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import javax.imageio.ImageIO;
/**
 * Hunter class extends Entity, the hunter object is the object that the player controls
 * @author Greg
 */
public class Hunter extends Entity{
	/**
	 * Declaring Bullet object
	 */
	private Bullet bullet;
	/**
	 * Declaring a LinkedList of Bullet objects
	 */
	private LinkedList<Bullet> bulletList;
	/**
	 * Declaring BufferedImages of Hunter and Hunter's weapon
	 */
	private BufferedImage hunterRight, hunterLeft, hunterBack, weaponUp, 
	weaponUpRight, weaponRight, weaponDownRight, weaponDown, weaponDownLeft, weaponLeft,
	weaponUpLeft;
	/**
	 * Declaring & instantiating the OFF_SET of images;
	 */
	private final int OFF_SET = 10;
	// WINDOW SIZE FOR QUARRIES HITTING THE EDGE
	/**
	 * Declaring & instantiating the width off set of the window
	 */
	private final int WIDTH_OFF_SET = 430;
	/**
	 * Declaring & instantiating the height off set of the window
	 */
	private final int HEIGHT_OFF_SET = 206;
	/**
	 * Declaring & instantiating the screenSize of the window
	 */
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	/**
	 * Declaring & instantiating the screen width of the window
	 */
	private final int screenWidth = (int) screenSize.getWidth() - WIDTH_OFF_SET;
	/**
	 * Declaring & instantiating the screen height of the window
	 */
	private final int screenHeight = (int) screenSize.getHeight() - HEIGHT_OFF_SET;
	/**
	 * The Hunter's constructor object  
	 * @param p, hunter's point location
	 * @param w, hunter's width
	 * @param h, hunter's height
	 * @param hp, hunter's hp
	 * @param sp, hunter's speed
	 */
	public Hunter(Point p, int w, int h, int hp, int sp){
		super(p, w, h, hp, sp, 1);
		bulletList = new LinkedList<Bullet>();
		try {
			hunterRight = ImageIO.read(new File("shannonRight.png"));
			hunterLeft = ImageIO.read(new File("shannonLeft.png"));
			hunterBack = ImageIO.read(new File("shannonBack.png"));
			
			weaponUp = ImageIO.read(new File("weaponUp.png"));
			weaponUpRight = ImageIO.read(new File("weaponUpRight.png"));
			weaponRight = ImageIO.read(new File("weaponRight.png"));
			weaponDownRight = ImageIO.read(new File("weaponDownRight.png"));
			weaponDown = ImageIO.read(new File("weaponDown.png"));
			weaponDownLeft = ImageIO.read(new File("weaponDownLeft.png"));
			weaponLeft = ImageIO.read(new File("weaponLeft.png"));
			weaponUpLeft = ImageIO.read(new File("weaponUpLeft.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * fireBullet fires a bullet from the hunter's current direction
	 */
	public void fireBullet(){
		// SETTING POSITION OF BULLET
		Point p = this.getLocation();
		p.setLocation(p.getX() + OFF_SET, p.getY() + OFF_SET);
		
		bullet = new Bullet(p, 20, 20, 1, 8, getDirection());
		bulletList.add(bullet);
	}
	/**
	 * testHit checks if the bullet hits any objects
	 * @param e, the entity object that was collided
	 * @return true if the bullet is collided, otherwise false if not
	 */
	public boolean testHit(Entity e){
		// COLLISION FOR BULLETS AND ENTITIES E
		boolean hit = false;
		for(int i = 0; i < bulletList.size(); i++){ //ARRAY OF BULLETS
			if(bulletList.get(i).testCollision(e)){ //USES testCollision from Bullet Class
				hit = true;
				bulletList.remove(i); // REMOVES BULLET IN THE ARRAY
			}
		}
		return hit;
	}
	@Override
	/**
	 * draw draws/renders the hunter object and the bullets simultaneously
	 */
	public void draw(Graphics g, Point p, int w, int h, int dir) {
		// update bullet (if fired)
		if(!bulletList.isEmpty()){
			for(int i = 0; i < bulletList.size(); i++){
				bulletList.get(i).toggleMoving();
				bulletList.get(i).update(g);
			}
		}
		// delete bullet when it goes out of bounce of the screen
		for(int i = 0; i < bulletList.size(); i++){
			Point bulletLoc = bulletList.get(i).getLocation();
			if((int) bulletLoc.getX() >= screenWidth || (int) bulletLoc.getY() >= screenHeight){
				bulletList.remove(i);
			}
			if((int) bulletLoc.getX() <= 0 || (int) bulletLoc.getY() <= 0){
				bulletList.remove(i);
			}
		}
		// hunter pass outside the window
		if((int) p.getX() > screenWidth){
			for(int j = 0; j < 4; j++){
				spinCW();
			}
			stopMoving();
			move();
		}
		if((int) p.getY() > screenHeight){
			for(int j = 0; j < 4; j++){
				spinCW();
			}
			stopMoving();
			move();
		}
		if((int) p.getX() < 0){
			for(int j = 0; j < 4; j++){
				spinCW();
			}
			stopMoving();
			move();
		}
		if((int) p.getY() < 0){
			for(int j = 0; j < 4; j++){
				spinCW();
			}
			stopMoving();
			move();
		}
		switch(dir){
		case 1:
			// north
			g.drawImage(weaponUp, (int) p.getX(), (int) p.getY() - 25, null);
			g.drawImage(hunterBack,(int) p.getX(), (int) p.getY(), null);
			break;
		case 2:
			// north-east
			g.drawImage(weaponUpRight, (int) p.getX() + 15, (int) p.getY() - 15, null);
			g.drawImage(hunterBack, (int) p.getX(), (int) p.getY(), null);
			break;
		case 3:
			// east
			g.drawImage(weaponRight, (int) p.getX() + 25, (int) p.getY(), null);
			g.drawImage(hunterRight, (int) p.getX(), (int) p.getY(), null);
			break;
		case 4:
			// south-east
			g.drawImage(weaponDownRight, (int) p.getX() + 15, (int) p.getY() + 15, null);
			g.drawImage(hunterRight, (int) p.getX(), (int) p.getY(), null);
			break;
		case 5:
			// south
			g.drawImage(weaponDown, (int) p.getX(), (int) p.getY() + 23, null);
			g.drawImage(hunterRight, (int) p.getX(), (int) p.getY(), null);
			break;
		case 6:
			// south-west
			g.drawImage(weaponDownLeft, (int) p.getX() - 12, (int) p.getY() + 12, null);
			g.drawImage(hunterLeft,(int) p.getX(), (int) p.getY(), null);
			break;
		case 7:
			// west
			g.drawImage(weaponLeft, (int) p.getX() - 25, (int) p.getY(), null);
			g.drawImage(hunterLeft,(int) p.getX(), (int) p.getY(), null);
			break;
		case 8:
			// north-west
			g.drawImage(weaponUpLeft, (int) p.getX() - 12, (int) p.getY() - 12, null);
			g.drawImage(hunterBack,(int) p.getX(), (int) p.getY(), null);
			break;
		}
	}
}