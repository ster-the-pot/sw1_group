package edu.baylor.ecs.sw1.monthView;


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
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;



/**
 * MonthView class creates the month view for the calendar. 
 * It uses a JPanel as the calendar base 
 * 
 * @author Elizabeth Brighton
 *
 */
public class MonthView extends JFrame implements ActionListener {
	private JFrame mainFrame;
	String[] dayHeader = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
	String[] months = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
			"October", "November", "December" };
	//List<Event> Events;
	static DefaultTableModel monthCalendar;
	//static JTable mTable;
	static JFrame mFrame;
	static JLabel mMonth, mDay;
	static JButton mLast, mNext, mSide;
	static JPanel mPanel, menuPanel;
	static int realYear, realMonth, realDay, currentYear, currentMonth;
	//TableColumn col;
	static JPanel panel, sidePanel, AllPanel;
	

	public MonthView() {
		prepareGUI();
	}

	public static void main(String[] args) {
		MonthView swingLayoutDemo = new MonthView();
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
		
		
		
		JPanel menuPanel = new JPanel();
		GridLayout menuLayout = new GridLayout(0, 5);
		menuLayout.setHgap(1);
		menuLayout.setVgap(1);
		menuPanel.setLayout(menuLayout);
		menuPanel.setPreferredSize(new Dimension(500, 20));
		
		
		mLast = new JButton("Last");
		mLast.addActionListener(this);
		menuPanel.add(mLast);
		menuPanel.add(new JPanel());
		mMonth = new JLabel(months[currentMonth] + " " + currentYear, SwingConstants.CENTER);
		menuPanel.add(mMonth);
		menuPanel.add(new JPanel());
		mNext = new JButton("Next");
		mNext.addActionListener(this);
		menuPanel.add(mNext);
		
		
		
		
		
		
		JPanel dayOfWeekPanel = new JPanel();
		GridLayout dayLayout = new GridLayout(0, 7);
		dayLayout.setHgap(1);
		dayLayout.setVgap(1);
		dayOfWeekPanel.setLayout(dayLayout);
		dayOfWeekPanel.setBackground(Color.darkGray);
		dayOfWeekPanel.setPreferredSize(new Dimension(500, 30));
		JLabel daylabel;
		JPanel cellPanel;
		for(int i = 0; i < 7; i++) {
			daylabel = new JLabel(dayHeader[i]);
			cellPanel = new JPanel();
			cellPanel.add(daylabel);
			dayOfWeekPanel.add(cellPanel);
		}
		dayOfWeekPanel.setBorder(BorderFactory.createLineBorder(Color.darkGray));
		
		
		
		
		mPanel = new JPanel();
		mPanel.setLayout(new BoxLayout(mPanel, BoxLayout.Y_AXIS));
		mPanel.add(menuPanel, BorderLayout.NORTH);
		mPanel.add(dayOfWeekPanel, BorderLayout.NORTH);
		

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
		GridLayout layout = new GridLayout(0, 7);
	
		layout.setHgap(1);
		layout.setVgap(1);
		panel.setLayout(layout);
		JLabel label;
		JPanel cellPanel;
		
		panel.setPreferredSize(new Dimension(500, 500));
		
		// Get current day
		GregorianCalendar cal = new GregorianCalendar(); // Create calendar
		realDay = cal.get(GregorianCalendar.DAY_OF_MONTH); // Get day
		realMonth = cal.get(GregorianCalendar.MONTH); // Get month
		realYear = cal.get(GregorianCalendar.YEAR); // Get year
		currentMonth = realMonth; // Match month and year
		currentYear = realYear;

		
		mMonth.setText(months[currentMonth] + " " + currentYear);


		int numDays,startMonth;
		numDays = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		startMonth = cal.get(GregorianCalendar.DAY_OF_WEEK) - cal.get(GregorianCalendar.DAY_OF_WEEK_IN_MONTH);


		int i = 1;
		for(i = 1; i <= startMonth; i++) {
			panel.add(new JPanel());
		}
		for (; i <= (numDays + startMonth); i++) {
			
			label = new JLabel("" + (i - startMonth));
			cellPanel = new JPanel();
			
			cellPanel.add(label, BorderLayout.WEST);
			panel.add(cellPanel);
		}
		while(i != 43) {
			panel.add(new JPanel());
			i++;
		}
		
		mPanel.add(panel);
		mainFrame.setVisible(true);
		

	}

	public void updateCalendar() {

		mMonth.setText(months[currentMonth] + " " + currentYear);


		panel.removeAll();
		
		
		int numDays, startMonth;
		GregorianCalendar cal = new GregorianCalendar(currentYear, currentMonth, 1);
		numDays = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		startMonth = cal.get(GregorianCalendar.DAY_OF_WEEK) - cal.get(GregorianCalendar.DAY_OF_WEEK_IN_MONTH);
		JLabel label;
		JPanel cellPanel;
		int i = 1;
		for(i = 1; i <= startMonth; i++) {
			panel.add(new JPanel());
		}
		for (; i <= (numDays + startMonth); i++) {
			
			label = new JLabel("" + (i - startMonth));
			cellPanel = new JPanel();
			
			cellPanel.add(label);
			panel.add(cellPanel);
		}
		while(i != 43) {
			panel.add(new JPanel());
			i++;
		}

		mPanel.add(panel);
		mainFrame.setVisible(true);

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
