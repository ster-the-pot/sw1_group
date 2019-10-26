package edu.baylor.ecs.sw1.weekView;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


/**
 * WeekView class creates the week view for the calendar. 
 * It contains a constructor with a JTable as the calendar base. 
 * 
 * @author Elizabeth Brighton
 *
 */
public class WeekView extends JFrame implements ActionListener {
	String[] dayHeader = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
	String[] months = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
			"October", "November", "December" };
	//List<Event> Events;
	static DefaultTableModel monthCalendar;
	static JTable mTable;
	static JFrame mFrame;
	static JLabel mMonth, mDay;
	static JButton mLast, mNext;
	static JPanel mPanel;
	static int realYear, realMonth, realDay, currentYear, currentMonth, currentDay, 
				lastYear, lastMonth, lastMaxDay;
	GregorianCalendar cal;
	TableColumn col;

	WeekView() {

		getContentPane().setBackground(Color.WHITE);
		getContentPane().setFont(new Font("Lucida Grande", Font.PLAIN, 34));
		getContentPane().setLayout(new BorderLayout(0, 0));

		// Basic JFrame
		mFrame = new JFrame("MonthView");
		mFrame.setSize(500, 400);
		mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mFrame.setVisible(true);

		// Make table
		monthCalendar = new DefaultTableModel();
		mTable = new JTable(monthCalendar);

		mPanel = new JPanel();

		mFrame.getContentPane().add(mPanel, BorderLayout.NORTH);

		mLast = new JButton("Last");
		mLast.addActionListener(this);
		mPanel.add(mLast);
		mMonth = new JLabel(months[currentMonth] + " " + currentYear, SwingConstants.CENTER);
		mMonth.setPreferredSize(new Dimension(340, 20));
		mPanel.add(mMonth);

		mNext = new JButton("Next");
		mNext.addActionListener(this);

		mPanel.add(mNext);
		mFrame.getContentPane().add(new JScrollPane(mTable));

		initCalendar();

	}

	private void initCalendar() {

		// Add comunms to the tableModel
		for (int i = 0; i < 7; i++) {
			monthCalendar.addColumn(dayHeader[i]);
			col = mTable.getColumnModel().getColumn(i);
			// col.setCellEditor(new MyTableCellEditor());

		}

		// Get current day
		cal = new GregorianCalendar(); // Create calendar
		realDay = cal.get(GregorianCalendar.DAY_OF_MONTH); // Get day
		realMonth = cal.get(GregorianCalendar.MONTH); // Get month
		realYear = cal.get(GregorianCalendar.YEAR); // Get year
		currentDay = realDay;
		currentMonth = realMonth; // Match month and year
		currentYear = realYear;
		
		GregorianCalendar tempCal;
		if(currentMonth > 1) {
			tempCal = new GregorianCalendar(realYear, realMonth-1, 1);
		} else {
			tempCal = new GregorianCalendar(realYear - 1, 12, 1);
		}
		lastMaxDay = tempCal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		lastMonth = tempCal.get(GregorianCalendar.MONTH); // Get month
		lastYear = tempCal.get(GregorianCalendar.YEAR);
		
		
		
		
		mMonth.setText(months[currentMonth] + " " + currentYear);
		mMonth.setBounds(100, 100, 200, 200);
		mTable.setColumnSelectionAllowed(true);
		mTable.setRowSelectionAllowed(true);
		mTable.setRowHeight(300);
		mTable.setPreferredScrollableViewportSize(new Dimension(500, 70));
		mTable.setFillsViewportHeight(true);

		monthCalendar.setColumnCount(7);
		monthCalendar.setRowCount(1);

		
		int numDays, dayOfWeek;
		numDays = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		dayOfWeek = cal.get(GregorianCalendar.DAY_OF_WEEK);


		System.out.println(currentDay - dayOfWeek + " " + (currentDay + 7 - dayOfWeek) + " " + numDays);
		System.out.println(currentDay + " " );
		for (int i = (currentDay - dayOfWeek + 1), j = 0; i <= (currentDay + 7 - dayOfWeek); i++, j++) {
			if(i > numDays) {
				monthCalendar.setValueAt(i-numDays, 0, j);
			} else if (i < 0){
				monthCalendar.setValueAt(i + lastMaxDay, 0, j);
			}else {
				monthCalendar.setValueAt(i, 0, j);
			}
				
			
		}

	}

	public void updateCalendar() {
		// Allow/disallow buttons

		// btnPrev.setEnabled(true); btnNext.setEnabled(true);

		mMonth.setText(months[currentMonth] + " " + currentYear);

		for (int j = 0; j < 7; j++) {
			monthCalendar.setValueAt(null, 0, j);
		}

		
		int numDays, startMonth, dayOfWeek;// , currentDay;
		numDays = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		startMonth = cal.get(GregorianCalendar.DAY_OF_WEEK);
		dayOfWeek = cal.get(GregorianCalendar.WEEK_OF_MONTH);
		// currentDay = cal.get(GregorianCalendar.DAY_OF_MONTH);

		System.out.println(currentDay - dayOfWeek + " " + (currentDay + 7 - dayOfWeek) + " " + numDays);
		System.out.println(currentDay);
		for (int i = (currentDay - dayOfWeek + 1), j = 0; i <= (currentDay + 7 - dayOfWeek); i++, j++) {
			if(i > numDays) {
				monthCalendar.setValueAt(i-numDays, 0, j);
			} else if (i < 0){
				monthCalendar.setValueAt(i + lastMaxDay, 0, j);
			}else {
				monthCalendar.setValueAt(i, 0, j);
			}
		}

		monthCalendar.fireTableDataChanged();

	}

	public static void main(String args[]) {
		WeekView m = new WeekView();
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
					currentMonth++;
					int currentDisp = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH) 
							- currentDay;
					//int ofset = cal.get(GregorianCalendar.DAY_OF_WEEK);
					
					cal = new GregorianCalendar(currentYear, currentMonth, 1);
					//System.out.println(currentDisp + " " + ofset);

					currentDay = 7- currentDisp;	
					
					
				} else {
					currentMonth = 0;
					currentYear++;
					int currentDisp = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH) 
							- currentDay;
					//int ofset = cal.get(GregorianCalendar.DAY_OF_WEEK) ;
					cal = new GregorianCalendar(currentYear, currentMonth, 1);
					//System.out.println(currentDisp + " " + ofset);
					
					currentDay = 7 - currentDisp;	
					
					
				}
			} else {
				currentDay = currentDay + 7;
			}
		}
		updateCalendar();
	}

}
