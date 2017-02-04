/**
 * importing necessary libs and apis
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
@SuppressWarnings("serial")
/**
 * Panel class is where everything is being painted and run inside the window.
 * All objects in the game are instantiated in panel class
 * Panel is also a thread therefore it runs simultaneously with main
 * It extends JPanel for the paintComponent and implements KeyListener, MouseListerner, MouseMotionListener for key and mouse controls
 * It also implements Runnable for thread
 * @author Greg
 */
public class Panel extends JPanel implements KeyListener, MouseListener, MouseMotionListener, Runnable{
	// --------- DECLARING OBJECTS ------------- //
	/**
	 * Declaring Hunter object
	 */
	private Hunter hunter;
	/**
	 * Declaring a Quarry Generator object
	 */
	private QuarryGenerator qGen;
	/**
	 * Declaring an ArrayList of Obstacles
	 */
	private ArrayList<Obstacle> obstacles;
	/**
	 * Declaring an ArrayList of Quarries
	 */
	private ArrayList<Quarry> quarries;
	/**
	 * Declaring background and control BufferedImages
	 */
	private BufferedImage background, controls;
	/**
	 * Declaring Timer object
	 */
	private Timer timer;
	/**
	 * Declaring background music
	 */
	private Clip bgmClip;
	/**
	 * Declaring & instantiating Timer's TIMER constant
	 */
	private final int TIMER = 30;
	/**
	 * Declaring & instantiating the initial quarry score
	 */
	private int quarryCount = 0;
	/**
	 * Declaring boolean for control/start game
	 */
	private boolean startGame = false;
	//---------- WINDOW SIZE FOR QUARRIES HITTING THE EDGE ---------//
	/**
	 * Declaring & instantiating screen width off set
	 */
	private final int WIDTH_OFF_SET = 415;
	/**
	 * Declaring & instantiating screen height off set
	 */
	private final int HEIGHT_OFF_SET = 185;
	/**
	 * Declaring & instantiating Dimension screenSize object
	 */
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	/**
	 * Declaring & instantiating final screenWidth of window
	 */
	private final int screenWidth = (int) screenSize.getWidth() - WIDTH_OFF_SET;
	/**
	 * Declaring & instantiating final screenHeight of window
	 */
	private final int screenHeight = (int) screenSize.getHeight() - HEIGHT_OFF_SET;	
	// --------- INSTANTIATING OBJECTS -------- //
	/**
	 * Panel constructor instantiates all necessary objects
	 */
	public Panel(){
		// CREATING HUNTER, GENERATOR, ARRAYLISTS
		Random r = new Random();
		this.setBackground(Color.BLACK);
		Point hunterPoint = new Point(r.nextInt(screenWidth - 50) + 20, 
				r.nextInt(screenHeight - 50) + 20);
		hunter = new Hunter(hunterPoint, 40, 40, 3, 3); 
		qGen = new QuarryGenerator();
		obstacles = new ArrayList<Obstacle>();
		quarries = new ArrayList<Quarry>();
		timer = new Timer(TIMER, quarryCount, 500, 45);
		
		// ADDING LISTENERS
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
		setFocusable(true);
		
		// BACKGROUND MUSIC
		try{
			bgmClip = AudioSystem.getClip();
			bgmClip.open(AudioSystem.getAudioInputStream(new File("bgm.wav")));
			bgmClip.start();
		}catch(LineUnavailableException e){
			System.out.println("Audio Error");
		}catch(IOException e){
			System.out.println("FNFE");
		}catch(UnsupportedAudioFileException e){
			System.out.println("WFT");
		}
		
		// BACKGROUND IMAGE & CONTROLS
		try {
			background = ImageIO.read(new File("background.png"));
			controls = ImageIO.read(new File("controls.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// OBSTACLES
		for(int i = 0; i < r.nextInt(7) + 2; i++)
		{
			Point obsTempPoint = new Point(r.nextInt(screenWidth - 100) + 50,
					r.nextInt(screenHeight - 100 ) + 50);
			int type = r.nextInt(2) + 1;
			Obstacle obsTemp = new Obstacle(obsTempPoint, type);
			obstacles.add(obsTemp);
		}
		
	}	
	//---------- COLLISION TESTS ----------- //
	/**
	 * testCollision method tests all the collisions in the game
	 */
	public void testCollisions(){
		// HUNTER AND OBSTACLE COLLISION
		for(int i = 0; i < obstacles.size(); i++){
			if(obstacles.get(i).testCollision(hunter)){
				for(int j = 0; j < 4; j++){
					hunter.spinCW();
				}
				hunter.stopMoving();
				hunter.move();
			}
			
		}
		// QUARRY AND OBSTACLE COLLISION
		for(int i = 0; i < obstacles.size(); i++){
			for(int j = 0; j < quarries.size(); j++){
				if(obstacles.get(i).testCollision(quarries.get(j))){
					for(int k = 0; k < 4; k++){
						quarries.get(j).spinCW();
					}
					quarries.get(j).move();
				}
			}
		}
		
		// QUARRY AND HUNTER COLLISION
		for (int i = 0; i < quarries.size(); i++){
			if (quarries.get(i).testCollision(hunter)) {
				for (int k = 0; k < 4; k++){
					quarries.get(i).spinCW();
				}
				quarries.get(i).move();
				
				for(int j = 0; j < 4; j++){
					hunter.spinCCW();
				}
				
				hunter.stopMoving();
				hunter.move();
			}
		}
	
		// TOGGLE TO MOVE THE QUARRIES
		if(!quarries.isEmpty()){
			for(int i = 0; i < quarries.size(); i++){			
				quarries.get(i).toggleMoving();
	
				Point quarryLoc = quarries.get(i).getLocation();
				
				// IF QUARRIES HIT A WALL, REMOVE IT
				if((int) quarryLoc.getX() > screenWidth || (int) quarryLoc.getY() > screenHeight){
					quarries.remove(i);
				}
				if((int) quarryLoc.getX() < 0 || (int) quarryLoc.getY() < 0){
					quarries.remove(i);
				}
			}
		}
		// HUNTER'S BULLET HITTING QUARRIES
		if(!quarries.isEmpty()){
			for(int i = 0; i < quarries.size(); i++){
				if(hunter.testHit(quarries.get(i))){  
					quarries.get(i).takeHit(); // QUARRY TAKES DAMAGE
					if(quarries.get(i).isDead()){
						Obstacle obsQuarry = null;
						int type = 0;
						if (quarries.get(i).getWeight() < 25) {
							type = 3;
						} else if (quarries.get(i).getWeight() < 100) {
							type = 4;
						} else if (quarries.get(i).getWeight() < 250) {
							type = 5;
						}
						obsQuarry = new Obstacle(quarries.get(i).getLocation(), type);
						obstacles.add(obsQuarry);
						timer.incQuarryCount();
						quarryCount = timer.getQuarryCount();
						quarries.remove(i); // REMOVE QUARRY
					}
				}
			}
		}
		// HUNTER'S BULLET HITTING OBSTACLES
		if(!obstacles.isEmpty()){
			for(int i = 0; i < obstacles.size(); i++){
				if(hunter.testHit(obstacles.get(i))){
					// DON'T DO ANYTHING IF OBSTACLE IS HIT
					// BULLET IS REMOVED IN testHit()
				}
			}
		}
		
	}
	// --------- UPDATE / RENDERING / REDRAWING OBJECTS GO HERE -------------- //
	/**
	 * paintComponent method paints all the graphical objects such as the hunter, quarries, and obstacles
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		//background
		g.drawImage(background, 0, 0, this);
		// update hunter
		hunter.update(g);
		// update quarries
		if(!quarries.isEmpty()){
			for(int i = 0; i < quarries.size(); i++){
				quarries.get(i).update(g);
			}
		}
		// update obstacles
		if(!obstacles.isEmpty()){
			for(int i = 0; i < obstacles.size(); i++){
				obstacles.get(i).update(g);
			}
		}
		// TIMER
		timer.paintComponent(g);
		// CONTROL IMAGE
		if(startGame == false){
			g.drawImage(controls, 0, 0, this);
		}
	}
	//---------- MOUSE AND KEY CONTROLS -----------
	/**
	 * Override mouseDragged (not used but needs to be overridden)
	 */
	@Override
	public void mouseDragged(MouseEvent e) {}
	/**
	 * Override mouseMoved method, sets the direction of the hunter depending where the mouse is at
	 */
	@Override
	public void mouseMoved(MouseEvent e){
		//System.out.println(e.getPoint());
		// Variables
		Point position = new Point(e.getPoint());
		double xHunter = hunter.getLocation().getX() + (hunter.getWidth()/2);
		double yHunter = hunter.getLocation().getY() + (hunter.getHeight()/2);
		double horizontal, vertical, hypotenuse, angle;
		
		// Calculate angle
		horizontal = position.getX() - xHunter;
		if(position.getY() < yHunter){
			vertical = Math.abs(position.getY() - yHunter);
		}
		else{
			vertical = -Math.abs(position.getY() - yHunter);
		}
		
		hypotenuse = Math.sqrt(Math.pow(vertical, 2) + Math.pow(horizontal, 2));
		angle = Math.toDegrees(Math.asin(vertical/hypotenuse));
		
		if(angle > 60){
			hunter.setDirection(1);
		}
		else if(angle > 30 && angle <= 60 && position.getX() > xHunter){
			hunter.setDirection(2);
		}
		else if((angle > -30 && angle <= 30) && position.getX() > xHunter){
			hunter.setDirection(3);
		}
		else if(angle < -30 && angle >= -60 && position.getX() > xHunter){
			hunter.setDirection(4);
		}
		else if(angle < -60){
			hunter.setDirection(5);
		}
		else if(angle < -30 && angle >= -60 && position.getX() < xHunter){
			hunter.setDirection(6);
		}
		else if(angle >= -30 && angle <= 30 && position.getX() < xHunter){
			hunter.setDirection(7);
		}
		else{
			hunter.setDirection(8);
		}
	}
	/**
	 * mouseClicked method fires a bullet from the hunter onto the hunter's direction
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		hunter.fireBullet();
	}
	/**
	 * Override mouseEntered (not used but needs to be overridden)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {}
	/**
	 * Override mouseExited (not used but needs to be overridden)
	 */
	@Override
	public void mouseExited(MouseEvent e) {}
	/**
	 * Override mousePressed (not used but needs to be overridden)
	 */
	@Override
	public void mousePressed(MouseEvent e) {}
	/**
	 * Override mouseReleased (not used but needs to be overridden)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {}
	/**
	 * keyPressed method is for action events of the hunter
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		// Special keys
		// Moves the hunter
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			hunter.toggleMoving();
		}
		// Hunter shoots
		if(e.getKeyCode() == KeyEvent.VK_SPACE){
			hunter.fireBullet();
			play("blip.wav");
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			hunter.spinCCW();
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			hunter.spinCW();
		}
		if(e.getKeyCode() == KeyEvent.VK_S){
			Thread t = new Thread(timer);
			t.start();
			startGame = true;
		}
	}
	@Override
	/**
	 * Override keyReleased (not used but needs to be overridden)
	 */
	public void keyReleased(KeyEvent e) {}
	@Override
	/**
	 * Override keyTyped (not used but needs to be overridden)
	 */
	public void keyTyped(KeyEvent e) {}	
	//---------- SOUNDS + MUSIC ----------------//
	/**
	 * play method plays any .wav or .au files
	 * @param filename, the file name of the sound
	 */
	public void play(String filename)
	{	
		try{
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(new File(filename)));
			clip.start();
		}catch(LineUnavailableException e){
			System.out.println("Audio Error");
		}catch(IOException e){
			System.out.println("FNFE");
		}catch(UnsupportedAudioFileException e){
			System.out.println("WFT");
		}
	}
	// --------- RUN CONTINUOUSLY ---------- //
	@Override
	/**
	 * run method runs continuously until the game ends via timer
	 */
	public void run(){
		while(true){	
			Random r = new Random();
			if(r.nextInt(80) == 1){
				quarries.add(qGen.generateQuarry());
			}
			
			//obstacle/quarries and bullet collision?
			testCollisions();
			
			if(timer.time() < 0){
				bgmClip.stop();
				play("gameOver.wav");
				JOptionPane.showMessageDialog(this, "GAME OVER \nSCORE:  " + Integer.toString(quarryCount));
				System.exit(0);
			}
			
			repaint();
			try{
				Thread.sleep(16);
			}catch(InterruptedException e){}
		}	
	}
}
