package edu.baylor.ecs.sw1.appCalendar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Contains buttons allowing the user to interact with the application.
 * @author Par Wilkinson
 *
 */
public class Sidebar extends JPanel {
	public JComboBox<String> viewSwitcher;
	
	public Sidebar() {
		this.setBackground(Color.GRAY);
		this.setPreferredSize(new Dimension(50, 40));
		GridLayout layout = new GridLayout(7, 0);
		layout.setHgap(1);
		layout.setVgap(1);
		this.setLayout(layout);
		
		String[] viewChoices = {"Month View", "Week View"};
		viewSwitcher = new JComboBox<>(viewChoices);
		this.add(viewSwitcher);
	}
}
