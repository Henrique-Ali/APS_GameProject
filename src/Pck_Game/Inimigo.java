package Pck_Game;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class Inimigo extends Personagens implements ActionListener{

	private Timer timerUpdate, timerAtack;
	private int state = 0;
	private int damageAttack = 50;
	private boolean atackAni = false;
	private ArrayList<Aliado> aliados = new ArrayList<Aliado>();
	private Aliado toAttack;
	private int[][] matrizMapa = new int[4][7];
	
	
	public Inimigo(int x, int y, String nomeInimigo) {
		super(x, y, 100, "Inimigos", nomeInimigo);
		
		timerUpdate = new Timer(200, this);
		timerAtack = new Timer(2000, this);
		
	}
	
	@Override
	public void draw(Graphics g) {
//		if (toAttack != null) {
//			g.drawString( toAttack.toString(), getX() + 120, getY());
//		}
		super.draw(g);
		if (atackAni) {
			g.drawImage(new ImageIcon("img\\sGarra.png").getImage(), getX()-30, getY()+getHeight()/2-30, null);
		}
	}
	
	public void move() {
		if (state == 0) {
			setX(getX()-2);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(timerUpdate)) {
			atackAni = false;
			timerUpdate.stop();

		}
		if (e.getSource().equals(timerAtack)) {
			if (toAttack.getLife() > damageAttack) {
				toAttack.setLife((int)toAttack.getLife()-damageAttack);
			}else {
				matrizMapa[(toAttack.getY()-280)/100][(toAttack.getX()-300)/100] = 0;
				aliados.remove(toAttack);
				stopTimers();
				state = 0;
				toAttack = null;
			}
			atackAni = true;
			timerUpdate.start();
			
		}
	}
	
	public void setState(int state, Aliado Atack) {
		if(Atack != this.toAttack) {
			if(state == 1 && this.state == 0) {
				System.out.println("AAAAA");
				this.state = state;
				timerAtack.start();
			}else if(state == 0 && this.state == 1) {
				System.out.println("BBBB");
				this.state = state;
				timerAtack.stop();
			}
		}
	}
	
	public void setAliados(ArrayList<Aliado> aliados) {
		this.aliados = aliados;
	}

	public void setToAttack(Aliado toAttack) {
		this.toAttack = toAttack;
	}
	
	public Aliado getToAttack() {
		return toAttack;
	}
	
	public void stopTimers() {
		timerUpdate.stop();
		timerAtack.stop();
	}

	public void setMatrizMapa(int[][] matrizMapa) {
		this.matrizMapa = matrizMapa;
	}
}
