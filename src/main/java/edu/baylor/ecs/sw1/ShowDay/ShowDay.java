package edu.baylor.ecs.sw1.ShowDay;

import javax.swing.JLabel;
import javax.swing.JPanel;



public class ShowDay extends JPanel{

	static String[] monthsAbv = { "Jan", "Feb", "March", "April", "May", "June", "July", "Aug", "Sept",
			"Oct", "Nov", "Dec" };
	static JLabel label;
	static JPanel dayPanel;
	
	public ShowDay(int day, int month, int year) {
		
		label = new JLabel("" + day);
		
		dayPanel = new JPanel();
		dayPanel.add(label);

		this.add(dayPanel);
		
	}
	public ShowDay(int day, int month, int year, Boolean isOtherMonth) {
		
		label = new JLabel(monthsAbv[month] + ": " + day);
		
		
		
		dayPanel = new JPanel();
		dayPanel.add(label);
		
		
		this.add(dayPanel);
		
	}
	
}
