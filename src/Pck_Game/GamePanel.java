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
	private int[] positionIni  = new int[4];
	private int mX, mY;
	private int attackDamage = 50;
	
	private boolean pausa = false;
	private boolean fechar = false;
	private boolean musica = true;
	
	private Timer timer, timerSpawn, timerAttack;
	private MyListeners mList = new MyListeners();
	private Random random;

	
	// MÉTODO CONSTRUTOR
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
		
		/*
		 * TESTE TIMER TIRO - PENDENTE
		 */
		timerAttack = new Timer(1000, this);
		timerAttack.start();
		
		random = new Random();
		
		inimigos.add(new Inimigo(SCREEN_WIDTH-50, 280 + 1/*random.nextInt(1)*/*100));
		addPositionInd(inimigos.get(inimigos.size()-1).getY());
	}

	////////////////////////////////////////////////////////
	
	// MÉTODO PAUSA
	public void setPausa(boolean pausa) {
		this.pausa = pausa;
	}
	public boolean getPausa() {
		return this.pausa;
	}
	
	
	// MÉTODO POSITION IND  -- PENDENTE --
	public void addPositionInd(int y) {
		int indY = (y-280)/100;
		if(indY >= 0) {
			positionIni[indY] += 1;
				//System.out.println("addposition"+indY+" "+positionIni[indY]+"  ");
		}
	}
	public void removePositionInd(int y) {
		int indY = (y-280)/100;
		if(indY >= 0) {
			positionIni[indY] -= 1;
				//System.out.println("removeposition"+indY+" "+positionIni[indY]+"  ");
		}
	}
	
	
	// MÉTODO FECHAR
	public boolean getFechar() {
		return this.fechar;
	}
	
	
	// MÉTODO PAINT
	public void paint(Graphics g) {
		super.paint(g);

		
		Graphics2D g2d = (Graphics2D) g;
		
		g.drawImage(new ImageIcon("img\\sGameBackground.jpeg").getImage(), 0, 0, this);
	
		
		// LINHAS
		g2d.setStroke(new BasicStroke(4));
		g2d.setColor(new Color(30, 30, 40));
		
		for(int i = 0; i < 5; i++) {
			g2d.drawLine(SCREEN_WIDTH-280, 282+(100*i), SCREEN_WIDTH, 282+(100*i));
		}
		
		g.setColor(new Color(60, 120, 60));
		for(int i = 0; i < aliados.size(); i++) {
			Aliado ind = aliados.get(i);
			if (ind.isToConstruct() == false) {
				if(mX > 300 && mX < 1000) {
					if (mY > 280 && mY < SCREEN_HEIGHT-40) {
					g.fillRect(300+(100*((mX-300)/100))-2, 280+(100*((mY-280)/100))+2, 100, 100);
					}
				}
			}
		}
		g2d.setColor(new Color(40, 90, 40));
		
		// Linha Vertical
		for(int i = 0; i < 8; i++) {
			g2d.drawLine(300-2+(i*100), 280+2, 300-2+(i*100), SCREEN_HEIGHT-40);
		}
		
		// Linha Horizontal
		for(int i = 0; i < 5; i++) {
			g2d.drawLine(300, 282+(100*i), 1000-2, 282+(100*i));
		}
		
//////////////////////////////////////////////////////////////////////////////////
		
		
		
		// "LOJA"
				g.setColor(new Color(176, 109, 76));
				g.fillRect(0, 80, 150, SCREEN_HEIGHT - 160);
				g.drawImage(new ImageIcon("img\\sPlayer.png").getImage(), 25, 100, this);
				
		// DRAW - ALIADOS
		for(int i = 0; i < aliados.size(); i++) {
			Aliado ind = aliados.get(i);
			ind.draw(g);
		}
		
		// DRAW - INIMIGOS
				for(int i = 0; i < inimigos.size(); i++) {
					Inimigo ind = inimigos.get(i);
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
		
		// botao pause
		if (pausa == false) {
			g.drawImage(new ImageIcon("img\\sBtnPausa.png").getImage(), SCREEN_WIDTH - 120, 20, this);
		}
		
		if (pausa) {
			g.drawImage(new ImageIcon("img\\backgroundPause.jpeg").getImage(), 0, 0, this);
			g.drawImage(new ImageIcon("img\\sBtnAvancar.png").getImage(), SCREEN_WIDTH - 120, 20, this);
			

			g.drawImage(new ImageIcon("img\\sBtnReset.png").getImage(), SCREEN_WIDTH/2 + 96, SCREEN_HEIGHT/2 - 36, this);
			g.drawImage(new ImageIcon("img\\sBtnHome.png").getImage(), SCREEN_WIDTH/2 - 96, SCREEN_HEIGHT/2 + 72, this);
			
			if(musica) {
				g.drawImage(new ImageIcon("img\\sBtnSomOn.png").getImage(), SCREEN_WIDTH/3 - 96, SCREEN_HEIGHT/2 - 36, this);
			}else {
				g.drawImage(new ImageIcon("img\\sBtnSomOff.png").getImage(), SCREEN_WIDTH/3 - 96, SCREEN_HEIGHT/2 - 36, this);
			}
		}
		
		//g.drawImage(new ImageIcon("img\\sAliado1.png").getImage(), 300, 280, this);
		//g.drawImage(new ImageIcon("img\\sAliado2.png").getImage(), 400, 380, this);
		//g.drawImage(new ImageIcon("img\\sAliado3.png").getImage(), 500, 480, this);
		
		//g.drawImage(new ImageIcon("img\\sInimigo1.png").getImage(), 1000, 280, this);
		//g.drawImage(new ImageIcon("img\\sInimigo2.png").getImage(), 1000, 380, this);
		//g.drawImage(new ImageIcon("img\\sInimigo3.png").getImage(), 1000, 480, this);
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
		
		for(int i = 0; i < inimigos.size(); i++) {
			Inimigo ini = inimigos.get(i);
			ini.setAliados(aliados);
			for(int k = 0; k < aliados.size(); k++) {
				Aliado ali = aliados.get(k);
				if (ini.getX() < ali.getX()+ali.getWidth() && ini.getX()+ini.getWidth() > ali.getX()+ali.getWidth()/2 && ini.getY()+5 > ali.getY() && ini.getY()+ini.getHeight() < ali.getY()+ali.getHeight()) {
					System.out.println(ini.getX() < ali.getX()+ali.getWidth() && ini.getX()+ini.getWidth() > ali.getX()+ali.getWidth()/2 && ini.getY()+5 > ali.getY() && ini.getY()+ini.getHeight() < ali.getY()+ali.getHeight());
					System.out.println(aliados);
					System.out.println(ali + "\n");
					if (ali.isToConstruct()) {
						if(ini.getToAttack() != ali) {
							ini.setState(1, ali);
							ini.setToAttack(ali);
							break;
						}
					}
				}else {
					ini.setState(0, ali);
					ini.setToAttack(null);
				}
			}
		}
		
		
		if(e.getSource().equals(timerSpawn)) {
			//inimigos.add(new Inimigo(SCREEN_WIDTH-50, 280 + 1/*random.nextInt(1)*/*100));
			//addPositionInd(inimigos.get(inimigos.size()-1).getY());
		}
		
		if(e.getSource().equals(timerAttack)) {
			for(int i = 0; i < aliados.size(); i++) {
				aliados.get(i).addAtaque(positionIni);
			}
		}
		
		// Colission  teste
		for(int i = 0; i < aliados.size(); i++) {
			Aliado ind = aliados.get(i);
			for(int j = 0; j < ind.getAtaque().size(); j++) {
				Ataque atk = ind.getAtaque().get(j);
				
				atk.setPausa(pausa);
				
				for(int k = 0; k < inimigos.size(); k++) {
					Inimigo ini = inimigos.get(k);
					if(ini.getX() <= atk.getX() && (ini.getY() <= atk.getY() && ini.getY()+ini.getHeight() >= atk.getY())) {
						int iniLife = (int)ini.getLife() - attackDamage;
						if(iniLife > 0) {
							ini.setLife(iniLife);
						}else {
							removePositionInd(ini.getY());
							ini.stopTimers();
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
					for(int i = 0; i < inimigos.size(); i++) {
						Inimigo ini = inimigos.get(i);
						ini.setAliados(aliados);
					}
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
				if (ind.isToConstruct() == false) {
					if(mX > 300 && mX < 1000 && mY > 280 && mY < SCREEN_HEIGHT-40) {
						if(matrizMapa[(mY-280)/100][(mX-300)/100] == 0) {
							matrizMapa[(mY-280)/100][(mX-300)/100] = 1;
							ind.setX(300+(100*((mX-300)/100))-2);
							ind.setY(280+(100*((mY-280)/100))+2);
							ind.setToConstruct(true);
							for(int k = 0; k < inimigos.size(); k++) {
								Inimigo ini = inimigos.get(k);
								ini.setMatrizMapa(matrizMapa);
							}
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
				if (ind.isToConstruct() == false) {
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
