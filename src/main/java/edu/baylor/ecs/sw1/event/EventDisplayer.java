package edu.baylor.ecs.sw1.event;

import java.awt.Color;
import java.awt.Frame;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.Border;

public class EventDisplayer {
	static Color blueColor = new Color(64, 143, 222);
	public static void display(Frame owner, Event event) {
		JLabel nameLabel = new JLabel(event.getEventName());
//		JLabel priorityLabel = new JLabel(event.getEventPriority());
		JLabel startDateLabel = new JLabel(event.getStartDate().toString());
		JLabel endDateLabel = new JLabel(event.getEndDate().toString());
		JLabel descriptionLabel = new JLabel(event.getEventDescription());
		
		Border border = BorderFactory.createLineBorder(Color.GRAY, 2);
		
		nameLabel.setBackground(blueColor);
		nameLabel.setBorder(border);
		nameLabel.setOpaque(true);
		startDateLabel.setBackground(blueColor);
		startDateLabel.setBorder(border);
		startDateLabel.setOpaque(true);
		endDateLabel.setBackground(blueColor);
		endDateLabel.setBorder(border);
		endDateLabel.setOpaque(true);
		descriptionLabel.setBackground(blueColor);
		descriptionLabel.setBorder(border);
		descriptionLabel.setOpaque(true);
		
		
		Object[] message = {
				"Event Name:",nameLabel,
	//			"Event Priority",priorityLabel,
				"Start Date:",startDateLabel,
				"End Date:",endDateLabel,
				"Description:",descriptionLabel,
		};
		
		
		Object[] options = {"Close"};
		JOptionPane.showOptionDialog(owner, message, "Event", JOptionPane.OK_OPTION, 
				JOptionPane.PLAIN_MESSAGE, new ImageIcon("empty.png"), options, options[0]);

	
	}
}
