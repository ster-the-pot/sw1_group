package edu.baylor.ecs.sw1.myday;

import edu.baylor.ecs.sw1.sidebar.*;

import javax.swing.JFrame;


public class SidebarTest {
	
	public static void main(String args[]) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame testFrame = new JFrame();
				testFrame.setVisible(true);
				testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				Sidebar sidebar = new Sidebar();
				sidebar.setVisible(true);
				testFrame.getContentPane().add(sidebar);
				
				testFrame.pack();
			}
		});
	}
}
