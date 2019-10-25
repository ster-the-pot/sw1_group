package edu.baylor.ecs.sw1.monthView;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class MonthView extends JFrame implements ActionListener {
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
	static int realYear, realMonth, realDay, currentYear, currentMonth;
	TableColumn col;

	MonthView() {

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
			//col = mTable.getColumnModel().getColumn(i);
	        //setUpEvents(mTable, mTable.getColumnModel().getColumn(i));
	        

		}

		// Get current day
		GregorianCalendar cal = new GregorianCalendar(); // Create calendar
		realDay = cal.get(GregorianCalendar.DAY_OF_MONTH); // Get day
		realMonth = cal.get(GregorianCalendar.MONTH); // Get month
		realYear = cal.get(GregorianCalendar.YEAR); // Get year
		currentMonth = realMonth; // Match month and year
		currentYear = realYear;

		mMonth.setText(months[currentMonth] + " " + currentYear);
		mMonth.setBounds(100, 100, 200, 200);
		mTable.setColumnSelectionAllowed(true);
		mTable.setRowSelectionAllowed(true);
		mTable.setRowHeight(50);
		mTable.setPreferredScrollableViewportSize(new Dimension(500, 70));
		mTable.setFillsViewportHeight(true);

		monthCalendar.setColumnCount(7);
		monthCalendar.setRowCount(6);

		int numDays, startMonth;
		numDays = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		startMonth = cal.get(GregorianCalendar.DAY_OF_WEEK);
		for (int i = 1; i <= numDays; i++) {
			
			 //String str = Integer.toString(i) + " HelloThere"; Object value = str;
			int row = ((i + startMonth - 2) / 7);
			int column = (i + startMonth - 2) % 7;
			monthCalendar.setValueAt(i, row, column);
		}

	}

	public void updateCalendar() {
		// Allow/disallow buttons

		// btnPrev.setEnabled(true); btnNext.setEnabled(true);

		mMonth.setText(months[currentMonth] + " " + currentYear);

		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				monthCalendar.setValueAt(null, i, j);
			}
		}

		int numDays, startMonth;
		GregorianCalendar cal = new GregorianCalendar(currentYear, currentMonth, 1);
		numDays = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		startMonth = cal.get(GregorianCalendar.DAY_OF_WEEK);

		for (int i = 1; i <= numDays; i++) {
			/*
			 * String str = Integer.toString(i) + " HelloThere"; Object value = str;
			 */
			int row = ((i + startMonth - 2) / 7);
			int column = (i + startMonth - 2) % 7;
			monthCalendar.setValueAt(i, row, column);
		}

		monthCalendar.fireTableDataChanged();

	}

	public static void main(String args[]) {
		MonthView m = new MonthView();
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
/*
	public void setUpEvents(JTable table, TableColumn AllColumns) {
//Set up the editor for the sport cells.
		JComboBox comboBox = new JComboBox();
		comboBox.addItem("Snowboarding");
		comboBox.addItem("Rowing");
		comboBox.addItem("Knitting");
		comboBox.addItem("Speed reading");
		comboBox.addItem("Pool");
		comboBox.addItem("None of the above");
		AllColumns.setCellEditor(new DefaultCellEditor(comboBox));

//Set up tool tips for the sport cells.
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("Click for combo box");
		AllColumns.setCellRenderer(renderer);
	}
*/
}
