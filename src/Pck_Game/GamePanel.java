package Pck_Game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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

import Pck_Menu.Derrota;
import Pck_Menu.Home;
import Pck_Menu.Vitoria;

import java.awt.event.MouseAdapter;


public class GamePanel extends JPanel implements ActionListener {
	private ArrayList<Inimigo> inimigos = new ArrayList<Inimigo>();
	private ArrayList<Aliado> aliados = new ArrayList<Aliado>();
	
	private static final int SCREEN_WIDTH = 1280;
	private static final int SCREEN_HEIGHT = 720;
	
	private int[][] matrizMapa = new int[4][7];
	private int[] positionIni  = new int[4];
	private int mX, mY;
	private int money = 500;
	private int attackDamage;
	private int tempo = 180;
	
	private boolean pausa = false;
	private boolean fechar = false;
	private boolean musica = true;
	
	private Timer timer, timerSpawn1, timerSpawn2, timerSpawn3, timerAttack;
	private MyListeners mList = new MyListeners();
	private Random random;
	private AudioPlayer mClick, mMusic;


	// MÉTODO CONSTRUTOR
	public GamePanel() {
		
		this.setBackground(Color.black);
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setFocusable(true);
		this.addMouseListener(mList);
		this.addMouseMotionListener(mList);
		
		
		timer = new Timer(5, this);
		timer.start();
		
		timerSpawn1 = new Timer(6000, this);
		timerSpawn1.start();
		
		timerSpawn2 = new Timer(5000, this);
		timerSpawn3 = new Timer(7000, this);
		
		timerAttack = new Timer(1000, this);
		timerAttack.start();
		
		random = new Random();
	
		mMusic = new AudioPlayer("/Sounds/soundMusic.wav");
		mMusic.loop();
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
		int indY = (y-250)/100;
		if(indY >= 0) {
			positionIni[indY] += 1;
				System.out.println("addposition"+indY+" "+positionIni[indY]+"  ");
		}
	}
	public void removePositionInd(int y) {
		int indY = (y-250)/100;
		if(indY >= 0) {
			positionIni[indY] -= 1;
				//System.out.println("removeposition"+indY+" "+positionIni[indY]+"  ");
		}
	}
	public int getPositionInd(int y) {
		int indY = (y-250)/100;
		System.out.println(indY);
		if(indY > 0) {
			return indY;
		}
		return 0;
		
	}
	
	
	// MÉTODO FECHAR
	public boolean getFechar() {
		return this.fechar;
	}
	
	
	// MÉTODO PAINT
	public void paint(Graphics g) {
		super.paint(g);

		
		Graphics2D g2d = (Graphics2D) g;
		
		g.drawImage(new ImageIcon("img\\Backgrounds\\backgroundGame.jpeg").getImage(), 0, 0, this);
	
		
		// LINHAS
		g2d.setStroke(new BasicStroke(4));
		
		g.setColor(new Color(180, 70, 35));
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
		g2d.setColor(new Color(70, 20, 5));
		// Linha Vertical
		for(int i = 0; i < 8; i++) {
			g2d.drawLine(300-2+(i*100), 280+2, 300-2+(i*100), SCREEN_HEIGHT-40);
		}
		
		// Linha Horizontal
		for(int i = 0; i < 5; i++) {
			g2d.drawLine(300, 282+(100*i), 1000-2, 282+(100*i));
			g2d.drawLine(SCREEN_WIDTH-280, 282+(100*i), SCREEN_WIDTH, 282+(100*i));
		}
		
//////////////////////////////////////////////////////////////////////////////////
		
		
		
		// "LOJA"
		g2d.setColor(new Color(70, 20, 5));
		for(int i = 0; i < 6; i++) {
			g2d.drawLine(2, SCREEN_HEIGHT-20-(90*i), 200, SCREEN_HEIGHT-20-(90*i));
		}
		g2d.drawLine(2, SCREEN_HEIGHT-470, 2, SCREEN_HEIGHT-20);
		g2d.drawLine(200, SCREEN_HEIGHT-470, 200, SCREEN_HEIGHT-20);
		
		g.drawImage(new ImageIcon("img\\Aliados\\sALiadoFlor.png").getImage(),   10, SCREEN_HEIGHT-490, this);
		g.drawImage(new ImageIcon("img\\Aliados\\sALiadoCobra.png").getImage(),  10, SCREEN_HEIGHT-400, this);
		g.drawImage(new ImageIcon("img\\Aliados\\sALiadoAranha.png").getImage(), 10, SCREEN_HEIGHT-310, this);
		g.drawImage(new ImageIcon("img\\Aliados\\sALiadoTucano.png").getImage(), 10, SCREEN_HEIGHT-220, this);
		g.drawImage(new ImageIcon("img\\Aliados\\sALiadoCacto.png").getImage(),  10, SCREEN_HEIGHT-130, this);
		
		g.drawImage(new ImageIcon("img\\Sprites\\sHudMoedas.png").getImage(), 20, 10, this);
		g2d.setColor(Color.black);
		g2d.setFont(new Font("Monospaced", Font.BOLD, 50));
		g2d.drawString("" + money, 110, 70);
		g2d.drawString(tempo/60 + ":" + tempo%60, 300, 70);
		g2d.setFont(new Font("Sans", Font.BOLD, 26));
		for(int i = 0; i < 5; i++) {
			g2d.drawString("Custo", 110, SCREEN_HEIGHT-65-(90*i));
		}

		g2d.drawString("100", 130, SCREEN_HEIGHT-405);
		g2d.drawString("150", 130, SCREEN_HEIGHT-315);
		g2d.drawString("200", 130, SCREEN_HEIGHT-225);
		g2d.drawString("300", 130, SCREEN_HEIGHT-135);
		g2d.drawString("300", 130, SCREEN_HEIGHT-45);
				
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
			g.drawImage(new ImageIcon("img\\Sprites\\sBtnPausa.png").getImage(), SCREEN_WIDTH - 120, 20, this);
		}
		
