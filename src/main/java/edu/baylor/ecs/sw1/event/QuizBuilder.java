package edu.baylor.ecs.sw1.event;

import java.util.Date;

/**
 * Implementation of EventBuilder that creates new Quizzes
 * @author Par Wilkinson
 *
 */
public class QuizBuilder implements EventBuilder {
	Event e;
	
	public QuizBuilder() {
		e = new Quiz();
	}
	
	@Override
	public EventBuilder setName(String eventName) {
		e.setEventName(eventName);
		return this;
	}


	@Override
	public EventBuilder setStartDate(Date startDate) {
		e.setStartDate(startDate);
		return this;
	}

	@Override
	public EventBuilder setEndDate(Date endDate) {
		e.setEndDate(endDate);
		return this;
	}

	@Override
	public EventBuilder setEventDescription(String eventDescription) {
		e.setEventDescription(eventDescription);
		return this;
	}

	@Override
	public EventBuilder setEventCompleted(Boolean completed) {
		e.setCompleted(completed);
		return this;
	}

	@Override
	public Event getEvent() {
		return e;
	}

	@Override
	public EventBuilder setEventID(String eventID) {
		e.setEventID(eventID);
		return this;
	}

	@Override
	public EventBuilder setEventIgnored(Boolean ignored) {
		e.setIgnored(ignored);
		return this;
	}

	@Override
	public EventBuilder setCourse(String course) {
		e.setCourse(course);
		return this;
	}
}