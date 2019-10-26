package edu.baylor.ecs.sw1.event;

import java.util.Date;

public class Event {
	String eventName;
	String eventPriority;
	Date startDate;
	Date endDate;
	String eventDescription;
	Boolean completed;

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