		if (pausa) {
			g.drawImage(new ImageIcon("img\\Backgrounds\\backgroundPause.jpeg").getImage(), 0, 0, this);
			g.drawImage(new ImageIcon("img\\Sprites\\sBtnAvancar.png").getImage(), SCREEN_WIDTH - 120, 20, this);
			

			g.drawImage(new ImageIcon("img\\Sprites\\sBtnReset.png").getImage(), SCREEN_WIDTH/2 + 96, SCREEN_HEIGHT/2 - 36, this);
			g.drawImage(new ImageIcon("img\\Sprites\\sBtnHome.png").getImage(), SCREEN_WIDTH/2 - 96, SCREEN_HEIGHT/2 + 72, this);
			
			if(musica) {
				g.drawImage(new ImageIcon("img\\Sprites\\sBtnSomOn.png").getImage(), SCREEN_WIDTH/3 - 96, SCREEN_HEIGHT/2 - 36, this);
			}else {
				g.drawImage(new ImageIcon("img\\Sprites\\sBtnSomOff.png").getImage(), SCREEN_WIDTH/3 - 96, SCREEN_HEIGHT/2 - 36, this);
			}
		}
	}
	
	
	public void reset() {
		inimigos = new ArrayList<Inimigo>();
		aliados = new ArrayList<Aliado>();
		matrizMapa = new int[4][7];
		tempo = 180;
		money = 500;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		for(int i = 0; i < inimigos.size(); i++) {
			Inimigo ind = inimigos.get(i);
			if (ind.getWidth()+ind.getX() < 290) {
				removePositionInd(ind.getY());
				inimigos.remove(ind);
				fechar = true;
				new Derrota().setVisible(true);
			}else {
				ind.move();
			}
		}
		
		for(int i = 0; i < inimigos.size(); i++) {
			Inimigo ini = inimigos.get(i);
			ini.setAliados(aliados);
			for(int k = 0; k < aliados.size(); k++) {
				Aliado ali = aliados.get(k);
				if (ini.getX() < ali.getX()+ali.getWidth() && ini.getX()+ini.getWidth() > ali.getX()+ali.getWidth()/2 && ini.getY()+25 < ali.getY() && ali.getY()+ali.getHeight() < ini.getY()+ini.getHeight()+10) {
					if (ali.isToConstruct()) {
						if(ini.getToAttack() != ali) {
							ini.setState(1, ali);
							ini.setToAttack(ali);
							break;
						}
					}
				}
			}
		}
		if(tempo == 0) {
			fechar = true;
			new Vitoria().setVisible(true);
		}
		
		
		if(tempo == 170 && timerSpawn2.isRunning() == false) {
			timerSpawn1 = new Timer(2000, this);
			timerSpawn1.start();
		}
		
		if(tempo == 120 && timerSpawn3.isRunning() == false) {
			timerSpawn2.start();
			timerSpawn1 = new Timer(3000, this);
			timerSpawn1.start();
		}
		
		if(tempo == 60 && timerSpawn1.getInitialDelay() == 2000) {
			timerSpawn1 = new Timer(2000, this);
			timerSpawn1.start();
			timerSpawn2 = new Timer(2000, this);
			timerSpawn2.start();
			timerSpawn3 = new Timer(4000, this);
			timerSpawn3.start();
		}
		
		if(e.getSource().equals(timerSpawn1)) {
			inimigos.add(new Inimigo(SCREEN_WIDTH-50, 250 + random.nextInt(4)*100, 100, "sInimigo1"));
			addPositionInd(inimigos.get(inimigos.size()-1).getY());
		}
		
		if(e.getSource().equals(timerSpawn2)) {
			inimigos.add(new Inimigo(SCREEN_WIDTH-50, 250 + random.nextInt(4)*100, 250, "sInimigo2"));
			addPositionInd(inimigos.get(inimigos.size()-1).getY());
		}
		
		if(e.getSource().equals(timerSpawn3)) {
			inimigos.add(new Inimigo(SCREEN_WIDTH-50, 250 + random.nextInt(4)*100, 500, "sInimigo3"));
			addPositionInd(inimigos.get(inimigos.size()-1).getY());
		}
		if(e.getSource().equals(timerAttack)) {
			tempo--;
			for(int i = 0; i < aliados.size(); i++) {
				if(aliados.get(i).getToAtaca()) {
					aliados.get(i).addAtaque(positionIni, aliados.get(i).getTipoAliado());
				}
				if(aliados.get(i).getTipoAliado() == "Flor" && aliados.get(i).isToConstruct()) {
					money += 10;
				}
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
						switch (ind.getTipoAliado()) {
						case "Cobra":
							attackDamage = 50;
							break;
						case "Aranha":
							attackDamage = 50;
							break;
						case "Tucano":
							attackDamage = 100;
							break;
							
						default:
							break;
						}
						int iniLife = (int)ini.getLife() - attackDamage;
						if(iniLife > 0) {
							if(ind.getTipoAliado() == "Aranha") {
								ini.setLentidao(true);
							}
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
			if (e.getX() > 2 && e.getX() < 200) {
				if(e.getY() > SCREEN_HEIGHT-470 && e.getY() < SCREEN_HEIGHT-380) {
					if(money >= 100) {
						aliados.add(new Aliado(e.getX()-50, e.getY()-50, 100, "Flor", false));
						for(int i = 0; i < inimigos.size(); i++) {
							Inimigo ini = inimigos.get(i);
							ini.setAliados(aliados);
						}
						money -= 100;
					}
				}
				
				if(e.getY() > SCREEN_HEIGHT-380 && e.getY() < SCREEN_HEIGHT-290) {
					if(money >= 150) {
						aliados.add(new Aliado(e.getX()-50, e.getY()-50, 150, "Cobra", true));
						for(int i = 0; i < inimigos.size(); i++) {
							Inimigo ini = inimigos.get(i);
							ini.setAliados(aliados);
						}
						money -= 150;
					}
				}
				
				if(e.getY() > SCREEN_HEIGHT-290 && e.getY() < SCREEN_HEIGHT-200) {
					if(money >= 200) {
						aliados.add(new Aliado(e.getX()-50, e.getY()-50, 150, "Aranha", true));
						for(int i = 0; i < inimigos.size(); i++) {
							Inimigo ini = inimigos.get(i);
							ini.setAliados(aliados);
						}
						money -= 200;
					}
				}
				
				if(e.getY() > SCREEN_HEIGHT-200 && e.getY() < SCREEN_HEIGHT-110) {
					if(money >= 300) {
						aliados.add(new Aliado(e.getX()-50, e.getY()-50, 150, "Tucano", true));
						for(int i = 0; i < inimigos.size(); i++) {
							Inimigo ini = inimigos.get(i);
							ini.setAliados(aliados);
						}
						money -= 300;
					}
				}
				
				if(e.getY() > SCREEN_HEIGHT-110 && e.getY() < SCREEN_HEIGHT-20) {
					if(money >= 300) {
						aliados.add(new Aliado(e.getX()-50, e.getY()-50, 300, "Cacto", false));
						for(int i = 0; i < inimigos.size(); i++) {
							Inimigo ini = inimigos.get(i);
							ini.setAliados(aliados);
						}
						money -= 300;
					}
				}
			}
			
			// Botão de Pausa e Continue
			if (e.getX() > SCREEN_WIDTH - 120 && e.getX() < SCREEN_WIDTH - 40 && e.getY() > 20 && e.getY() < 100) {
				if (pausa == false) {
					timer.stop();
					timerSpawn1.stop();
					timerSpawn2.stop();
					timerSpawn3.stop();
					pausa = true;
					for(int i = 0; i < aliados.size(); i++) {
						Aliado ind = aliados.get(i);
						for(int j = 0; j < ind.getAtaque().size(); j++) {
							Ataque atk = ind.getAtaque().get(j);
							atk.setPausa(pausa);
						}
					}	
					if(musica == true) {
						mClick = new AudioPlayer("/Sounds/soundClick1.wav");
						mClick.play();
					}
					repaint();
				}else if(pausa == true) {
					if(musica == true) {
						mClick = new AudioPlayer("/Sounds/soundClick1.wav");
						mClick.play();
					}
					timer.start();
					timerSpawn1.start();
					if(tempo == 100) {
						timerSpawn2.start();
					}
					if(tempo == 200) {
						timerSpawn3.start();
					}
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
					if(musica == true) {
						mClick = new AudioPlayer("/Sounds/soundClick1.wav");
						mClick.play();
					}
					timer.start();
					timerSpawn1.start();
					pausa = false;
					reset();
				}
			// Botão Sound	
			}else if (e.getX() > SCREEN_WIDTH/3 - 96 && e.getX() < SCREEN_WIDTH/3 + 72 && e.getY() > SCREEN_HEIGHT/2 - 36 && e.getY() < SCREEN_HEIGHT/2 + 36) {
				if(pausa == true) {
					if(musica) {
						mClick = new AudioPlayer("/Sounds/soundClick1.wav");
						mClick.play();
						musica = false;
						mMusic.stop();
					}else {
						musica = true;
						mMusic = new AudioPlayer("/Sounds/soundMusic.wav");
						mMusic.loop();
					}
				}
				repaint();
			// Botão Home
			}else if (e.getX() > SCREEN_WIDTH/2 - 96 && e.getX() < SCREEN_WIDTH/2 + 102 && e.getY() > SCREEN_HEIGHT/2 + 72 && e.getY() < SCREEN_HEIGHT/2 + 144) {
				if(pausa == true) {
					if(musica == true) {
						mClick = new AudioPlayer("/Sounds/soundClick1.wav");
						mClick.play();
					}
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
						switch (aliados.get(i).getTipoAliado()) {
						case "Flor":
							money += 100;
							break;
						case "Cobra":
							money += 150;
							break;
						case "Aranha":
							money += 200;
							break;
						case "Tucano":
							money += 300;
							break;
						case "Cacto":
							money += 300;
							break;
						default:
							break;
						}
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
