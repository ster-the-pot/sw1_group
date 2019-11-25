package edu.baylor.ecs.sw1.scheduleRender;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import edu.baylor.ecs.sw1.event.Event;


public class Schedule {
	List<Event> events;
	final int numMonth = 4, numWeek = 7;
	
	public Schedule(List<Event> e) {
		
		events = new ArrayList<Event>(e);
		
		
		
		events.sort(Comparator.comparing(Event::getEventName));
		
		
		
		
		
		//reversed may or may not be correct - pretty sure its reversed
		
		//events.sort(Comparator.comparing(Event::getEventPriority).reversed());
	}

	
	
	
	
	
	
	
	public List<Event> getEventList() {
		return events;
	}
	
	
	public int getTotal() {
		return events.size();
	}

}
