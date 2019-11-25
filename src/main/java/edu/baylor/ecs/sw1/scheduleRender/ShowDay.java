package edu.baylor.ecs.sw1.scheduleRender;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import edu.baylor.ecs.sw1.View.View;
import edu.baylor.ecs.sw1.event.Assignment;
import edu.baylor.ecs.sw1.event.Course;
import edu.baylor.ecs.sw1.event.Event;
import edu.baylor.ecs.sw1.event.Quiz;

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
	//Color redColor = new Color(180, 63, 63);
	Color redColor = new Color(205,65,65);
	Color purpleColor = new Color(186,85,211);
	Color greenColor = new Color(50,205,50);
	
	// Schedule schedule;
	List<Event> events;
	Boolean isWeek = false;
	final int numMonth = 5, numWeek = 8;
	int numEvents = 0;
	String leftOver = null;

	/* Month View Constructor */
	public ShowDay(int day, Date dayDate, List<Event> e) {

		numEvents = numMonth - 1;
		events = (new Schedule(e)).getEventList(); //new ArrayList<Event>(e);
		if (events.size() < numEvents) {
			numEvents = events.size();
		}

		// Day of the Month
		// label = new JLabel(" " + day);
		this.setLayout(new GridLayout(numMonth, 0));
		// this.add(label);

		String title = " " + day;
		Border border = BorderFactory.createLineBorder(Color.BLACK, -3);
		Border border2 = BorderFactory.createTitledBorder(border, title);
		this.setBorder(border2);

		renderEvents();

	}

	/* Week View Constructor */
	public ShowDay(int day, int month, Date dayDate, List<Event> e, Boolean isOtherMonth) {

		isWeek = true;
		events = (new Schedule(e)).getEventList(); // ArrayList<Event>(e);
		numEvents = numWeek - 1;
		if (events.size() < numEvents) {
			numEvents = events.size();
		}

		String title = " " + day;
		// Day of the Week
		if (isOtherMonth) {
			label = new JLabel(" " + monthsAbv[month] + ": " + day);
			title = " " + monthsAbv[month] + ": " + day;
		} else {
			label = new JLabel(" " + day);
		}

		this.setLayout(new GridLayout(numWeek, 0));
		// this.add(label);

		Border border = BorderFactory.createLineBorder(Color.BLACK, -3);
		Border border2 = BorderFactory.createTitledBorder(border, title);
		this.setBorder(border2);

		renderEvents();
	}

	private void renderEvents() {

		//this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

		JButton Eventlabel = null;

		for (int i = 0; i < numEvents; i++) {
			addEvent(Eventlabel, i);
		}

		checkEventSize();

	}

	private void checkEventSize() {
		if ((num = events.size()) > numEvents) {
			if (num - numEvents == 1) {
				addEvent(new JButton(), numEvents);
				numEvents++;
			} else {
				leftOver = " " + (num - numEvents) + " More Events";
				JButton label1 = new JButton(leftOver);
				this.add(label1);
				label1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				label1.addActionListener(this);
			}

		}
	}

	private void addEvent(JButton Eventlabel, int i) {

		Event e = events.get(i);
		if (isWeek) {
			Eventlabel = new JButton(e.getEventName() + " " + (new SimpleDateFormat("hh:mmaa").format(e.getEndDate())));
		} else {
			Eventlabel = new JButton(e.getEventName());
		}

		// Change to suit priority level
		// if(e.getEventPriority())
		// if(new SimpleDateFormat("HH:mm a").format(e.getEndDate()) != "11:59 pm") {
		Eventlabel.setBackground(e.accept(this));
		
		if (e.getEventName().toLowerCase().contains("test") 
				|| e.getEventName().toLowerCase().contains("exam") || e.getEventName().toLowerCase().contains("midterm")) {
			Eventlabel.setBackground(redColor);
			
		} 

		Eventlabel.addActionListener(this);
		Eventlabel.setHorizontalAlignment(JLabel.CENTER);
		Eventlabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		this.add(Eventlabel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (leftOver != null && leftOver.equals(e.getActionCommand())) {
			ExtraEvents viewExtra = new ExtraEvents(events);
			currentSelected = viewExtra.getEvent();

		} else {
			for (Event ev : events) {
				if ((ev.getEventName().equals(e.getActionCommand()))
						|| (ev.getEventName() + " " + (new SimpleDateFormat("hh:mmaa").format(ev.getEndDate())))
								.equals(e.getActionCommand())) {

					currentSelected = ev;
				}
			}
		}

		// Kinda bad to call View here - but it would make sure there is always only one
		// selected event
		View.setSelectedEvent(currentSelected);
	}

	
	public Color getColor(Assignment a) {
		return purpleColor;
		//return redColor;
		//return new Color(64, 143, 222);
	}
	public Color getColor(Course c) {
		return blueColor;
		//return new Color(64, 143, 222);
	}
	public Color getColor(Quiz q) {
		 
		return greenColor;
	}
	
	
	
	
	// returns the EventID of the currently selected Event
	Event getCurrentEvent() {
		return currentSelected;
	}

}
