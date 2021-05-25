package Pck_Game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import Pck_Menu.Home;

import java.awt.event.MouseAdapter;


public class GamePanel extends JPanel implements ActionListener {
	private ArrayList<Inimigo> inimigos = new ArrayList<Inimigo>();
	private ArrayList<Aliado> aliados = new ArrayList<Aliado>();
	private static final int SCREEN_WIDTH = 1280;
	private static final int SCREEN_HEIGHT = 720;
	private int[][] matrizMapa = new int[4][7];
	private int mX, mY;
	private boolean pausa = false;
	private boolean fechar = false;
	private boolean musica = true;
	private Timer timer, timerSpawn, timerAttack;
	private MyListeners mList = new MyListeners();
	private Random random;

	public int [] positionIni = new int[4];
	
	public GamePanel() {
		this.setBackground(Color.black);
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setFocusable(true);
		this.addMouseListener(mList);
		this.addMouseMotionListener(mList);
		
		
		timer = new Timer(1, this);
		timer.start();
		timerSpawn = new Timer(3000, this);
		timerSpawn.start();
		timerAttack = new Timer(1000, this);
		timerAttack.start();
		
		random = new Random();
		
	}
	
	public void setPausa(boolean pausa) {
		this.pausa = pausa;
	}
	
	public boolean getPausa() {
		return this.pausa;
	}
	
	public boolean getFechar() {
		return this.fechar;
	}
	
	public void addPositionInd(int y) {
		int indY = (y-240)/100;
		if(indY > 0) {
			positionIni[indY] += 1;
				System.out.println("addposition"+indY+" "+positionIni[indY]+"  ");
			
		}
	}
	public void removePositionInd(int y) {
		int indY = (y-240)/100;
		if(indY > 0) {
			positionIni[indY] -= 1;
				System.out.println("removeposition"+indY+" "+positionIni[indY]+"  ");
			
		}
	}
	
	public void paint(Graphics g) {
		super.paint(g);
//////////////////////////////////////////////////////////////////////////////////
		
		Graphics2D g2d = (Graphics2D) g;
		
		g.setColor(new Color(100, 200, 255));
		g.fillRect(0, 0, SCREEN_WIDTH, 220);
		
		g.setColor(new Color(50, 100, 50));
		g.fillRect(0, 220, SCREEN_WIDTH-378, SCREEN_HEIGHT-220);
		
		g.setColor(new Color(40, 40, 50));
		g.fillRect(SCREEN_WIDTH-380, 220, SCREEN_WIDTH - 900, SCREEN_HEIGHT-220);
		
		g2d.setStroke(new BasicStroke(4));
		g2d.setColor(new Color(30, 30, 40));
		
		for(int i = 0; i < 5; i++) {
			g2d.drawLine(SCREEN_WIDTH-380, 242+(100*i), SCREEN_WIDTH, 242+(100*i));
		}
		
		g.setColor(new Color(60, 120, 60));
		for(int i = 0; i < aliados.size(); i++) {
			Aliado ind = aliados.get(i);
			if (ind.isToContruct() == false) {
				if(mX > 200 && mX < 900) {
					if (mY > 240 && mY < SCREEN_HEIGHT-80) {
					g.fillRect(200+(100*((mX-200)/100))-2, 240+(100*((mY-240)/100))+2, 100, 100);
					}
				}
			}
		}
		
		g2d.setColor(new Color(40, 90, 40));
		//Linha Vertical
		for(int i = 0; i < 8; i++) {
			g2d.drawLine(200-2+(i*100), 240+2, 200-2+(i*100), SCREEN_HEIGHT-80);
		}
		
		//Linha Horizontal
		for(int i = 0; i < 5; i++) {
			g2d.drawLine(200, 242+(100*i), 900-2, 242+(100*i));
		}
		
//////////////////////////////////////////////////////////////////////////////////
		
		for(int i = 0; i < inimigos.size(); i++) {
			Inimigo ind = inimigos.get(i);
			ind.draw(g);	
		}
		
		// botao pause
		if (pausa == false) {
			g.drawImage(new ImageIcon("img\\sBtnAvancar.png").getImage(), SCREEN_WIDTH - 120, 20, this);
		}
		
		g.setColor(new Color(176, 109, 76));
		g.fillRect(0, 80, 150, SCREEN_HEIGHT - 160);
		
		g.drawImage(new ImageIcon("img\\sPlayer.png").getImage(), 25, 100, this);
		
		for(int i = 0; i < aliados.size(); i++) {
			Aliado ind = aliados.get(i);
			ind.draw(g);
		}
		
		// Draw attacks
		for(int i = 0; i < aliados.size(); i++) {
			Aliado ind = aliados.get(i);
			for(int j = 0; j < ind.getAtaque().size(); j++) {
				Ataque atk = ind.getAtaque().get(j);
				atk.draw(g);
				
			}
		}
		
		if (pausa) {
			g.drawImage(new ImageIcon("img\\backgroundPause.jpg").getImage(), 0, 0, this);
			g.drawImage(new ImageIcon("img\\sBtnAvancar.png").getImage(), SCREEN_WIDTH - 120, 20, this);
			

			g.drawImage(new ImageIcon("img\\sBtnReset.png").getImage(), SCREEN_WIDTH/2 + 96, SCREEN_HEIGHT/2 - 36, this);
			g.drawImage(new ImageIcon("img\\sBtnHome.png").getImage(), SCREEN_WIDTH/2 - 96, SCREEN_HEIGHT/2 + 72, this);
			
			if(musica) {
				g.drawImage(new ImageIcon("img\\sBtnSomOn.png").getImage(), SCREEN_WIDTH/3 - 96, SCREEN_HEIGHT/2 - 36, this);
			}else {
				g.drawImage(new ImageIcon("img\\sBtnSomOff.png").getImage(), SCREEN_WIDTH/3 - 96, SCREEN_HEIGHT/2 - 36, this);
			}
		}
	}
	
	
	public void reset() {
		inimigos = new ArrayList<Inimigo>();
		aliados = new ArrayList<Aliado>();
		matrizMapa = new int[4][7];
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		for(int i = 0; i < inimigos.size(); i++) {
			Inimigo ind = inimigos.get(i);
			if (ind.getWidth()+ind.getX() < 0) {
				removePositionInd(ind.getY());
				inimigos.remove(ind);
			}else {
				ind.move();
			}
		}
		
		
		if(e.getSource().equals(timerSpawn)) {
			inimigos.add(new Inimigo(SCREEN_WIDTH-50, 240 + random.nextInt(4)*100));
			addPositionInd(inimigos.get(inimigos.size()-1).getY());
		}
		if(e.getSource().equals(timerAttack)) {
			for(int i = 0; i < aliados.size(); i++) {
				aliados.get(i).addAtaque(positionIni);
			}
		}
		
		// Colission  teste
		for(int i = 0; i < aliados.size(); i++) {
			Aliado ind = aliados.get(i);
			ind.setPausa(pausa);
			for(int j = 0; j < ind.getAtaque().size(); j++) {
				Ataque atk = ind.getAtaque().get(j);
				
				atk.setPausa(pausa);
				
				for(int k = 0; k < inimigos.size(); k++) {
					Inimigo ini = inimigos.get(k);
					if(ini.getX() <= atk.getX() && (ini.getY() <= atk.getY() && ini.getY()+ini.getHeight() >= atk.getY())) {
						int iniLife = ini.getLife() - 50;
						if(iniLife > 0) {
							ini.setLife(iniLife);
						}else {
							removePositionInd(ini.getY());
							inimigos.remove(k);
						}
						ind.getAtaque().remove(j);
						break;
					}
				}
				
			}
		}
		repaint();
	}
	
