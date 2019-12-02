package edu.baylor.ecs.sw1.scheduleRender;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import edu.baylor.ecs.sw1.event.Event;


/**
 * ExtraEvents class is used with not all of the Events for a specific day can be displayed 
 * in the specific view.  
 * 
 * @author Elizabeth Brighton
 *
 */
public class ExtraEvents {
	List<Event> events = null;
	String[] eventName = null;
	int index = -1;
	Event selected = null;
	
	/**
	 * Constructor takes a list of events and displays them in a JOptionPane.
	 * 
	 * @param e list of events on a specific day
	 */
	ExtraEvents(List<Event> e) {
		events = new ArrayList<Event>(e);
		
		eventName = new String[events.size()];

		
		for(int i = 0; i < events.size(); i++) {
			eventName[i] = events.get(i).getEventName();
		}
		

		//ISSUES WITH DUPLICATE NAMES
	   /* String name = (String) JOptionPane.showInputDialog(frame, "Which event would you like?",
	    		"Select event, then select option from side panel", JOptionPane.PLAIN_MESSAGE,
	        null, eventName, "Which Event would you like?");
	    
	    Event []str = events.stream().filter(ev -> ev.getEventName() == name).toArray(Event[]::new);
	    
	    //If duplicate names - will pick first one
	    if(str.length > 0) 
	    	index = events.indexOf(str[0]);
	    else {
	    	index = -1;
	    }*/
	    
	    
	
	    

		index = JOptionPane.showOptionDialog(null,
		    "Pick Event - Then select option on left panel of Calander View",
		    "Which Event would you like?",
		    JOptionPane.DEFAULT_OPTION,
		    JOptionPane.QUESTION_MESSAGE,
		    null,
		    eventName,
		    eventName[0]);

	}
	
	
	/**
	 * GetEvent returns the selected Event
	 * @return
	 */
	public Event getEvent() {
		if(index != -1)
			return events.get(index);
		return null;
	}
	
	
	
}
