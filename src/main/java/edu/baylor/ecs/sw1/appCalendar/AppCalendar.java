package edu.baylor.ecs.sw1.appCalendar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.baylor.ecs.sw1.View.MonthView;
import edu.baylor.ecs.sw1.View.View;
import edu.baylor.ecs.sw1.View.WeekView;
import edu.baylor.ecs.sw1.event.*;

/**
 * This class ties together Calendar views and the Sidebar
 * @author Par Wilkinson
 *
 */
public class AppCalendar extends JFrame implements ActionListener {
	Sidebar sidebar;
	View weekView;
	View monthView;
	Boolean inMonthView;
	JLabel myDayLabel;
	Font loginBold = new Font(Font.SANS_SERIF, Font.BOLD, 26);
	Color labelColor = new Color(64, 143, 222);
	JPanel topLabel;
	
	
	private void render() {
		this.getContentPane().removeAll();

		this.setBackground(Color.WHITE);
		
		//this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.X_AXIS));
		this.setLayout(new BorderLayout());
		
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		
		this.getContentPane().add(topLabel, BorderLayout.PAGE_START);
		
		this.getContentPane().add(sidebar, BorderLayout.LINE_START);
		
		if(inMonthView) {
			weekView.setVisible(false);
			this.getContentPane().add(monthView, BorderLayout.CENTER);
			monthView.setVisible(true);
		} else {
			monthView.setVisible(false);
			this.getContentPane().add(weekView, BorderLayout.CENTER);
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

		
		topLabel = new JPanel();
		topLabel.setBackground(labelColor);
		
		myDayLabel = new JLabel("Welcome to MyDay Calendar!");
		myDayLabel.setFont(loginBold);
		myDayLabel.setForeground(Color.WHITE);	
		
		topLabel.add(myDayLabel);
		
		
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
