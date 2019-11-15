package edu.baylor.ecs.sw1.myday;

import javax.swing.JFrame;

import edu.baylor.ecs.sw1.View.MonthView;
import edu.baylor.ecs.sw1.View.WeekView;


public class ViewTester {
	public static void main(String args[]) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame testFrame = new JFrame();
				testFrame.setVisible(true);
				testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				//MonthView view = new MonthView();
				WeekView view = new WeekView();
				testFrame.getContentPane().add(view);
				view.setVisible(true);
				testFrame.pack();
			}
		});
	}
}
