package edu.baylor.ecs.sw1.loginPage;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;


/**
 * StatsPanel pulls current eventcount,usercount,etc and allows display to the user
 * @author strafford
 *
 */
public class StatsPanel extends JPanel{
	 Color tColor = new Color(64, 143, 222);
	
/**
 * Render's Stats Panel, pulling data from User Data to get a total event count
 */
	public StatsPanel() {
		JLabel eCount = new JLabel(this.getEventsCount(127),SwingConstants.CENTER);
		this.setLayout(new GridLayout(0,1));
		eCount.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
		eCount.setForeground(tColor);
		JLabel countLabel = new JLabel("<html> <center>schedules currently being managed</center></html>",SwingConstants.CENTER);
		countLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 26));
		countLabel.setForeground(tColor);
		
		this.add(eCount);
		this.add(countLabel);
	}
	
	String getEventsCount(Integer count) {
		//Count is set to static number until Iteration 3
		return count.toString();
	}

}
