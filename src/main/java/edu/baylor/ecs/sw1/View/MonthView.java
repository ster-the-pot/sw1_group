package edu.baylor.ecs.sw1.View;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import edu.baylor.ecs.sw1.ShowDay.ShowDay;



/**
 * MonthView class creates the month view for the calendar. 
 * It uses a JPanel as the calendar base 
 * 
 * @author Elizabeth Brighton
 *
 */
public class MonthView extends View {

	static int realYear, realMonth, realDay;
	
	static JPanel panel;

	


	protected void initCalendar() {
		/*this.setBackground(Color.darkGray);
		this.setSize(500, 700);
		GridLayout layout = new GridLayout(0, 7);
	
		layout.setHgap(1);
		layout.setVgap(1);
		this.setLayout(layout);*/
	
		

		panel = new JPanel();
		
		panel.setBackground(Color.darkGray);
		//panel.setSize(500, 700);
		
		panel.setPreferredSize(new Dimension(1100, 600));
		//panel.setMaximumSize( panel.getPreferredSize() );
		GridLayout layout = new GridLayout(0, 7);
		layout.setHgap(1);
		layout.setVgap(1);
		
		panel.setLayout(layout);
		panel.setBorder(BorderFactory.createLineBorder(Color.darkGray));
		
		
		//this.setPreferredSize(new Dimension(500, 500));
		
		// Get current day
		GregorianCalendar cal = new GregorianCalendar(); // Create calendar
		realDay = cal.get(GregorianCalendar.DAY_OF_MONTH); // Get day
		realMonth = cal.get(GregorianCalendar.MONTH); // Get month
		realYear = cal.get(GregorianCalendar.YEAR); // Get year
		currentMonth = realMonth; // Match month and year
		currentYear = realYear;


		mMonth.setText(months[currentMonth] + " " + currentYear);


		//Function that adds all the Panels 
		addPanels();
	}

	public void updateCalendar() {

		mMonth.setText(months[currentMonth] + " " + currentYear);

		panel.removeAll();
	
		
		
		//Function that adds all the Panels
		addPanels();
		//initCalendar();
		
		panel.revalidate();
		panel.repaint();
	}

	protected void addPanels() {
		
		
		int numDays, startMonth;
		cal = new GregorianCalendar(currentYear, currentMonth, 1);
		numDays = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		startMonth = cal.get(GregorianCalendar.DAY_OF_WEEK) - cal.get(GregorianCalendar.DAY_OF_WEEK_IN_MONTH);
		int i = 1;
		for(i = 1; i <= startMonth; i++) {
			panel.add(new JPanel());
		}
		for (; i <= (numDays + startMonth); i++) {
			panel.add(new ShowDay((i-startMonth), currentMonth, currentYear));	
		}
		while(i != 43) {
			panel.add(new JPanel());
			i++;
		}
		
		mPanel.add(panel);
		this.add(mPanel);
		this.setBackground(Color.WHITE);
		mPanel.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if ("Last".equals(e.getActionCommand())) {
			if (currentMonth != 0) {
				currentMonth--;
			} else {
				currentMonth = 11;
				currentYear--;
			}
		} else if ("Next".equals(e.getActionCommand())) {
			if (currentMonth != 11) {
				currentMonth++;
			} else {
				currentMonth = 0;
				currentYear++;
			}
		}
		updateCalendar();
	}
	

}
