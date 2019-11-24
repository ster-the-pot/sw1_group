package edu.baylor.ecs.sw1.event;

import java.util.Date;


/**
 * 
 * @author Par Wilkinson
 *
 */
public class Event {
	
	String eventName;
	String eventID;
	String eventDescription;
	EventPriority Priority;
	Date startDate;
	Date endDate;
	Boolean completed;

	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public EventPriority getEventPriority() {
		return Priority;
	}
	public void setEventPriority(EventPriority eventPriority) {
		this.Priority = eventPriority;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getEventDescription() {
		return eventDescription;
	}
	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}
	public Boolean getCompleted() {
		return completed;
	}
	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}
	
}
