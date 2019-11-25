package edu.baylor.ecs.sw1.event;

public class Quiz extends Event {
	@Override
	public void accept(EventVisitor visitor) {
		visitor.visit(this);
	}
}
