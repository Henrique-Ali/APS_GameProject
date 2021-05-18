package Pck_Game;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Inimigo extends Personagens implements ActionListener{
	private Timer timer;

	public Inimigo(int x, int y) {
		super(x, y, "img\\sEnemy.png");
		
		timer = new Timer(1, this);
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		setX(getX()-2);
		
	}

}
