package Pck_Game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener {
	private ArrayList<Inimigo> inimigos = new ArrayList<Inimigo>();
	private ArrayList<Aliado> aliados = new ArrayList<Aliado>();
	private static final int SCREEN_WIDTH = 1280;
	private static final int SCREEN_HEIGHT = 720;
	private int mX, mY;
	private Timer timer, timerSpawn;
	private MyListeners mList = new MyListeners();
	private Random random;

	public GamePanel() {
		this.setBackground(Color.black);
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setFocusable(true);
		this.addMouseListener(mList);
		this.addMouseMotionListener(mList);
		
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
		
		g.setColor(new Color(60, 120, 60));
		for(int i = 0; i < aliados.size(); i++) {
			Aliado ind = aliados.get(i);
			if (ind.isToContruct() == false) {
				if(mX > 200 && mX < 900) {
					if (mY > 240 && mY < SCREEN_HEIGHT-80) {
					g.fillRect(200+(100*((mX-200)/100))-2, 240+(100*((mY-240)/100))+2, 100, 100);
					}
				}
			}
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
		
		g.drawImage(new ImageIcon("img\\sPlayer.png").getImage(), 25, 100, this);
		
		for(int i = 0; i < aliados.size(); i++) {
			Aliado ind = aliados.get(i);
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
			inimigos.add(new Inimigo(SCREEN_WIDTH-50, 240 + random.nextInt(4)*100));
		}
	}
	
	public class MyListeners implements MouseListener, MouseMotionListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (e.getX() > 25 && e.getX() < 125) {
				if(e.getY() > 100 && e.getY() < 200) {
					aliados.add(new Aliado(e.getX()-50, e.getY()-50));
				}
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			for(int i = 0; i < aliados.size(); i++) {
				Aliado ind = aliados.get(i);
				if (ind.isToContruct() == false) {
					if(mX > 200 && mX < 900 && mY > 240 && mY < SCREEN_HEIGHT-80) {
						ind.setX(200+(100*((mX-200)/100))-2);
						ind.setY(240+(100*((mY-240)/100))+2);
						ind.setToContruct(true);
						
					}else {
						aliados.remove(i);
					}
				}
			}
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			mX = e.getX();
			mY = e.getY();
			for(int i = 0; i < aliados.size(); i++) {
				Aliado ind = aliados.get(i);
				if (ind.isToContruct() == false) {
					ind.setX(e.getX()-50);
					ind.setY(e.getY()-50);
				}
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			mX = e.getX();
			mY = e.getY();
		}
		
	}

}
