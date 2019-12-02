package edu.baylor.ecs.sw1.scheduleRender;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import edu.baylor.ecs.sw1.event.Event;


/**
 * Schedule is used to sort the Events for each day in a specific order. 
 * 
 * @author Elizabeth Brighton
 *
 */
public class Schedule {
	List<Event> events;
	final int numMonth = 4, numWeek = 7;
	
	
	/**
	 * Constructor takes list of events and sorts them by priority 
	 * @param e list of unsorted Events
	 */
	public Schedule(List<Event> e) {
		
		events = new ArrayList<Event>(e);
		
		
		
		events.sort(Comparator.comparing(Event::getEventName));
		
		//reversed may or may not be correct - pretty sure its reversed
		
		//events.sort(Comparator.comparing(Event::getEventPriority).reversed());
	}

	
	
	
	
	
	
	/**
	 * getEventList returns the sorted Events
	 * 
	 * @return
	 */
	public List<Event> getEventList() {
		return events;
	}
	
	/**
	 * getTotal returns the size of the list given
	 * @return
	 */
	public int getTotal() {
		return events.size();
	}

}
