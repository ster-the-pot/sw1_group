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


/**
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
	 * @param 
	 * @return
	 */
	public View() {
		prepareGUI();
	}

	
	/**
	 * prepareGUI() gets the Events for the specific student
	 * and creates the basic Calendar view outline
	 * @param 
	 * @return
	 */
	private void prepareGUI() {
		
		//QUERY HERE!!!!!!
		DatabaseConnector database = new DatabaseConnector("java","userdata","cerny");
		ArrayList<Document> userEvents = database.getUserEvents(AppCalendar.userName);
		
		List<Event> events = new ArrayList<>();
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		
		for(Document eventDoc : userEvents) {
			String eventName = (String) eventDoc.get("name");
			

			String eventID = (String) eventDoc.get("id");
			Boolean ignored = (Boolean) eventDoc.get("ignore");
			Boolean completed = (Boolean) eventDoc.get("completed");
			String dueTimeStamp = (String) eventDoc.get("due_at");
			
			String eventDescription = "";
			if(eventDoc.containsKey("description")) {
				eventDescription = (String) eventDoc.get("description");
			}
			
			if(eventName == null || eventID == null || completed == null || dueTimeStamp == null) {
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
			.setEventID(eventID);
			
			Date dueDate = null;
			try {
				dueDate = formatter.parse(dueTimeStamp);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			eventBuilder.setEndDate(dueDate);
			
			Event event = eventBuilder.getEvent();
			events.add(event);
		}
		
		View.setEvents(events);
		
		events.sort(Comparator.comparing(Event::getEndDate).reversed());
		
		
		
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
	 * @param 
	 * @return
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
		//selected.setOpaque(true);
		//selected.setBackground(Color.WHITE);
		selectPanel.add(selected);
		
		
		selectPanel.setBackground(Color.WHITE);
		
		mPanel.add(selectPanel);
		
	}
	
	/**
	 * Is called when an Event is edited, created or deleted
	 * 
	 * @param eOld Old Event that is no longer up do date.
	 * @param e New Event to replace the old Event
	 */
	public void refreshEvent(Event eOld, Event e) {
		
		events.set(events.indexOf(eOld), e);
		
		updateCalendar();
		
	}
	
	public void createEvent(Event e) {
		
		events.add(e);
		
		updateCalendar();
		
	}
	
	public void removeEvent(Event e) {
		events.remove(e);
		
		updateCalendar();
		
	}

	
	protected abstract void initCalendar();
	
	protected abstract void updateCalendar();
	
	protected abstract void addPanels();
	
	@Override
	public abstract void actionPerformed(ActionEvent e);
	
	
	public abstract int accept(ShowDay sd);
}
