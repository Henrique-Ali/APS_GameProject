package Pck_Game;

import java.awt.Graphics;

public class Inimigo extends Personagens {

	public Inimigo(int x, int y) {
		super(x, y, 100, "img\\sEnemy.png");
	
	}
	
	public void move() {
		setX(getX()-2);
	}
}
