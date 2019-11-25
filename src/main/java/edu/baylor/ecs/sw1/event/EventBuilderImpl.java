package edu.baylor.ecs.sw1.event;

import java.util.Date;

public class EventBuilderImpl implements EventBuilder {
	Event e;
	
	public EventBuilderImpl() {
		e = new Event();
	}
	
	@Override
	public void setName(String eventName) {
		e.setEventName(eventName);
	}

	@Override
	public void setEventPriority(EventPriority eventPriority) {
		e.setEventPriority(eventPriority);
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

}
