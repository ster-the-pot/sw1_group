package edu.baylor.ecs.sw1.loginPage;

import javax.swing.SwingUtilities;

import org.junit.Test;


/**
 * Unit test to verify correct LoginWindow and login activity
 * @author SterlingTrafford
 *
 */
public class loginTester {

	public loginTester() {

	}
	
	@Test
	public void renderPage() {
		LoginWindow login = new LoginWindow();
		SwingUtilities.invokeLater(login);
	
	}
}
