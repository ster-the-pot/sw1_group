package edu.baylor.ecs.sw1.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.bson.Document;

import edu.baylor.ecs.sw1.appCalendar.AppCalendar;
import edu.baylor.ecs.sw1.canvas.DatabaseConnector;
import edu.baylor.ecs.sw1.event.AssignmentBuilder;
import edu.baylor.ecs.sw1.event.Event;
import edu.baylor.ecs.sw1.event.EventBuilder;
import edu.baylor.ecs.sw1.event.QuizBuilder;
import edu.baylor.ecs.sw1.scheduleRender.Schedule;
import edu.baylor.ecs.sw1.scheduleRender.ShowDay;


/** DESIGN PATTERN TEMPLATE METHOD
 * View is parent to MonthView and WeekView (Template Method). Each child
 * uses ShowDay in order to render events for a specific day
 * 
 * @author Elizabeth Brighton
 *
 */
public abstract class View extends JPanel implements ActionListener{
	String[] dayHeader = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
	String[] months = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
			"October", "November", "December" };
	
	static List<Event> events = new ArrayList<Event>();
	Event selectedEvent = null;
	JPanel selectPanel;
	JLabel selected;
	JLabel mMonth, mDay;
	JButton mLast, mNext;
	JPanel mPanel;
	int currentYear, currentMonth;
	Date dayDate;
	
	//static JPanel panel;
	GregorianCalendar cal;
	Font loginBold = new Font(Font.SANS_SERIF, Font.BOLD, 18);
	
	/**
	 * Constructor calls the prepareGUI method
	 */
	public View() {
		prepareGUI();
	}
	
	/**
	 * Takes all the events specific to the user from 
	 * the database and places them into an ArrayList.
	 */
	public static void pullEventsFromDatabase() {
		
		DatabaseConnector database = new DatabaseConnector("java","userdata","cerny");
		ArrayList<Document> userEvents = database.getUserEvents(AppCalendar.userName);
				
		events = new ArrayList<>();
				
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		
		SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		formatter2.setTimeZone(TimeZone.getTimeZone("CST"));
		
		

		for(Document eventDoc : userEvents) {
			String eventName = (String) eventDoc.get("name");
					

			Double eventID = (Double) eventDoc.get("id");
			Boolean ignored = (Boolean) eventDoc.get("ignore");
			Boolean completed = (Boolean) eventDoc.get("completed");
			String dueTimeStamp = (String) eventDoc.get("due_at");
			String course = (String) eventDoc.get("course");
			
			
			String eventDescription = "";
			if(eventDoc.containsKey("description")) {
				eventDescription = (String) eventDoc.get("description");
			}
					
			if(eventName == null || eventID == null || dueTimeStamp == null) {

				continue;
			}
					
			String lowered = eventName.toLowerCase();
					
			EventBuilder eventBuilder = null;
			if(lowered.contains("exam") || lowered.contains("midterm") || lowered.contains("quiz")) {
				eventBuilder = new QuizBuilder();
			} else {
				eventBuilder = new AssignmentBuilder();
			}
					
			eventBuilder.setName(eventName)
			.setEventCompleted(completed)
			.setEventIgnored(ignored)
			.setEventDescription(eventDescription)
			.setEventID(eventID).setCourse(course);
					
			Date dueDate = null;
			try {
				dueDate = formatter.parse(dueTimeStamp);
				String d = formatter2.format(dueDate);
				dueDate = formatter2.parse(d);
			} catch (ParseException e) {
				e.printStackTrace();
			}
					
			eventBuilder.setEndDate(dueDate);
					
			Event event = eventBuilder.getEvent();
			events.add(event);
		}
				
		events.sort(Comparator.comparing(Event::getEndDate).reversed());
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

	
	
	public Event getSelectedEvent() {
		return selectedEvent;
	}

	/**
	 * setSelectedEvent gets the current selected event and
	 * renders it using the initSelect Method
	 * 
	 * @param e Is the new selected Event to replace the old selected Event
	 */
	public void setSelectedEvent(Event e) {
		selectedEvent = e;
		initSelect();
	}
	
	
	public static List<Event> getEvents() {
		return events;
	}

	public static void setEvents(List<Event> e) {
		events = e;
	}
	
	/**
	 * getDayEvents returns the events specifically the day given.
	 * This is used by ShowDay to display the events.
	 * @param d Date used to find the Events specifically on that day. 
	 * @return
	 */
	protected List<Event> getDayEvents(Date d) {
			
		Calendar calendar = Calendar.getInstance();
		List<Event>  temp = new ArrayList<Event>();
		Boolean found1 = false;
		for(Event e: events) {
			
			calendar.setTime(e.getEndDate());
			if(d.getYear() == calendar.get(Calendar.YEAR) && d.getMonth() == calendar.get(Calendar.MONTH)
					&& d.getDate() == calendar.get(Calendar.DAY_OF_MONTH)) {
				temp.add(e);
				found1 = true;
			} else if (found1) {
				break;
			}
		}
		
		Schedule s = new Schedule(temp);
		
		temp = s.getEventList();
		return temp;
	}
	
	/**
	 * Displays the currently selected Event below the Calendar
	 */
	protected void initSelect() {
		
		if(selected != null) {
			selectPanel.setVisible(false);
			selected.setVisible(false);
		}
		selectPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		//selectPanel = new JPanel();
		if(selectedEvent != null)
			selected = new JLabel("Selected Event: " + selectedEvent.getEventName() + ", due: " 
					+ selectedEvent.getEndDate().toString(), SwingConstants.LEFT);
		else 
			selected = new JLabel("No Event Selected", SwingConstants.LEFT);
		
		selected.setVisible(true);
		selectPanel.add(selected);
		
		
		selectPanel.setBackground(Color.WHITE);
		
		mPanel.add(selectPanel);
		
	}
	
	/**
	 * Is called when an Event is edited
	 * 
	 */
	public void refreshEvent(/*Event eOld, Event e*/) {
		
		
		for(Event e: events) {
			if(e.getEventID().equals(selectedEvent.getEventID())) {
				e.setEventName(selectedEvent.getEventName());
				e.setCompleted(selectedEvent.getCompleted());
				e.setEndDate(selectedEvent.getEndDate());
				e.setEventDescription(selectedEvent.getEventDescription());
				e.setIgnored(selectedEvent.getIgnored());
				e.setStartDate(selectedEvent.getStartDate());
				e.setCourse(selectedEvent.getCourse());
			}
		}
		updateCalendar();
		
	}
	
	
	/**
	 * Is called when an Event is created
	 * 
	 */
	public void createEvent(Event e) {
		
		events.add(e);
		
		updateCalendar();
		
	}
	
	/**
	 * Is called when an Event is deleted
	 * 
	 */
	public void removeEvent(Event e) {
		
		
		events.remove(e);
		
		updateCalendar();
		
	}
	
	/**
	 * Is called when an Event is completed
	 * 
	 */
	public void completeEvent(Event e) {
		
		events.get(events.indexOf(e)).setCompleted(true);
		
		
		updateCalendar();
		
	}

	
	protected abstract void initCalendar();
	/**
	 * updateCalendar is used when an Event has been changed or a new View is selected
	 * using the Last/Next options. It refreshes the entire Month/Week using the addPanels function below
	 */
	public abstract void updateCalendar();
	
	protected abstract void addPanels();
	
	@Override
	public abstract void actionPerformed(ActionEvent e);
	
	/**
	 * Visitor. Accepts a ShowDay object in order for ShowDay to add different
	 * amounts of Events per day, depending on the View
	 * @param sd
	 * @return
	 */
	public abstract int accept(ShowDay sd);
}
