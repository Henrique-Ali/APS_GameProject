package Pck_Game;

public class Inimigo extends Personagens {

	public Inimigo(int x, int y) {
		super(x, y, "img\\sEnemy.png");
	
	}
	
	public void move() {
		setX(getX()-2);
	}
}
