package edu.baylor.ecs.sw1.myday;


import javax.swing.JFrame;

import edu.baylor.ecs.sw1.appCalendar.AppCalendar;

public class AppCalendarTester {
	public static void main(String args[]) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				AppCalendar.setUserName("HELLO");
				AppCalendar appCalendar = new AppCalendar();
				appCalendar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				appCalendar.setVisible(true);
			}
		});
	}
}
