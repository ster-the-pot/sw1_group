package edu.baylor.ecs.sw1.myday;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

import edu.baylor.ecs.sw1.View.MonthView;
import edu.baylor.ecs.sw1.View.View;
import edu.baylor.ecs.sw1.View.WeekView;
import edu.baylor.ecs.sw1.appCalendar.AppCalendar;
import edu.baylor.ecs.sw1.event.Event;
import edu.baylor.ecs.sw1.event.EventBuilder;
import edu.baylor.ecs.sw1.event.EventBuilderImpl;
import edu.baylor.ecs.sw1.event.EventPriority;


public class ViewTester {
	public static void main(String args[]) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Random r = new Random();
				Calendar date = Calendar.getInstance();
				
				List<Event> events = new ArrayList<Event>();
				for(int i = 0; i < 2000; i++) {

						EventBuilder e = new EventBuilderImpl();
						e.setName("test: " + i);
						
						
						date.set(Calendar.YEAR, 2019 + r.nextInt(2));
						date.set(Calendar.MONTH, r.nextInt(12));
						date.set(Calendar.DAY_OF_MONTH, 1 + r.nextInt(28));
						e.setEndDate(date.getTime());	
						e.setEventCompleted(false);
						e.setEventDescription(" " + i);
						e.setStartDate(date.getTime());
						//System.out.println(e.getEvent().getStartDate().toString());
						
						events.add(e.getEvent());
					
				}
				View.setEvents(events);
				AppCalendar appCalendar = new AppCalendar();
				appCalendar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				appCalendar.setVisible(true);
				
				
				/*JFrame testFrame = new JFrame();
				testFrame.setVisible(true);
				testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				//MonthView view = new MonthView();
				WeekView view = new WeekView();
				testFrame.getContentPane().add(view);
				view.setVisible(true);
				testFrame.pack();*/
			}
		});
	}
}
