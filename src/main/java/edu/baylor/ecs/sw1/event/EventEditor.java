package edu.baylor.ecs.sw1.event;

import java.awt.Frame;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


import org.jdatepicker.impl.*;

import edu.baylor.ecs.sw1.utils.DateFormattedListCellRenderer;
import edu.baylor.ecs.sw1.utils.DateLabelFormatter;

public class EventEditor {
	JTextField eventNameField;
	JTextField eventPriorityField;
	
	JDatePickerImpl startDatePicker;
	JDatePickerImpl endDatePicker;
	
	JComboBox<Date> startTimeChooser;
	JComboBox<Date> endTimeChooser;

	JTextField descriptionField;
	JButton finishButton;
	
	public EventEditor() {

	}
	
	public void edit(Frame owner, Event event) {
		Date eventStartDate = event.getStartDate();
		Date eventEndDate = event.getEndDate();
		
		eventNameField = new JTextField();
		eventNameField.setText(event.getEventName());
		
		eventPriorityField = new JTextField();
		eventPriorityField.setText(event.getEventPriority());

		UtilDateModel model = new UtilDateModel();
		UtilDateModel endModel = new UtilDateModel();
		
		Properties datePickerProps = new Properties();
		datePickerProps.put("date.today","Today");
		datePickerProps.put("date.month","Month");
		datePickerProps.put("date.year","Year");
		
		model.setDate(Integer.parseInt((new SimpleDateFormat("yyyy")).format(eventStartDate)),
                Integer.parseInt((new SimpleDateFormat("MM")).format(eventStartDate))-1,
                Integer.parseInt((new SimpleDateFormat("dd")).format(eventStartDate)));	
		
		endModel.setDate(Integer.parseInt((new SimpleDateFormat("yyyy")).format(eventEndDate)),
                Integer.parseInt((new SimpleDateFormat("MM")).format(eventEndDate))-1,
                Integer.parseInt((new SimpleDateFormat("dd")).format(eventEndDate)));	

		startDatePicker = new JDatePickerImpl(new JDatePanelImpl(model,datePickerProps), new DateLabelFormatter()); 
		endDatePicker = new JDatePickerImpl(new JDatePanelImpl(endModel,datePickerProps), new DateLabelFormatter()); 
		
		initTimeChoosers();
		
		descriptionField = new JTextField();
		descriptionField.setText(event.getEventDescription());

		Object[] message = {
				"Enter Event Name",eventNameField,
				"Enter Event Priority",eventPriorityField,
				"Select Start Date",startDatePicker,
				"Select End Date",endDatePicker,
				"Select Start Time",startTimeChooser,
				"Select End Time",endTimeChooser,
				"Enter Description",descriptionField,
		};
		
		displayOptions(owner,message,event);
	}
	
	private void displayOptions(Frame owner, Object[] message, Event event) {
		Object[] options = {"Confirm","Cancel"};
		int option = JOptionPane.showOptionDialog(owner, message,"Create Event",JOptionPane.OK_CANCEL_OPTION
				,JOptionPane.QUESTION_MESSAGE,new ImageIcon("empty.png"),options,options[0]);
		if(option == JOptionPane.OK_OPTION) {
			// info validation
			String eventName = eventNameField.getText();

			Object startDValue = startDatePicker.getModel().getValue();
			Object endDValue = endDatePicker.getModel().getValue();
			
			Object startTValue = startTimeChooser.getSelectedItem();
			Object endTValue = endTimeChooser.getSelectedItem();
			
			
			if(eventName.strip().equals("") || startDValue == null || endDValue == null || startTValue == null || endTValue == null) {
				
				String errorMessage = "Error: Invalid data.";
				if(eventName.strip().equals("")) {
					errorMessage = "Error: No event name.";
				} else if(startDValue == null) {
					errorMessage = "Error: No start date.";
				} else if(endDValue == null) {
					errorMessage = "Error: No end date.";
				} else if(startTValue == null) {
					errorMessage = "Error: No start time.";
				} else if(endTValue == null) {
					errorMessage = "Error: No end time.";
				}
				
				JOptionPane.showMessageDialog(owner, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
				
				displayOptions(owner,message,event);
			} else {
				Date startDate = (Date) startDValue; 
				Date endDate = (Date) endDValue; 
				
				Date startTime = (Date) startTValue;
				Date endTime = (Date) endTValue;
				
				startDate.setHours(startTime.getHours());
				startDate.setMinutes(startTime.getMinutes());
				startDate.setSeconds(0);
				endDate.setHours(endTime.getHours());
				endDate.setMinutes(endTime.getMinutes());
				endDate.setSeconds(0);
				
				event.setStartDate(startDate);
				event.setEndDate(endDate);
				event.setEventPriority(eventPriorityField.getText());
				event.setEventDescription(descriptionField.getText());
			}
		}
	}

	
	private void initTimeChoosers() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		
		DefaultComboBoxModel<Date> startModel = new DefaultComboBoxModel<>();
		DefaultComboBoxModel<Date> endModel = new DefaultComboBoxModel<>();
		while(cal.getTime().getHours() <= 23) {
			startModel.addElement(cal.getTime());
			endModel.addElement(cal.getTime());
			cal.add(Calendar.MINUTE, 30);
			
			if(cal.getTime().getMinutes() == 0 && cal.getTime().getHours() == 0)
				break;
		}
		
		startTimeChooser = new JComboBox<>(startModel);
		endTimeChooser = new JComboBox<>(endModel);
		
		startTimeChooser.setRenderer(new DateFormattedListCellRenderer(new SimpleDateFormat("HH:mm")));
		endTimeChooser.setRenderer(new DateFormattedListCellRenderer(new SimpleDateFormat("HH:mm")));
	}
	
}
