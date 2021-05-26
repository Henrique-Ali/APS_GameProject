package Pck_Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Timer;

public class Aliado extends Personagens{
	private Timer timer;
	private ArrayList<Ataque> ataque = new ArrayList<Ataque>();
	private boolean toConstruct = false;
	private boolean toAtaca;
	private String tipoAliado;
	
	public Aliado(int x, int y, String nomeAliado, boolean b) {
		super(x, y, 100, "Aliados", "sAliado"+nomeAliado);	
		tipoAliado = nomeAliado;
		toAtaca = b;
	}

	public boolean isToConstruct() {
		return toConstruct;
	}

	public void setToConstruct(boolean toConstruct) {
		this.toConstruct = toConstruct;
	}
	
	public void addAtaque(int[] positionInd, String aliado) {
		if(this.getY() > 280 && toConstruct == true) {
			int y = (this.getY()-280)/100;
			if (positionInd[y] > 0) {
				ataque.add(new Ataque(this.getX()+this.getWidth(),this.getY()+this.getHeight()/2, aliado));
			}
		}
	}
	public void addAtaque(String vazio, String aliado) {
		// Este aliado não ataca
	}


	public ArrayList<Ataque> getAtaque() {
		return ataque;
	}

	public void setAtaque(ArrayList<Ataque> ataque) {
		this.ataque = ataque;
	}
	
	public String getTipoAliado() {
		return tipoAliado;
	}
	public boolean getToAtaca() {
		return toAtaca;
	}
}
