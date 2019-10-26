package edu.baylor.ecs.sw1.appCalendar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import edu.baylor.ecs.sw1.monthView.MonthView;
import edu.baylor.ecs.sw1.weekView.WeekView;

/**
 * This class ties together Calendar views and the Sidebar
 * @author Par Wilkinson
 *
 */
public class AppCalendar extends JFrame {
	Sidebar sidebar;
	WeekView weekView;
	MonthView monthView;
	Boolean inMonthView;
	
	private void render() {
		this.getContentPane().removeAll();

		this.add(sidebar);
		
		if(inMonthView) {
			weekView.setVisible(false);
			this.getContentPane().add(monthView);
		} else {
			monthView.setVisible(false);
			this.getContentPane().add(weekView);
		}
	}
	
	public AppCalendar() {
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.X_AXIS));
		
		inMonthView = true;
		
		sidebar = new Sidebar();
		sidebar.setVisible(true);
		
		sidebar.viewSwitcher.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> cb = (JComboBox<String>) e.getSource();
				String curr = (String) cb.getSelectedItem();
				if(curr.equals("Week View")) {
					inMonthView = false;
				} else {
					inMonthView = true;
				}
				
				render();
			}
		});
		
		monthView = new MonthView();
		monthView.setVisible(true);
		
		weekView = new WeekView();
		weekView.setVisible(true);

		render();
	}
}
