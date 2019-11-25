package edu.baylor.ecs.sw1.event;

public class Course extends Event {
	@Override
	public void accept(EventVisitor visitor) {
		visitor.visit(this);
	}
}
