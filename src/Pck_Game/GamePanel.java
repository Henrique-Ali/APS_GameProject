package Pck_Game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener {
	private ArrayList<Inimigo> inimigos = new ArrayList<Inimigo>();
	private static final int SCREEN_WIDTH = 1200;
	private static final int SCREEN_HEIGHT = 800;
	private Timer timer, timerSpawn;
	private Random random;

	public GamePanel() {
		this.setBackground(Color.black);
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setFocusable(true);
		timer = new Timer(1, this);
		timer.start();
		timerSpawn = new Timer(1000, this);
		timerSpawn.start();
		
		random = new Random();
		
	}
	
	public void paint(Graphics g) {
		super.paint(g);
//////////////////////////////////////////////////////////////////////////////////
		Graphics2D g2d = (Graphics2D) g;
		
		g.setColor(new Color(100, 200, 255));
		g.fillRect(0, 0, SCREEN_WIDTH, 300);
		
		g.setColor(new Color(50, 100, 50));
		g.fillRect(0, 300, 500, SCREEN_HEIGHT-300);
		
		g.setColor(new Color(40, 90, 40));
		g2d.setStroke(new BasicStroke(4));
		g2d.drawLine(0+2, 300+2, 500-2, 300+2);
		g2d.drawLine(0+2, 400+2, 500-2, 400+2);
		g2d.drawLine(0+2, 500+2, 500-2, 500+2);
		g2d.drawLine(0+2, 600+2, 500-2, 600+2);
		g2d.drawLine(0+2, 700+2, 500-2, 700+2);
		
		g2d.drawLine(0+2, 300+2, 0+2, SCREEN_HEIGHT-2);
		g2d.drawLine(100+2, 300+2, 100+2, SCREEN_HEIGHT-2);
		g2d.drawLine(200+2, 300+2, 200+2, SCREEN_HEIGHT-2);
		g2d.drawLine(300+2, 300+2, 300+2, SCREEN_HEIGHT-2);
		g2d.drawLine(400+2, 300+2, 400+2, SCREEN_HEIGHT-2);
		
		g.setColor(new Color(40, 40, 50));
		g.fillRect(500, 300, SCREEN_WIDTH-500, SCREEN_HEIGHT-300);
		g.setColor(new Color(30, 30, 40));
		g2d.drawLine(500+2, 300+2, SCREEN_WIDTH-2, 300+2);
		g2d.drawLine(500+2, 400+2, SCREEN_WIDTH-2, 400+2);
		g2d.drawLine(500+2, 500+2, SCREEN_WIDTH-2, 500+2);
		g2d.drawLine(500+2, 600+2, SCREEN_WIDTH-2, 600+2);
		g2d.drawLine(500+2, 700+2, SCREEN_WIDTH-2, 700+2);
//////////////////////////////////////////////////////////////////////////////////
		
		
		for(int i = 0; i < inimigos.size(); i++) {
			Inimigo ind = inimigos.get(i);
			ind.draw(g);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
		for(int i = 0; i < inimigos.size(); i++) {
			Inimigo ind = inimigos.get(i);
			if (ind.getWidth()+ind.getX() < 0) {
				inimigos.remove(ind);			}
		}
		if(e.getSource().equals(timerSpawn)) {
			inimigos.add(new Inimigo(SCREEN_WIDTH-100, 300 + random.nextInt(5)*100));
		}
	}

}
