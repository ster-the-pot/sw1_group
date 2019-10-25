package edu.baylor.ecs.sw1.event;

import java.awt.Frame;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;




public class EventCreator {
	JTextField eventNameField;
	JTextField eventPriorityField;
	JComboBox dateSelection;
	JComboBox durationSelection;
	JTextField startTimeField;
	JTextField descriptionField;
	JButton finishButton;
	
	EventInfo event;

	public EventCreator(Frame owner) {
		eventNameField = new JTextField();
		eventPriorityField = new JTextField();
		
		dateSelection = new JComboBox();
		durationSelection = new JComboBox();
		
		startTimeField = new JTextField();
		descriptionField = new JTextField();
		

		Object[] message = {
				"Enter Event Name",eventNameField,
				"Enter Event Priority",eventPriorityField,
				"Select Date",dateSelection,
				"Select Duration",durationSelection,
				"Enter Start Time",startTimeField,
				"Enter Description",descriptionField,
		};

		int option = JOptionPane.showConfirmDialog(owner, message);
		if(option == JOptionPane.OK_OPTION) {
			event = new EventInfo();
			event.setEventName(eventNameField.getText());
		}
	}
	
	public EventInfo getEvent() {
		return event;
	}
}
