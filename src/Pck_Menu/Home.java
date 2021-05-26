package Pck_Menu;
import  Pck_Game.GameFrame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Home extends JFrame {

	private JPanel contentPane;

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
        
        
        // Start game
        btnComecar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new GameFrame().setVisible(true);
				dispose();
			}
		});
        
        // Btn Sobre
        btnSobre.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Sobre().setVisible(true);
				dispose();
			}
		});
	}
}
