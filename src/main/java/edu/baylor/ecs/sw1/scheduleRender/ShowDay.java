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
import edu.baylor.ecs.sw1.View.MonthView;
import edu.baylor.ecs.sw1.View.View;
import edu.baylor.ecs.sw1.View.WeekView;
import edu.baylor.ecs.sw1.event.Assignment;
import edu.baylor.ecs.sw1.event.Course;
import edu.baylor.ecs.sw1.event.Event;
import edu.baylor.ecs.sw1.event.Quiz;

/**
 * DESIGN PATTERN VISITOR ShowDay is called for each specific day. It takes a
 * list of the Events on that specific day and displays the number of Events the
 * Month/Week view is able to display. ShowDay uses visitor in order to
 * determine Which View is being used and What Event subtype is used for the
 * color
 * 
 * @author Elizabeth Brighton
 *
 */
public class ShowDay extends JPanel implements ActionListener {
	Event currentSelected;
	static int num;
	static String[] monthsAbv = { "Jan", "Feb", "March", "April", "May", "June", "July", "Aug", "Sept", "Oct", "Nov",
			"Dec" };
	static JPanel dayPanel;

	Color currentColor;
	Color currentColor2;

	Color blueColor = new Color(0, 112, 192);
	Color redColor = new Color(192, 0, 0);
	Color purpleColor = new Color(112, 48, 160);
	Color greenColor = new Color(79, 98, 40);

	Color blueColor2 = new Color(218, 238, 243);
	Color redColor2 = new Color(242, 220, 219);
	Color purpleColor2 = new Color(204, 192, 218);
	Color greenColor2 = new Color(216, 228, 188);

	List<Event> events;
	Boolean isWeek = false;
	final int numMonth = 5, numWeek = 8;
	int numEvents = 0;
	String leftOver = null;
	View parentV;

	/**
	 * The Constructor takes a list of Events for each day and displays the amount
	 * of Events each day can handle, depending on the specific implementation of
	 * View (Month or Week). This is determined by a visitor using the accept
	 * function on View. Sorting the events is done using the Schedule class before
	 * the events are given to the ShowDay class.
	 * 
	 * @param dayDate      Gives the date of the current day
	 * @param e            Gives all the Events that are scheduled for that day.
	 *                     Already scheduled using the Schedule class
	 * @param isOtherMonth Is used when a specific day of the week is in the
	 *                     next/last Month.
	 * @param v            Gives the specific type of view (this) in order to update
	 *                     the Current Selected Event and use the visitor
	 * @return
	 */
	public ShowDay(Date dayDate, List<Event> e, Boolean isOtherMonth, View v) {

		parentV = v;

		events = new ArrayList<Event>(e);

		numEvents = v.accept(this) - 1;
		if (events.size() < numEvents) {
			numEvents = events.size();
		}

		String title = " " + dayDate.getDate();
		if (isOtherMonth) {
			title = " " + monthsAbv[dayDate.getMonth()] + ": " + dayDate.getDate();
		}

		this.setLayout(new GridLayout(v.accept(this), 0));

		Border border = BorderFactory.createLineBorder(Color.BLACK, -3);
		Border border2 = BorderFactory.createTitledBorder(border, title);
		this.setBorder(border2);

		renderEvents();

	}

	/**
	 * RenderEvents adds the number of Events needed for the specific view to the
	 * day panel
	 * 
	 * @param
	 * @return
	 */
	private void renderEvents() {

		JButton Eventlabel = null;

		for (int i = 0; i < numEvents; i++) {
			addEvent(Eventlabel, i);
		}

		checkEventSize();

	}

	/**
	 * checkEventSize compares the max display number of Month/Week with the number
	 * of Events for each day. If the number of events is greater than the
	 * ExtraEvents class is called which creates the option of viewing all Events
	 * for the specific day.
	 * 
	 * @param
	 * @return
	 */
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

	/**
	 * addEvent is called for each Event to be displayed on the day It gets the
	 * event information needed, including the color for each Type of event using a
	 * Visitor.
	 * 
	 * @param Eventlabel
	 * @param i
	 */
	private void addEvent(JButton Eventlabel, int i) {

		Event e = events.get(i);

		if (isWeek) {
			Eventlabel = new JButton(e.getEventName() + " " + (new SimpleDateFormat("hh:mmaa").format(e.getEndDate())));
		} else {
			Eventlabel = new JButton(e.getEventName());
		}

		Eventlabel.setForeground(e.accept(this));
		Eventlabel.setBackground(currentColor2);

		// Eventlabel.setBackground(Color.WHITE);
		if (e.getCompleted()) {

			Eventlabel = new JButton("Complete! " + e.getEventName());
			Eventlabel.setBackground(Color.LIGHT_GRAY);
			Eventlabel.setForeground(Color.BLACK);
		} else if (e.getEventName().toLowerCase().contains("test") || e.getEventName().toLowerCase().contains("exam")
				|| e.getEventName().toLowerCase().contains("midterm")
				|| e.getEventName().toLowerCase().contains("mid-term")) {
			Eventlabel.setBackground(redColor2);
			Eventlabel.setForeground(redColor);
		}
		// For testing purposes
		/*
		 * else if (e.getEventName().substring(e.getEventName().length() -
		 * 1).equals("8") || e.getEventName().substring(e.getEventName().length() -
		 * 1).equals("9")) { Eventlabel.setBackground(greenColor2);
		 * Eventlabel.setForeground(greenColor);
		 * 
		 * } else if (e.getEventName().substring(e.getEventName().length() -
		 * 1).equals("4") || e.getEventName().substring(e.getEventName().length() -
		 * 1).equals("5")) { Eventlabel.setBackground(blueColor2);
		 * Eventlabel.setForeground(blueColor); }
		 */

		Eventlabel.addActionListener(this);
		Eventlabel.setHorizontalAlignment(JLabel.CENTER);
		Eventlabel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

		this.add(Eventlabel);
	}

	/**
	 * This is called when an event is selected or when a request to view all events
	 * for that day is made
	 * 
	 */
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
		parentV.setSelectedEvent(currentSelected);
	}

	/**
	 * Visitor Method with Event - Subtype: Assignment
	 * 
	 * @param a
	 * @return
	 */
	public Color getColor(Assignment a) {
		currentColor = purpleColor;
		currentColor2 = purpleColor2;
		return purpleColor;
	}

	/**
	 * Visitor Method with Event - Subtype: Course
	 * 
	 * @param a
	 * @return
	 */
	public Color getColor(Course c) {
		currentColor = blueColor;
		currentColor2 = blueColor2;
		return blueColor;
	}

	/**
	 * Visitor Method with Event - Subtype: Quiz
	 * 
	 * @param a
	 * @return
	 */
	public Color getColor(Quiz q) {
		currentColor = greenColor;
		currentColor2 = greenColor2;
		return greenColor;
	}

	/**
	 * Visitor Method with View - Subtype: MonthView
	 * 
	 * @param v
	 * @return
	 */
	public int numGrid(MonthView v) {
		return numMonth;
	}

	/**
	 * Visitor Method with View - Subtype: WeekView
	 * 
	 * @param v
	 * @return
	 */
	public int numGrid(WeekView v) {
		isWeek = true;
		return numWeek;
	}

	/**
	 * Returns the currently selected event. This is called by the action performed
	 * override
	 * 
	 * @return currentSelected The currently selected event
	 */
	public Event getCurrentEvent() {
		return currentSelected;
	}

}
