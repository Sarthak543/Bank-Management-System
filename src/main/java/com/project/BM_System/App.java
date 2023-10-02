package com.project.BM_System;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import dataBase.STATUS;
import dataBase.logIn;


public class App extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel background, heading, userName, password;
	private JTextField userName_tf;
	private JPasswordField password_tf;
	private JButton signIn, signUp;

	public App() {
		ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("./Icons./main_wallpaper.jpg"));
		Image img = icon.getImage().getScaledInstance(700, 700, Image.SCALE_DEFAULT);
		ImageIcon newIcon = new ImageIcon(img);
		setLayout(null);
		background = new JLabel(newIcon);
		background.setBounds(70, 70, 600, 500);
		add(background);

		heading = new JLabel("Sign In");
		heading.setFont(new Font("Raleway", Font.BOLD, 30));
		heading.setBounds(900, 40, 500, 150);
		add(heading);

		userName = new JLabel("User Name");
		userName.setFont(new Font("Raleway", Font.BOLD, 17));
		userName.setBounds(750, 140, 400, 100);
		add(userName);

		userName_tf = new JTextField();
		userName_tf.setBounds(750, 220, 350, 30);
		add(userName_tf);

		password = new JLabel("Password");
		password.setFont(new Font("Raleway", Font.BOLD, 17));
		password.setBounds(750, 230, 400, 100);
		add(password);

		password_tf = new JPasswordField();
		password_tf.setBounds(750, 310, 350, 30);
		add(password_tf);

		signIn = new JButton("Sign In");
		signIn.setBounds(825, 370, 170, 40);
		signIn.setBackground(new Color(115, 224, 171));
		signIn.setForeground(Color.WHITE);
		signIn.addActionListener(this);
		add(signIn);

		JLabel info = new JLabel("Dont have an account?");
		info.setFont(new Font("Raleway", Font.BOLD, 10));
		info.setBounds(750, 470, 500, 150);
		add(info);

		signUp = new JButton("Sign Up");
		signUp.addActionListener(this);
		signUp.setBounds(900, 520, 150, 40);
		signUp.setBackground(new Color(115, 224, 171));
		signUp.setForeground(Color.WHITE);
		
		add(signUp);
		
		setForeground(Color.WHITE);
		setTitle("Bank Management System");
		setSize(1200, 700);
		setLocation(50, 50);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		new App();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == signUp) {
			new NewApplicant().setVisible(true);
			this.dispose();
		} else if (e.getSource() == signIn) {

			String un = this.userName_tf.getText();
			String ps = new String(this.password_tf.getPassword());
			if (un.length() == 0 || ps.length() == 0) {
				JOptionPane.showMessageDialog(null, "Please enter the username and password");
			} else {
				logIn person = null;
				// connecting to the DB
				Configuration cnf = new Configuration().configure().addAnnotatedClass(logIn.class);
				SessionFactory sf = cnf.buildSessionFactory();
				Session s = sf.openSession();
				person=(logIn)s.get(logIn.class,un);
				s.close();
				if(person==null) {
					JOptionPane.showMessageDialog(null, "No user Found");
				}else if(person.getAccountStatus()!=STATUS.OPEN) {
					JOptionPane.showMessageDialog(null, "Your account is "+person.getAccountStatus()+". Please talk to Manager");
				}
				else if (un.equals(person.getUserName())  &&  ps.equals(person.getPassword()) ) {
					new userLogin(un).setVisible(true);
					this.dispose();
				} else {
					JOptionPane.showMessageDialog(null, "credentials incorrect");
				}
				
			}
		}

	}
}
