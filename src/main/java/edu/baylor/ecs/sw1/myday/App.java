package edu.baylor.ecs.sw1.myday;

import edu.baylor.ecs.sw1.loginPage.LoginWindow;

//INCLUDE Javadoc comments in all submitted code

/**
 * Represent's the entry point of the main myDay runnable, responsible for initating the gui
 * and preparing execution
 * 
 * @author Sterling Trafford
 * 
 */
public class App 
{
    public static void main( String[] args )
    {
    	javax.swing.SwingUtilities.invokeLater(new LoginWindow());
    }
}
