package Pck_Menu;
import  Pck_Game.AudioPlayer;
import  Pck_Game.GameFrame;
import  javax.swing.JFrame;
import  javax.swing.JPanel;
import  javax.swing.JLabel;
import  javax.swing.ImageIcon;
import  java.awt.Color;
import  java.awt.Component;
import  java.awt.event.MouseAdapter;
import  java.awt.event.MouseEvent;

public class Derrota extends JFrame {
	private JPanel contentPane;
	private AudioPlayer mClick;
	
	public Derrota() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1280, 720);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		getContentPane().setLayout(null);
        
        JLabel btnReset = new JLabel("");
        btnReset.setBounds(740, 400, 210, 72);
        getContentPane().add(btnReset);
        btnReset.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        btnReset.setBackground(Color.black);
        btnReset.setIcon(new ImageIcon("img\\Sprites\\sBtnReset.png"));
			
        JLabel btnHome = new JLabel("");
        btnHome.setBounds(342, 400, 198, 72);
        getContentPane().add(btnHome);
        btnHome.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        btnHome.setIcon(new ImageIcon("img\\Sprites\\sBtnHome.png"));
        
        JLabel background = new JLabel("");
        background.setBounds(0, 0, 1280, 720);
        getContentPane().add(background);
        background.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        background.setIcon(new ImageIcon("img\\Backgrounds\\backgroundDerrota.jpeg"));

        btnReset.addMouseListener(new MouseAdapter() { // REINICAR O JOGO
			@Override
			public void mouseClicked(MouseEvent e) {
				mClick = new AudioPlayer("/Sounds/soundClick.wav");
				mClick.play();
				new GameFrame().setVisible(true);
				dispose();
			}
		});

        btnHome.addMouseListener(new MouseAdapter() { // BOTÃO SOBRE
			@Override
			public void mouseClicked(MouseEvent e) {
				mClick = new AudioPlayer("/Sounds/soundClick.wav");
				mClick.play();
				new Home().setVisible(true);
				dispose();
			}
		});
	}
}