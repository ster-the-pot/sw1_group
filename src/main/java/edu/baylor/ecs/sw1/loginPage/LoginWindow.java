package edu.baylor.ecs.sw1.loginPage;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JTextField;
import javax.swing.JPasswordField;

/**
 * Responsible only for rendering all Login Window UI elements.
 * @author strafford
 *
 */
public class LoginWindow extends JFrame implements Runnable {
	LoginWindow(){
		
		
		getContentPane().setLayout(new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS));
		JPanel topLabel = new JPanel();
		topLabel.setPreferredSize(new Dimension(50,500));
		topLabel.setBackground(Color.BLUE);
		JLabel myDayLabel = new JLabel("Welcome to MyDay!");
		myDayLabel.setForeground(Color.WHITE);
		
		JPanel midGrid = new JPanel(new GridLayout(0,2));
		midGrid.setPreferredSize(new Dimension(midGrid.getMaximumSize()));
		
		JPanel statsLogo = new JPanel(new GridLayout(2,1));
		statsLogo.setBackground(Color.BLACK);
		ImagePanel img = new ImagePanel("src/main/resources/testIMG.png");
		statsLogo.add(img);
		JPanel loginPanel = new JPanel();
		
		
		midGrid.add(statsLogo);
		midGrid.add(loginPanel);
		
		topLabel.add(myDayLabel);
		this.getContentPane().add(topLabel);
		this.getContentPane().add(midGrid);
		this.pack();
		
	}
		
	@Override
	public void run() {
		this.setPreferredSize(new Dimension(900,900));
		this.setVisible(true);
		
	}
	

	
	
	

}
