package edu.baylor.ecs.sw1.event;

/**
 * This interface requires its implementations to implement a visit method taking an event.
 * Event has a matching .accept(EventVisitor) method that will call .visit(this) following the
 * Visitor DP
 * @author Par Wilkinson
 *
 */
public interface EventVisitor {
	public void visit(Event event);
}
