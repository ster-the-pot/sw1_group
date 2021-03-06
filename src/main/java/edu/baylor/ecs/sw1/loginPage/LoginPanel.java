package edu.baylor.ecs.sw1.loginPage;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import edu.baylor.ecs.sw1.auth.AuthService;

import javax.swing.JPasswordField;

/**
 * renders login panel in the login window, responsible for accepting user input
 * @author strafford
 *
 */
public class LoginPanel extends JPanel {
	private JTextField txtusername;
	private JPasswordField passwordField;
	
	public JButton btnLogin;
	
	public String getUsername() {
		return txtusername.getText();
	}
	
	public String getPassword() {
		return passwordField.getText();
	}
	
	public LoginPanel()  {
		

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		ImagePanel lblCernyPhoto = new ImagePanel("cerny.png");
		lblCernyPhoto.setPreferredSize(new Dimension(200,200));
		lblCernyPhoto.setBackground(Color.white);
		GridBagConstraints gbc_lblCernyPhoto = new GridBagConstraints();
		gbc_lblCernyPhoto.gridheight = 5;
		gbc_lblCernyPhoto.gridwidth = 7;
		gbc_lblCernyPhoto.insets = new Insets(20, 0, 80, 0);
		gbc_lblCernyPhoto.gridx = 1;
		gbc_lblCernyPhoto.gridy = 1;
		add(lblCernyPhoto, gbc_lblCernyPhoto);
		
		JLabel lblUsername = new JLabel("Username");
		
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.gridwidth = 3;
		gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername.gridx = 4;
		gbc_lblUsername.gridy = 8;
		add(lblUsername, gbc_lblUsername);
		
		
		txtusername = new JTextField();
		txtusername.setText("Tomas Cerny");
		txtusername.setHorizontalAlignment(JTextField.CENTER);
		GridBagConstraints gbc_txtusername = new GridBagConstraints();
		gbc_txtusername.gridwidth = 3;
		gbc_txtusername.insets = new Insets(0, 100, 10, 100);
		gbc_txtusername.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtusername.gridx = 4;
		gbc_txtusername.gridy = 9;
		add(txtusername, gbc_txtusername);
		txtusername.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.gridwidth = 3;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 4;
		gbc_lblPassword.gridy = 10;
		add(lblPassword, gbc_lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setHorizontalAlignment(JPasswordField.CENTER);
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.gridwidth = 3;
		gbc_passwordField.insets = new Insets(0, 100, 20, 100);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 4;
		gbc_passwordField.gridy = 11;
		add(passwordField, gbc_passwordField);
		
		
		btnLogin = new JButton("Login");
		GridBagConstraints gbc_btnLogin = new GridBagConstraints();
		gbc_btnLogin.gridwidth = 3;
		gbc_btnLogin.insets = new Insets(0, 0, 15, 6);
		gbc_btnLogin.gridx = 3;
		gbc_btnLogin.gridy = 13;
		add(btnLogin, gbc_btnLogin);
		
		
		
		JButton btnCreateAccount = new JButton("Create Account");
		GridBagConstraints gbc_btnCreateAccount = new GridBagConstraints();
		gbc_btnCreateAccount.gridwidth = 3;
		gbc_btnCreateAccount.insets = new Insets(0, 0, 30, 10);
		gbc_btnCreateAccount.gridx = 4;
		gbc_btnCreateAccount.gridy = 14;
		add(btnCreateAccount, gbc_btnCreateAccount);
		
		
		JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);
		
		btnCreateAccount.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JTextField dUsernameField = new JTextField();
				JTextField dPasswordField = new JPasswordField();
				
				Object message[] = {
					"Username: ", dUsernameField,
					"Password: ", dPasswordField,
				};
				
				Object[] options = {"Confirm","Cancel"};
				
				int option;
				try {
					option = JOptionPane.showOptionDialog(owner, message,"Create Account",JOptionPane.OK_CANCEL_OPTION
							,JOptionPane.QUESTION_MESSAGE,new ImageIcon(ImageIO.read(getClass().getClassLoader().getResource("empty.png"))),options,options[0]);
					if(option == JOptionPane.OK_OPTION) {
						String curUserName = dUsernameField.getText();
						String currPass = dPasswordField.getText();
						
						AuthService auth = AuthService.getAuthService();
						if(auth.accountExists(curUserName)) {
							// account already exists
							JOptionPane.showMessageDialog(owner, "That username is already registered.", "Error", JOptionPane.ERROR_MESSAGE);
						} else if(!auth.passwordStrength(currPass)) {
							// password too weak
							JOptionPane.showMessageDialog(owner, "Password is too short.", "Error", JOptionPane.ERROR_MESSAGE);
						} else {
							auth.createAccount(curUserName, currPass);
						}
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				
			}
		});
	}

}
