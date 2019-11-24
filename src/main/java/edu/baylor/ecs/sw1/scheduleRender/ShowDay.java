package edu.baylor.ecs.sw1.scheduleRender;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import edu.baylor.ecs.sw1.View.View;
import edu.baylor.ecs.sw1.event.Event;

/**
 * 
 * @author Elizabeth Brighton
 *
 */
public class ShowDay extends JPanel implements ActionListener {
	Event currentSelected;
	static int num;
	static String[] monthsAbv = { "Jan", "Feb", "March", "April", "May", "June", "July", "Aug", "Sept", "Oct", "Nov",
			"Dec" };
	JLabel label;
	static JPanel dayPanel;
	Color blueColor = new Color(64, 143, 222);
	Color redColor = new Color(180, 63, 63);
	Schedule schedule;
	List<Event> events;
	Boolean isWeek = false;
	final int numMonth = 5, numWeek = 8;
	String leftOver = null;
	/* Month View Constructor */
	public ShowDay(int day, int month, int year) {

		// Day of the Month
		//label = new JLabel(" " + day);
		this.setLayout(new GridLayout(numMonth, 0));
		//this.add(label);

		String title = " " + day;
		Border border = BorderFactory.createLineBorder(Color.BLACK, -3);
		Border border2 = BorderFactory.createTitledBorder(border, title);
		this.setBorder(border2);
		
		
		schedule = new Schedule(day, month, year);
		events = schedule.getMonthViewList();
		renderEvents();

	}

	/* Week View Constructor */
	public ShowDay(int day, int month, int year, Boolean isOtherMonth) {

		isWeek = true;
		String title = " " + day;
		// Day of the Week
		if (isOtherMonth) {
			label = new JLabel(" " + monthsAbv[month] + ": " + day);
			title = " " + monthsAbv[month] + ": " + day;
		} else {
			label = new JLabel(" " + day);
		}

		this.setLayout(new GridLayout(numWeek, 0));
		//this.add(label);
		
		Border border = BorderFactory.createLineBorder(Color.BLACK, -3);
		Border border2 = BorderFactory.createTitledBorder(border, title);
		this.setBorder(border2);
		
		
		schedule = new Schedule(day, month, year);
		events = schedule.getWeekViewList();
		renderEvents();
	}

	private void renderEvents() {

		//this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

		JButton Eventlabel;
		for (Event e : events) {

			if (isWeek) {
				Eventlabel = new JButton(
						e.getEventName() + " " + (new SimpleDateFormat("hh:mmaa").format(e.getEndDate())));
			} else {
				Eventlabel = new JButton(e.getEventName());
			}

			// Change to suit priority level
			// if(e.getEventPriority())
			// if(new SimpleDateFormat("HH:mm a").format(e.getEndDate()) != "11:59 pm") {

			if (e.getEventName().contains("2")) {
				Eventlabel.setBackground(redColor);
				// Eventlabel.setForeground(Color.WHITE);
			} else {
				Eventlabel.setBackground(blueColor);
			}

			Eventlabel.addActionListener(this);
			Eventlabel.setHorizontalAlignment(JLabel.CENTER);
			Eventlabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			
			this.add(Eventlabel);
			
		}

		checkEventSize();

	}

	private void checkEventSize() {
		if ((num = schedule.getTotal()) > events.size()) {
			leftOver = " " + (num - events.size()) + " More Events";
			JButton label1 = new JButton(leftOver);
			this.add(label1);
			label1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			label1.addActionListener(this);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		System.out.println(leftOver);
		System.out.println(e.getActionCommand());
		
		if(leftOver.equals(e.getActionCommand())) {
			
			//DISPLAY THE REST OF THE EVENTS SOME HOW...... IF TIME
			System.out.println("HERE");
		
			
			ExtraEvents viewExtra = new ExtraEvents(schedule.getEventList());
			currentSelected = viewExtra.getEvent();
			
			
		} else {
			for (Event ev : events) {
				if ((ev.getEventName().equals(e.getActionCommand()))
					|| (ev.getEventName() + " " + (new SimpleDateFormat("hh:mmaa").format(ev.getEndDate())))
							.equals(e.getActionCommand())) {
				
					
					Event EventTemp = new Event();
					currentSelected = EventTemp;
				}
			}
		}
		
		// Kinda bad to call View here - but it would make sure there is always only one
		// selected event
		View.setSelectedEvent(currentSelected);
	}

	// returns the EventID of the currently selected Event
	Event getCurrentEvent() {
		return currentSelected;
	}

}


