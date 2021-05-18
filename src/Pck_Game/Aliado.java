package Pck_Game;

public class Aliado extends Personagens {
	private boolean toContruct = false;
	
	public Aliado(int x, int y) {
		super(x, y, "img\\sPlayer.png");
		
	}

	public boolean isToContruct() {
		return toContruct;
	}

	public void setToContruct(boolean toContruct) {
		this.toContruct = toContruct;
	}
	
}
