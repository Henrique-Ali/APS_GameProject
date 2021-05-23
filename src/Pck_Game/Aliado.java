package Pck_Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

public class Aliado extends Personagens implements ActionListener{
	private Timer timer;
	private ArrayList<Ataque> ataque = new ArrayList<Ataque>();
	private boolean toContruct = false;
	
	public Aliado(int x, int y) {
		super(x, y, "img\\sPlayer.png");
		timer = new Timer(2000,this);
		timer.start();
	}

	public boolean isToContruct() {
		return toContruct;
	}

	public void setToContruct(boolean toContruct) {
		this.toContruct = toContruct;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		ataque.add(new Ataque(this.getX()+this.getWidth(),this.getY()+this.getHeight()/2));
		
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
