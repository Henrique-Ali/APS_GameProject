package Pck_Game;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Rectangle;

public class GamePause extends JPanel{
	private static final int SCREEN_WIDTH = 1280;
	private static final int SCREEN_HEIGHT = 720;

	
	public GamePause() {
		this.setBackground(Color.cyan);
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setFocusable(true);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(new Rectangle(0, 0, 1280, 720));
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\mccai\\Desktop\\tela pause\\botao-home.png"));
		lblNewLabel_1.setBounds(974, 25, 296, 107);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\mccai\\Desktop\\tela pause\\fundo-tela-pause.jpg"));
		lblNewLabel.setBounds(new Rectangle(0, 5, 1280, 720));
		panel.add(lblNewLabel);
		
	
	}
}
