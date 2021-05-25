package Pck_Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

public class Aliado extends Personagens implements ActionListener{
	private Timer timer;
	private ArrayList<Ataque> ataque = new ArrayList<Ataque>();
	private boolean toConstruct = false;
	
	public Aliado(int x, int y) {
		super(x, y, 100, "img\\sPlayer.png");
		timer = new Timer(2000,this);
		timer.start();
	}

	public boolean isToConstruct() {
		return toConstruct;
	}

	public void setToConstruct(boolean toConstruct) {
		this.toConstruct = toConstruct;
	}
	
	public void addAtaque(int[] positionInd) {
		if(this.getY() > 240 && toConstruct == true) {
			int y = (this.getY()-240)/100;
			System.out.println("tiro " + y); 
			if (positionInd[y] > 0) {
				ataque.add(new Ataque(this.getX()+this.getWidth(),this.getY()+this.getHeight()/2));
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
//		if (toContruct) {
//			ataque.add(new Ataque(this.getX()+this.getWidth(),this.getY()+this.getHeight()/2));
//		}
	
	}

	public ArrayList<Ataque> getAtaque() {
		return ataque;
	}

	public void setAtaque(ArrayList<Ataque> ataque) {
		this.ataque = ataque;
	}
	
	public void setPausa(boolean pausa) {
		if (pausa == false) {
			timer.start();
		} else {
			timer.stop();
		}
	}
	
}
