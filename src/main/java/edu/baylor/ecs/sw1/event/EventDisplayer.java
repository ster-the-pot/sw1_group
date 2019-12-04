package edu.baylor.ecs.sw1.event;

import java.awt.Color;
import java.awt.Frame;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 * This class displays a dialog with the event information.
 * @author Par Wilkinson
 *
 */
public class EventDisplayer {
	static JLabel startDateLabel, endDateLabel, descriptionLabel, courseLabel;
	
	static Color blueColor = new Color(230,230,250);
	public static void display(Frame owner, Event event) {
		JLabel nameLabel = new JLabel(event.getEventName());
//		JLabel priorityLabel = new JLabel(event.getEventPriority());
		if(event.getStartDate() != null) {
			startDateLabel = new JLabel(event.getStartDate().toString());
		} else {
			startDateLabel = new JLabel("No Start Date");
		}
		
		if(event.getCourse() != null) {
			courseLabel = new JLabel(event.getCourse());
		} else {
			courseLabel = new JLabel("No associated course");
		}
		
		if(event.getEndDate() != null) {
			endDateLabel = new JLabel(event.getEndDate().toString());
		} else {
			endDateLabel = new JLabel("No End Date");
		}

		if(event.getEventDescription() != null && !event.getEventDescription().isEmpty()) {
			descriptionLabel = new JLabel(event.getEventDescription());
		} else {
			descriptionLabel = new JLabel("No Description");
		}
		
		
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
		courseLabel.setBackground(blueColor);
		courseLabel.setBorder(border);
		courseLabel.setOpaque(true);
	
		
		Object[] message = {
				"Event Name:",nameLabel,
				"Course: ",courseLabel,
				"Start Date:",startDateLabel,
				"End Date:",endDateLabel,
				"Description:",descriptionLabel,
		};
		
		
		Object[] options = {"Close"};
		JOptionPane.showOptionDialog(owner, message, "Event", JOptionPane.OK_OPTION, 
				JOptionPane.PLAIN_MESSAGE, new ImageIcon("empty.png"), options, options[0]);

	
	}
}
