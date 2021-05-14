package Pck_Game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class Inimigo implements ActionListener{
	private int x, y;
	private int width, height;
	private Image img;
	private Timer timer;

	public Inimigo(int x, int y) {
		this.x = x;
		this.y = y;
		ImageIcon ss = new ImageIcon("img\\sEnemy.png");
		img = ss.getImage();
		this.width = img.getWidth(null);
		this.height = img.getHeight(null);
		
		timer = new Timer(1, this);
		timer.start();
	}
	
	public void draw(Graphics g) {
		g.drawImage(img, x, y, null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		x -= 2;
	}
	public int getX() {
		return x;
	}

	public int getWidth() {
		return width;
	}
}
