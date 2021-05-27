package Pck_Game;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.Timer;

public class GameFrame extends JFrame implements ActionListener{
	private GamePanel gamePanel = new GamePanel();
	private Timer timer;
	
	public GameFrame() {	
		// Tela do jogo
		this.add(gamePanel);
		this.setResizable(false);
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBackground(Color.black);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		timer = new Timer(1, this);
		timer.start();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (gamePanel.getFechar()) { dispose(); }
	}
}