	public class MyListeners implements MouseListener, MouseMotionListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (e.getX() > 25 && e.getX() < 125) {
				if(e.getY() > 100 && e.getY() < 200) {
					aliados.add(new Aliado(e.getX()-50, e.getY()-50));
				}
			}
			
			// Botão de Pausa e Continue
			if (e.getX() > SCREEN_WIDTH - 120 && e.getX() < SCREEN_WIDTH - 40 && e.getY() > 20 && e.getY() < 100) {
				if (pausa == false) {
					timer.stop();
					timerSpawn.stop();
					pausa = true;
					for(int i = 0; i < aliados.size(); i++) {
						Aliado ind = aliados.get(i);
						ind.setPausa(pausa);
						for(int j = 0; j < ind.getAtaque().size(); j++) {
							Ataque atk = ind.getAtaque().get(j);
							atk.setPausa(pausa);
						}
					}	
					repaint();
				}else if(pausa == true) {
					timer.start();
					timerSpawn.start();
					pausa = false;
					for(int i = 0; i < aliados.size(); i++) {
						Aliado ind = aliados.get(i);
						ind.setPausa(pausa);
						for(int j = 0; j < ind.getAtaque().size(); j++) {
							Ataque atk = ind.getAtaque().get(j);
							atk.setPausa(pausa);
						}
					}
				}
			// Botão Reset	
			}else if (e.getX() > SCREEN_WIDTH/2 + 96 && e.getX() < SCREEN_WIDTH/2 + 268 && e.getY() > SCREEN_HEIGHT/2 - 36 && e.getY() < SCREEN_HEIGHT/2 + 36) {
				if(pausa == true) {
					timer.start();
					timerSpawn.start();
					pausa = false;
					reset();
				}
			// Botão Sound	
			}else if (e.getX() > SCREEN_WIDTH/3 - 96 && e.getX() < SCREEN_WIDTH/3 + 72 && e.getY() > SCREEN_HEIGHT/2 - 36 && e.getY() < SCREEN_HEIGHT/2 + 36) {
				if(pausa == true) {
					if(musica) {
						musica = false;
						
					}else {
						musica = true;
					}
				}
				repaint();
			// Botão Home
			}else if (e.getX() > SCREEN_WIDTH/2 - 96 && e.getX() < SCREEN_WIDTH/2 + 102 && e.getY() > SCREEN_HEIGHT/2 + 72 && e.getY() < SCREEN_HEIGHT/2 + 144) {
				if(pausa == true) {
					new Home().setVisible(true);
					fechar = true;
				}
			}
		}

		

		@Override
		public void mouseReleased(MouseEvent e) {
			for(int i = 0; i < aliados.size(); i++) {
				Aliado ind = aliados.get(i);
				if (ind.isToContruct() == false) {
					if(mX > 200 && mX < 900 && mY > 240 && mY < SCREEN_HEIGHT-80) {
						if(matrizMapa[(mY-240)/100][(mX-200)/100] == 0) {
							matrizMapa[(mY-240)/100][(mX-200)/100] = 1;
							ind.setX(200+(100*((mX-200)/100))-2);
							ind.setY(240+(100*((mY-240)/100))+2);
							ind.setToContruct(true);
						}else {
							aliados.remove(i);
						}
					}else {
						aliados.remove(i);
					}
				}
			}
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			mX = e.getX();
			mY = e.getY();
			for(int i = 0; i < aliados.size(); i++) {
				Aliado ind = aliados.get(i);
				if (ind.isToContruct() == false) {
					ind.setX(e.getX()-50);
					ind.setY(e.getY()-50);
				}
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			mX = e.getX();
			mY = e.getY();
		}
		
	}

}
