package Pck_Menu;
import Pck_Game.AudioPlayer;
import Pck_Game.GameFrame;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Home extends JFrame {
	private JPanel contentPane;
	private AudioPlayer mClick;
	
	public Home() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1280, 720);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		getContentPane().setLayout(null);
        
        JLabel btnComecar = new JLabel("");
        btnComecar.setBounds(495, 375, 290, 72);
        getContentPane().add(btnComecar);
        btnComecar.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        btnComecar.setIcon(new ImageIcon("img\\Sprites\\sBtnComecar.png"));
			
        JLabel btnSobre=new JLabel("");
        btnSobre.setBounds(534, 483, 212, 72);
        getContentPane().add(btnSobre);
        btnSobre.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        btnSobre.setIcon(new ImageIcon("img\\Sprites\\sBtnSobre.png"));
        
        JLabel background =new JLabel("");
        background.setBounds(0, 0, 1280, 720);
        getContentPane().add(background);
        background.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        background.setIcon(new ImageIcon("img\\Backgrounds\\backgroundMenu.jpg"));
        
        btnComecar.addMouseListener(new MouseAdapter() { // INICIAR JOGO
			@Override
			public void mouseClicked(MouseEvent e) {
				mClick = new AudioPlayer("/Sounds/soundClick.wav");
				mClick.play();
				new GameFrame().setVisible(true);
				dispose();
			}
		});
        
        btnSobre.addMouseListener(new MouseAdapter() { // BOTÃO SOBRE
			@Override
			public void mouseClicked(MouseEvent e) {
				mClick = new AudioPlayer("/Sounds/soundClick.wav");
				mClick.play();
				new Sobre().setVisible(true);
				dispose();
			}
		});
	}
}
