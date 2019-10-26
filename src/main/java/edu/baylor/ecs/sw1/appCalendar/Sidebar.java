package edu.baylor.ecs.sw1.appCalendar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Contains buttons allowing the user to interact with the application.
 * @author Par Wilkinson
 *
 */
public class Sidebar extends JPanel {
	public Sidebar() {
		JLabel label = new JLabel("HELLO");

		this.setBackground(Color.GRAY);
		this.add(label);
		this.setPreferredSize(new Dimension(50, 40));
		GridLayout layout = new GridLayout(7, 0);
		layout.setHgap(1);
		layout.setVgap(1);
		this.setLayout(layout);
	}
}
