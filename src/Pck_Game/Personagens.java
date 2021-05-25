package Pck_Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public abstract class Personagens {
	private int x, y;
	private int width, height;
	private double maxLife, life;
	private Image img;
	
	public Personagens(int x, int y,int maximumLife, String path) {
		this.x = x;
		this.y = y;
		
		maxLife = maximumLife;
		life = maxLife;
		
		ImageIcon ss = new ImageIcon(path);
		img = ss.getImage();
		width = img.getWidth(null);
		height = img.getHeight(null);
	}
	
	public void draw(Graphics g) {
		g.drawImage(img, x, y, null);
		g.setColor(Color.black);
		g.fillRect(this.getX(), this.getY() - 15, (int)((life / maxLife)*100), 15);
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
	
	public double getMaxLife() {
		return maxLife;
	}
	
	public double getLife() {
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
	
	public Image getImg() {
		return img;
	}
	
	
}
