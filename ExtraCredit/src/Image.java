//Greg Paolo D. Violan, 011706641, Project6 CECS277
/**
 * imports necessary java io and utilities from library
 */
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
/**
 * Image class creates all the images and does all the image animations in the game such as the
 * press and shake. This extends JPanel for paintComponent and uses two different methods with thread
 * for animations.
 */
@SuppressWarnings("serial")
public class Image extends JPanel{
	/**
	 * Declares the point of the image
	 */
	private Point imgPoint;
	/**
	 * Declares the BufferedImages of the Image
	 */
	private BufferedImage img, imgDown;
	/**
	 * Declares a boolean tracker for pressed Images
	 */
	private boolean pressed;
	/**
	 * Image constructor creates an two BufferedImages for animations
	 * @param x, the x coordinates of the Image
	 * @param y, the y coordinates of the Image
	 * @param f, the file name of the first Image
	 * @param fd, the file name of the second Image
	 */
    public Image(int x, int y, String f, String fd) {
    	imgPoint = new Point(x,y);
    	pressed = false;
    	
    	try {
            img = ImageIO.read(new File(f));
            if(fd != null){
            	imgDown = ImageIO.read(new File(fd));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    /**
     * getBounds method creates a Rectangle for the Images
     * This is used for contains so that we can click on the Images and create an event
     */
    public Rectangle getBounds(){
    	Rectangle bounds = new Rectangle(imgPoint, new Dimension(img.getWidth(), img.getHeight()));
    	return bounds;
    }
    /**
     * paintComponent paints the Images and its pressed down Image if press method is called
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (img != null && imgPoint != null) {
            g.drawImage(img, imgPoint.x, imgPoint.y, this);
        }
        
        if(imgDown != null){
	        if(pressed == true){
	        	g.drawImage(imgDown, imgPoint.x, imgPoint.y, this);
			} else {
				g.drawImage(img, imgPoint.x, imgPoint.y, this);
			}
        }
    }
    /**
     * press method just instantiate pressed as true
     */
    public void press(){
    	pressed = true;
    }
    /**
     * pressThread is the thread for press method, once press method is called, it sleeps for 220 millisecond
     * and then release the press creating a press-like animation
     */
    public void pressThread(){	
    	Runnable r = new Runnable(){
			@Override
			public void run() {
				while(true){
					pressed = false;
					
					repaint();
					try {
						Thread.sleep(220);
						
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
    		
    	};
    	Thread t = new Thread(r);
    	t.start();
    }
    /**
     * shake method shakes the Image
     */
    public void shake(){
    	Point point = imgPoint.getLocation();
    	int delay = 75;
    	Runnable r = new Runnable(){
			@Override
			public void run() {
				for(int i = 0; i < 3; i++){
					try {
						
						imgPoint.setLocation(new Point((int)imgPoint.getX() + 3, (int)imgPoint.getY()));
						Thread.sleep(delay);
						imgPoint.setLocation(point);
						Thread.sleep(delay);
						imgPoint.setLocation(new Point((int)imgPoint.getX() - 3, (int)imgPoint.getY()));
						Thread.sleep(delay);
						imgPoint.setLocation(point);
						Thread.sleep(delay);
					
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
    	};
    	Thread t = new Thread(r);
    	t.start();
    }
    /**
     * decrease the width of the object each param
     * @param damage, the amount to decrease the width
     */
    public void decreaseWidth(int damage){
    	Rectangle rect = this.getBounds();
    	rect.setSize(img.getWidth() - damage, img.getHeight());
    }
}