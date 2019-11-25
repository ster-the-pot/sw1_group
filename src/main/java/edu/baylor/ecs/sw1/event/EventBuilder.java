package edu.baylor.ecs.sw1.event;

import java.util.Date;

public interface EventBuilder {
	public void setName(String eventName);
	public void setEventPriority(EventPriority eventPriority);
	public void setStartDate(Date startDate);
	public void setEndDate(Date endDate);
	public void setEventDescription(String eventDescription);
	public void setEventCompleted(Boolean completed);
	
	public Event getEvent();
}
