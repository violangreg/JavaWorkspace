/**
 * import necessary libs and apis
 */
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
/**
 * Entity is a abstract class for all the objects in the game, it controls all the information of the objects
 * @author Greg
 */
public abstract class Entity {
	/**
	 * Declares a Rectangle called location for the location of objects
	 */
	private Rectangle location;
	/**
	 * Declares HP, speed, and direction of the object
	 */
	private int HP, speed, direction;
	/**
	 * Declares boolean moving for moving the object
	 */
	private boolean moving;
	/**
	 * Entity constructor instantiates all necessary variables of entity object
	 * @param p, is the point or location of the object
	 * @param w, is the width of the object (in rectangle)
	 * @param h, is the height of the object (in rectangle)
	 * @param hp, is the hp of the object
	 * @param sp, is the speed of the object
	 * @param dir, is the direction of the object
	 */
	public Entity(Point p, int w, int h, int hp, int sp, int dir){
		location = new Rectangle((int)p.getX(), (int)p.getY(), w, h);
		HP = hp;
		speed = sp;
		direction = dir;
	}
	/**
	 * getLocation gets the location of the object
	 * @return location of object
	 */
	public Point getLocation(){
		return location.getLocation();
	}
	/**
	 * getBounds gets the Rectangle of the object (used for collision)
	 * @return Rectangle of object
	 */
	public Rectangle getBounds(){
		return location;
	}
	/**
	 * getWidth gets the width of the object
	 * @return width of the object
	 */
	public int getWidth(){
		return (int) location.getWidth();
	}
	/**
	 * getHeight gets the height of the object
	 * @return the height of the object
	 */
	public int getHeight(){
		return (int) location.getHeight();
	}
	/**
	 * getHp gets the Hit Points of the object
	 * @return HP, the hit points of the object
	 */
	public int getHp(){
		return HP;
	}
	/**
	 * getSpeed gets the speed of the object
	 * @return speed, the speed of the object
	 */
	public int getSpeed(){
		return speed;
	}
	/**
	 * getDirections gets direction of the object
	 * @return direction, the direction of the object
	 */
	public int getDirection(){
		return direction;
	}
	/**
	 * isDead returns true if the object has equal or less than 0 HP, otherwise false
	 * @return true if the object has equal or less than 0 HP, otherwise false
	 */
	public boolean isDead(){
		boolean dead = false;
		if(HP <= 0) dead = true;
		return dead;
	}
	/**
	 * takeHit takes away one HP of the objects current HP
	 */
	public void takeHit(){
		HP -= 1;
	}
	/**
	 * spinCW spins the object once clock-wise
	 */
	public void spinCW(){
		if(direction == 8){
			direction = 1;
		}
		else{
			++direction;
		}
	}
	/**
	 * spinCCW spins the object once counter-clock-wise
	 */
	public void spinCCW(){
		if(direction == 1){
			direction = 8;
		}
		else{
			--direction;
		}
	}
	/**
	 * setDirection sets the direction of the object from (1-8 cardinal points, 1 being north -> 2 north-east -> ... -> 8 being north-west)
	 * @param d, sets the direction depending on the parameter (1-8 cardinal points)
	 */
	public void setDirection(int d){
		direction = d;
	}
	/**
	 * setWidth sets the width of the object
	 * @param w, sets the width
	 */
	public void setWidth(int w){
		location.width = w;
	}
	/**
	 * setHeight sets the height of the object
	 * @param h, sets the height
	 */
	public void setHeight(int h){
		location.height = h;
	}
	/**
	 * toggleMoving toggles between moving or not moving, if called while its true, it goes to false and if called while false it goes to true
	 */
	public void toggleMoving(){
		if(moving == true){
			moving = false;
		}
		else{
			moving = true;
		}
	}
	/**
	 * stopMoving set object's moving to false
	 */
	public void stopMoving(){
		moving = false;
	}
	/**
	 * update draws/renders the object
	 * @param g, the Graphics object from the library which draws the objects
	 */
	public void update(Graphics g){
		// renders the object
		draw(g, getLocation(), getWidth(), getHeight(), getDirection());
		
		// if is able can move
		if(moving == true){
			move();
		}
	}
	public void move(){
		switch(direction){
		case 1: // north 
			location.translate(0, -1 * speed); // y - is up, x is the same
			break;
		case 2: // north-east
			location.translate(1 * speed, -1 * speed); // y + is down
			break;
		case 3: // east
			location.translate(1 * speed, 0);
			break;
		case 4: // south-east
			location.translate(1 * speed, 1 * speed);
			break;
		case 5: // south
			location.translate(0, 1 * speed);
			break;
		case 6: // south-west
			location.translate(-1 * speed, 1 * speed);
			break;
		case 7: // west
			location.translate(-1 * speed, 0);
			break;
		case 8: // north-west
			location.translate(-1 * speed, -1 * speed);
			break;
		}
	}
	/**
	 * draws/renders the objects visuals
	 * @param g, the Graphics object from the library 
	 * @param p, the point (location) of the object
	 * @param w, the width of the object
	 * @param h, the height of the object
	 * @param dir, the direction of the object
	 */
	public abstract void draw(Graphics g, Point p, int w, int h, int dir);
}
