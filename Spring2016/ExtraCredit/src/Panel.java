//Greg Paolo Violan, 011706641
// NOTE: UPDATED THE IO OBJECT STREAM FOR THE READING AND WRITING HERO OBJECT
/**
 * imported necessary api's
 */
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import javax.sound.sampled.*;
import javax.swing.*;

@SuppressWarnings("serial")
/**
 * Panel class handles all the graphics and events of the game
 * @author Greg
 */
public class Panel extends JPanel implements Runnable, MouseListener, KeyListener {
	// DECLARING VARIABLES
	/**
	 * private Dimension for the screen size of the window
	 */
	private Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	/**
	 * private int for the screen width of the window 
	 */
	private int screen_width = (int) screen.getWidth();
	/**
	 * private int for the screen height of the window
	 */
	private int screen_height = (int) screen.getHeight();
	/**
	 * private int for the window width 
	 */
	private int window_width = screen_width - screen_height/2;
	/**
	 * private int for the window height
	 */
	private int window_height = screen_height - screen_width/10;
	/**
	 * private Hero object
	 */
	private Hero hero;
	/**
	 * private ArrayList<Enemy> for Enemy objects Array List
	 */
	private ArrayList<Enemy> enemyList;
	/**
	 * private ArrayList<Item> for Item objects Array List
	 */
	private ArrayList<Item> itemList;
	/**
	 * private EnemyGenerator for generating enemies
	 */
	private EnemyGenerator eGen;
	/**
	 * private ItemGenerator for generating items
	 */
	private ItemGenerator iGen;
	/**
	 * private Strings for Hero's object name and quip
	 */
	private String heroName, heroQuip;
	/**
	 * private ints for monst (for Array List indexer), heroHit and monstHit
	 * for showing how much damage appears in battle status
	 */
	private int monst, heroHit, monstHit;
	/**
	 * private booleans for starting the game, for when menu ends, for when battle occurs, hit for when object
	 * is hit, shop_b for shop status, and gameOver_b for when game over event occurs, level for when level changes
	 */
	private boolean start, menu, battle, hit, shop_b, gameOver_b, level;
	/**
	 * private Images for graphically displaying images onto the panel, gameTitle for the games title,
	 * startB for the start button, exitB for the exit button, loadB for the load button, level1 for the map
	 * of the game, playerStatus for the player panel, shopStatus for the shop panel, battleStatus for the 
	 * shop battle, attackB for the attack button, usePotionB for the use potion button, fleeB for the flee
	 * button, healthPotion of the player panel potion button, shop for the shop image in the map, finalRoom
	 * for the final room image in the map, gameOver for the game over image display, avatar for the player's
	 * avatar
	 */
	private Image gameTitle, startB, exitB, loadB, level1, playerStatus, shopStatus, battleStatus, attackB,
					usePotionB, fleeB, healthPotion, shop, finalRoom, gameOver, avatar;
	/**
	 * private Point for the starting point of the Hero object
	 */
	private Point startingPoint;
	/**
	 * private File for the .dat file of Hero object
	 */
	private File f;
	/**
	 * private Font for the fonts of the game, font is 20f and font2 is 15f
	 */
	private Font font, font2;
	/**
	 * private Clip for the back ground music of the game
	 */
	private Clip startBgm, gameBgm;
	/**
	 * Panel constructor instantiates and initializes all the objects such as the Strings, ints, booleans,
	 * Images, Clips, Point, File, Fonts, ArrayLists, Hero
	 */
	public Panel(){
		// INSTANTIATING VARIABLES
		setBackground(Color.BLACK);
		setLayout(null);
		start = menu = battle = hit = shop_b = level = false;
		heroHit = monstHit = 0;
		hero = null;
		f = new File("hero.dat");
		eGen = new EnemyGenerator();
		enemyList = new ArrayList<Enemy>();
		iGen = new ItemGenerator();
		itemList = new ArrayList<Item>();
		startingPoint = new Point(580,385);

		// INSTANTIATING LISTENERS
		addMouseListener(this);
		addKeyListener(this);
		setFocusable(true);
		
		// INSTANTIATING IMAGES
		gameTitle = new Image(0, 0, "gameTitle.png", null);
		startB = new Image(window_width/2 - 70, window_height/2 + 30, "startB.png", null);
		loadB = new Image(window_width/2 - 130, window_height/2 + 70, "loadB.png", null);
		exitB = new Image(window_width/2 - 73, window_height/2 + 120, "exitB.png", null);
		level1 = new Image(window_width/2 + 75, window_height/2 - 170, "level1.png", null);
		playerStatus = new Image(window_width/2 - 400, window_height/2 - 175, "playerStatus.png", null);
		battleStatus = new Image(window_width/2 - 400, window_height/2 - 175, "battleStatus.png", null);
		attackB = new Image(window_width/2 - 370, window_height/2 + 40, "attackB.png", null);
		usePotionB = new Image(window_width/2 - 370, window_height/2 + 60, "usePotionB.png", null);
		fleeB = new Image(window_width/2 - 370, window_height/2 + 80, "fleeB.png", null);
		healthPotion = new Image(395, 361, "healthPotion.png", null);
		shop = new Image(575, 375, "shop.png", null);
		shopStatus = new Image(window_width/2 - 400, window_height/2 - 175, "shopStatus.png", null);
		finalRoom = new Image (808, 169, "finalRoom.png", null);
		gameOver = new Image(0, 0, "gameOver.png", null);
		avatar = new Image(389, 175, "avatar.png", null);
		
		// CUSTOM FONT
		try {
			BufferedInputStream bis = new BufferedInputStream(
			        new FileInputStream("fixedsys.ttf"));
			Font f = Font.createFont(Font.PLAIN, bis);
			font = f.deriveFont(20f); // Font size
			
			BufferedInputStream bis2 = new BufferedInputStream(
			        new FileInputStream("fixedsys.ttf"));
			Font f2 = Font.createFont(Font.PLAIN, bis2);
			font2 = f2.deriveFont(15f);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// MUSIC MENU
		try{
			startBgm = AudioSystem.getClip();
			startBgm.open(AudioSystem.getAudioInputStream(new File("startBgm.wav")));
			startBgm.start();
			
			gameBgm = AudioSystem.getClip();
			gameBgm.open(AudioSystem.getAudioInputStream(new File("gameBgm.wav")));
			
		}catch(LineUnavailableException e){
			System.out.println("Audio Error");
		}catch(IOException e){
			System.out.println("FNFE");
		}catch(UnsupportedAudioFileException e){
			System.out.println("WFT");
		}
		
	}
	/**
	 * repaints all objects continously
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		// START MENU
		if(!start){
			gameTitle.paintComponent(g);
			startB.paintComponent(g);
			loadB.paintComponent(g);
			exitB.paintComponent(g);
		}
		
		// GAME STARTS
		if(menu){
			Point p = hero.getLocation();

			if(level){
				// PAINTING MAP
				level1.paintComponent(g);
				
				// UPDATING ENEMY IMAGES
				for(int i = 0; i < enemyList.size(); i++){
					if(!enemyList.get(i).isDead()){
						enemyList.get(i).update(g);
					}
				}
				
				// UPDATING ITEM IMAGES
				for(int i = 0; i < itemList.size(); i++){
					if(!itemList.isEmpty()){
						itemList.get(i).getImage().paintComponent(g);
					}
				}
				
				// BATTLE PANEL
				if(battle){
					battleStatus.paintComponent(g);
					g.setColor(Color.RED);
					g.fillRect(window_width/2 - 136, window_height/2 + 8, enemyList.get(monst).getHp(), 15);
					Image enemy = new Image(366, 220, enemyList.get(monst).getName() + ".png", null);
					Image heroB = new Image(136, 224, "shannonRight.png", null);
					enemy.paintComponent(g);
					heroB.paintComponent(g);
					attackB.paintComponent(g);
					usePotionB.paintComponent(g);
					fleeB.paintComponent(g);
			
					if(hit){
						g.setFont(font);
						g.setColor(Color.RED);
						g.drawString(Integer.toString(heroHit), 375, 210);
						g.drawString(Integer.toString(monstHit), 145, 215);
					}
					
					if(enemyList.get(monst).getHp() <= 0){
						battle = false;
					}
					if(hero.getHp() <= 0){
						battle = false;
						gameOver();
						gameOver_b = true;
					}
				} // SHOP PANEL
				else if(shop_b){
					g.setFont(font);
					g.setColor(Color.WHITE);
					shopStatus.paintComponent(g);
					g.drawString(hero.getName(), window_width/2 - 330, window_height/2 - 56);
					g.drawString(hero.getQuip(), window_width/2 - 328, window_height/2 - 28);
					g.drawString(Integer.toString(hero.getLevel()), window_width/2 - 317, window_height/2 - 107);
					g.drawString(Integer.toString(hero.getGold()), window_width/2 - 204, window_height/2 - 107);
					if(!hero.getItems().isEmpty()){
						for(int i = 0; i < hero.getItems().size(); i++){
							g.setFont(font2);
							g.drawString(hero.getItems().get(i).getName(),
									150, 355 + i*18);
							g.drawString(Integer.toString(hero.getItems().get(i).getValue()),
									280, 355 + i*18);
						}
					}
				} // PLAYER STATUS PANEL
				else{
					playerStatus.paintComponent(g);
					g.setFont(font);
					g.setColor(Color.WHITE);
					hero.getLocation().setLocation(p);
					g.drawString(hero.getName(), window_width/2 - 330, window_height/2 - 56);
					g.drawString(hero.getQuip(), window_width/2 - 328, window_height/2 - 28);
					g.drawString(Integer.toString(hero.getLevel()), window_width/2 - 317, window_height/2 - 107);
					g.drawString(Integer.toString(hero.getGold()), window_width/2 - 204, window_height/2 - 107);
					healthPotion.paintComponent(g);
					avatar.paintComponent(g);
					if(!hero.getItems().isEmpty()){
						for(int i = 0; i < hero.getItems().size(); i++){
							g.setFont(font2);
							g.drawString(hero.getItems().get(i).getName(),
									150, 355 + i*18);
							g.drawString(Integer.toString(hero.getItems().get(i).getValue()),
									280, 355 + i*18);
						}
					}
					
				}
			}
			finalRoom.paintComponent(g);
			shop.paintComponent(g);
			hero.update(g);
		}
		
		// GAME OVER PANEL
		if(gameOver_b){
			gameOver.paintComponent(g);
			exitB.paintComponent(g);
		}
				
	}
	@Override
	/**
	 * Controls all the keyboard press events
	 * @param e, the key press
	 */
	public void keyPressed(KeyEvent e) {
		if(!gameOver_b){
			// WASD OR ARROW CONTROLS
			if(!battle){
				if(e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP){
					hero.setDirection(1);
					hero.move();
				}
				if(e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN){
					hero.setDirection(3);
					hero.move();
				}
				if(e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT){
					hero.setDirection(2);
					hero.move();
				}
				if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT){
					hero.setDirection(4);
					hero.move();
				}
			}
			
			// IF HERO ENCOUNTERS AN ENEMY OBJECT
			for(int i = 0; i < enemyList.size(); i++){
				if(hero.getBounds().intersects(enemyList.get(i).getImage().getBounds())){
					battle = true;
					monst = i;
				}
			}
			
			// IF HERO ENCOUNTERS AN ITEM OBJECT
			for(int i = 0; i < itemList.size(); i++){
				if(hero.getBounds().intersects(itemList.get(i).getImage().getBounds())){
					if(hero.getItems().size() < 5){
						play("sound1.wav");
						hero.getItems().add(itemList.get(i));
						itemList.remove(i);
					}
					else{
						play("sound2.wav");
						hero.collectGold(itemList.get(i).getValue());
						itemList.remove(i);
					}
				}
			}
			
			// IF HERO ENCOUNTERS THE SHOP
			if(hero.getBounds().intersects(shop.getBounds())){
				shop_b = true;
			} else {
				shop_b = false;
			}
			
			// IF HERO ENCOUNTERS A SHOP, HERO CAN SELL FROM 1,2,3,4,5 keys
			if(shop_b){
				if(e.getKeyCode() == KeyEvent.VK_1){
					if(hero.getItems().get(0) != null){
						play("sound6.wav");
						hero.collectGold(hero.getItems().get(0).getValue());
						hero.getItems().remove(0);
					}
				}
				if(e.getKeyCode() == KeyEvent.VK_2){
					if(hero.getItems().get(1) != null){
						play("sound6.wav");
						hero.collectGold(hero.getItems().get(1).getValue());
						hero.getItems().remove(1);
					}
				}
				if(e.getKeyCode() == KeyEvent.VK_3){
					if(hero.getItems().get(2) != null){
						play("sound6.wav");
						hero.collectGold(hero.getItems().get(2).getValue());
						hero.getItems().remove(2);
					}
				}
				if(e.getKeyCode() == KeyEvent.VK_4){
					if(hero.getItems().get(3) != null){
						play("sound6.wav");
						hero.collectGold(hero.getItems().get(3).getValue());
						hero.getItems().remove(3);
					}
				}
				if(e.getKeyCode() == KeyEvent.VK_5){
					if(hero.getItems().get(4) != null){
						play("sound6.wav");
						hero.collectGold(hero.getItems().get(4).getValue());
						hero.getItems().remove(4);
					}
				}	
			}
			
			// IF HERO ENCOUNTERS THE FINAL ROOM
			if(hero.getBounds().intersects(finalRoom.getBounds())){
				play("sound5.wav");
				hero.increaseLevel();
				hero.heal(162 - hero.getHp());
				hero.setPosition(startingPoint);
				changeLevel();
				saveGame();
			}
		}
	}
	@Override
	/**
	 * mouseClicked controls all the click events
	 * @param e, Mouse Event
	 */
	public void mouseClicked(MouseEvent e) {
		Point click = new Point(e.getX(), e.getY());
		System.out.println(e.getPoint());
		if(!gameOver_b){
			// GAME MENU INTERACTIONS
			if(!menu){
				if(startB.getBounds().contains(click)){
					play("sound1.wav");
					start = true;
					newGame();
				}
				if(loadB.getBounds().contains(click)){
					play("sound1.wav");
					loadGame();	
				}
			}
			else{
				// BATTLE MENU INTERACTIONS
				if(battle){
					
					// ATTACK BUTTON
					if(attackB.getBounds().contains(click)){
						play("sound1.wav");
						heroHit = hero.attack(enemyList.get(monst));
						monstHit = enemyList.get(monst).attack(hero);
						hit = true;
						for(int i = 0; i < enemyList.size(); i++){
							if(enemyList.get(i).isDead()){
								hit = true;
								hero.collectGold(enemyList.get(i).getGold());
								if(hero.getItems().size() < 5){
									hero.pickUpItem(enemyList.get(i).getItem());
								}
							}
						}
					}
					
					// USE POTION BUTTON
					if(usePotionB.getBounds().contains(click)){
						play("sound1.wav");
						for(int i = 0; i < hero.getItems().size(); i++){
							if(hero.getItems().get(i).getName().equals("Health Potion")){
								int max_hp = 162;
								if(hero.getHp() >= max_hp - 50){
									hero.heal(max_hp - hero.getHp());
								}else{
									hero.heal(50);
								}
								
								hero.getItems().remove(i);
							}
						}
					}
					
					// FLEE BUTTON
					if(fleeB.getBounds().contains(click)){
						play("sound4.wav");
						Random rand = new Random();
						hero.setDirection(rand.nextInt(4) + 1);
						hero.move();
						battle = false;
						
						for(int i = 0; i < enemyList.size(); i++){
							if(hero.getBounds().intersects(enemyList.get(i).getImage().getBounds())){
								battle = true;
								monst = i;
							}
							
						}
					}
				} // (!battle)
				
				// HEALTH POTION BUTTON
				if(healthPotion.getBounds().contains(click)){
					for(int i = 0; i < hero.getItems().size(); i++){
						if(hero.getItems().get(i).getName().equals("Health Potion")){
							play("sound3.wav");
							int max_hp = 162;
							if(hero.getHp() >= max_hp - 50){
								hero.heal(max_hp - hero.getHp());
							}else{
								hero.heal(50);
							}
							hero.getItems().remove(i);
						}
					}
				}
			}
		} // (gameOver)
		//hit = false;
		
		// EXIT BUTTON FOR GAME OVER PANEL
		if(exitB.getBounds().contains(click)){
			play("sound2.wav");
			try {
				Thread.sleep(350);
				System.exit(0);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}	
		}
	}
	/**
	 * Game Over scene event, occurs when Hero object is less than 0 hp
	 */
	public void gameOver(){
		gameBgm.stop();
		play("gameOverBgm.wav");
		try {
			Thread.sleep(3000);
			gameOver_b = true;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Changes the monsters and item's after changing levels
	 */
	public void changeLevel(){
		if(!enemyList.isEmpty()){
			for(int i = 0; i < enemyList.size(); i++){
				enemyList.remove(i);
			}
		}
		for(int i = 0; i < 4+hero.getLevel(); i++){
			enemyList.add(eGen.generateEnemy(hero.getLevel()));
		}
		if(!itemList.isEmpty()){
			for(int i = 0; i < itemList.size(); i++){
				itemList.remove(i);
			}
		}
		for(int i = 0; i < 3; i++){
			itemList.add(iGen.generateItem());
		}
	}
	/**
	 * Save game upon interacting with final room. Uses ObjectOutputStream to write onto file
	 */
	public void saveGame(){
		try{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
			out.writeObject(hero);
			out.close();
		}catch(IOException e){
			System.out.println("Error processing file");
		}
	}
	/**
	 * Load game for the game menu. Uses ObjectInputStream to read in file
	 */
	public void loadGame(){
		if(f.exists()){
			try{
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
				hero = (Hero) in.readObject();
				in.close();
				
			}catch(IOException e1){
				System.out.println("Error processing file");
			}catch(ClassNotFoundException e1){
				System.out.println("Could not find class.");
			}
			level = true;
			changeLevel();
			menu = true;
			startBgm.stop();
			gameBgm.loop((int) gameBgm.getMicrosecondLength());
			start = true;
		}
		else{
			UIManager.put("OptionPane.background", Color.WHITE);
			UIManager.put("Panel.background", Color.WHITE);
			UIManager.put("OptionPane.font", font);

			JOptionPane.showMessageDialog(null, "No saved game","Loading...", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	/**
	 * New game menu scene, uses JLabel, JTextField, and JButton
	 */
	public void newGame(){
		JLabel jl = new JLabel("Please enter your Hero's name");
		JTextField jt = new JTextField("Hero name", 20);
		JButton jb = new JButton("Enter");
		jl.setForeground(Color.WHITE);
		
		JLabel jlQ = new JLabel("Please enter Hero's  quip");
		JTextField jtQ = new JTextField("Hero quip", 20);
		JButton jbQ= new JButton("Enter");
		jlQ.setForeground(Color.WHITE);
		
		this.add(jl);
		this.add(jt);
		this.add(jb);
		this.add(jlQ);
		this.add(jtQ);
		this.add(jbQ);
		
		jl.setFont(font);
		jt.setFont(font);
		jb.setForeground(Color.BLACK);
		jb.setBackground(Color.ORANGE);
		
		jlQ.setFont(font);
		jtQ.setFont(font);
		jbQ.setForeground(Color.BLACK);
		jbQ.setBackground(Color.ORANGE);
		
		jl.setLocation(window_width/2 - 160, window_height/2 - 240);
		jl.setSize(500,300);
		jt.setLocation(window_width/2 - 97, window_height/2 - 60);
		jt.setSize(170,25);
		jb.setLocation(window_width/2 - 97, window_height/2 - 20);
		jb.setSize(170, 30);
		
		jlQ.setLocation(window_width/2 - 145, window_height/2 - 240);
		jlQ.setSize(400,300);
		jtQ.setLocation(window_width/2 - 97, window_height/2 - 60);
		jtQ.setSize(170,25);
		jbQ.setLocation(window_width/2 - 97, window_height/2 - 20);
		jbQ.setSize(170, 30);
	
		jlQ.setVisible(false);
		jtQ.setVisible(false);
		jbQ.setVisible(false);
	
		
		jb.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				heroName = jt.getText();
				play("sound1.wav");
			
				jl.setVisible(false);
				jt.setVisible(false);
				jb.setVisible(false);
				
				jlQ.setVisible(true);
				jtQ.setVisible(true);
				jbQ.setVisible(true);
			}
		});
		
		jbQ.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				heroQuip = jtQ.getText();
				play("sound1.wav");
				
				jlQ.setVisible(false);
				jtQ.setVisible(false);
				jbQ.setVisible(false);
				
				hero = new Hero(heroName, heroQuip, startingPoint, 1);
				level = true;
				Item potion = new Item("Health Potion", 25);
				hero.getItems().add(potion);
				
				changeLevel();
				
				menu = true;
				startBgm.stop();
				gameBgm.loop((int) gameBgm.getMicrosecondLength());
			}
		});
	}
	/**
	 * plays sounds
	 * @param filename, name of file
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
	/**
	 * Thread for repainting/updating the panel
	 */
	@Override
	public void run() {
		while(true){
			repaint();
			
			try{
				Thread.sleep(16);
			}catch(InterruptedException e){}
		}
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {}
	@Override
	public void mouseExited(MouseEvent arg0) {}
	@Override
	public void mousePressed(MouseEvent arg0) {}
	@Override
	public void mouseReleased(MouseEvent arg0) {}
	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void keyTyped(KeyEvent e) {}
	
}
