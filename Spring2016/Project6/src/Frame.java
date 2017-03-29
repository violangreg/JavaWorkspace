//Greg Paolo D. Violan, 011706641, Project6 CECS277
/**
 * imports necessary java io and utilities from library
 */
import java.awt.*;
import javax.swing.JFrame;
/**
 * Frame class just creates the Frame of the game. Instantiates the necessary dimensions, height, and width
 * of the window. Sets the title and visibility and resizability. It also adds the panel to the thread and 
 * contentPane. This then finally starts the panel thread.
 */
@SuppressWarnings("serial")
public class Frame extends JFrame {
	/**
	 * Declares Panel object
	 */
	private static Panel panel;
	/**
	 * Frame constructor instantiates necessary dimensions for the window and instantiates the panel
	 * as well as add it onto the getContentPane. This also instantiates the Thread for the panel class
	 * sets all necessary window utilities and finally start the thread
	 */
	public Frame(){
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int screen_width = (int) screen.getWidth();
		int screen_height = (int) screen.getHeight();
		int window_width = screen_width - screen_height/2;
		int window_height = screen_height - screen_width/10;
		
		setBounds(screen_width/7, screen_height/10, window_width, window_height);
		panel = new Panel();
		getContentPane().add(panel);
		Thread t = new Thread(panel);
		
		setTitle("Rock Paper Scissors by Greg Violan");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		
		t.start();
	}
	/**
	 * main just creates a Frame object to run the Game
	 * @param args
	 */
	public static void main(String[] args) {
		new Frame();
	}
}
