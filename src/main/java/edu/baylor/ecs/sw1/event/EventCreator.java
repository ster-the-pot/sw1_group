package edu.baylor.ecs.sw1.event;

import java.awt.Frame;
import java.util.Date;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.DateFormatter;


import org.jdatepicker.impl.*;

import edu.baylor.ecs.sw1.utils.DateLabelFormatter;

public class EventCreator {
	JTextField eventNameField;
	JTextField eventPriorityField;
	
	JDatePickerImpl startDatePicker;
	JDatePickerImpl endDatePicker;

	JTextField descriptionField;
	JButton finishButton;
	
	EventInfo event;

	public EventCreator(Frame owner) {
		eventNameField = new JTextField();
		eventPriorityField = new JTextField();

		//TODO: get info from calendar
		int year = 2015;
		int month = 4;
		int day = 0;
		
		UtilDateModel model = new UtilDateModel();
		model.setDate(year,month,day);
		
		UtilDateModel endModel = new UtilDateModel();
		endModel.setDate(year,month,day);
		
		Properties datePickerProps = new Properties();
		datePickerProps.put("date.today","Today");
		datePickerProps.put("date.month","Month");
		datePickerProps.put("date.year","Year");
		
		startDatePicker = new JDatePickerImpl(new JDatePanelImpl(model,datePickerProps), new DateLabelFormatter()); 
		endDatePicker = new JDatePickerImpl(new JDatePanelImpl(endModel,datePickerProps), new DateLabelFormatter()); 

		descriptionField = new JTextField();

		Object[] message = {
				"Enter Event Name",eventNameField,
				"Enter Event Priority",eventPriorityField,
				"Select Start Date",startDatePicker,
				"Select End Date",endDatePicker,
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
