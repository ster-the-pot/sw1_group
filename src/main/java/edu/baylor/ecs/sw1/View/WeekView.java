package edu.baylor.ecs.sw1.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import edu.baylor.ecs.sw1.scheduleRender.ShowDay;

/**
 * WeekView class creates the week view for the calendar. It uses View as
 * the calendar base. The main differences between this and the MonthView class
 * is how the tables are made and updated, as well as the Action performed
 * Override.
 * 
 * @author Elizabeth Brighton
 *
 */
public class WeekView extends View {

	static int realYear, realMonth, realDay, currentDay, lastYear, lastMonth, lastMaxDay, dayOfWeek;
	static JPanel panel;

	
	/**
	 * InitCalendar initializes the Calendar with the Week View. This also sets
	 * the first day to be the current day, and the first Week to be the current week
	 * @param 
	 * @return
	 */
	protected void initCalendar() {
		panel = new JPanel();

		panel.setBackground(Color.darkGray);
		panel.setPreferredSize(new Dimension(1100, 580));
		panel.setMaximumSize(panel.getPreferredSize());
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

		//Get the number of days in the month
		int numDays;
		numDays = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		if (currentDay - 7 < 0) {
			dayOfWeek = cal.get(GregorianCalendar.DAY_OF_WEEK_IN_MONTH) - cal.get(GregorianCalendar.DAY_OF_WEEK);
		} else {
			dayOfWeek = cal.get(GregorianCalendar.DAY_OF_WEEK);
		}

		// Function that adds all the Panels
		addPanels();

	}

	/**
	 * updateCalendar is used when an Event has been changed or a new week is selected
	 * using the Last/Next options. It refreshes the entire Week using the addPanels function below
	 * @param 
	 * @return
	 */
	public void updateCalendar() {

		mMonth.setText(months[currentMonth] + " " + currentYear);

		panel.removeAll();

		// Function that adds all the Panels
		addPanels();

		panel.revalidate();
		panel.repaint();
	}

	
	/**
	 * addPanels goes day by day adding the Events for each day. Each day to be added calls
	 * the ShowDay class, which correctly implements each Events for the day.
	 * @param 
	 * @return 
	 */
	protected void addPanels() {

		int numDays;
		numDays = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);

		int i = 1;
		for (i = (currentDay - dayOfWeek + 1); i <= (currentDay + 7 - dayOfWeek); i++) {

			if (i > numDays) {
				if (currentMonth != 11) {
					dayDate = new Date(currentYear, currentMonth + 1, (i - numDays));
					panel.add(new ShowDay(dayDate, getDayEvents(dayDate), true, this));
				} else {
					dayDate = new Date(currentYear + 1, 0, (i - numDays));
					panel.add(new ShowDay(dayDate, getDayEvents(dayDate), true, this));
				}
			} else if (i <= 0) {
				if (currentMonth != 0) {
					dayDate = new Date(currentYear, currentMonth - 1, (i + lastMaxDay));
					panel.add(new ShowDay(dayDate, getDayEvents(dayDate), true, this));
				} else {
					dayDate = new Date(currentYear-1, 11, (i + lastMaxDay));
					panel.add(new ShowDay(dayDate, getDayEvents(dayDate), true, this));
				}
			} else {
				dayDate = new Date(currentYear, currentMonth, (i));
				panel.add(new ShowDay(dayDate, getDayEvents(dayDate), false, this));
			}
		}

		mPanel.add(panel);
		this.add(mPanel);
		this.setBackground(Color.WHITE);
		mPanel.setVisible(true);
		
		//The default Selected Event is null or none
		initSelect();
		
	}

	/**
	 * When an action is performed (Last/Next). The current week/month/year must be changed.
	 * The WeekView version of this is a bit more complicated than the month view. 
	 * @param 
	 * @return
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if ("Last".equals(e.getActionCommand())) {
			//If the current day goes into the last month - the month must be changed
			if (currentDay - 7 <= 0) {
				//If its not January then this is fairly easy. 
				if (currentMonth != 0) {
					currentMonth--;
					lastMonth--;
					lastMaxDay = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);

					cal = new GregorianCalendar(currentYear, currentMonth, 1);
					currentDay = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH) - 7 + currentDay;
					//If it is January then the month and year must be changed
				} else {
					lastMonth = 0;
					lastYear = currentYear;
					currentMonth = 11;
					currentYear--;
					lastMaxDay = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);

					cal = new GregorianCalendar(currentYear, currentMonth, 1);
					currentDay = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH) - 7 + currentDay;

				}
				//If it does not change Months, then just minus 7 from the current day
			} else {
				currentDay = currentDay - 7;
			}
			
			//If the Next button is Hit
		} else if ("Next".equals(e.getActionCommand())) {
			//If the current day goes into the next Month
			if (currentDay + 7 > cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH)) {
				//If the month is not December then add a month
				if (currentMonth != 11) {
					lastMonth = currentMonth;
					currentMonth++;
					lastMaxDay = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);

					int currentDisp = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH) - currentDay;

					cal = new GregorianCalendar(currentYear, currentMonth, 1);

					currentDay = 7 - currentDisp;
					//If the month is December then manually change the month and year
				} else {
					lastMonth = currentMonth;
					lastYear = currentYear;
					lastMaxDay = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);

					currentMonth = 0;
					currentYear++;

					int currentDisp = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH) - currentDay;
					cal = new GregorianCalendar(currentYear, currentMonth, 1);

					currentDay = 7 - currentDisp;

				}
				//If the month does not change then just add 7 to the current day
			} else {
				currentDay = currentDay + 7;
			}
		}
		updateCalendar();
	}
	
	
	@Override
	public int accept(ShowDay sd) {
		return sd.numGrid(this);
	}

}
