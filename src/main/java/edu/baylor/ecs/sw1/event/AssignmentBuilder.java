package edu.baylor.ecs.sw1.event;

import java.util.Date;

/**
 * Implementation of EventBuilder that creates new Assignments
 * @author Par Wilkinson
 *
 */
public class EventBuilderImpl implements EventBuilder {
	Event e;
	
	public EventBuilderImpl() {
		//e = new Quiz();
		e = new Assignment();
	}
	
	@Override
	public void setName(String eventName) {
		e.setEventName(eventName);
	}


	@Override
	public void setStartDate(Date startDate) {
		e.setStartDate(startDate);
	}

	@Override
	public void setEndDate(Date endDate) {
		e.setEndDate(endDate);
	}

	@Override
	public void setEventDescription(String eventDescription) {
		e.setEventDescription(eventDescription);
	}

	@Override
	public void setEventCompleted(Boolean completed) {
		e.setCompleted(completed);
	}

	@Override
	public Event getEvent() {
		return e;
	}

	@Override
	public void setEventID(String eventID) {
		e.setEventID(eventID);
	}

	@Override
	public void setEventIgnored(Boolean ignored) {
		e.setIgnored(ignored);
	}

}
