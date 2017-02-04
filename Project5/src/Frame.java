import javax.swing.JFrame;
import java.awt.*;
/**
 * @author Greg Violan, Frame instantiates the panel, screenSize and sets everything for the window
 */
@SuppressWarnings("serial")
public class Frame extends JFrame {
	/**
	 * declare private class panel for the game
	 */
	private Panel panel;
	/**
	 * Frame class instantiates all the necessary screen dimensions, set things, panel and thread
	 */
	public Frame(){
		//For screen size
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) screenSize.getWidth();
		int height = (int) screenSize.getHeight();
		
		setBounds(width/7, height/7, width - height/2, height - width/10); // x, y, w, h of window
		panel = new Panel();
		getContentPane().add(panel);
		Thread t = new Thread(panel); // creates a thread of panel
		
		setTitle("Oregon Trail Hunter (ver. Greg V)");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		
		t.start(); // starts thread panel (objects should be able to start running [moving])]
	}
	/**
	 * the main of the game where it starts
	 * @param args
	 */
	public static void main(String[] args){
		Frame f = new Frame();
	}
}