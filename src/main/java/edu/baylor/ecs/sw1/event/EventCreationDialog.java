package edu.baylor.ecs.sw1.event;

import java.awt.Color;
import java.awt.Frame;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

import org.jdatepicker.impl.*;

import edu.baylor.ecs.sw1.View.View;
import edu.baylor.ecs.sw1.utils.DateFormattedListCellRenderer;
import edu.baylor.ecs.sw1.utils.DateLabelFormatter;

/**
 * This class displays a dialog that gets information for the creation of an event,
 * and validates the info before returning the event.
 * @author Par Wilkinson
 *
 */
public class EventCreationDialog {
	JTextField eventNameField;
	//JTextField eventPriorityField;
	
	JDatePickerImpl startDatePicker;
	JDatePickerImpl endDatePicker;
	
	JComboBox<Date> startTimeChooser;
	JComboBox<Date> endTimeChooser;

	JTextField descriptionField;
	JButton finishButton;

	Color labelColor = new Color(64, 143, 222);
	Event event;

	public EventCreationDialog(Frame owner) {
		eventNameField = new JTextField();
	//	eventPriorityField = new JTextField();

		
		Calendar cal = Calendar.getInstance();
		
		//TODO: get info from calendar
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		
		UtilDateModel model = new UtilDateModel();
		model.setDate(year,month,day);
		
		UtilDateModel endModel = new UtilDateModel();
		endModel.setDate(year,month,day);
		
		Properties datePickerProps = new Properties();
		datePickerProps.put("date.today","Today");
		datePickerProps.put("date.month","Month");
		datePickerProps.put("date.year","Year");
		
		
		
		startDatePicker = new JDatePickerImpl(new JDatePanelImpl(model,datePickerProps), new DateLabelFormatter()); 
		JFormattedTextField textField = startDatePicker.getJFormattedTextField();
		textField.setBackground(Color.WHITE);
		endDatePicker = new JDatePickerImpl(new JDatePanelImpl(endModel,datePickerProps), new DateLabelFormatter()); 
		JFormattedTextField textField2 = endDatePicker.getJFormattedTextField();
		textField2.setBackground(Color.WHITE);
		
		initTimeChoosers();

		descriptionField = new JTextField();

		Object[] message = {
				"Enter Event Name",eventNameField,
	//			"Enter Event Priority",eventPriorityField,
				"Select Start Date",startDatePicker,
				"Select End Date",endDatePicker,
				"Select Start Time",startTimeChooser,
				"Select End Time",endTimeChooser,
				"Enter Description",descriptionField,
		};
		
		displayOptions(owner,message);
	}
	
	private void displayOptions(Frame owner, Object[] message) {
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
			
			
			if(eventName.trim().equals("") || startDValue == null || endDValue == null || startTValue == null || endTValue == null) {
				
				String errorMessage = "Error: Invalid data.";
				if(eventName.trim().equals("")) {
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
				
				displayOptions(owner,message);
			} else {
				EventBuilder builder = new AssignmentBuilder();
				builder.setName(eventName);
				
				Boolean validID = false;
				String eventID = "";
				Random random = new Random(System.currentTimeMillis());
				
				while(!validID) {
					eventID = Integer.valueOf(random.nextInt()).toString();
					validID = true;
					
					for(Event otherEvent : View.getEvents()) {
						if(otherEvent.getEventID().equals(eventID)) {
							validID = false;
							break;
						}
					}
				}
				
				builder.setEventID(eventID);
				
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
				
				builder.setStartDate(startDate);
				builder.setEndDate(endDate);
				builder.setEventDescription(descriptionField.getText());
				builder.setEventCompleted(false);
				
				event = builder.getEvent();
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
		startTimeChooser.setBackground(Color.WHITE);
		endTimeChooser = new JComboBox<>(endModel);
		endTimeChooser.setBackground(Color.WHITE);
		
		startTimeChooser.setRenderer(new DateFormattedListCellRenderer(new SimpleDateFormat("HH:mm")));
		endTimeChooser.setRenderer(new DateFormattedListCellRenderer(new SimpleDateFormat("HH:mm")));
	}
	
	public Event getEvent() {
		return event;
	}
}
