package edu.baylor.ecs.sw1.ShowDay;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.baylor.ecs.sw1.event.Event;



public class ShowDay extends JPanel implements ActionListener{
	static Event currentSelected;
	static String[] monthsAbv = { "Jan", "Feb", "March", "April", "May", "June", "July", "Aug", "Sept",
			"Oct", "Nov", "Dec" };
	static JLabel label;
	static JPanel dayPanel;
	Color labelColor = new Color(64, 143, 222);
	
	public ShowDay(int day, int month, int year) {
		
		label = new JLabel(" " + day);
		

		this.setLayout(new GridLayout(6,0));
		
		

		this.add(label);
		
		if(day == 29 && month == 9) {
			JButton label1 = new JButton("Iteration #2");
			label1.setBackground(labelColor);
			
			this.add(label1);
			label1.setHorizontalAlignment(JLabel.CENTER);
		} else if (day == 24 && month == 8) {
			JButton label2 = new JButton("Iteration #1");
			label2.setBackground(labelColor);
			
			label2.setHorizontalAlignment(JLabel.CENTER);
			this.add(label2);
		} else if (day == 5 && month == 11) {
			JButton label3 = new JButton("Iteration #3");
			label3.setBackground(labelColor);
			label3.setHorizontalAlignment(JLabel.CENTER);
			this.add(label3);
		}
		
		
		
		
		
	}
	public ShowDay(int day, int month, int year, Boolean isOtherMonth) {
		
		label = new JLabel(" " + monthsAbv[month] + ": " + day);
		
		this.setLayout(new GridLayout(6,0));
		
		

		this.add(label);
		
		if(day == 29 && month == 9) {
			JButton label1 = new JButton("Iteration #2");
			label1.setBackground(labelColor);
			label1.addActionListener(this);
			
			this.add(label1);
			label1.setHorizontalAlignment(JLabel.CENTER);
		} else if (day == 24 && month == 8) {
			JButton label2 = new JButton("Iteration #1");
			label2.setBackground(labelColor);
			
			label2.setHorizontalAlignment(JLabel.CENTER);
			this.add(label2);
		} else if (day == 5 && month == 11) {
			JButton label3 = new JButton("Iteration #3");
			label3.setBackground(labelColor);
			label3.setHorizontalAlignment(JLabel.CENTER);
			this.add(label3);
		}
		
		
		
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if ("Iteration #1".equals(e.getActionCommand())) {
			//CAN SHORTCUT CALL VIEW EVENT DETAILS
			//OR MAKE THIS THE SELECTED EVENT
			Event EventTemp = new Event();
			currentSelected = EventTemp;
			
		} else if ("Iteration #2".equals(e.getActionCommand())) {
			Event EventTemp = new Event();
			currentSelected = EventTemp;
		} else if ("Iteration #3".equals(e.getActionCommand())) {
			Event EventTemp = new Event();
			currentSelected = EventTemp;
		}
		
	}
	//returns the EventID of the currently selected Event
	static Event getCurrentEvent() {
		return currentSelected;
	}
}
