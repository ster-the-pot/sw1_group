package edu.baylor.ecs.sw1.event;

import java.util.Date;

/**
 * EventBuilder is an interface for an object that builds an Event of some subtype, setting its necessary fields.
 * @author Par Wilkinson
 *
 */
public interface EventBuilder {
	public EventBuilder setName(String eventName);
	public EventBuilder setStartDate(Date startDate);
	public EventBuilder setEndDate(Date endDate);
	public EventBuilder setEventDescription(String eventDescription);
	public EventBuilder setEventCompleted(Boolean completed);
	public EventBuilder setEventID(String eventID);
	public EventBuilder setCourse(String course);
	public EventBuilder setEventIgnored(Boolean ignored);
	
	public Event getEvent();
}
