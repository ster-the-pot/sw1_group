package edu.baylor.ecs.sw1.eventFrames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class CreateEventDialog extends JDialog {
	JTextField eventNameField;
	JTextField eventPriorityField;
	JTextField startTimeField;
	JTextField descriptionField;
	JButton finishButton;

	public CreateEventDialog(Frame owner) {
		super(owner,"Create Event");

		this.setBackground(Color.WHITE);
		this.setFont(new Font("Lucida Grande", Font.PLAIN, 34));
//		this.setPreferredSize(new Dimension(200,100));
//		this.setLayout(new GridBagLayout());
		
		
		
		eventNameField = new JTextField();
		eventPriorityField = new JTextField();
		//TODO: date and duration choice
		startTimeField = new JTextField();
		descriptionField = new JTextField();
		

		Object[] message = {
				"Enter Event Name",eventNameField,
				"Enter Event Priority",eventPriorityField,
				//TODO: add date and duration choice
				"Enter Start Time",startTimeField,
				"Enter Description",descriptionField,
		};

		int option = JOptionPane.showConfirmDialog(this, message);
	}
}
