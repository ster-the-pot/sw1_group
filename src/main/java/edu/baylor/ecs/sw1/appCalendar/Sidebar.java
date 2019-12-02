package edu.baylor.ecs.sw1.appCalendar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.baylor.ecs.sw1.View.View;
import edu.baylor.ecs.sw1.event.Event;

/**
 * Contains buttons allowing the user to interact with the application.
 * 
 * @author Par Wilkinson
 * @author Elizabeth Brighton
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
	public JLabel userName;
	public JPanel legend;
	public JPanel legend2;

	Color blue = new Color(31, 97, 141);
	Color red = new Color(176, 58, 46);
	Color purple = new Color(118, 68, 138);
	Color green = new Color(20, 143, 119);
	
/*	Color blue = new Color(23, 112, 171);
	Color red = new Color(205, 65, 65);
	Color purple = new Color(143, 62, 151);
	Color green = new Color(34,139,34);*/

	
	/**
	 * Creates and returns a visible added JButton. FACTORY METHOD dp 
	 * @param text
	 * @return
	 */
	private JButton initButton(String text) {
		JButton button = new JButton(text);
		button.setVisible(true);
		this.add(button);
		return button;
	}

	/**
	 * Constructs and populates the Sidebar
	 */
	public Sidebar() {
		GridLayout layout = new GridLayout(12, 0);
		layout.setHgap(1);
		layout.setVgap(1);
		this.setLayout(layout);
		this.setBackground(Color.WHITE);

		userName = initLabel("Welcome: STERLING", Color.WHITE);
		this.add(userName);
		
		String[] viewChoices = { "Month View", "Week View" };
		viewSwitcher = new JComboBox<>(viewChoices);
		this.add(viewSwitcher);

		
		createEvent = initButton("Create Event");
		editEvent = initButton("Edit Event Details");
		displayEventDetails = initButton("Display Event Details");
		deleteEvent = initButton("Delete Event");
		completeEvent = initButton("Complete Event");
		connectEvent = initButton("Connect Canvas");

		
		legend = initPanel("Exam", red, "Course", blue, true);
		legend2 = initPanel("Assignment", purple, "Quiz", green, false);
		
	}

	
	/**
	 * Constructs the legend panel
	 * @param text
	 * @param c
	 * @param text2
	 * @param c2
	 * @param first
	 * @return
	 */
	private JPanel initPanel(String text, Color c, String text2, Color c2, Boolean first) {
		JPanel button = new JPanel();
		GridLayout layout = new GridLayout(3, 0);
		layout.setHgap(1);
		layout.setVgap(1);
		button.setLayout(layout);
		button.setBackground(Color.WHITE);
		
		if (first) {
			button.add(initLabel("Color Legend:", Color.WHITE));
		}

		button.add(initLabel(text, c));
		button.add(initLabel(text2, c2));

		this.add(button);

		return button;
	}

	
	/**
	 * Constructs and returns a JLabel fitting the Sidebar's design.
	 * @param text
	 * @param c
	 * @return
	 */
	private JLabel initLabel(String text, Color c) {
		JLabel label = new JLabel(text);

		label.setVisible(true);
		label.setOpaque(true);
		label.setBackground(c);

		return label;
	}

}
