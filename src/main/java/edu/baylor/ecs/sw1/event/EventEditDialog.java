package edu.baylor.ecs.sw1.event;

import java.awt.Color;
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

/**
 * This dialog displays an event's information and allows it to be edited.
 * 
 * @author Par Wilkinson
 *
 */
public class EventEditDialog {
	JTextField eventNameField;
	JTextField courseField;

	JDatePickerImpl startDatePicker;
	JDatePickerImpl endDatePicker;

	JComboBox<Date> startTimeChooser;
	JComboBox<Date> endTimeChooser;

	JTextField descriptionField;
	JButton finishButton;
	Event event;

	public EventEditDialog() {

	}

	/**
	 * Takes a passed Event and given owner Frame, pops up a dialog
	 * that allows the user to edit info about the event.
	 * @param owner
	 * @param e
	 */
	public void edit(Frame owner, Event e) {
		event = e;

		Date eventStartDate = event.getStartDate();
		Date eventEndDate = event.getEndDate();

		eventNameField = new JTextField();
		eventNameField.setText(event.getEventName());

		courseField = new JTextField();
		courseField.setText(event.getCourse());

		UtilDateModel model = new UtilDateModel();
		UtilDateModel endModel = new UtilDateModel();

		Properties datePickerProps = new Properties();
		datePickerProps.put("date.today", "Today");
		datePickerProps.put("date.month", "Month");
		datePickerProps.put("date.year", "Year");
		
		if (eventStartDate != null) {
			model.setDate(Integer.parseInt((new SimpleDateFormat("yyyy")).format(eventStartDate)),
					Integer.parseInt((new SimpleDateFormat("MM")).format(eventStartDate)) - 1,
					Integer.parseInt((new SimpleDateFormat("dd")).format(eventStartDate)));
		}
		if (eventEndDate != null) {
			endModel.setDate(Integer.parseInt((new SimpleDateFormat("yyyy")).format(eventEndDate)),
					Integer.parseInt((new SimpleDateFormat("MM")).format(eventEndDate)) - 1,
					Integer.parseInt((new SimpleDateFormat("dd")).format(eventEndDate)));
		}
		
		startDatePicker = new JDatePickerImpl(new JDatePanelImpl(model, datePickerProps), new DateLabelFormatter());
		endDatePicker = new JDatePickerImpl(new JDatePanelImpl(endModel, datePickerProps), new DateLabelFormatter());

		startDatePicker.getJFormattedTextField().setBackground(Color.WHITE);
		startDatePicker.getJFormattedTextField().setOpaque(true);
		endDatePicker.getJFormattedTextField().setBackground(Color.WHITE);
		endDatePicker.getJFormattedTextField().setOpaque(true);

		initTimeChoosers();

		descriptionField = new JTextField();
		descriptionField.setText(event.getEventDescription());

		Object[] message = { "Enter Event Name", eventNameField,
				"Enter Course",courseField,
				"Select Start Date", startDatePicker, "Select End Date", endDatePicker, "Select Start Time",
				startTimeChooser, "Select End Time", endTimeChooser, "Enter Description", descriptionField, };

		displayOptions(owner, message, event);
	}

	private void displayOptions(Frame owner, Object[] message, Event e) {
		event = e;

		Object[] options = { "Confirm", "Cancel" };
		int option = JOptionPane.showOptionDialog(owner, message, "Edit Event", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE, new ImageIcon("empty.png"), options, options[0]);
		if (option == JOptionPane.OK_OPTION) {
			// info validation
			String eventName = eventNameField.getText();

			Object startDValue = startDatePicker.getModel().getValue();
			Object endDValue = endDatePicker.getModel().getValue();

			Object startTValue = startTimeChooser.getSelectedItem();
			Object endTValue = endTimeChooser.getSelectedItem();

			if (eventName.trim().equals("") || startDValue == null || endDValue == null || startTValue == null
					|| endTValue == null) {

				String errorMessage = "Error: Invalid data.";
				if (eventName.trim().equals("")) {
					errorMessage = "Error: No event name.";
				} else if (startDValue == null) {
					errorMessage = "Error: No start date.";
				} else if (endDValue == null) {
					errorMessage = "Error: No end date.";
				} else if (startTValue == null) {
					errorMessage = "Error: No start time.";
				} else if (endTValue == null) {
					errorMessage = "Error: No end time.";
				}

				JOptionPane.showMessageDialog(owner, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);

				displayOptions(owner, message, event);
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

				event.setEventName(eventName);
				event.setStartDate(startDate);
				event.setEndDate(endDate);
				event.setCourse(courseField.getText());
//				event.setEventPriority(eventPriorityField.getText());
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
		while (cal.getTime().getHours() <= 23) {
			startModel.addElement(cal.getTime());
			endModel.addElement(cal.getTime());
			cal.add(Calendar.MINUTE, 30);

			if (cal.getTime().getMinutes() == 0 && cal.getTime().getHours() == 0)
				break;
		}

		startTimeChooser = new JComboBox<>(startModel);
		endTimeChooser = new JComboBox<>(endModel);

		startTimeChooser.setBackground(Color.WHITE);
		startTimeChooser.setOpaque(true);
		endTimeChooser.setBackground(Color.WHITE);
		endTimeChooser.setOpaque(true);

		startTimeChooser.setRenderer(new DateFormattedListCellRenderer(new SimpleDateFormat("HH:mm")));
		endTimeChooser.setRenderer(new DateFormattedListCellRenderer(new SimpleDateFormat("HH:mm")));
	}

	public Event getEvent() {
		return event;
	}

}
