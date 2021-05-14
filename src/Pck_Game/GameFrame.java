package Pck_Game;

import java.awt.Color;

import javax.swing.JFrame;

public class GameFrame extends JFrame{

	public GameFrame() {
		this.add(new GamePanel());
		this.setResizable(false);
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBackground(Color.black);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

}
