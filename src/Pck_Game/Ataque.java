package Pck_Game;


import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Ataque extends Personagens implements ActionListener{
	private Timer timer;
	
	public Ataque(int x, int y) {
		super(x, y, 999, "img\\sBullet.png");
		
		timer = new Timer(1, this);
		timer.start();
	}
	@Override
	public void draw(Graphics g) {
		g.drawImage(this.getImg(), this.getX(), this.getY(), null);
		
	}
	public void actionPerformed(ActionEvent e) {	
		setX(getX()+4);
	}

	public void setPausa(boolean pausa) {
		if (pausa == false) {
			timer.start();
		} else {
			timer.stop();
		}
	}
	
}
