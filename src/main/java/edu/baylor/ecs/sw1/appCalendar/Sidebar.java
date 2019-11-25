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
 * 
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
	public JPanel legend;
	public JPanel legend2;

	Color blue = new Color(64, 143, 222);
	//178,34,34
	Color red = new Color(205,65,65);
	Color purple = new Color(186,85,211);
	Color green = new Color(50,205,50);
	
	
	private JButton initButton(String text) {
		JButton button = new JButton(text);
		button.setVisible(true);
		this.add(button);
		return button;
	}

	public Sidebar() {
		// this.setBackground(Color.GRAY);
		GridLayout layout = new GridLayout(12, 0);
		layout.setHgap(1);
		layout.setVgap(1);
		this.setLayout(layout);
		this.setBackground(Color.WHITE);

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

	private JPanel initPanel(String text, Color c, String text2, Color c2, Boolean first) {
		JPanel button = new JPanel();
		GridLayout layout = new GridLayout(3, 0);
		layout.setHgap(1);
		layout.setVgap(1);
		button.setLayout(layout);

		if (first) {
			JLabel top = new JLabel("Color Legend:");
			top.setVisible(true);
			top.setOpaque(true);
			top.setBackground(Color.WHITE);
			button.add(top);
		}

		button.add(initLabel(text, c));
		button.add(initLabel(text2, c2));

		this.add(button);

		return button;
	}

	private JLabel initLabel(String text, Color c) {
		JLabel label = new JLabel(text);

		label.setVisible(true);
		label.setOpaque(true);
		label.setBackground(c);

		return label;
	}

}
