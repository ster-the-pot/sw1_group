package edu.baylor.ecs.sw1.event;

public class Assignment extends Event {

	@Override
	public void accept(EventVisitor visitor) {
		visitor.visit(this);
	}

}
