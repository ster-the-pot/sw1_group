package edu.baylor.ecs.sw1.loginPage;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import java.awt.GridLayout;

import javax.swing.BoxLayout;


import javax.swing.JTextField;

/**
 * Responsible only for rendering all Login Window UI elements.
 * @author strafford
 *
 */
public class LoginWindow extends JFrame implements Runnable {
	
	Font loginBold = new Font(Font.SANS_SERIF, Font.BOLD, 26);
	Color labelColor = new Color(64, 143, 222);
	JTextField username, password;
	JLabel uLabel, pLabel;
	LoginWindow(){
		getContentPane().setLayout(new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS));
		JPanel topLabel = new JPanel();
		topLabel.setPreferredSize(new Dimension(10,5));
		topLabel.setBackground(labelColor);
		JLabel myDayLabel = new JLabel("Welcome to MyDay!");
		myDayLabel.setFont(loginBold);
		myDayLabel.setForeground(Color.WHITE);	

		JPanel midGrid = new JPanel(new GridLayout(0,2));
		midGrid.setPreferredSize(new Dimension(midGrid.getMaximumSize()));
		
		JPanel statsLogo = new JPanel(new GridLayout(2,1));
		JPanel stats = new StatsPanel();
	
		ImagePanel logo = new ImagePanel("src/main/resources/testIMG.png");
		statsLogo.add(logo);
		statsLogo.add(stats);
		
		
		
		
		
		
		LoginPanel loginPanel = new LoginPanel();
		loginPanel.setBackground(Color.WHITE);
		midGrid.add(statsLogo);
		midGrid.add(loginPanel);
		
		
		topLabel.add(myDayLabel);
		
		this.getContentPane().add(topLabel);
		this.getContentPane().add(midGrid);

		
	}
		
	@Override
	public void run() {
		this.setPreferredSize(new Dimension(900,600));
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	

	
	
	

}
