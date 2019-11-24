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

import edu.baylor.ecs.sw1.event.Event;


/**
 * This is for ShowDay and its children. It is the panel for each
 * specific day on the different views (Month or Week)
 * 
 * @author Elizabeth Brighton
 *
 */
public abstract class View extends JPanel implements ActionListener{
	String[] dayHeader = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
	String[] months = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
			"October", "November", "December" };
	
	static Event selectedEvent = null;
	
	JLabel mMonth, mDay;
	JButton mLast, mNext;
	JPanel mPanel;
	int currentYear, currentMonth;
	//static JPanel panel;
	GregorianCalendar cal;
	Font loginBold = new Font(Font.SANS_SERIF, Font.BOLD, 18);
	
	
	public View() {
		prepareGUI();
	}

	private void prepareGUI() {
		
		
		JPanel menuPanel = new JPanel();
		GridLayout menuLayout = new GridLayout(0, 5);
		menuLayout.setHgap(1);
		menuLayout.setVgap(1);
		menuPanel.setLayout(menuLayout);
		menuPanel.setPreferredSize(new Dimension(500, 20));
		
		
		mLast = new JButton("Last");
		mLast.addActionListener(this);
		menuPanel.add(mLast);
		JPanel temp = new JPanel();
		temp.setBackground(Color.WHITE);
		menuPanel.add(temp);
		mMonth = new JLabel(months[currentMonth] + " " + currentYear, SwingConstants.CENTER);
		mMonth.setFont(loginBold);
		menuPanel.add(mMonth);
		JPanel temp2 = new JPanel();
		temp2.setBackground(Color.WHITE);
		menuPanel.add(temp2);
		mNext = new JButton("Next");
		mNext.addActionListener(this);
		menuPanel.add(mNext);
		menuPanel.setBackground(Color.WHITE);
		
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
		dayOfWeekPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		mPanel = new JPanel();
		mPanel.setLayout(new BoxLayout(mPanel, BoxLayout.Y_AXIS));

		mPanel.add(menuPanel, BorderLayout.NORTH);
		mPanel.add(dayOfWeekPanel, BorderLayout.NORTH);
		
		
		initCalendar();
	}

	
	
	public static Event getSelectedEvent() {
		return selectedEvent;
	}

	public static void setSelectedEvent(Event selectedEvent) {
		View.selectedEvent = selectedEvent;
	}
	
	
	
	protected abstract void initCalendar();
	
	protected abstract void updateCalendar();
	
	protected abstract void addPanels();
	
	@Override
	public abstract void actionPerformed(ActionEvent e);
}
