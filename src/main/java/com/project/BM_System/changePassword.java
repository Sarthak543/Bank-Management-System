package com.project.BM_System;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import dataBase.logIn;
import modifiedClasses.TextField;

public class changePassword extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel heading;
	private String uname;
	private TextField name, password, repassword;
	private JRadioButton btn;
	private JButton done;

	public changePassword(String uname) {
		this.uname = uname;
		btn = new JRadioButton();
		btn.setBounds(600, 50, 500, 500);
		add(btn);
		heading = new JLabel("Setting Up the Credentials");
		heading.setFont(new Font("Raleway", Font.BOLD, 27));
		heading.setBounds(100, 10, 450, 40);
		add(heading);

		name = new TextField("Old Password*", 130, 100, 290, 28, Color.black);
		name.setFont(new Font("Raleway", Font.ROMAN_BASELINE, 15));
		add(name);

		password = new TextField("New Password*", 130, 150, 290, 28, Color.black);
		password.setFont(new Font("Raleway", Font.ROMAN_BASELINE, 15));
		add(password);

		repassword = new TextField("Re-Enter Password*", 130, 200, 290, 28, Color.black);
		repassword.setFont(new Font("Raleway", Font.ROMAN_BASELINE, 15));
		add(repassword);

		done = new JButton("Submit");
		done.setBounds(150, 300, 240, 30);
		done.setBackground(new Color(115, 224, 171));
		done.setForeground(Color.WHITE);
		done.addActionListener(this);
		add(done);

		setLayout(null);
		getContentPane().setBackground(Color.WHITE);
		setSize(550, 500);
		setLocation(50, 50);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == done) {
			String newPassword = password.getText();
			Configuration config = new Configuration().configure("hibernate.cfg.xml");
			SessionFactory sf = config.buildSessionFactory();
			Session s = sf.openSession();
			logIn person = (logIn) s.get(logIn.class, uname);
			person.setPassword(newPassword);
			Transaction tx = s.beginTransaction();
			s.update(person);
			tx.commit();
			s.close();
			sf.close();
			this.dispose();
			new App();
		}
	}
}
