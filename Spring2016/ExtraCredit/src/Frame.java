//Greg Paolo Violan, 011706641
/**
 * imported necessary api's
 */
import java.awt.*;
import javax.swing.*;
@SuppressWarnings("serial")
/**
 * Frame class creates a Frame for the Panel class. It also instantiates a Thread to run Panel's thread.
 * @author Greg
 */
public class Frame extends JFrame {
	/**
	 * private Panel object for the frame
	 */
	private Panel panel;
	/**
	 * Frame constructor sets the Bounds of the frame. It also instantiates Panel and starts the thread
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
		
		setTitle("Dungeon Crawler 2.0");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		
		t.start();
	}
	/**
	 * main method of the program. This creates a Frame object
	 * @param args
	 */
	public static void main(String[] args){
		new Frame();
	}
}
