import java.awt.Color;
import java.awt.Graphics;

import javax.swing.*;

public class Panel extends JPanel implements Runnable {
	public Panel(){
		setBackground(Color.BLACK);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		g.drawRect(100, 100, 100, 100);
		g.fillArc(100, 100, 80, 80, 120, 40);
	}

	@Override
	public void run() {
		while(true){
			repaint();
			try {
				Thread.sleep(16);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
}
