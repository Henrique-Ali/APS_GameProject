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
	private static final int SCREEN_WIDTH = 1280;
	private static final int SCREEN_HEIGHT = 720;
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
		g.fillRect(0, 0, SCREEN_WIDTH, 220);
		
		g.setColor(new Color(50, 100, 50));
		g.fillRect(0, 220, SCREEN_WIDTH-378, SCREEN_HEIGHT-220);
		
		g.setColor(new Color(40, 40, 50));
		g.fillRect(SCREEN_WIDTH-380, 220, SCREEN_WIDTH - 900, SCREEN_HEIGHT-220);
		
		g2d.setStroke(new BasicStroke(4));
		g2d.setColor(new Color(30, 30, 40));
		
		for(int i = 0; i < 5; i++) {
			g2d.drawLine(SCREEN_WIDTH-380, 242+(100*i), SCREEN_WIDTH, 242+(100*i));
		}
		
		g2d.setColor(new Color(40, 90, 40));
		//Linha Vertical
		for(int i = 0; i < 8; i++) {
			g2d.drawLine(200-2+(i*100), 240+2, 200-2+(i*100), SCREEN_HEIGHT-80);
		}
		
		//Linha Horizontal
		for(int i = 0; i < 5; i++) {
			g2d.drawLine(200, 242+(100*i), 900-2, 242+(100*i));
		}
		
//////////////////////////////////////////////////////////////////////////////////
		
		for(int i = 0; i < inimigos.size(); i++) {
			Inimigo ind = inimigos.get(i);
			ind.draw(g);
		}
		
		g.setColor(new Color(176, 109, 76));
		g.fillRect(0, 80, 150, SCREEN_HEIGHT - 160);
		
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
			inimigos.add(new Inimigo(SCREEN_WIDTH-50, 240 + random.nextInt(4)*100));
		}
	}

}
