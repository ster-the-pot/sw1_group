package edu.baylor.ecs.sw1.myday;


import javax.swing.JFrame;

import edu.baylor.ecs.sw1.event.*;


public class CreateEventTest {
	
	public static void main(String args[]) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame testFrame = new JFrame();
				testFrame.setVisible(true);
				testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				testFrame.pack();
				
				EventCreator eventDialog = new EventCreator(testFrame);
				
				EventInfo event = eventDialog.getEvent();
				if(event != null) {
					System.out.println("Got event: " + event.getEventName());
				} else {
					System.out.println("User cancelled?");
				}
			}
		});
	}
}
