package Pck_Game;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Ataque extends Personagens implements ActionListener{
	private Timer timer;
	
	public Ataque(int x, int y) {
		super(x, y, "img\\sBullet.png");
		
		timer = new Timer(1, this);
		timer.start();
	}
	
	public void actionPerformed(ActionEvent e) {
		setX(getX()+2);
		
	}
}
