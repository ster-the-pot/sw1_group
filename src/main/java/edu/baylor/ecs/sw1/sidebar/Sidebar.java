package edu.baylor.ecs.sw1.sidebar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/*
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;

import javax.swing.ImageIcon;
import javax.swing.UIManager;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
*/

/**
 * Responsible for displaying various options to the user in the form
 * of buttons, and handles user interaction with those buttons by delegating
 * to the responsible class.
 *  
 * @author Par Wilkinson
 *
 */
public class Sidebar extends JPanel {
	private void setButtonAppearance(JButton button) {
		button.setFont(new Font("Lucida Grande",Font.PLAIN,18));
		button.setBackground(Color.WHITE);
		button.setFocusable(false);
		button.setMaximumSize(new Dimension(200,100));
	}
	
	public Sidebar() {
		this.setBackground(Color.WHITE);
		this.setFont(new Font("Lucida Grande", Font.PLAIN, 34));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		this.setMaximumSize(new Dimension(200,500));
		
		JButton deleteEvent = new JButton("Delete Event");
		setButtonAppearance(deleteEvent);
		this.add(deleteEvent);
		
		JButton createEvent = new JButton("Create Event");
		setButtonAppearance(createEvent);
		this.add(createEvent);
		
		JButton editDetails = new JButton("Edit Event Details");
		setButtonAppearance(editDetails);
		this.add(editDetails);
		
	}
}
