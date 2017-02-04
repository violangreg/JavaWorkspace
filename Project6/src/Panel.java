//Greg Paolo D. Violan, 011706641, Project6 CECS277
/**
 * imports necessary java io, awt, swing, net, sound and utilities from library
 */
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.sound.sampled.*;
import javax.swing.*;
/**
 * Panel class has the Client socket that connects to the server. This class also puts together
 * every graphics that may show in the game. This uses what the server sends over to the PrintStream and 
 * use that to determine what to show on the game. This also uses a thread which repaints it every 60 fps
 */
@SuppressWarnings("serial")
public class Panel extends JPanel implements MouseListener, Runnable {
	// Declaring all necessary variables
	/**
	 * Declares image variables used in the game
	 */
	private Image rock, paper, scissors, exit, win, lose, tie, scoreboard, background,
					rockRight, rockLeft, paperRight, paperLeft, scissorsRight, scissorsLeft;
	/**
	 * Declares score points for the game as well as the player's weapon and computer's prediction
	 */
	private int playerScore, computerScore, tieScore, userWeapon, computerWeapon;
	/**
	 * Declares the strings to grab informations from the server through the BufferedReader
	 */
	private String results, prediction;
	/**
	 * Declares the socket to connect to the server
	 */
	private Socket s;
	/**
	 * Declares the BufferedReader to read the information sent by the server
	 */
	private BufferedReader in;
	/**
	 * Declares the PrintStream to spit out information to the server
	 */
	private PrintStream out;
	/**
	 * Declares font for the custom font of score
	 */
	private Font font;
	/**
	 * Panel constructor instantiates all variables and objects needed for this class
	 * such as the background, the images, MouseListener, server socket, BufferedReader and
	 * PrintStream. It also instantiates all the custom thread animations
	 */
	public Panel(){
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		final int window_height = (int) screen.getHeight() - (int) screen.getWidth()/10;
		
		background = new Image(0, 0, "background.png", null);
		rock = new Image(103, (window_height/6) + 65, "rock.png", "rockDown.png");
		rockRight = new Image(240, 415, "rockRight.png", null);
		rockLeft = new Image(620, 415, "rockLeft.png", null);
		paper = new Image(395, (window_height/6) + 65, "paper.png", "paperDown.png");
		paperRight = new Image(240, 415, "paperRight.png", null);
		paperLeft = new Image(620, 415, "paperLeft.png", null);
		scissors = new Image(690, (window_height/6) + 65, "scissors.png", "scissorsDown.png");
		scissorsRight = new Image(240, 415, "scissorsRight.png", null);
		scissorsLeft = new Image(620, 415, "scissorsLeft.png", null);
		win = new Image(101, 390, "win.png", null);
		lose = new Image(101, 390, "lose.png", null);
		tie = new Image(101, 390, "tie.png", null);
		exit = new Image(101, 40, "exit.png", "exitDown.png");
		scoreboard = new Image (263, 40, "scoreBoard.png", null);
		playerScore = computerScore = tieScore = 0;
		results = prediction = "";
		//play("bgm.wav");
		
		addMouseListener(this);
		setFocusable(true);
		
		try {
			s = new Socket("localhost", 1025);
			System.out.println("Connected \nGame is playing");
			out = new PrintStream(s.getOutputStream());
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			
			// Custom font
			BufferedInputStream bis = new BufferedInputStream(
                    new FileInputStream("brandon_bld.ttf"));
			Font f = Font.createFont(Font.PLAIN, bis);
			font = f.deriveFont(25f); // Font size
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (FontFormatException e) {
			e.printStackTrace();
		}
		
		// Thread Animations
		rock.pressThread();
		paper.pressThread();
		scissors.pressThread();
		exit.pressThread();
	}
	/**
	 * mouseClicked method keeps track of the mouse and set events accordingly on the 
	 * mouse action. It spits out whatever the user click to the server. Simultaneously it grabs whatever
	 * the server gives to this Client. This Client then uses that information to have execute certain events 
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		 Point click = e.getPoint();
		
		 // once player click a weapon image, send weapon to server
         if(rock.getBounds().contains(click)) {
             rock.press();
             userWeapon = 1;
             out.println("r");
     		 out.flush();
         }
         if(paper.getBounds().contains(click)){
             paper.press();
             userWeapon = 2;
             out.println("p");
     		 out.flush();
         }
         if(scissors.getBounds().contains(click)){
        	 scissors.press();
        	 userWeapon = 3;
        	 out.println("s");
     		 out.flush();
         }
         if(exit.getBounds().contains(click)){
        	 exit.press();
        	 out.println("e");
     		 out.flush();
     		 try {
				Thread.sleep(250);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
     		 exitGame(playerScore, computerScore, tieScore);
         }
         
         // simultaneously also receive back the info from the server
         // use this result to change score and display pop up result animation
         try {
			results = in.readLine();
			prediction = in.readLine();
			computerWeapon = Integer.parseInt(prediction);
         } catch (IOException e1) {
			e1.printStackTrace();
		 }
         
         // Update score & animations 
         if(results.equals("w")){
 			++playerScore;
 			play("win.wav");
 			win.shake();
 		 }
 		 else if(results.equals("l")){
 			++computerScore;
 			play("lose.wav");
 			lose.shake();
 		 }
 		 else if(results.equals("t")){
 			++tieScore;
 			play("tie.wav");
 			tie.shake();
 		 }
         
         if(results.equals("w") || results.equals("l") || results.equals("t")){
  			rockRight.shake();
  			rockLeft.shake();
  			paperRight.shake();
  			paperLeft.shake();
  			scissorsRight.shake();
  			scissorsLeft.shake();
         }
	}	
	/**
	 * paintComponent methods paints all the graphic components onto the frame
	 * It also grabs whatever the server spits out after evaluating what the outcome of the game was
	 * This method also updates the score and other component animations
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		background.paintComponent(g);
		
		// Displaying rock, paper, scissors, exit images
		rock.paintComponent(g);
		paper.paintComponent(g);
		scissors.paintComponent(g);
		exit.paintComponent(g);
		scoreboard.paintComponent(g);
		
		// Displaying pop up result animation
		if(results.equals("w")){
			win.paintComponent(g);
			if(userWeapon == 1 && computerWeapon == 3){
				rockRight.paintComponent(g);
				scissorsLeft.paintComponent(g);
			}
			else if(userWeapon == 2 && computerWeapon == 1){
				paperRight.paintComponent(g);
				rockLeft.paintComponent(g);
				
			}
			else if(userWeapon == 3 && computerWeapon == 2){
				scissorsRight.paintComponent(g);
				paperLeft.paintComponent(g);
			}
		} 
		else if(results.equals("l")){
			lose.paintComponent(g);
			if(userWeapon == 3 && computerWeapon == 1){
				scissorsRight.paintComponent(g);
				rockLeft.paintComponent(g);
			}
			else if(userWeapon == 1 && computerWeapon == 2){
				rockRight.paintComponent(g);
				paperLeft.paintComponent(g);
			}
			else if(userWeapon == 2 && computerWeapon == 3){
				paperRight.paintComponent(g);
				scissorsLeft.paintComponent(g);
			}
		}
		else if(results.equals("t")){
			tie.paintComponent(g);
			if(userWeapon == 1 && computerWeapon == 1){
				rockRight.paintComponent(g);
				rockLeft.paintComponent(g);
			}
			else if(userWeapon == 2 && computerWeapon == 2){
				paperRight.paintComponent(g);
				paperLeft.paintComponent(g);
			}
			else if(userWeapon == 3 && computerWeapon == 3){
				scissorsRight.paintComponent(g);
				scissorsLeft.paintComponent(g);
			}
		}
		
		// Displaying score
		g.setColor(Color.BLACK);
		g.setFont(font);
		g.drawString(Integer.toString(playerScore) + " points", 358, 90);
		g.drawString(Integer.toString(computerScore) + " points", 567, 90);
		g.drawString(Integer.toString(tieScore) + " points", 768, 90);
	}
	/**
	 * exitGame exits the game and display a message of the score, also closes the socket	
	 * @param p, the player score
	 * @param c, the computer score
	 * @param t, the tie score
	 */
	public void exitGame(int p, int c, int t){
		 System.out.println("Game is exiting... \nDisconnected");
		 JOptionPane.showMessageDialog(this, "Player: " + Integer.toString(p) +
				 "\nComputer: " + Integer.toString(c) + "\nTie: " + Integer.toString(t)
				 + "\nThanks for playing");
		 try {
			s.close();
		 } catch (IOException e1) {
			e1.printStackTrace();
		 }
		System.exit(0);
	}
	/**
	 * The Panel thread that repaints all the graphics
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
	/**
	 * play method plays the sound of the file name
	 * @param filename, the .wav file
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
	 * Unused mouse methods
	 */
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
}