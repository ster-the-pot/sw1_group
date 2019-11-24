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

import edu.baylor.ecs.sw1.scheduleRender.ShowDay;


/**
 * WeekView class creates the week view for the calendar. 
 * It uses a JPanel as the calendar base. The main differences
 * between this and the MonthView class is how the tables
 * are made and updated, as well as the Action performed Override.
 * 
 * @author Elizabeth Brighton
 *
 */
public class WeekView extends View {

	static int realYear, realMonth, realDay, currentDay, lastYear, lastMonth, lastMaxDay, dayOfWeek;
	static JPanel panel;
	
	

	protected void initCalendar() {
		panel = new JPanel();
		
		panel.setBackground(Color.darkGray);
		//panel.setSize(500, 700);
		panel.setPreferredSize(new Dimension(1100, 600));
		panel.setMaximumSize( panel.getPreferredSize() );
		GridLayout layout = new GridLayout(0, 7);
		layout.setHgap(1);
		layout.setVgap(1);
		
		panel.setLayout(layout);
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		

		
		// Get current day
		cal = new GregorianCalendar(); // Create calendar
		realDay = cal.get(GregorianCalendar.DAY_OF_MONTH); // Get day
		realMonth = cal.get(GregorianCalendar.MONTH); // Get month
		realYear = cal.get(GregorianCalendar.YEAR); // Get year
		currentMonth = realMonth; // Match month and year
		currentYear = realYear;
		currentDay = realDay;
		

		mMonth.setText(months[currentMonth] + " " + currentYear);


		int numDays;
		numDays = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		if(currentDay - 7 < 0) {
			dayOfWeek = cal.get(GregorianCalendar.DAY_OF_WEEK_IN_MONTH) - cal.get(GregorianCalendar.DAY_OF_WEEK);
		} else {
			dayOfWeek = cal.get(GregorianCalendar.DAY_OF_WEEK);
		}
		
		
		
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
		
		int numDays;
		numDays = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);

		int i = 1;
		for (i = (currentDay - dayOfWeek + 1); i <= (currentDay + 7 - dayOfWeek); i++) {
			if(i > numDays) {	
				if(currentMonth != 11)
					panel.add(new ShowDay((i  - numDays), currentMonth+1, currentYear, true));
				else
					panel.add(new ShowDay((i - numDays), 0, currentYear+1, true));
				
			} else if (i <= 0){
				if(currentMonth != 0)
					panel.add(new ShowDay((lastMaxDay + i), currentMonth-1, currentYear, true));	
				else 
					panel.add(new ShowDay((lastMaxDay + i), 11, currentYear-1, true));

			}else {
				panel.add(new ShowDay(i, currentMonth, currentYear, false));
			}
		}
		
		mPanel.add(panel);
		this.add(mPanel);
		this.setBackground(Color.WHITE);
		mPanel.setVisible(true);
	}
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if ("Last".equals(e.getActionCommand())) {
			if (currentDay - 7 <= 0) {
				if (currentMonth != 0) {
					currentMonth--;
					lastMonth--;
					lastMaxDay = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
					
					cal = new GregorianCalendar(currentYear, currentMonth, 1);
					currentDay = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH) - 7 + currentDay;

				} else {
					lastMonth = 0;
					lastYear = currentYear;
					currentMonth = 11;
					currentYear--;
					lastMaxDay = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
					
					cal = new GregorianCalendar(currentYear, currentMonth, 1);
					currentDay = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH) - 7 + currentDay;

				}
			} else {
				currentDay = currentDay - 7;
			}
		//If the calendar is moving forward we don't have to worry about last month
		} else if ("Next".equals(e.getActionCommand())) {
			if (currentDay + 7 > cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH)) {
				if (currentMonth != 11) {
					lastMonth = currentMonth;
					currentMonth++;
					lastMaxDay = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
					
					
					int currentDisp = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH) 
							- currentDay;
					
					cal = new GregorianCalendar(currentYear, currentMonth, 1);

					currentDay = 7- currentDisp;	
					
					
				} else {
					lastMonth = currentMonth;
					lastYear = currentYear;
					lastMaxDay = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
					
					currentMonth = 0;
					currentYear++;
					
					int currentDisp = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH) 
							- currentDay;
					cal = new GregorianCalendar(currentYear, currentMonth, 1);

					
					currentDay = 7 - currentDisp;	
					
				}
			} else {
				currentDay = currentDay + 7;
			}
		}
		updateCalendar();
	}
	

}
