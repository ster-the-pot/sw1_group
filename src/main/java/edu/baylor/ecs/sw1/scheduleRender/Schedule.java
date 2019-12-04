package edu.baylor.ecs.sw1.scheduleRender;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import edu.baylor.ecs.sw1.event.Assignment;
import edu.baylor.ecs.sw1.event.Course;
import edu.baylor.ecs.sw1.event.Event;
import edu.baylor.ecs.sw1.event.Quiz;

/** DESIGN PATTERN VISITOR
 * Schedule is used to sort the Events for each day in a specific order. Uses the
 * Visitor Design pattern to assign Events to specific lists
 * 
 * @author Elizabeth Brighton
 *
 */
public class Schedule {
	List<Event> events;
	List<Event> tests = new ArrayList<Event>();
	List<Event> quizes = new ArrayList<Event>();
	List<Event> assign = new ArrayList<Event>();
	List<Event> course = new ArrayList<Event>();
	List<Event> completed = new ArrayList<Event>();

	/**
	 * Constructor takes list of events and sorts them by priority
	 * 
	 * @param e list of unsorted Events
	 */
	public Schedule(List<Event> e) {


		events = new ArrayList<Event>(e);

		
		checkPriority();
		
		tests.sort(Comparator.comparing(Event::getEndDate));
		quizes.sort(Comparator.comparing(Event::getEndDate));
		assign.sort(Comparator.comparing(Event::getEndDate));
		course.sort(Comparator.comparing(Event::getEndDate));
		completed.sort(Comparator.comparing(Event::getEndDate));
		
		
		events = new ArrayList<Event>(tests);
		events.addAll(quizes);
		events.addAll(assign);
		events.addAll(course);
		events.addAll(completed);
		
		
		// events.sort(Comparator.comparing(Event::getEventName));
		// events.sort(Comparator.comparing(checkPriority(Event)));

		// reversed may or may not be correct - pretty sure its reversed

		// events.sort(Comparator.comparing(Event::getEventPriority).reversed());
	}

	public void checkPriority() {
		for (Event e : events) {


			if (e.getCompleted()) {
				completed.add(e);
			}
			else if (e.getEventName().toLowerCase().contains("test") || e.getEventName().toLowerCase().contains("exam")
					|| e.getEventName().toLowerCase().contains("midterm")) {
				tests.add(e);
				
			} else {
				e.accept(this);	
			}
			
		}

	}

	/**
	 * getEventList returns the sorted Events
	 * 
	 * @return
	 */
	public List<Event> getEventList() {
		return events;
	}


	/**
	 * getTotal returns the size of the list given
	 * 
	 * @return
	 */
	public int getTotal() {
		return events.size();
	}

	/**
	 * Visitor Method with Event - Subtype: Assignment
	 * 
	 * @param a Event Subtype Assignment
	 * @return
	 */
	public void getValue(Assignment a) {
		assign.add(a);
	}

	/**
	 * Visitor Method with Event - Subtype: Quiz
	 * 
	 * @param q Event Subtype Quiz
	 * @return
	 */
	public void getValue(Quiz q) {
		quizes.add(q);
	}

	/**
	 * Visitor Method with Event - Subtype: Course
	 * 
	 * @param c Event Subtype Course
	 * @return
	 */
	public void getValue(Course c) {
		course.add(c);
	}

}
