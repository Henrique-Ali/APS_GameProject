package Pck_Menu;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Pck_Game.GameFrame;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Rectangle;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Insets;
import java.awt.Component;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Home extends JFrame {

	private JPanel contentPane;

	public Home() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		setUndecorated(true);
		setBounds(0, 0, 1280, 720);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		getContentPane().setLayout(null);
        
        JLabel btnComecar = new JLabel("");
        btnComecar.setBounds(495, 375, 290, 72);
        getContentPane().add(btnComecar);
        btnComecar.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        btnComecar.setIcon(new ImageIcon("img\\sBtnComecar.png"));
			
        JLabel btnSobre=new JLabel("");
        btnSobre.setBounds(534, 483, 212, 72);
        getContentPane().add(btnSobre);
        btnSobre.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        btnSobre.setIcon(new ImageIcon("img\\sBtnSobre.png"));

       
        
        JLabel background =new JLabel("");
        background.setBounds(0, 0, 1280, 720);
        getContentPane().add(background);
        background.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        background.setIcon(new ImageIcon("img\\backgroundMenu.jpg"));
        
        
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
