package edu.baylor.ecs.sw1.appCalendar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JFrame;

import edu.baylor.ecs.sw1.event.*;
import edu.baylor.ecs.sw1.monthView.MonthView;
import edu.baylor.ecs.sw1.weekView.WeekView;

/**
 * This class ties together Calendar views and the Sidebar
 * @author Par Wilkinson
 *
 */
public class AppCalendar extends JFrame implements ActionListener {
	Sidebar sidebar;
	WeekView weekView;
	MonthView monthView;
	Boolean inMonthView;
	
	private void render() {
		this.getContentPane().removeAll();

		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.X_AXIS));

		this.getContentPane().add(sidebar);
		
		if(inMonthView) {
			weekView.setVisible(false);
			this.getContentPane().add(monthView);
			monthView.setVisible(true);
		} else {
			monthView.setVisible(false);
			this.getContentPane().add(weekView);
			weekView.setVisible(true);
		}
		
		this.getContentPane().revalidate();
		this.getContentPane().repaint();
	}
	
	private void connectButton(JButton button, String command) {
		button.setActionCommand(command);
		button.addActionListener(this);
	}
	
	public AppCalendar() {
		
		inMonthView = true;
		
		sidebar = new Sidebar();
		sidebar.setVisible(true);
		
		sidebar.viewSwitcher.setActionCommand("SWAP");
		sidebar.viewSwitcher.addActionListener(this);
		
		connectButton(sidebar.createEvent,"CREATE");
		connectButton(sidebar.editEvent,"EDIT");
		connectButton(sidebar.displayEventDetails,"DISPLAY");
		connectButton(sidebar.deleteEvent,"DELETE");
		connectButton(sidebar.completeEvent,"COMPLETE");
		
		monthView = new MonthView();
		monthView.setVisible(true);
		
		weekView = new WeekView();
		weekView.setVisible(true);

		render();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String act = e.getActionCommand();
		if(act.equals("SWAP")) {
			if(((String) sidebar.viewSwitcher.getSelectedItem()).equals("Month View")) {
				inMonthView = true;
			} else {
				inMonthView = false;
			}
			render();
		} else if(act.equals("CREATE")) {
			EventCreator creator = new EventCreator(this);
			Event event = creator.getEvent();
		} else if(act.equals("EDIT")) {
			
		} else if(act.equals("DISPLAY")) {
			
		} else if(act.equals("DELETE")) {
			
		} else if(act.equals("COMPLTE")) {
			
		}
	}
}
