package Pck_Menu;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Sobre extends JFrame {

	private JPanel contentPane;
	static int sum = 0;


	public Sobre() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setUndecorated(true);
		setBounds(0, 0, 1280, 720);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
	        getContentPane().setLayout(null);
	        
				        JLabel btnVoltar = new JLabel("");
				        btnVoltar.setBounds(468, 563, 80, 80);
				        getContentPane().add(btnVoltar);
				        btnVoltar.setAlignmentY(Component.BOTTOM_ALIGNMENT);
				        btnVoltar.setIcon(new ImageIcon("img\\sBtnVoltar.png"));
							
				        JLabel btnAvancar = new JLabel("");
				        btnAvancar.setBounds(732, 563, 80, 80);
				        getContentPane().add(btnAvancar);
				        btnAvancar.setAlignmentY(Component.BOTTOM_ALIGNMENT);
				        btnAvancar.setIcon(new ImageIcon("img\\sBtnAvancar.png"));
				        
				        JLabel btnCancelar = new JLabel("");
				        btnCancelar.setBounds(1175, 17, 80, 80);
				        getContentPane().add(btnCancelar);
				        btnCancelar.setAlignmentY(Component.BOTTOM_ALIGNMENT);
				        btnCancelar.setIcon(new ImageIcon("img\\sBtnCancelar.png"));
				        			        
				        JLabel background =new JLabel("");
	        	        background.setBounds(0, 0, 1280, 720);
	        	        getContentPane().add(background);
	        	        background.setAlignmentY(Component.BOTTOM_ALIGNMENT);
	        	        background.setIcon(new ImageIcon("img\\sobreTela1.jpg"));
	        	        
	        	        
	        	        
	        	        btnCancelar.addMouseListener(new MouseAdapter() {
				        	@Override
				        	public void mouseClicked(MouseEvent e) {
				        		new Home().setVisible(true);
				        		dispose();
				        		
				        	}
				        });
	        	        
	        	        btnAvancar.addMouseListener(new MouseAdapter() {
				        	@Override
				        	public void mouseClicked(MouseEvent e) {
				        		background.setIcon(new ImageIcon(mudarTela(1)));
				        	}
				        });
	        	        
	        	        btnVoltar.addMouseListener(new MouseAdapter() {
				        	@Override
				        	public void mouseClicked(MouseEvent e) {
				        		background.setIcon(new ImageIcon(mudarTela(-1)));
				        	}
				        });
	        	        
	        	        
	        	       
	}
	public static String mudarTela(int x){
		String telas[] = {"img\\sobreTela1.jpg", "img\\sobreTela2.jpg", "img\\sobreTela3.jpg"};
		int ref = sum;
		ref += x;
		if(ref < 0 || ref >= telas.length) {
			ref -=x;
			return telas[ref];
		}
		sum+=x;
		return telas[sum];
    }

}
