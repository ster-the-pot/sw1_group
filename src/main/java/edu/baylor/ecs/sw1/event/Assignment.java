package edu.baylor.ecs.sw1.event;

import java.awt.Color;

import edu.baylor.ecs.sw1.scheduleRender.Schedule;
import edu.baylor.ecs.sw1.scheduleRender.ShowDay;

/**
 * Subclass of Event pertaining to most events
 * 
 * @author Par Wilkinson
 *
 */
public class Assignment extends Event {
	@Override
	public void accept(EventVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public Color accept(ShowDay sd) {
		return sd.getColor(this);
	}

	@Override
	public void accept(Schedule sh) {
		sh.getValue(this);
	}

}
