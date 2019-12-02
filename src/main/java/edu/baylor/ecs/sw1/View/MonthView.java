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
 * MonthView class creates the month view for the calendar. 
 * It uses a View as the calendar base 
 * 
 * @author Elizabeth Brighton
 *
 */
public class MonthView extends View {

	static int realYear, realMonth, realDay;
	
	static JPanel panel;
	

	/**
	 * InitCalendar initializes the Calendar with the Month View. This also sets
	 * the first day to be the current day, and the first Month to be the current Month
	 * @param 
	 * @return
	 */
	protected void initCalendar() {
		
		//Create the base panel 
		panel = new JPanel();
		panel.setBackground(Color.darkGray);
		panel.setPreferredSize(new Dimension(1100, 580));
		//Make it a grid layout - 7 days per row
		GridLayout layout = new GridLayout(0, 7);
		layout.setHgap(1);
		layout.setVgap(1);
		panel.setLayout(layout);
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		
		
		
		// Get current day
		GregorianCalendar cal = new GregorianCalendar(); // Create calendar
		realDay = cal.get(GregorianCalendar.DAY_OF_MONTH); // Get day
		realMonth = cal.get(GregorianCalendar.MONTH); // Get month
		realYear = cal.get(GregorianCalendar.YEAR); // Get year
		currentMonth = realMonth; // Match month and year
		currentYear = realYear;

		//put the current month and year in the panel
		mMonth.setText(months[currentMonth] + " " + currentYear);


		//Function that adds all the Panels 
		addPanels();
	}

	/**
	 * updateCalendar is used when an Event has been changed or a new month is selected
	 * using the Last/Next options. It refreshes the entire Month using the addPanels function below
	 * @param 
	 * @return
	 */
	public void updateCalendar() {

		mMonth.setText(months[currentMonth] + " " + currentYear);

		panel.removeAll();
	
		//Function that adds all the Panels
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
		
		int numDays, startMonth;
		cal = new GregorianCalendar(currentYear, currentMonth, 1);
		numDays = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		startMonth = cal.get(GregorianCalendar.DAY_OF_WEEK) - cal.get(GregorianCalendar.DAY_OF_WEEK_IN_MONTH);
		int i = 1;
		for(i = 1; i <= startMonth; i++) {
			panel.add(new JPanel());
		}
		for (; i <= (numDays + startMonth); i++) {
			dayDate = new Date(currentYear, currentMonth, (i-startMonth));
			panel.add(new ShowDay(dayDate, getDayEvents(dayDate), false, this));	
		}
		while(i != 43) {
			panel.add(new JPanel());
			i++;
		}
		
		mPanel.add(panel);
		this.add(mPanel);
		this.setBackground(Color.WHITE);
		mPanel.setVisible(true);
		
		//Display null when no event has been selected
		initSelect();

	}
	
	
	/**
	 * When an action is performed (Last/Next). The current month/year must be changed
	 * @param 
	 * @return
	 */
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

	@Override
	public int accept(ShowDay sd) {
		return sd.numGrid(this);
	}
	

}
