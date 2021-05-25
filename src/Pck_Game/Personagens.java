package Pck_Game;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public abstract class Personagens {
	private int x, y;
	private int width, height;
	private int life;
	private Image img;
	
	public Personagens(int x, int y, String path) {
		this.x = x;
		this.y = y;
		
		life = 100;
		
		ImageIcon ss = new ImageIcon(path);
		img = ss.getImage();
		width = img.getWidth(null);
		height = img.getHeight(null);
	}
	
	public void draw(Graphics g) {
		g.drawImage(img, x, y, null);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	
}
