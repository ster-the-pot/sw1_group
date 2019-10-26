package edu.baylor.ecs.sw1.weekView;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;



/**
 * MonthView class creates the month view for the calendar. 
 * It contains a constructor with a JTable as the calendar base. 
 * 
 * @author Elizabeth Brighton
 *
 */
public class WeekView extends JFrame implements ActionListener {
	private JFrame mainFrame;
	String[] dayHeader = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
	String[] months = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
			"October", "November", "December" };
	String[] monthsAbv = { "Jan", "Feb", "March", "April", "May", "June", "July", "Aug", "Sept",
			"Oct", "Nov", "Dec" };
	//List<Event> Events;
	static DefaultTableModel monthCalendar;
	//static JTable mTable;
	static JFrame mFrame;
	static JLabel mMonth, mDay;
	static JButton mLast, mNext, mSide;
	static JPanel mPanel, menuPanel;
	static int realYear, realMonth, realDay, currentYear, currentMonth, currentDay, 
	lastYear, lastMonth, lastMaxDay, dayOfWeek;
	//TableColumn col;
	static JPanel panel, sidePanel, AllPanel;
	GregorianCalendar cal;

	public WeekView() {
		prepareGUI();
	}

	public static void main(String[] args) {
		WeekView swingLayoutDemo = new WeekView();
		//swingLayoutDemo.showGridLayoutDemo();
	}

	private void prepareGUI() {
		
		mainFrame = new JFrame("MonthView");
		mainFrame.setSize(700, 600);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		AllPanel = new JPanel();		
		
		sidePanel = new JPanel();
		JLabel label = new JLabel("HELLO");

		sidePanel.setBackground(Color.GRAY);
		sidePanel.add(label);
		sidePanel.setPreferredSize(new Dimension(50, 40));
		GridLayout layout = new GridLayout(7, 0);
		layout.setHgap(1);
		layout.setVgap(1);
		sidePanel.setLayout(layout);
		
		
		
		menuPanel = new JPanel(new FlowLayout());
		
		menuPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		mLast = new JButton("Last");
		mLast.addActionListener(this);
		menuPanel .add(mLast);
		mMonth = new JLabel(months[currentMonth] + " " + currentYear, SwingConstants.CENTER);
		mMonth.setPreferredSize(new Dimension(100, 20));
		menuPanel.add(mMonth);
		mNext = new JButton("Next");
		mNext.addActionListener(this);
		menuPanel.add(mNext);
		
		
	
		
		mPanel = new JPanel();
		mPanel.setLayout(new FlowLayout());
		mPanel.add(menuPanel, BorderLayout.NORTH);
		
		

		mainFrame.add(sidePanel, BorderLayout.WEST);
		mainFrame.add(mPanel);
		
		//mainFrame.add(AllPanel);
		mainFrame.setVisible(true);
		
		initCalendar();
		
	}


	private void initCalendar() {

		
		panel = new JPanel();
		
		panel.setBackground(Color.darkGray);
		panel.setSize(500, 700);
		panel.setPreferredSize(new Dimension(500, 500));
		GridLayout layout = new GridLayout(0, 7);
		layout.setHgap(1);
		layout.setVgap(1);
		
		panel.setLayout(layout);
		panel.setBorder(BorderFactory.createLineBorder(Color.darkGray));
	
		
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
		
		

		JLabel label;
		JPanel cellPanel;

		int i = 1;

		for (i = (currentDay - dayOfWeek + 1); i <= (currentDay + 7 - dayOfWeek); i++) {

			if(i > numDays) {
				label = new JLabel(monthsAbv[(currentMonth+1)%13] + " " + (i + currentDay - numDays));
				cellPanel = new JPanel();
				cellPanel.add(label);
				panel.add(cellPanel);
			} else if (i < 0){
				if(currentMonth != 0)
					label = new JLabel(monthsAbv[currentMonth-1] + " " + (lastMaxDay + i));
				else 
					label = new JLabel(monthsAbv[11] + " " + (lastMaxDay + i));
				cellPanel = new JPanel();
				cellPanel.add(label);
				panel.add(cellPanel);
			}else {
				label = new JLabel("" + (i));
				cellPanel = new JPanel();
				cellPanel.add(label);
				panel.add(cellPanel);
			}
				
			
		}
		

		
		mPanel.add(panel);
		mainFrame.setVisible(true);
		

	}

	public void updateCalendar() {

		mMonth.setText(months[currentMonth] + " " + currentYear);


		panel.removeAll();
		
		
		int numDays;
		numDays = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		JLabel label;
		JPanel cellPanel;

		int i = 1;
		
		for (i = (currentDay - dayOfWeek + 1); i <= (currentDay + 7 - dayOfWeek); i++) {

			if(i > numDays) {
				label = new JLabel(monthsAbv[(currentMonth+1)%13] + " " + (i + currentDay - numDays));
			} else if (i <= 0){
				if(currentMonth != 0)
					label = new JLabel(monthsAbv[currentMonth-1] + " " + (lastMaxDay + i));
				else 
					label = new JLabel(monthsAbv[11] + " " + (lastMaxDay + i));
			}else {

				label = new JLabel("" + (i));

			}	
			cellPanel = new JPanel();
			cellPanel.add(label);
			panel.add(cellPanel);
		}

		mPanel.add(panel);
		mainFrame.setVisible(true);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		if ("Last".equals(e.getActionCommand())) {
			if (currentDay - 7 <= 0) {
				if (currentMonth != 0) {
					currentMonth = lastMonth;
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
					//int ofset = cal.get(GregorianCalendar.DAY_OF_WEEK);
					
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
					//int ofset = cal.get(GregorianCalendar.DAY_OF_WEEK) ;
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
