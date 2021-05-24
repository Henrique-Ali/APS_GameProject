package Pck_Game;

import java.awt.Color;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GameFrame extends JFrame{

	public GameFrame() {
		// Menu Pause
		GamePause gamePause = new GamePause();
		
		
		
		// Tela do jogo
		GamePanel gamePanel = new GamePanel();
		this.add(gamePanel);
	
		this.setResizable(false);
		this.pack();
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBackground(Color.black);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		
		
	}
	

	

}
