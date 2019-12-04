package edu.baylor.ecs.sw1.appCalendar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import edu.baylor.ecs.sw1.View.MonthView;
import edu.baylor.ecs.sw1.View.View;
import edu.baylor.ecs.sw1.View.WeekView;
import edu.baylor.ecs.sw1.auth.AuthService;
import edu.baylor.ecs.sw1.canvas.CanvasAgentKey;
import edu.baylor.ecs.sw1.canvas.CanvasDBAdapter;
import edu.baylor.ecs.sw1.canvas.DatabaseConnector;
import edu.baylor.ecs.sw1.event.*;

/**
 * This class ties together Calendar views and the Sidebar
 * 
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
	DatabaseConnector db;

	public static String userName;

	/**
	 * clears the JFrame and readds the appropriate components in the current view
	 */
	private void render() {
		this.getContentPane().removeAll();

		this.setBackground(Color.WHITE);

		// this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.X_AXIS));
		this.setLayout(new BorderLayout());

		this.setExtendedState(JFrame.MAXIMIZED_BOTH);

		this.getContentPane().add(topLabel, BorderLayout.PAGE_START);

		this.getContentPane().add(sidebar, BorderLayout.LINE_START);

		if (inMonthView) {
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

	/**
	 * This method sets a button's action listener to this object, and its command
	 * to the string passed in.
	 * 
	 * @param button
	 * @param command
	 */
	private void connectButton(JButton button, String command) {
		button.setActionCommand(command);
		button.addActionListener(this);
	}

	/**
	 * Constructs the AppCalendar's components and connects the buttons to the
	 * listener
	 */
	public AppCalendar(String str) {
		userName = str;
		inMonthView = true;

		db = new DatabaseConnector("java", "userdata", "cerny");

		sidebar = new Sidebar(str);
		sidebar.setVisible(true);

		sidebar.viewSwitcher.setActionCommand("SWAP");
		sidebar.viewSwitcher.addActionListener(this);

		connectButton(sidebar.createEvent, "CREATE");
		connectButton(sidebar.editEvent, "EDIT");
		connectButton(sidebar.displayEventDetails, "DISPLAY");
		connectButton(sidebar.deleteEvent, "DELETE");
		connectButton(sidebar.completeEvent, "COMPLETE");
		connectButton(sidebar.connectEvent, "CONNECT");

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

	/**
	 * Listens for all events and responds appropriately
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String act = e.getActionCommand();

		Event event;
		if (inMonthView) {
			event = monthView.getSelectedEvent();
		} else {
			event = weekView.getSelectedEvent();
		}

		if (act.equals("SWAP")) {
			if (((String) sidebar.viewSwitcher.getSelectedItem()).equals("Month View")) {
				inMonthView = true;
			} else {
				inMonthView = false;
			}
			render();
		} else if (act.equals("CREATE")) {
			EventCreationDialog creator = new EventCreationDialog(this);
			event = creator.getEvent();
			
			if (event != null) {
				
				
				
				db.addUserEvent(userName, event);

				if (inMonthView) {
					monthView.createEvent(event);
				} else {
					weekView.createEvent(event);
				}
				
				
				
			}

		} else if (act.equals("EDIT")) {

			Event newEvent = null;
			if (event != null) {
				EventEditDialog ed = new EventEditDialog();
				ed.edit(this, event);

				newEvent = ed.getEvent();
				if (newEvent != null) {
					if (inMonthView) {
						monthView.refreshEvent(event, newEvent);
					} else {
						weekView.refreshEvent(event, newEvent);
					}
					db.changeEventDetails(userName, event);
				}
			}

		} else if (act.equals("DISPLAY")) {

			if (event != null) {
				EventDisplayer.display(this, event);
			}

		} else if (act.equals("DELETE")) {

			if (event != null) {
				int a = JOptionPane.showConfirmDialog(this, "Delete " + event.getEventName() + "?", "Delete Event",
						JOptionPane.YES_NO_OPTION);

				if (a == JOptionPane.YES_OPTION) {

					// event.setIgnore(true);

					if (inMonthView) {
						monthView.removeEvent(event);
						monthView.setSelectedEvent(null);
					} else {
						weekView.removeEvent(event);
						weekView.setSelectedEvent(null);
					}

					
					db.changeEventDetails(userName, event);
				}

			}

		} else if (act.equals("COMPLETE")) {

			if (event != null) {
				int a = JOptionPane.showConfirmDialog(this, "Complete " + event.getEventName() + "?", "Complete Event",
						JOptionPane.YES_NO_OPTION);

				if (a == JOptionPane.YES_OPTION) {

					Event newEvent = null;
					newEvent = event;

					newEvent.setCompleted(true);

					if (inMonthView) {
						monthView.refreshEvent(event, newEvent);
					} else {
						weekView.refreshEvent(event, newEvent);
					}

					db.changeEventDetails(userName, newEvent);
				}

			}

		} else if (act.equals("CONNECT")) {
			// box with canvas code spot
			AuthService authService = AuthService.getAuthService();
			CanvasAgentKey canvas = new CanvasAgentKey();
			CanvasDBAdapter db = new CanvasDBAdapter(canvas,userName);
			
			if(!db.syncStudentCanvas(userName)) {
				JTextField canvasTokenField = new JTextField();
				
				Object message[] = {
					"Canvas Token: ", canvasTokenField,
				};
				
				Object[] options = {"Confirm","Cancel"};
				
				int option = JOptionPane.showOptionDialog(this, message,"Enter Canvas Token",JOptionPane.OK_CANCEL_OPTION
						,JOptionPane.QUESTION_MESSAGE,new ImageIcon("empty.png"),options,options[0]);
				
				if(option == JOptionPane.OK_OPTION) {
					authService.setCanvasToken(userName, canvasTokenField.getText());
					
					if(!db.syncStudentCanvas(userName)) {
						JOptionPane.showMessageDialog(this, "Canvas Token Failure", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			} else {
				JOptionPane.showMessageDialog(this, "Canvas Synced", "Success", JOptionPane.PLAIN_MESSAGE);
			}
		}
	}
}
