package Pck_Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

public class Aliado extends Personagens{
	private Timer timer;
	private ArrayList<Ataque> ataque = new ArrayList<Ataque>();
	private boolean toConstruct = false;
	
	public Aliado(int x, int y) {
		super(x, y, 100, "img\\sPlayer.png");
		
	}

	public boolean isToConstruct() {
		return toConstruct;
	}

	public void setToConstruct(boolean toConstruct) {
		this.toConstruct = toConstruct;
	}
	
	public void addAtaque(int[] positionInd) {
		if(this.getY() > 280 && toConstruct == true) {
			int y = (this.getY()-280)/100;
			if (positionInd[y] > 0) {
				ataque.add(new Ataque(this.getX()+this.getWidth(),this.getY()+this.getHeight()/2));
			}
		}
	}


	public ArrayList<Ataque> getAtaque() {
		return ataque;
	}

	public void setAtaque(ArrayList<Ataque> ataque) {
		this.ataque = ataque;
	}
	
}
