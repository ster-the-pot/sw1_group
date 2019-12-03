package edu.baylor.ecs.sw1.myday;

import javax.swing.JFrame;

import edu.baylor.ecs.sw1.event.*;

public class EditEventTest {
	public static void printEvent(Event event) {
		System.out.println("Event Name: " + event.getEventName());
		System.out.println("Event Start Date: " + event.getStartDate().toString());
		System.out.println("Event End Date: " + event.getEndDate().toString());
		System.out.println("Event Description: " + event.getEventDescription());
	}
	
	public static void main(String args[]) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame testFrame = new JFrame();
				testFrame.setVisible(true);
				testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				testFrame.pack();
				
				
				System.out.println("Creating event...");
				EventCreationDialog creator = new EventCreationDialog(testFrame);
				
				Event event = creator.getEvent();
				if(event != null) {
					printEvent(event);
					
					System.out.println("\nEditing event...");
					EventEditDialog editor = new EventEditDialog();
					editor.edit(testFrame, event);
					
					printEvent(event);
				} else {
					System.out.println("User cancelled event creation.");
				}
			}
		});
	}
}
