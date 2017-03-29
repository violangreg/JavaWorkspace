/**
 * import necessary libraries and API's
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JPanel;
/**
 * Timer class extends JPanel and implements Runnable, this class is used to keep track of the time count down
 * when it reaches 0, the game ends and displays the score
 * @author Greg
 */
@SuppressWarnings("serial")
public class Timer extends JPanel implements Runnable {
	/**
	 * Declaring the position of the timer and the timer time and the score's count
	 */
	private int mX, mY, timer, QuarryCount;
	/**
	 * Timer's constructor
	 * @param t, the time of the timer
	 * @param q, the QuarryCount which is the score 
	 * @param x, the x position of the timer
	 * @param y, the y position of the timer
	 */
	public Timer(int t, int q,  int x, int y){
		timer = t;
		QuarryCount = q;
		mX = x;
		mY = y;
	}
	/**
	 * incQuarryCount increments the QuarryCount by one
	 */
	public void incQuarryCount(){
		QuarryCount++;
	}
	/**
	 * getQuarryCount gets the QuarryCount
	 * @return QuarryCount, the score of the player
	 */
	public int getQuarryCount(){
		return QuarryCount;
	}
	/**
	 * time gets the timer or time
	 * @return timer, the time of the timer
	 */
	public int time(){
		return timer;
	}
	/**
	 * paintComponent paints the timer
	 * @param g, the Graphics of the timer
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		// COLOR AND FONT FOR THE TEXT
		g.setColor(Color.WHITE);
		g.setFont(new Font("Calibri", Font.PLAIN, 20));
		g.drawString("Kills: " + Integer.toString(QuarryCount), mX - 17, mY + 25);
		
		g.drawString("TIMER", mX - 14, mY - 25);
		if(timer < 10){
			g.setColor(Color.RED);
		}
		if(timer <= 0){
			g.drawString("0", mX, mY);
		}
		else{
			g.drawString(Integer.toString(timer), mX, mY);
		}
	}
	/**
	 * play plays the sound of the filename
	 * @param filename, the filename of the sound
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
	@Override
	/**
	 * run is a thread that runs the timer and decrement it every second
	 */
	public void run() {
		boolean running = true;
		while(running){
			--timer;
			if(timer < 0){
				running = false;
			}
			if(timer < 10){
				play("beep.wav");
			}
		try{
			Thread.sleep(1000);
		}catch(InterruptedException e){}
		}
	}
}