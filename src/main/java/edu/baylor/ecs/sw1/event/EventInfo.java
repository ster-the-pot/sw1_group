package edu.baylor.ecs.sw1.event;

import java.time.Duration;
import java.util.Date;

public class EventInfo {
	String eventName;
	String eventPriority;
	Date eventDate;
	Duration eventDuration;
	String eventDescription;

	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getEventPriority() {
		return eventPriority;
	}
	public void setEventPriority(String eventPriority) {
		this.eventPriority = eventPriority;
	}
	public Date getEventDate() {
		return eventDate;
	}
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	public Duration getEventDuration() {
		return eventDuration;
	}
	public void setEventDuration(Duration eventDuration) {
		this.eventDuration = eventDuration;
	}
	public String getEventDescription() {
		return eventDescription;
	}
	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}
	
	
}
