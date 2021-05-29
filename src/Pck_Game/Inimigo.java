package Pck_Game;
import  java.awt.Graphics;
import  java.awt.event.ActionEvent;
import  java.awt.event.ActionListener;
import  java.util.ArrayList;
import  javax.swing.ImageIcon;
import  javax.swing.Timer;

public class Inimigo extends Personagens implements ActionListener{
	private Timer timerUpdate, timerAtack, timer;
	private int state = 0;
	private int damageAttack = 50;
	private int[][] matrizMapa = new int[4][7];
	private boolean atackAni = false;
	private boolean lentidao = false;
	private ArrayList<Aliado> aliados = new ArrayList<Aliado>();
	private Aliado toAttack;
	
	public Inimigo(int x, int y, int vida, String nomeInimigo) {
		super(x, y, vida, "Inimigos", nomeInimigo);
		timerUpdate = new Timer( 200, this);
		timerAtack  = new Timer(1000, this);
		timer = new Timer(5, this);
		timer.start();
	}
	
	@Override
	public void draw(Graphics g) {
		super.draw(g);
		if (atackAni) {
			g.drawImage(new ImageIcon("img\\inimigos\\sMachado.png").getImage(), getX()-40, getY()+getHeight()/2-40, null);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(timer)) {
			if (state == 0) {
				setX(getX()-2);
				if(lentidao == false) {
					timer.setDelay(5);
				}else {
					timer.setDelay(20);
				}
			}
		}
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
				this.state = state;
				timerAtack.start();
			}else if(state == 0 && this.state == 1) {
				this.state = state;
				timerAtack.stop();
			}
		}
	}
	
	public void stopTimers() {
		timerUpdate.stop();
		timerAtack.stop();
	}
	public void pausa(boolean pausa) {
		if(pausa) {
			timerUpdate.stop();
			timerAtack .stop();
			timer.stop();
		}else {
			timerUpdate.start();
			timerAtack .start();
			timer.start();
		}
	}
	public void setAliados(ArrayList<Aliado> aliados) { this.aliados = aliados;}

	public void setToAttack(Aliado toAttack) { this.toAttack = toAttack; }
	public Aliado getToAttack() { return toAttack; }
	
	public void setMatrizMapa(int[][] matrizMapa) { this.matrizMapa = matrizMapa; }
	public int[][] getMatrizMapa() { return this.matrizMapa; }

	public void setLentidao(boolean lentidao) { this.lentidao = lentidao; }
}