package edu.baylor.ecs.sw1.scheduleRender;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import edu.baylor.ecs.sw1.event.Event;


public class Schedule {
	List<Event> events;
	final int numMonth = 4, numWeek = 7;
	
	public Schedule(Date dayDate) {
		
		events = new ArrayList<Event>();
		
		for(int i = 0; i < 10; i++) {
			Event e = new Event();
			e.setEventName("test: " + i);
			e.setEndDate(new Date());
			events.add(e);
		}
		
		//Query and add all to events......
		/*events = getEvents(day, month, year)*/
		
		
		
		//reversed may or may not be correct - pretty sure its reversed
		
		//events.sort(Comparator.comparing(Event::getEventPriority).reversed());
		
		
		
		
		
	}

	
	
	
	public List<Event> getEventList() {
		return events;
	}
	
	//Set Month view to only return as many as we want in the Month view
	public List<Event> getMonthViewList() {
		if(numMonth <= events.size()) {
			return events.subList(0, numMonth);
		}
		return events;
	}

	public List<Event> getWeekViewList() {
		if(numWeek <= events.size()) {
			return events.subList(0, numWeek);
		}
		return events;
	}
	
	public int getTotal() {
		return events.size();
	}

}
