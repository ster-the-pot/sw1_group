package edu.baylor.ecs.sw1.appCalendar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
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
	public JButton createEvent;
	public JButton editEvent;
	public JButton displayEventDetails;
	public JButton deleteEvent;
	public JButton completeEvent;
	public JButton connectEvent;
	
	private JButton initButton(String text) {
		JButton button = new JButton(text);
		button.setVisible(true);
		this.add(button);
		return button;
	}
	
	public Sidebar() {
		//this.setBackground(Color.GRAY);
		GridLayout layout = new GridLayout(12, 0);
		layout.setHgap(1);
		layout.setVgap(1);
		this.setLayout(layout);
		this.setBackground(Color.WHITE);
		
		String[] viewChoices = {"Month View", "Week View"};
		viewSwitcher = new JComboBox<>(viewChoices);
		this.add(viewSwitcher);
		
		createEvent = initButton("Create Event");
		editEvent = initButton("Edit Event Details");
		displayEventDetails = initButton("Display Event Details");
		deleteEvent = initButton("Delete Event");
		completeEvent = initButton("Complete Event");
		connectEvent = initButton("Connect Canvas");
	}
}
