package edu.baylor.ecs.sw1.event;

import java.awt.Frame;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class EventDisplayer {
	public static void display(Frame owner, Event event) {
		JLabel nameLabel = new JLabel(event.getEventName());
		JLabel priorityLabel = new JLabel(event.getEventPriority());
		JLabel startDateLabel = new JLabel(event.getStartDate().toString());
		JLabel endDateLabel = new JLabel(event.getEndDate().toString());
		JLabel descriptionLabel = new JLabel(event.getEventDescription());
		
		Object[] message = {
				"Event Name",nameLabel,
				"Event Priority",priorityLabel,
				"Start Date",startDateLabel,
				"End Date",endDateLabel,
				"Description",descriptionLabel,
		};
		
		
		Object[] options = {"Close"};
		JOptionPane.showOptionDialog(owner, message, "Event", JOptionPane.OK_OPTION, 
				JOptionPane.PLAIN_MESSAGE, new ImageIcon("empty.png"), options, options[0]);

	}
}
