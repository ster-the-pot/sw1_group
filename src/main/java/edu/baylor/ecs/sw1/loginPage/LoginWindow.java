package edu.baylor.ecs.sw1.loginPage;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;

import javax.swing.ImageIcon;
import javax.swing.UIManager;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

/**
 * Responsible only for rendering all Login Window UI elements.
 * @author strafford
 *
 */
public class LoginWindow  extends JFrame  {
	private JTextField txtUsernamefield;
	private JPasswordField passwordField;

	public LoginWindow(){
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setFont(new Font("Lucida Grande", Font.PLAIN, 34));
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel northPanel = new JPanel();
		
		northPanel.setBackground(UIManager.getColor("PopupMenu.selectionBackground"));
		northPanel.setPreferredSize(new Dimension(40, 50));
		FlowLayout flowLayout = (FlowLayout) northPanel.getLayout();
		getContentPane().add(northPanel, BorderLayout.NORTH);
		
		JLabel lblWelcomeToMyday = new JLabel("Welcome to MyDay!");
		lblWelcomeToMyday.setForeground(Color.WHITE);
		lblWelcomeToMyday.setFont(new Font("Lucida Grande", Font.BOLD, 33));
		lblWelcomeToMyday.setHorizontalAlignment(SwingConstants.CENTER);
		northPanel.add(lblWelcomeToMyday);
		
		JPanel westPanel = new JPanel();
		getContentPane().add(westPanel, BorderLayout.WEST);
		westPanel.setPreferredSize(new Dimension(500, 50));
		
		ImageIcon wImage = new ImageIcon("testIMG.png");
		westPanel.setLayout(new CardLayout(0, 0));
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setIcon(wImage);
		westPanel.add(lblNewLabel, "name_1343097577624496");
		
		JPanel eastPanel = new JPanel();
		eastPanel.setBackground(Color.WHITE);
		eastPanel.setPreferredSize(new Dimension(510, 50));
		getContentPane().add(eastPanel, BorderLayout.EAST);
		GridBagLayout gbl_eastPanel = new GridBagLayout();
		gbl_eastPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_eastPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_eastPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_eastPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		eastPanel.setLayout(gbl_eastPanel);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setPreferredSize(new Dimension(75,25));
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.gridwidth = 3;
		gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername.gridx = 4;
		gbc_lblUsername.gridy = 7;
		eastPanel.add(lblUsername, gbc_lblUsername);
		
		txtUsernamefield = new JTextField();
		GridBagConstraints gbc_txtUsernamefield = new GridBagConstraints();
		gbc_txtUsernamefield.gridwidth = 3;
		gbc_txtUsernamefield.insets = new Insets(0, 0, 5, 5);
		gbc_txtUsernamefield.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtUsernamefield.gridx = 4;
		gbc_txtUsernamefield.gridy = 8;
		eastPanel.add(txtUsernamefield, gbc_txtUsernamefield);
		txtUsernamefield.setColumns(11);
		
		JLabel lblPassword = new JLabel("Password");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.gridwidth = 3;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 4;
		gbc_lblPassword.gridy = 9;
		eastPanel.add(lblPassword, gbc_lblPassword);
		
		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.gridwidth = 3;
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 4;
		gbc_passwordField.gridy = 10;
		eastPanel.add(passwordField, gbc_passwordField);
		
		JButton btnCreateAccount = new JButton("Create Account");
		btnCreateAccount.setPreferredSize(new Dimension(75,75));
		GridBagConstraints gbc_btnCreateAccount = new GridBagConstraints();
		gbc_btnCreateAccount.insets = new Insets(0, 0, 5, 5);
		gbc_btnCreateAccount.gridx = 3;
		gbc_btnCreateAccount.gridy = 13;
		eastPanel.add(btnCreateAccount, gbc_btnCreateAccount);
		
		JButton btnLogin = new JButton("Login");
		GridBagConstraints gbc_btnLogin = new GridBagConstraints();
		btnLogin.setPreferredSize(new Dimension(75,100));
		gbc_btnLogin.insets = new Insets(0, 0, 5, 5);
		gbc_btnLogin.gridx = 7;
		gbc_btnLogin.gridy = 13;
		eastPanel.add(btnLogin, gbc_btnLogin);
		
	}

}
