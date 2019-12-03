package edu.baylor.ecs.sw1.event;

import java.util.Date;

/**
 * EventBuilder is an interface for an object that builds an Event of some subtype, setting its necessary fields.
 * @author Par Wilkinson
 *
 */
public interface EventBuilder {
	public void setName(String eventName);
	public void setStartDate(Date startDate);
	public void setEndDate(Date endDate);
	public void setEventDescription(String eventDescription);
	public void setEventCompleted(Boolean completed);
	public void setEventID(String eventID);
	public void setEventIgnored(Boolean ignored);
	
	public Event getEvent();
}
