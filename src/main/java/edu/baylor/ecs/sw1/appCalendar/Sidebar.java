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

/**DESIGN PATTERN FACTORY METHOD
 * Contains buttons allowing the user to interact with the application. Factory Method
 * is used with the initButton(), initPanel(), and initLabel() functions
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


	
	Color blue = new Color(0, 112, 192);
	Color red = new Color(192, 0, 0);
	Color purple = new Color(112, 48, 160);
	Color green = new Color(79, 98, 40);
	
	Color blue2 = new Color(218, 238, 243);
	Color red2 = new Color(242,220,219);
	Color purple2 = new Color(204, 192, 218);
	Color green2 = new Color(216, 228, 188);


	
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
	public Sidebar(String str) {
		GridLayout layout = new GridLayout(12, 0);
		layout.setHgap(1);
		layout.setVgap(1);
		this.setLayout(layout);
		this.setBackground(Color.WHITE);

		userName = initLabel("Welcome: " + str, Color.BLACK, Color.WHITE );
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

		
		legend = initPanel("Exam", red, red2, "Course", blue, blue2, true);
		legend2 = initPanel("Assignment", purple, purple2, "Quiz", green, green2, false);
		
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
	private JPanel initPanel(String text, Color c, Color c2, String text2, Color c3, Color c4, Boolean first) {

		JPanel button = new JPanel();
		GridLayout layout = new GridLayout(3, 0);
		layout.setHgap(1);
		layout.setVgap(1);
		button.setLayout(layout);
		button.setBackground(Color.WHITE);
		
		if (first) {
			button.add(initLabel("Color Legend:", Color.BLACK, Color.WHITE ));
		}

		button.add(initLabel(text, c, c2));
		button.add(initLabel(text2, c3, c4));

		this.add(button);

		return button;
	}


	
	
	/**
	 * Constructs and returns a JLabel fitting the Sidebar's design.
	 * @param text
	 * @param c
	 * @return
	 */
	private JLabel initLabel(String text, Color c, Color c2) {
		JLabel label = new JLabel(text);

		label.setVisible(true);
		label.setOpaque(true);
		label.setBackground(c2);
		label.setForeground(c);
		if(!c.equals(Color.BLACK))
			label.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		
		return label;
	}

}
