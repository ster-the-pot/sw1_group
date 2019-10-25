package edu.baylor.ecs.sw1.myday;


import edu.baylor.ecs.sw1.eventFrames.*;
import javax.swing.JFrame;


public class CreateEventTest {
	
	public static void main(String args[]) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame testFrame = new JFrame();
				testFrame.setVisible(true);
				testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				testFrame.pack();
				
				CreateEventDialog eventDialog = new CreateEventDialog(testFrame);
				eventDialog.setVisible(true);
			}
		});
	}
}
