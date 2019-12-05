package edu.baylor.ecs.sw1.myday;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.swing.JFrame;
import edu.baylor.ecs.sw1.View.View;
import edu.baylor.ecs.sw1.appCalendar.AppCalendar;
import edu.baylor.ecs.sw1.event.Event;
import edu.baylor.ecs.sw1.event.EventBuilder;
import edu.baylor.ecs.sw1.event.AssignmentBuilder;


/**
 * Tests the AppCalendar Class by giving View 2000 randomly generated Events
 * 
 * @author Elizabeth Brighton
 *
 */
public class ViewTester {
	 static String str = "abcdefghijklmnopqrstuvxyz"; 
	 
	 /**
	  * Main class creates the 2000 events and passes them to the View before init of AppCalendar
	  * @param args
	  */
	public static void main(String args[]) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Random r = new Random();
				Calendar date1 = Calendar.getInstance();
				Calendar date2 = Calendar.getInstance();
				
				List<Event> events = new ArrayList<Event>();
				for (int i = 0; i < 2000; i++) {

					EventBuilder e = new AssignmentBuilder();
					if (i < 500) {
						e.setName("test:" + i);	
					} else {
						StringBuilder sb = new StringBuilder(6); 
						for (int j = 0; j < 6; j++) { 
				           int index= (int)(str.length() 
				                        * Math.random()); 
				            sb.append(str.charAt(index)); 
				        } 
						e.setName(sb.toString() + ":" + i);
						
					}

					date1.set(Calendar.YEAR, 2019 + r.nextInt(2));
					date1.set(Calendar.MONTH, r.nextInt(12));
					date1.set(Calendar.DAY_OF_MONTH, 1 + r.nextInt(28));
					date1.set(Calendar.HOUR_OF_DAY, 1 + r.nextInt(24));


					e.setEventCompleted(false);
					e.setEventDescription(" " + i);
					e.setStartDate(date1.getTime());
					date2 = date1;
					date2.add(Calendar.HOUR_OF_DAY, r.nextInt(3));
					
					e.setEndDate(date2.getTime());

					events.add(e.getEvent());

				}
				View.setEvents(events);
				AppCalendar.setUserName("STERLING");
				AppCalendar appCalendar = new AppCalendar();
				appCalendar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				appCalendar.setVisible(true);

				/*
				 * JFrame testFrame = new JFrame(); testFrame.setVisible(true);
				 * testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				 * 
				 * //MonthView view = new MonthView(); WeekView view = new WeekView();
				 * testFrame.getContentPane().add(view); view.setVisible(true);
				 * testFrame.pack();
				 */
			}
		});
	}
}
