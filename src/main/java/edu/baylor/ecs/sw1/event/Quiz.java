package edu.baylor.ecs.sw1.event;

import java.awt.Color;

import edu.baylor.ecs.sw1.scheduleRender.ShowDay;

public class Quiz extends Event {
	@Override
	public void accept(EventVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public Color accept(ShowDay sd) {
		return sd.getColor(this);
	}
}
